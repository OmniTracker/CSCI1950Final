package wizard.behaviortree.conditions;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.behaviortree.Conditions;
import engine.gameobject.GameObject;

public class IsAthletic extends WIZBehaviorSequence  implements Conditions {
	private boolean _status; 
	public IsAthletic(GameObject obj, GameObject targetObj) {
		super(obj, targetObj);
		this.setBehaviorType("Condition");
		this.setBehaviorName("Is Character Athletic?");
	}
	public boolean run () {			
		return this.ConditionSatisfied();
	}
	@Override
	public boolean ConditionSatisfied() {
		return this.status();
	}
	public boolean status() {
		return _status;
	}
	public void setStatus(boolean _status) {
		this._status = _status;
	}

}
