package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class PhysicsComponent extends Component{

	protected Vec2d _velocity, _pos;
	protected Vec2d _impulse, _force;
	protected float _maxVelocity;
	protected float _mass;
	protected float _gravity;
	public boolean _static;
	protected boolean _grounded;
	protected double _restitution;
	
	public PhysicsComponent(GameObject go, float mass, boolean s) {
		super(go);
		// TODO Auto-generated constructor stub
		_static = s;
		_grounded = true;
		_velocity = new Vec2d(0,0);
		_maxVelocity = 1000000000;
		Vec2d temp = ((TransformComponent)_go.getComponent("TRANSFORM")).getLoc();
		_pos = new Vec2d(temp.x, temp.y);
		_force = new Vec2d(0,0);
		_impulse = new Vec2d(0,0);
		_mass = mass;
		_restitution = 0;
		setGravity(0);
	}
	
	public PhysicsComponent(GameObject go, float mass, boolean s, double d) {
		super(go);
		// TODO Auto-generated constructor stub
		_static = s;
		_grounded = false;
		_velocity = new Vec2d(0,0);
		_maxVelocity = 1000000000;
		Vec2d temp = ((TransformComponent)_go.getComponent("TRANSFORM")).getLoc();
		_pos = new Vec2d(temp.x, temp.y);
		_force = new Vec2d(0,0);
		_impulse = new Vec2d(0,0);
		_mass = mass;
		_restitution = d;
		setGravity(0);
	}

	public void applyForce(Vec2d f) {
		if(!_static) {
			_force = _force.plus(f);
		}		
	}
	
	public void applyImpulse(Vec2d p) {
		if(!_static) {
			_impulse = _impulse.plus(p);
		}
	}
	
	@Override
	public void tick(long nanosSinceLastTick) {
		// TODO Auto-generated method stub
		if(!_static) {
			long secs = nanosSinceLastTick/10000000;
			_velocity = _velocity.plus((_force.sdiv(_mass)).smult(secs)).plus(_impulse.sdiv(_mass));
			if(!_grounded) {
				_velocity = _velocity.plus(new Vec2d(0,_gravity));
			}
			if(Math.abs(_velocity.x)>_maxVelocity) {
				_velocity = _velocity.pmult(new Vec2d(Math.abs(_maxVelocity/_velocity.x),1));
			}
			_pos = _pos.plus(_velocity.smult(secs));
			if(_go.hasComponent("TRANSFORM")) {
				TransformComponent tc = (TransformComponent)_go.getComponent("TRANSFORM");
				tc.setLoc(_pos);
			}
			_force = _force.smult(0);
			_impulse = _impulse.smult(0);
		}
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		// TODO Auto-generated method stub
		
	}
	
	public void staticCollide(Vec2d mtv) {
		
	}
	
	public void dynamicCollide(Vec2d mtv) {
		_pos = _pos.plus(mtv.smult(0.5));
		TransformComponent tc = (TransformComponent)_go.getComponent("TRANSFORM");
		tc.setLoc(_pos);
	}
	
	public void restitutionCollision(Vec2d mtv, PhysicsComponent other) {
		if(!_static) {
			this.dynamicCollide(mtv);
			double COR = Math.sqrt(_restitution * other.getRestitution());
			Vec2d impulse;
			Vec2d diff = other.getVelocity().minus(_velocity);
			if(other._static) {
				impulse = diff.smult(_mass * (1+COR));
			}
			else {
				double num = _mass * other.getMass() * (1+COR);
				double den = _mass + other.getMass();
				impulse = diff.smult(num/den);
			}

			this.applyImpulse(impulse);
		}

	}

	public double getRestitution() {
		return _restitution;
	}
	
	public Vec2d getMomentum() {
		return _velocity.smult(_mass);
	}
	
	public double getMass() {
		return _mass;
	}

	public void setMass(float mass) {
		_mass = mass;
	}

	public float getGravity() {
		return _gravity;
	}

	public void setGravity(float gravity) {
		this._gravity = gravity;
	}
	
	public void setVelocity(Vec2d v) {
		_velocity = v;
	}
	
	public Vec2d getVelocity() {
		return _velocity;
	}
	
	public Vec2d getPosition() {
		return _pos;
	}
	
	public void ground() {
		this._grounded = true;
	}
	
	public void unground() {
		_grounded = false;
	}
}
