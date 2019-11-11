package wizard.behaviortree.conditions;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.behaviortree.Conditions;
import engine.gameobject.GameObject;

public class DoesCharacterShareRowOrColumn extends WIZBehaviorSequence  implements Conditions {

	protected DoesCharacterShareRowOrColumn(GameObject obj, GameObject targetObj) {
		super(obj, targetObj);
		this.setBehaviorType("Condition");
		this.setBehaviorName("Does Character Share Row Or Column?");
	}
	public boolean run () {
		return false;
	}
	private boolean checkRowAndColVal () {
		if ( (Math.floor(this.getTargetObj().getData().getPosition().x)  - 
				Math.floor(this.getObj().getData().getPosition().x)) == 0 ) {
			return true;
		}
		if ( (Math.floor(this.getTargetObj().getData().getPosition().y)  - 
				Math.floor(this.getObj().getData().getPosition().y)) == 0 ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean ConditionSatisfied() {
		return checkRowAndColVal();
	}

}
