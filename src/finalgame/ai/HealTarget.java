package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.Status;
import finalgame.engineAdditions.GameObject;

public class HealTarget extends BTAction {

	public HealTarget() {
		target = null;
	}
	
	@Override
	public Status update(float seconds) {
		
		if (target==null) {
			return Status.FAILURE;
		}
		
		
		
		
		return null;
	}

	@Override
	public void reset() {
	}

}
