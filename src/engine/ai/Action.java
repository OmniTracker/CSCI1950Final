package engine.ai;

import finalgame.engineAdditions.GameObject;
import engine.GameWorld;

public abstract class Action {

	public Action() {
		
	}
	
	public abstract void act(GameWorld w, GameObject obj);
	
} 