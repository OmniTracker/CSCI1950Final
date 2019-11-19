package engine.finalai;

import engine.GameWorld;
import java.util.ArrayList;

public abstract class BTAction implements Action {

	public GameWorld _world;
	public GOAP _goap;
	public ArrayList<Action> _actions;
	public AStar _astar;
	public Heuristic heur;
	
	public BTAction(GameWorld w) {
		_world = w;
		
	}

	@Override
	public Status update(float seconds) {
		// Instantiate GOAP, _actions, according to wanted behavior.
		// Run AStar on GOAP.
		// Will Return GOAPState, which has the corresponding action.
		// Use the act() method to run the correct action.
		return null;
	}
}
