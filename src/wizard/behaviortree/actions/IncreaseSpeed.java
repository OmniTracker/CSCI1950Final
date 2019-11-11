package wizard.behaviortree.actions;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.behaviortree.Actions;
import engine.gameobject.GameObject;

public class IncreaseSpeed extends WIZBehaviorSequence  implements Actions {

	public IncreaseSpeed(GameObject obj, GameObject targetObj) {
		super(obj,targetObj);  
		this.setBehaviorType("Action");
		this.setBehaviorName("Increase Speed");
	}
	private double  _speedUp; 
	private double _maxSpeed;
	public boolean run () {
		return this.ActionCompleted();
	}
	@Override
	public boolean ActionCompleted() {
		// TODO Auto-generated method stub
		return this.implementAction();
	}
	@Override
	public boolean implementAction() {
		// TODO Auto-generated method stub
		return false;	
	}
	public boolean canYouMoveFaster () {
		return false; 
	}
	double getSpeedUp() {
		return _speedUp;
	}
	void setSeedUp(double _speedUp) {
		this._speedUp = _speedUp;
	}
	public double getMaxSpeed() {
		return _maxSpeed;
	}
	public void setMaxSpeed(double _maxSpeed) {
		this._maxSpeed = _maxSpeed;
	}
}
