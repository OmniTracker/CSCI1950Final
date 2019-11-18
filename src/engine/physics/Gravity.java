package engine.physics;

import java.util.ArrayList;
import java.util.Iterator;

import engine.gameobject.GameObject;
import support.Vec2d;
import support.collision.Collision;
import support.collision.MTV;

public class Gravity {
	
	private ArrayList<GameObject> _otherCharacter;
	private GameObject _mainCharacter;
	
	private boolean staticFlag;
	private double _mass;
	private double _COR;
	private Vec2d _position;
	private Vec2d _velocity;
	private Vec2d _impulse;
	private Vec2d _force;
	private double _timeStep;
	private double _jumpPos;
	private double _jumpVel;
	private double _walkVel;
	private double _gravity;
	private double acceleration = getGravity();
	private boolean _jumpEnabled;
	private boolean _jumping;
		
	public Gravity(GameObject mainCharacter) {
		this.setOtherCharacter( new  ArrayList<GameObject> ());	
		this.setMainCharacter(mainCharacter);
		this.setStaticFlag(true);
		this.setPosition(new Vec2d(0,0));
		this.setVelocity(new Vec2d(0,0));
		this.setImpulse(new Vec2d(0,0));
		this.setForce(new Vec2d(0,0));
		this.setTimeStep(0.01);
		this.setJumpPos(0);
		this.setJumpVel(10);
		this.setWalkVel(0);
		this.setGravity(9.8);
		this.setJumpEnabled(false);
	}

	public void incrementX () {
		if (isStaticFlag() == true) 
		{
			this.setVelocity(new Vec2d(_walkVel,0));
			double x = _walkVel - _timeStep; 		
			
			this.setPosition( new Vec2d(this.getMainCharacter().getData().getPosition().x + (x) ,
					this.getMainCharacter().getData().getPosition().y) ) ;
			
			this.getMainCharacter().getData().setPosition(this.getPosition());
		}
	}
	
	public void applyForce(Vec2d f) {
		this.setForce( this.getForce().plus(f));
	}
	public void applyImpulse(Vec2d p) {		
		this.setImpulse( this.getImpulse().plus(p));
	}
	public boolean check () {
		return true;
	}
	public void onTick(float t) {
		
		Vec2d vel = this.getForce().pdiv(this.getMass(),this.getMass()).pmult(t, t); 
		this.setVelocity( this.getVelocity().plus(vel));
		this.setPosition( this.getPosition().plus(this.getVelocity().pmult(t,t)));
		this.setForce(new Vec2d(0,0));
		this.setImpulse(new Vec2d(0,0));
	}
	// The jump will go on until the character hits the ground. Basically when a MTV Y axis have a value
	public boolean jump () {
		
		// Velocity first (Symplectic Euler)
		_jumpVel = _jumpVel - _timeStep * _gravity;
		_jumpPos = _jumpPos + _jumpVel * _timeStep;

		if (_jumpVel <= -10.5) {
			_timeStep = 0.01;
			_jumpPos = 0; 
			_jumpVel = 10; 
			return false; 
		}
		// Set position
		this.setPosition( new Vec2d(this.getMainCharacter().getData().getPosition().x, - 70 * _jumpPos + 400));
		this.getMainCharacter().getData().setPosition(this.getPosition());
		return true;
	}

	public void  useResitution() {
		/*
		GameObject otherOne = null; 
		otherOne = helper(otherOne);
		if (otherOne == null) {
			return; 
		}
		GameObject otherPhysics = (GameObject) otherOne.getNinBehaviorTree().getSequence().get(0).get(1); 
		double massA = this.getMass(); 
		double massB = otherPhysics.getMass();
		double COR_A = this.getCOR();
		Vec2d velA = this.getVelocity(); 
		Vec2d velB = otherPhysics.getData().getVelocity();
		Vec2d impulse = CollisonResponds.calculateFinalImpulse(massA, massB, COR_A, velA, velB); 
		Vec2d velocity =  impulse.pdiv(massA,massA);
		this.setVelocity(velocity);
		_walkVel = ( -1 * velocity.x);
		*/ 
	}
	
