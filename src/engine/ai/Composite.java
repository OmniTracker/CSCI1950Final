package engine.finalai;

import java.util.ArrayList;

public abstract class Composite implements BTNode {

	public ArrayList<BTNode> _children = new ArrayList<BTNode>();
	public BehaviorTree _tree;
	
	public abstract Status update(float seconds);
	
	public void reset() {
		for (BTNode n: _children) {
			n.reset();
		}
	}
	
	public ArrayList<BTNode> getChildren() {
		return _children;
	}
	
	public abstract BTNode getLastRunning();
	
	public abstract void addChildren(BTNode node);
	
	public void setTree(BehaviorTree tree) {
		_tree = tree;
	}
	
	public BehaviorTree getTree() {
		return _tree;
	}
	
}
