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
	
	public NotInRange(GameObject target, double range) {
		this.range = range;
		this.target = target;
	}
	
	@Override
	public Status update(float seconds) {
		TransformComponent ttc = (TransformComponent) target.getComponent("TRANSFORM");
		TransformComponent tc = (TransformComponent) tree.getObject().getComponent("TRANSFORM");
		
		if (tc.getLoc().dist2(ttc.getLoc())>=range) {
//			System.out.println(1);
			return Status.SUCCESS;
		} else {
//			System.out.println(10);
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
