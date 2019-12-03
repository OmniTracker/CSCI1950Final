package nin.behaviortree.actions;

import java.util.ArrayList;

import support.Vec2d;
import engine.gameobject.GameObject;

public class PhysicsActionData {
	public ArrayList<GameObject> _otherCharacter;
	public GameObject _mainCharacter;
	public boolean staticFlag;
	public double _mass;
	public double _COR;
	public Vec2d _position;
	public Vec2d _velocity;
	public Vec2d _impulse;
	public Vec2d _force;
	public double _timeStep;
	public double _jumpPos;
	public double _jumpVel;
	public double _walkVel;
	public double _gravity;
	public double acceleration = _gravity;
	public boolean _jumpEnabled;
	public boolean _jumping;

	public PhysicsActionData(boolean staticFlag, Vec2d _position,
			Vec2d _velocity, Vec2d _impulse, Vec2d _force, double _timeStep,
			double _jumpPos, double _jumpVel, double _walkVel, double _gravity,
			boolean _jumpEnabled) {
		this.staticFlag = staticFlag;
		this._position = _position;
		this._velocity = _velocity;
		this._impulse = _impulse;
		this._force = _force;
		this._timeStep = _timeStep;
		this._jumpPos = _jumpPos;
		this._jumpVel = _jumpVel;
		this._walkVel = _walkVel;
		this._gravity = _gravity;
		this._jumpEnabled = _jumpEnabled;
	}
}