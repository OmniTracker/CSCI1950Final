package finalgame.ai;

import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.TransformComponent;
import engine.ai.BehaviorTree;
import engine.ai.Condition;
import engine.ai.Status;

public class NotNearGroup implements Condition {

	private BehaviorTree tree;
	private GameObject obj;
	private double threshold;
	
	public NotNearGroup(GameObject o, double threshold) {
		obj = o;
		this.threshold = threshold;
	}
	
	@Override
	public Status update(float seconds) {
		GameObject leader = ((TestGI) tree.getGI()).getLeader();
		TransformComponent ltc = (TransformComponent) leader.getComponent("TRANSFORM");
		TransformComponent tc = (TransformComponent) obj.getComponent("TRANSFORM");
		if (tc.getLoc().dist2(ltc.getLoc())>=threshold) {
			return Status.SUCCESS;
		} else {
			return Status.FAILURE;
		}
	}

	@Override
	public void reset() {

	}

	@Override
	public void setTree(BehaviorTree tree) {
		this.tree = tree;
	}

	@Override
	public BehaviorTree getTree() {
		return tree;
	}

}
