package engine.ai;

import finalgame.engineAdditions.GameObject;
import support.Vec2d;
import engine.GameWorld;

public abstract class Action {

	public Action() {
		
	}
	
	public abstract void act(GameWorld w, GameObject obj);
	
	// For Animation
	public abstract Vec2d getDir();
} 