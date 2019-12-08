package finalgame.ai;

import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.TransformComponent;
import engine.GameWorld;
import engine.ai.Action;
import support.Vec2d;

public class Move extends Action {

	private double speed;
	private Vec2d dir;
	
	public Move(double s, Vec2d dir) {
		
		this.speed = Math.abs(s);
		this.dir = dir.normalize();
	}
	
	public Move(Action act) {
		Move m = (Move) act;
		this.speed = m.speed;
		this.dir = m.dir;
	}
	
	@Override
	public void act(GameWorld w, GameObject obj) {
		TransformComponent m = (TransformComponent) obj.getComponent("TRANSFORM");
		m.move(dir.smult(speed));
		w.tickCollision();
	}
	
	@Override
	public Vec2d getDir() {
		return dir;
	}
}
