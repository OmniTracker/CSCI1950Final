package engine.finalai;

import java.util.Comparator;

public abstract class Heuristic implements Comparator<GameState> {

	public Heuristic(SearchProblem p) {
	}
	
	public double estimate(GameState s) {
		return 1;
	}

	@Override
	public int compare(GameState arg0, GameState arg1) {
		double estimate1 = this.estimate(arg0);
		double estimate2 = this.estimate(arg1);
		return (int) Math.signum(estimate1-estimate2);
	}

}
