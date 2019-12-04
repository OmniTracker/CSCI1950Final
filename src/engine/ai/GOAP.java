package engine.ai;

import java.util.ArrayList;
import java.util.HashSet;

import finalgame.engineAdditions.GameObject;

public abstract class GOAP extends SearchProblem {

	public GameObject _obj;
	public ArrayList<Action> _actions;
	public GOAPState _start;
	public GOAPState _goal;
	
	public GOAP(GameObject g, ArrayList<Action> actions, GOAPState start, GOAPState goal) {
		_obj = g;
		_actions = actions;
		_start = start;
		_goal = goal;
	}

	@Override
	public GameState getStartState() {
		return _start;
	}

	@Override
	public boolean isGoalState(GameState s) {
		if (((GOAPState) s).compare(_goal)) {
			return true;
		}
		return false;
	}

	// This is what this method should generally look like.
	/*GOAPState state = (GOAPState) s;
	HashSet<GameState> succ = new HashSet<GameState>();
	
	for (Action a: _actions) {
		succ.add(new GOAPState(a,state,_w));
	}
	
	return succ;*/
	@Override
	public abstract HashSet<GameState> getSuccessors(GameState s);

	@Override
	public GameState getGoalState() {
		return _goal;
	}
	
}
