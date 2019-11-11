package engine.behaviortree;

import engine.GameWorld;

public interface Action {
	boolean runSequenceNode (GameWorld gameWorld); 
	public boolean isActionSatisfied(); 
	boolean conductSequenceAction(); 
	boolean sendFalsePositive();
}
