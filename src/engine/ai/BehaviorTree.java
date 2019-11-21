package engine.ai;

import java.util.ArrayList;

import engine.GameWorld;

public class BehaviorTree {

	private Composite root;
	private ArrayList<BTNode> nodes;
	private GameWorld world;
	
	public BehaviorTree(Composite root, GameWorld world) {
		this.root = root;
		nodes = new ArrayList<BTNode>();
		nodes.add(root);
		this.world = world;
	}
	
	public BehaviorTree(Composite root, GroupInformation gi, GameWorld world) {
		this.root = root;
		this.root.setTree(this);
		nodes = new ArrayList<BTNode>();
		nodes.add(root);
		this.world = world;
	}

	public void addBehavior(Composite node, BTNode newNode) {
		if (!nodes.contains(node)) {
			throw new Error("No Such Element in BT");
		}
		node.addChildren(newNode);
		nodes.add(newNode);
		try {
			Composite newN = (Composite) newNode;
			newN.setTree(this);
		} catch(ClassCastException e) {
			return;
		} 
	}
	
	public void addBehavior(int index, BTNode newNode) {
		Composite node;
		try {
			node = (Composite) nodes.get(index);
		} catch(ClassCastException e) {
			e.printStackTrace();
			return;
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return;
		}

		node.addChildren(newNode);
		nodes.add(newNode);
		try {
			Composite newN = (Composite) newNode;
			newN.setTree(this);
		} catch(ClassCastException e) {
			return;
		}
	}
	
	public void tick(float seconds) {
		root.update(seconds);
	}
	
	public GameWorld getWorld() {
		return world;
	}
}
