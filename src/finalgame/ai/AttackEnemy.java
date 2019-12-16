package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.Status;
import finalgame.engineAdditions.AnimateGraphicsComponent;
import finalgame.engineAdditions.EnemyRangedAbilityComponent;
import finalgame.engineAdditions.TransformComponent;
import support.Vec2d;

public class AttackEnemy extends BTAction {

	public AttackEnemy() {
	}
	
	@Override
	public Status update(float seconds) {
				
		EnemyRangedAbilityComponent c = (EnemyRangedAbilityComponent) _tree.getObject().getComponent("ABILITY");
		c.activateAbility();	
		
		try {
			AnimateGraphicsComponent g = (AnimateGraphicsComponent) _tree.getObject().getComponent("ANIMATE");
			Vec2d loc = ((TransformComponent) _tree.getObject().getComponent("TRANSFORM")).getLoc();
			Vec2d dir = ((TransformComponent) c.getTarget().getComponent("TRANSFORM")).getLoc().minus(loc);
			if (dir.x<0 && Math.abs(dir.x)>Math.abs(dir.y)) {
				g.setAnimate(2);
			} else if (dir.x>0 && Math.abs(dir.x)>Math.abs(dir.y)) {
				g.setAnimate(3);
			} else if (dir.y<0) {
				g.setAnimate(4);
			} else if (dir.y>0) {
				g.setAnimate(1);
			} else {
				g.setAnimate(0);
			}
			
		} catch (NullPointerException e) {
			
		}
		
		return Status.SUCCESS;
	}

	@Override
	public void reset() {
	}

}
