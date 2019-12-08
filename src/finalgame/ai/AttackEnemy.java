package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.Status;
import finalgame.engineAdditions.GameObject;

public class AttackEnemy extends BTAction {

	private BehaviorTree tree;
	private GameObject target;
	
	public AttackEnemy(GameObject target) {
		this.target = target;
	}
	
	@Override
	public Status update(float seconds) {
		
		
		
		
		return Status.FAILURE;
	}

	@Override
	public void reset() {
	}

	@Override
	public void setTree(BehaviorTree tree) {
		this.tree = tree;
	}

	@Override
	public BehaviorTree getTree() {
		return tree;
	}
}
