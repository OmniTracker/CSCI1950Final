package engine.ai;

import java.util.ArrayList;

public abstract class BTAction implements BTNode {

	public BehaviorTree _tree;
	public GOAP _goap;
	public ArrayList<Action> _actions;
	public AStar _astar;
	public Heuristic heur;
	
	@Override
	public abstract Status update(float seconds);
		// Instantiate GOAP, _actions, according to wanted behavior.
		// Run AStar on GOAP.
		// Will Return GOAPState, which has the corresponding action.
		// Use the act() method to run the correct action.
}
