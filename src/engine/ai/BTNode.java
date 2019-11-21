package engine.ai;

public interface BTNode {

	public Status update(float seconds);
	
	public void reset();
	
	public void setTree(BehaviorTree tree);
	
	public BehaviorTree getTree();
}
