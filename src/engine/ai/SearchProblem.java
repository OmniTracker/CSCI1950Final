package engine.ai;

import java.util.HashSet;

public abstract class SearchProblem {

	public SearchProblem() {
	}
	
	public abstract GameState getStartState();
	
	public abstract boolean isGoalState(GameState s);
	
	public abstract HashSet<GameState> getSuccessors(GameState s);
	
	public abstract GameState getGoalState();

}
