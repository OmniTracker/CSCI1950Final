package finalgame.ai;

import java.util.ArrayList;

import engine.ai.AStar;
import engine.ai.Action;
import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.Status;
import finalgame.engineAdditions.TransformComponent;
import support.Vec2d;

public class MoveToLeader extends BTAction {

	
	public MoveToLeader() {
	}
	
	@Override
	public Status update(float seconds) {

		_actions = new ArrayList<Action>();
		_actions.add(new Move(10,new Vec2d(0,1)));
		_actions.add(new Move(10,new Vec2d(0,-1)));
		_actions.add(new Move(10,new Vec2d(1,0)));
		_actions.add(new Move(10,new Vec2d(-1,0)));
		if (((TestGI) _tree.getGI()).isLeader(_tree.getObject())) {
			return Status.SUCCESS;
		}
		TransformComponent tc = (TransformComponent) _tree.getObject().getComponent("TRANSFORM");
		TransformComponent ltc = (TransformComponent) ((TestGI) _tree.getGI()).getLeader().getComponent("TRANSFORM");
		DistanceState start = new DistanceState(null,tc.getLoc(),tc.getDim(), _tree.getWorld(),_tree.getObject());
		DistanceState goal = new DistanceState(null, ltc.getLoc(), ltc.getDim(), _tree.getWorld(), _tree.getObject());
		
		_goap = new DistanceGOAP(_tree.getObject(),_actions,start,goal);
		_astar = new AStar(new DistanceHeuristic((DistanceGOAP) _goap),_goap);
		
		DistanceState s = (DistanceState) _astar.getNext(true);
		if (s==null) {
			return Status.FAILURE;
		}
		Action action = s.action;
		action.act(_tree.getWorld(), _tree.getObject());
		if (s.compare(goal)) {
			return Status.SUCCESS;
		}
		return Status.RUNNING;
	}

	@Override
	public void reset() {

	}

	@Override
	public void setTree(BehaviorTree tree) {
		_tree = tree;
	}

	@Override
	public BehaviorTree getTree() {
		return _tree;
	}

}