	private GameObject helper(GameObject otherOne) {
		
		if (!this.getMainCharacter().getData().getName().contains("Main")){
			
			Iterator<GameObject> collider = _otherCharacter.iterator();
			while (collider.hasNext())
			{
				GameObject other = collider.next();    
				
				if ( Collision.isColliding(this.getMainCharacter().getData().getBox(), 
						other.getData().getBox()) ) 
				{
					
					otherOne = other; 
				
					Vec2d mtv = MTV.collision(otherOne.getData().getBox(), this.getMainCharacter().getData().getBox()); 
					this.getMainCharacter().getData().setCurrentMTV(mtv);
					
					try {
						
						if (_walkVel != 0.0) {
							Vec2d newPos = new Vec2d ( this.getMainCharacter().getData().getPosition().x - mtv.x, 
									this.getMainCharacter().getData().getPosition().y - mtv.y); 
							
							this.getMainCharacter().getData().setPosition(newPos);
						}

					} catch (Exception e) {

					}	 
				}
			}		
		}
		return otherOne;
	}
	
	public boolean isJumping() {
		return _jumping;
	}
	public void setJumping(boolean _jumping) {
		this._jumping = _jumping;
	}
	private boolean isJumpEnabled() {
		return _jumpEnabled;
	}
	private void setJumpEnabled(boolean _jumpEnabled) {
		this._jumpEnabled = _jumpEnabled;
	}
	private ArrayList<GameObject> getOtherCharacter() {
		return _otherCharacter;
	}
	private void setOtherCharacter(ArrayList<GameObject> _otherCharacter) {
		this._otherCharacter = _otherCharacter;
	}
	private GameObject getMainCharacter() {
		return _mainCharacter;
	}
	private void setMainCharacter(GameObject _mainCharacter) {
		this._mainCharacter = _mainCharacter;
	}
	private boolean isStaticFlag() {
		return staticFlag;
	}
	private void setStaticFlag(boolean staticFlag) {
		this.staticFlag = staticFlag;
	}
	private double getMass() {
		return _mass;
	}
	private void setMass(double _mass) {
		this._mass = _mass;
	}
	private double getCOR() {
		return _COR;
	}
	private void setCOR(double _COR) {
		this._COR = _COR;
	}
	private Vec2d getPosition() {
		return _position;
	}
	private void setPosition(Vec2d _position) {
		this._position = _position;
	}
	private Vec2d getVelocity() {
		return _velocity;
	}
	private void setVelocity(Vec2d _velocity) {
		this._velocity = _velocity;
	}
	private Vec2d getImpulse() {
		return _impulse;
	}
	private void setImpulse(Vec2d _impulse) {
		this._impulse = _impulse;
	}
	private Vec2d getForce() {
		return _force;
	}
	private void setForce(Vec2d _force) {
		this._force = _force;
	}
	private double getTimeStep() {
		return _timeStep;
	}
	private void setTimeStep(double _timeStep) {
		this._timeStep = _timeStep;
	}
	private double getJumpPos() {
		return _jumpPos;
	}
	private void setJumpPos(double _jumpPos) {
		this._jumpPos = _jumpPos;
	}
	private double getJumpVel() {
		return _jumpVel;
	}
	private void setJumpVel(double _jumpVel) {
		this._jumpVel = _jumpVel;
	}
	private double getWalkVel() {
		return _walkVel;
	}
	private void setWalkVel(double _walkVel) {
		this._walkVel = _walkVel;
	}
	private double getAcceleration() {
		return acceleration;
	}
	private void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	private double getGravity() {
		return _gravity;
	}

	private void setGravity(double _gravity) {
		this._gravity = _gravity;
	}
}
