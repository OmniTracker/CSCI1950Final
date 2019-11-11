package wizard.behaviortree.actions;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.behaviortree.Actions;
import engine.gameobject.GameObject;

public class SlowDownSpeed extends WIZBehaviorSequence  implements Actions {
	double _speed;
	double _speedMax; 

	public SlowDownSpeed(GameObject obj, GameObject targetObj) {
		super(obj,targetObj);  
		this.setBehaviorType("Action");
		this.setBehaviorName("Slow Down Speed");
	}
	public boolean run () {
		return this.ActionCompleted();
	}
	@Override
	public boolean ActionCompleted() {
		return this.implementAction();
	}
	@Override
	public boolean implementAction() {
		return false;	
	}
}
