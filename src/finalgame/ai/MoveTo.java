package finalgame.ai;

import java.util.ArrayList;

import engine.ai.Action;
import engine.ai.BTAction;
import engine.ai.BehaviorTree;
import engine.ai.CopyWorld;
import engine.ai.Status;
import support.Vec2d;

public class MoveTo extends BTAction {
	
	public MoveTo() {
		_actions = new ArrayList<Action>();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Status update(float seconds) {

		// Instantiate GOAP, _actions, according to wanted behavior.
		// Run AStar on GOAP.
		// Will Return GOAPState, which has the corresponding action.
		// Use the act() method to run the correct action.
		
		
		
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
