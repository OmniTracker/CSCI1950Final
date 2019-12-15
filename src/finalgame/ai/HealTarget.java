package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.Status;

public class HealTarget extends BTAction {

	public HealTarget() {
		
	}
	
	@Override
	public Status update(float seconds) {
				
		try {
			((TestGI) _tree.getGI()).getHeal().heal(10);
		} catch(NullPointerException e) {
			return Status.FAILURE;
		}
	
		return Status.SUCCESS;
	}

	@Override
	public void reset() {
	}

}
