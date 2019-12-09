package nin.behaviortree.actions;

import java.util.ArrayList;
import java.util.Iterator;

import nin.behaviortree.NinBehaviorSequence;
import engine.ai.behaviortree.Actions;
import engine.gameobject.GameObject;
import engine.physics.CollisonResponds;
import support.Vec2d;
import support.collision.Collision;
import support.collision.MTV;

public class PhysicsAction extends NinBehaviorSequence implements  Actions {
	public PhysicsAction(GameObject mainCharacter) {
		this.setBehaviorType("Action");	
		this.setMainCharacter(mainCharacter);
		this.setOtherCharacter( new  ArrayList<GameObject> ());
	}
	public PhysicsActionData data = new PhysicsActionData(true, new Vec2d(0,0),
			new Vec2d(0,0), new Vec2d(0,0), new Vec2d(0,0), 0.02, 0, 10, 0, 10.0, false);

	public void incrementX () {
		if (isStaticFlag() == true) {
			this.setVelocity(new Vec2d(data._walkVel,0));
			double x = data._walkVel - data._timeStep; 		
			this.setPosition( new Vec2d(this.getMainCharacter().getData().getPosition().x + (x) , this.getMainCharacter().getData().getPosition().y) ) ;
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
		data._jumpVel = data._jumpVel - data._timeStep * data._gravity;
		data._jumpPos = data._jumpPos + data._jumpVel * data._timeStep;
		if (data._jumpVel <= -10.5) {
			data._timeStep = 0.02;
			data._jumpPos = 0; 
			data._jumpVel = 10; 
			return false; 
		}
		// Set position
		this.setPosition( new Vec2d(this.getMainCharacter().getData().getPosition().x, - 70 * data._jumpPos + 400));
		this.getMainCharacter().getData().setPosition(this.getPosition());
		return true;
	}

	public void  useResitution() {
		GameObject otherOne = null; 
		otherOne = helper(otherOne);
		if (otherOne == null) {
			return; 
		}
		PhysicsAction otherPhysics = (PhysicsAction) otherOne.getData().getNinBehaviorTree().getSequence().get(0).get(1); 
		double massA = this.getMass(); 
		double massB = otherPhysics.getMass();
		double COR_A = this.getCOR();
		Vec2d velA = this.getVelocity(); 
		Vec2d velB = otherPhysics.getVelocity();
		Vec2d impulse = CollisonResponds.calculateFinalImpulse(massA, massB, COR_A, velA, velB); 
		Vec2d velocity =  impulse.pdiv(massA,massA);
		this.setVelocity(velocity);
		data._walkVel = ( -1 * velocity.x);
		
	}

	Vec2d getForce() {
		return data._force;
	}
	void setForce(Vec2d _force) {
		this.data._force = _force;
	}
	private Vec2d getImpulse() {
		return data._impulse;
	}
	private void setImpulse(Vec2d _impulse) {
		this.data._impulse = _impulse;
	}
	private Vec2d getVelocity() {
		return data._velocity;
	}
	public void setVelocity(Vec2d _velocity) {
		this.data._velocity = _velocity;
	}
	private Vec2d getPosition() {
		return data._position;
	}
	private void setPosition(Vec2d _position) {
		this.data._position = _position;
	}
	public double getMass() {
		return data._mass;
	}
	@Override
	public boolean ActionCompleted() {
		return false;
	}
	@Override
	public boolean implementAction() {
		return false;
	}
	public void setMass(double _mass) {
		this.data._mass = _mass;
	}
	public double getCOR() {
		return data._COR;
	}
	public void setCOR(double _COR) {
		this.data._COR = _COR;
	}
	public void setWalkVel(double _walkVel) {
		this.setVelocity(new Vec2d(_walkVel,0));
		this.data._walkVel = _walkVel;
	}
	public GameObject getMainCharacter() {
		return data._mainCharacter;
	}
	public void setMainCharacter(GameObject _mainCharacter) {
		this.data._mainCharacter = _mainCharacter;
	}
	public ArrayList<GameObject> getOtherCharacter() {
		return data._otherCharacter;
	}

	private void setOtherCharacter(ArrayList<GameObject> _otherCharacter) {
		this.data._otherCharacter = _otherCharacter;
	}

	public boolean isStaticFlag() {
		return data.staticFlag;
	}

	public void setStaticFlag(boolean staticFlag) {
		this.data.staticFlag = staticFlag;
	}
	
	private GameObject helper(GameObject otherOne) {
		if (!this.getMainCharacter().getData().getName().contains("Main")){
			Iterator<GameObject> collider = data._otherCharacter.iterator();
			while (collider.hasNext())
			{
				GameObject other = collider.next();        	
				if ( Collision.isColliding(this.getMainCharacter().getData().getBox(), other.getData().getBox()) ) {
					otherOne = other; 
					Vec2d mtv = MTV.collision(otherOne.getData().getBox(), this.getMainCharacter().getData().getBox()); 
					this.getMainCharacter().getData().setCurrentMTV(mtv);
					try {
						
						if (data._walkVel != 0.0) {
							Vec2d newPos = new Vec2d ( this.getMainCharacter().getData().getPosition().x - mtv.x,  this.getMainCharacter().getData().getPosition().y - mtv.y); 
							this.getMainCharacter().getData().setPosition(newPos);
						}

					} catch (Exception e) {

					}	 
				}
			}		
		}
		return otherOne;
	}
}
