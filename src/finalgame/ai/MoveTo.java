package finalgame.ai;

import java.util.ArrayList;

import engine.ai.AStar;
import engine.ai.Action;
import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.Status;
import finalgame.engineAdditions.AnimateGraphicsComponent;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.TransformComponent;
import support.Vec2d;

public class MoveTo extends BTAction {
	
	private double dist;
	private GameObject target;
	
	public MoveTo(GameObject target, double dist) {
		this.dist = dist;
		this.target = target;
		
	}
	
	@Override
	public Status update(float seconds) {
		
		double speed = 10;
		_actions = new ArrayList<Action>();
		_actions.add(new Move(speed,new Vec2d(0,1)));
		_actions.add(new Move(speed,new Vec2d(0,-1)));
		_actions.add(new Move(speed,new Vec2d(1,0)));
		_actions.add(new Move(speed,new Vec2d(-1,0)));
		TransformComponent ttc = (TransformComponent) target.getComponent("TRANSFORM");
		TransformComponent tc = (TransformComponent) _tree.getObject().getComponent("TRANSFORM");
		DistanceState start = new DistanceState(null,tc.getLoc(),tc.getDim(), _tree.getWorld(),_tree.getObject());
		DistanceState goal = new DistanceState(null, ttc.getLoc(), ttc.getDim(), _tree.getWorld(), _tree.getObject());
		
		_goap = new DistanceGOAP(_tree.getObject(),_actions,start,goal,dist);
		_astar = new AStar(new DistanceHeuristic((DistanceGOAP) _goap),_goap);
		
		DistanceState s = (DistanceState) _astar.getNext(true);
		if (s==null) {
			return Status.FAILURE;
		}
		Action action = s.action;
		
		try {
			
			AnimateGraphicsComponent g = (AnimateGraphicsComponent) _tree.getObject().getComponent("ANIMATE");
			
			Vec2d d = action.getDir().normalize();
			if (d.x<0) {
				g.setAnimate(2);
			} else if (d.x>0) {
				g.setAnimate(3);
			} else if (d.y<0) {
				g.setAnimate(4);
			} else if (d.y>0) {
				g.setAnimate(1);
			} else {
				g.setAnimate(0);
			}
		} catch (NullPointerException e) {
			
		}
		
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
