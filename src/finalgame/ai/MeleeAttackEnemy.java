package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.Status;
import finalgame.engineAdditions.EnemyMeleeAbilityComponent;
import finalgame.engineAdditions.GameObject;

public class MeleeAttackEnemy extends BTAction {

	public MeleeAttackEnemy(GameObject target) {
		this.target = target;
	}

	@Override
	public Status update(float seconds) {
		EnemyMeleeAbilityComponent c = (EnemyMeleeAbilityComponent) _tree.getObject().getComponent("ABILITY");
		c.activateAbility();
		
		return Status.SUCCESS;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}	

}
