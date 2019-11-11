package wizard.behaviortree.conditions;

import java.util.Stack;

import support.Vec2i;
import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.behaviortree.Conditions;
import engine.gameobject.GameObject;

/**
 * Check to see if there are any spots left in the search path. If the
 * search path is less than or equal to 2, do a few checks and then tell
 * the behavior tree to update the list.
 * @author rbaker2
 *
 */
public class NeedToUpdateStoredPathForCharacter  extends WIZBehaviorSequence  implements Conditions {	
	private boolean DEBUG = false; 
	public NeedToUpdateStoredPathForCharacter(GameObject obj, GameObject targetObj) {
		super(obj, targetObj);
		this.setBehaviorType("Condition");
		this.setBehaviorName("Needs to update Stored Path?");
	}
	public boolean run () {
		return ConditionSatisfied();
	}
	public boolean ConditionSatisfied() {
		return isOnStoredPath ();
	}
	private boolean isOnStoredPath() {	
		if ( this.getObj().getSinglePathList() == null ) {
			return true;			
		}
		if ( this.getObj().getSinglePathList().size() == 0 ) {
			return true;			
		}
		Stack<Vec2i> single = this.getObj().getSinglePathList(); 
		if (DEBUG == true)  {
			System.out.print("Path Listing:  \n");
			for (int i = this.getObj().getSinglePathList().size() - 1; i > 0 ; i--) 
			{
				System.out.print("<----  " + single.get(i).x + " , " + single.get(i).y  +  " ----> \n");
			}			
		}
		return false;
	}
}
