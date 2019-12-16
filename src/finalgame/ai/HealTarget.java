package finalgame.ai;

import engine.ai.BTAction;
import engine.ai.Status;
import finalgame.engineAdditions.AnimateGraphicsComponent;
import support.Vec2d;
import finalgame.engineAdditions.HealthComponent;
import finalgame.engineAdditions.TransformComponent;

public class HealTarget extends BTAction {

	public HealTarget() {
		
	}
	
	@Override
	public Status update(float seconds) {
		
		try {
			AnimateGraphicsComponent g = (AnimateGraphicsComponent) _tree.getObject().getComponent("ANIMATE");
			Vec2d loc = ((TransformComponent) _tree.getObject().getComponent("TRANSFORM")).getLoc();
			Vec2d dir = ((TransformComponent) ((TestGI) _tree.getGI()).getHeal().getComponent("TRANSFORM")).getLoc().minus(loc);
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
		try {
			((HealthComponent) ((TestGI) _tree.getGI()).getHeal().getComponent("HEALTH")).heal(10);
		} catch(NullPointerException e) {
			return Status.FAILURE;
		}
	
		return Status.SUCCESS;
	}

	@Override
	public void reset() {
	}

}
