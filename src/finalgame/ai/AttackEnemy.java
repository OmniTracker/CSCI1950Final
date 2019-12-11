package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.Status;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.PlayerHealthComponent;

public class AttackEnemy extends BTAction {

	private BehaviorTree tree;
	private GameObject target;
	private int counter = 0;

	public AttackEnemy(GameObject target) {
		this.target = target;
	}
	
	@Override
	public Status update(float seconds) {
		
		counter++;
		if (counter>=10) {
			PlayerHealthComponent p = (PlayerHealthComponent) target.getComponent("HEALTH");
			p.takeDamage(10);
			counter = 0;
		}
		
		
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
