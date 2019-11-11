package engine.behaviortree;

import engine.GameWorld;

public interface Condition {
	boolean runSequenceNode (GameWorld gameWorld); 
	public boolean isConditionSatisfied(); 
	boolean conductSequenceCondition(); 
	boolean sendFalsePositive();
}
