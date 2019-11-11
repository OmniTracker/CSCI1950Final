package wizard.behaviortree.actions;

import support.Vec2i;
import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.astar.AStarSearch;
import engine.ai.behaviortree.Actions;
import engine.gameobject.GameObject;


public class UpdateDirectedTargetLocation extends WIZBehaviorSequence implements Actions {
	// This action should always return true.
	private boolean DEBUG = false;
	public UpdateDirectedTargetLocation(GameObject obj, GameObject targetObj) {
		super(obj,targetObj); 
		this.setBehaviorType("Action");
		this.setBehaviorName("Update Directed Target Location");
	}
	public boolean run () {
		return this.ActionCompleted();
	}
	@Override
	public boolean ActionCompleted() {
		return this.implementAction();
	}
	@Override
	public boolean implementAction() {
		return  isUpdated(); 
	}
	Vec2i swap(Vec2i s) { 
		return new Vec2i (s.y, s.x);  
	}
	private boolean isUpdated() {
		if (DEBUG) {
			System.out.print( " Current level: "  + this.getObj().getGameLevel() +  "\n" );			
		}
		AStarSearch aStar = null;
		if ( this.getObj().getGameLevel() == 0) { 
			aStar = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel0AStarSearch(); 
		} else if (this.getObj().getGameLevel() == 1) {
			aStar = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel1AStarSearch();  
		}
		if (aStar == null) {
			return false;
		}
		Vec2i me  = swap(this.getObj().getData().getAIGridLocation());
		Vec2i him  = this.getTargetObj().getData().getGameGridLocation(); 
		if (DEBUG) {	
			System.out.print( " me -> x: " + me.x +  " y: " + me.y + "\n" );
			System.out.print( " him -> x: " + him.x +  " y: " + him.y + "\n" );		
			System.out.print( " AStar -> x: " + aStar.getMaxRow() +  " y: " + aStar.getMaxCol() + "\n" );		
		}
		this.getObj().setSinglePathList(aStar.starSearch(me,him)); 
		
		if (DEBUG) {
			System.out.print("Path Step count : " + this.getObj().getSinglePathList().size() + "\n" );		
		}	
		
		return true;
	}
}
