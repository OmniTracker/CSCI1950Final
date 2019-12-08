package engine.ai;

public abstract class GOAPState extends GameState {

	public Action action;
	
	public GOAPState(Action a) {
		// Do a.act() in GameWorld and generate current GOAPState based on GameWorld. Depends on what we want to do.
		action = a;
	}

}
