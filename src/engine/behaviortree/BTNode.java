package engine.behaviortree;

public interface BTNode {
	
	boolean update(float seconds);
	void reset();

}
