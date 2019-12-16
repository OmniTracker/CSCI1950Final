package finalgame.ai;

import engine.GameWorld;
import engine.ai.Action;
import finalgame.engineAdditions.AnimateGraphicsComponent;
import finalgame.engineAdditions.GameObject;
import support.Vec2d;

public class Turn extends Action {

	private Vec2d dir;
	
	public Turn(Vec2d dir) {
		this.dir = dir.normalize();
	}

	@Override
	public void act(GameWorld w, GameObject obj) {
		
		try {
			AnimateGraphicsComponent g = (AnimateGraphicsComponent) obj.getComponent("ANIMATE");
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
		// TODO Auto-generated method stub

	}

	@Override
	public Vec2d getDir() {
		return dir;
	}

}
