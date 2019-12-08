package finalgame.ai;

import engine.ai.BehaviorTree;
import engine.ai.Condition;
import engine.ai.Status;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.TransformComponent;

public class NotInRange implements Condition {

	private BehaviorTree tree;
	private double range;
	private GameObject target;
	
	public NotInRange(double range, GameObject target) {
		this.range = range;
		this.target = target;
	}
	
	@Override
	public Status update(float seconds) {
		TransformComponent ttc = (TransformComponent) target.getComponent("TRANSFORM");
		TransformComponent tc = (TransformComponent) tree.getObject().getComponent("TRANSFORM");
		if (tc.getLoc().dist2(ttc.getLoc())>=range) {
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
