package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.Status;
import finalgame.engineAdditions.EnemyRangedAbilityComponent;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.PlayerHealthComponent;

public class AttackEnemy extends BTAction {

	public AttackEnemy() {
	}
	
	@Override
	public Status update(float seconds) {
		
		EnemyRangedAbilityComponent c = (EnemyRangedAbilityComponent) _tree.getObject().getComponent("ABILITY");
		c.activateAbility();
		
		return Status.SUCCESS;
	}

	@Override
	public void reset() {
	}

}
