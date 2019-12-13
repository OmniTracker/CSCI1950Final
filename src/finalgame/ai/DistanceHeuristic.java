package finalgame.ai;

import engine.ai.GameState;
import engine.ai.Heuristic;

public class DistanceHeuristic extends Heuristic {

	DistanceGOAP p;
	
	public DistanceHeuristic(DistanceGOAP p) {
		super(p);
		this.p = p;
	}

	@Override
	public double estimate(GameState s) {
		DistanceState curr = (DistanceState) s;
		DistanceState goal = (DistanceState) p.getGoalState();
		//Vec2d dist = goal.getLoc().minus(curr.getLoc());
		//double cost = (Math.abs(dist.x)+Math.abs(dist.y));
		double distance = curr.getLoc().dist2(goal.getLoc());
		double cost = Math.min(Math.abs(distance-p.dist), Math.abs(distance-p.lower_dist));
		curr.setCost(cost);
		return cost;
	}

}
