package engine.ai;

import java.util.ArrayList;

import finalgame.engineAdditions.GameObject;
import engine.GameWorld;

public class BehaviorTree {

	private Composite root;
	private ArrayList<BTNode> nodes;
	private GameWorld world;
	private GroupInformation gi;
	private GameObject g;
	
	public BehaviorTree(Composite root, GameWorld world, GameObject o) {
		this.root = root;
		nodes = new ArrayList<BTNode>();
		nodes.add(root);
		g = o;
		this.world = world;
		
	}
	
	public BehaviorTree(Composite root, GroupInformation gi, GameWorld world, GameObject o) {
		this.root = root;
		this.root.setTree(this);
		nodes = new ArrayList<BTNode>();
		nodes.add(root);
		g = o;
		this.world = world;
		this.gi = gi;
	}

	public void addBehavior(Composite node, BTNode newNode) {
		if (!nodes.contains(node)) {
			throw new Error("No Such Element in BT");
		}
		node.addChildren(newNode);
		nodes.add(newNode);
		newNode.setTree(this);
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
		newNode.setTree(this);
		
	}
	
	public BTNode getBehavior(int index) {
		return nodes.get(index);
	}
	
	public GroupInformation getGI() {
		return gi;
	}
	
	public void tick(long seconds) {
		if (gi!=null) {
			gi.tick(seconds);
		}
		root.update(seconds);
	}
	
	public GameWorld getWorld() {
		return world;
	}
	
	public GameObject getObject() {
		return g;
	}
}

