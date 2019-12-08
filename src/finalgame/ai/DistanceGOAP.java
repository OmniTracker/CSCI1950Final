package finalgame.ai;

import java.util.ArrayList;
import java.util.HashSet;

import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.TransformComponent;
import engine.ai.Action;
import engine.ai.CopyWorld;
import engine.ai.GOAP;
import engine.ai.GOAPState;
import engine.ai.GameState;

public class DistanceGOAP extends GOAP {

	// Maximum distance from target.
	private double dist;
	
	public DistanceGOAP(GameObject g, ArrayList<Action> actions, GOAPState start, GOAPState goal, double dist) {
		super(g, actions, start, goal);
		this.dist = dist;
	}

	@Override
	public HashSet<GameState> getSuccessors(GameState s) {
		DistanceState ds = (DistanceState) s;
		
		HashSet<GameState> succ = new HashSet<GameState>();
		
		for (Action a: _actions) {
			CopyWorld c = new CopyWorld(ds.getWorld(),ds.getWorld().curr);
			a.act(c,c.curr);
			TransformComponent tc = (TransformComponent) c.curr.getComponent("TRANSFORM");
			DistanceState newds = new DistanceState(a,tc.getLoc(),tc.getDim(),c,c.curr);
			if (!newds.compare(ds)) {
				succ.add(newds);
			}
			
		}
		
		return succ;
	}
	
	@Override
	public boolean isGoalState(GameState s) {
		if (((DistanceState) s).getLoc().dist2(((DistanceState) _goal).getLoc())<=dist) {
			return true;
		} else {
			return false;
		}
	}
}
