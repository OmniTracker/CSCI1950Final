package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.Status;
import finalgame.engineAdditions.EnemySystem;

public class SpawnMinions extends BTAction {

	private int cooldown = 150;
	private EnemySystem es;
	
	public SpawnMinions(EnemySystem es) {
		this.es = es;
	}
	
	@Override
	public Status update(float seconds) {
		
		cooldown++;
		if (cooldown<=200) {
			return Status.FAILURE;
		}
		es.createMinions(_tree.getGI(), _tree.getObject());
		
		cooldown = 0;
		
		return Status.SUCCESS;
	}


	@Override
	public void reset() {
	}

	
}
