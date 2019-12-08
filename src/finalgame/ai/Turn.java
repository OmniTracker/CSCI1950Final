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
			
			
		} catch (NullPointerException e) {
			
		}
		// TODO Auto-generated method stub

	}

	@Override
	public Vec2d getDir() {
		return dir;
	}

}
