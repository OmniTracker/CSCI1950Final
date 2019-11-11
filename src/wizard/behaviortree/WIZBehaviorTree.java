package wizard.behaviortree;

import java.util.ArrayList;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.GameWorld;
import engine.ai.behaviortree.BehaviorTree;

/**
 * Each Sequence will only have "ONE" condition paired with "ONE" action. 
 * This made this feature easier to implement.
 */
public class WIZBehaviorTree extends BehaviorTree 
{	
	// Each Sequence has two behaviors. The first is a condition and the
	// second is the Action.
	private ArrayList<ArrayList<WIZBehaviorSequence>> _sequence; 
	private boolean DEBUG = false; 
	public WIZBehaviorTree () 
	{
		this.setSequence( new ArrayList<ArrayList<WIZBehaviorSequence>>() );
	}
	
	public void runTree(GameWorld gameWorld) 
	{
		if (this.getSequence().size() == 0)
		{
			return;
		}
		// Get each sequence Array. The logic within the Array should be
		// Properly handled in the for loop
		
		
		for ( int idx0 = 0; idx0 < this.getSequence().size(); idx0++ ) 
		{
			ArrayList<WIZBehaviorSequence> subSequence = this.getSequence().get(idx0);
			
			if (DEBUG == true) 
			{
				System.out.print( "\nSequence Number: " + idx0 + "\n");				
			}
			
			int eventCount = 0;
			
			for (int idx1 = 0; idx1 <  subSequence.size(); idx1++ ) 
			{					
				if (DEBUG == true) 
				{
					System.out.print( "Type: " + subSequence.get(idx1).getBehaviorType());
					System.out.print( ", Name: " + subSequence.get(idx1).getBehaviorName() + " \n");
				}		
				// False with run statement usually means you need to move to the next branch.
				if ( subSequence.get(idx1).getGameWorld() == null ) 
				{
					subSequence.get(idx1).setGameWorld(gameWorld);
				}
				
				if (subSequence.get(idx1).run() == false) 
				{	
					break; 
				} 
				else 
				{
					eventCount++;
				}
			}
			// If able to get through the entire node with getting true for all the run events,
			// the sequence should end. being able to get through an entire sequence means 
			// you are done with your behavior tree run.
			if (eventCount ==  subSequence.size()) 
			{
				return;
			}
		}
	}
	public boolean runSequence () {
		return false; 
	}
	public ArrayList<ArrayList<WIZBehaviorSequence>> getSequence() {
		return _sequence;
	}
	public void setSequence(ArrayList<ArrayList<WIZBehaviorSequence>> _sequence) {
		this._sequence = _sequence;
	}
}