package nin.behaviortree;

import java.util.ArrayList;

import nin.behaviortree.actions.PhysicsAction;
import nin.behaviortree.conditions.JumpSignaled;
import engine.ai.behaviortree.BehaviorTree;

/**
 * Each Sequence will only have "ONE" condition paired with "ONE" action. 
 * This made this feature easier to implement.
 */
public class NINBehaviorTree extends BehaviorTree {
	ArrayList<ArrayList<NinBehaviorSequence>> _sequence; 

	private String _name;

	public NINBehaviorTree () {
		this.setSequence( new ArrayList<ArrayList<NinBehaviorSequence>>() );
	}

	public void onTick (long t) {
		this.runTree(); 
	}

	public void runTree() 
	{
		if (this.getSequence().size() == 0) {
			return;
		}
		for ( int i = 0; i < this.getSequence().size(); i++ )  {			
			// Each sequence should only have two children.
			if (this.getSequence().get(i).size() != 2) {
				return;
			}
			// Check to make sure the first Behavior is a Condition
			if (this.getSequence().get(i).get(0).getBehaviorType() == "Condition" ) 
			{	
				// Just worry about the main character.
				if (this.getName() == "Main") {
					// Check is condition is true, conduct the action.
					if (this.getSequence().get(i).get(0).check() == true) 
					{
						if (this.getSequence().get(i).get(1).getBehaviorType() == "Action" ) 
						{
							// Check if the action paired sequence is true
							if (this.getSequence().get(i).get(1).check() == true) 
							{
								// If the sequence was found to be true, get out of here.
								PhysicsAction action = (PhysicsAction) this.getSequence().get(i).get(1); 
								if ( action.jump() == false) 
								{
									JumpSignaled stop = (JumpSignaled) this.getSequence().get(i).get(0); 
									stop.setJumpSignaled(false);
									stop.setSaveJumpContex(false);
								}
								return;
							}
						}
					} 
				} else {
					PhysicsAction action = (PhysicsAction) this.getSequence().get(i).get(1); 
					// Check is condition is true, conduct the action.
					if (this.getSequence().get(i).get(0).check() == true) {
						// Take the two collision boxes and move them back.
						action.useResitution(); 
					} else {
						// Move the object based on velocity and time.
						action.incrementX();
					}
				}
			}
		}
	}
	public boolean runSequence () {
		return false; 
	}
	public ArrayList<ArrayList<NinBehaviorSequence>> getSequence() {
		return _sequence;
	}
	public void setSequence(ArrayList<ArrayList<NinBehaviorSequence>> _sequence) {
		this._sequence = _sequence;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
}
