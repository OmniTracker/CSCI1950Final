package finalgame.ai;

import engine.ai.BehaviorTree;
import engine.ai.Condition;
import engine.ai.Status;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.TransformComponent;

public class NotInRange implements Condition {

	private BehaviorTree tree;
	private double upper_range;
	private double lower_range;
	private GameObject target;
	
	public NotInRange(GameObject target, double upper_range, double lower_range) {
		this.upper_range = upper_range;
		this.lower_range = lower_range;
		this.target = target;
	}
	
	@Override
	public Status update(float seconds) {
		TransformComponent ttc = (TransformComponent) target.getComponent("TRANSFORM");
		TransformComponent tc = (TransformComponent) tree.getObject().getComponent("TRANSFORM");
		
		if (tc.getLoc().dist2(ttc.getLoc())>=upper_range || tc.getLoc().dist2(ttc.getLoc())<=lower_range) {
			System.out.println(1);
			return Status.SUCCESS;
		} else {
			System.out.println(0);
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
	
	public void setTarget(GameObject obj) {
		target = obj;
	}


}
