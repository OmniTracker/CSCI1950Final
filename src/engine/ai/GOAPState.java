package engine.finalai;

import engine.GameWorld;

public abstract class GOAPState extends GameState {

	public Action action;
	public GOAPState prev;
	
	public GOAPState(Action a, GOAPState p, GameWorld w) {
		// Do a.act() in GameWorld and generate current GOAPState based on GameWorld. Depends on what we want to do.
		action = a;
		prev = p;
	}

	public boolean compare(GameState other) {
		return false;
	}

}
