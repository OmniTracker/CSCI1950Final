package wizard.behaviortree.conditions;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.behaviortree.Conditions;
import engine.gameobject.GameObject;

public class DoesEnemyHaveTracker extends WIZBehaviorSequence  implements Conditions {
	// This is usually set before the start of the game. It is possible to lose or find
	// tracker during the duration of the game. I will not explore this option due to 
	// lack of time I have.
	private boolean _activeTracker;
	
	public DoesEnemyHaveTracker(GameObject obj, GameObject targetObj) {
		super(obj, targetObj);
		this.setBehaviorType("Condition");
		this.setBehaviorName("Does Enemy Have Tracker?");
	}
	public boolean run () {
		return this.ConditionSatisfied();
	}	
	public boolean ConditionSatisfied() {
		return this.isActiveTracker();
	}
	public boolean isActiveTracker() {
		return _activeTracker;
	}
	public void setActiveTracker(boolean _activeTracker) {
		this._activeTracker = _activeTracker;
	}

}
