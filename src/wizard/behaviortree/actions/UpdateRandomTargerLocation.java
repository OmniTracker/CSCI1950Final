package wizard.behaviortree.actions;

import java.util.Random;
import support.Vec2i;
import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.ai.astar.AStarSearch;
import engine.ai.behaviortree.Actions;
import engine.gameobject.GameObject;

public class UpdateRandomTargerLocation extends WIZBehaviorSequence  implements Actions {
	private boolean DEBUG = false;
	public UpdateRandomTargerLocation(GameObject obj, GameObject targetObj) {
		super(obj,targetObj);  
		this.setBehaviorType("Action");
		this.setBehaviorName("Update Random Targer Location");
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
		return this.generateNewTargetPath(); 
	} 
	Vec2i swap(Vec2i s) { 
		return new Vec2i (s.y, s.x);  
	}
	boolean generateNewTargetPath () {
		if (DEBUG) {
			System.out.print( " Current level: "  + this.getObj().getGameLevel() +  "\n" );			
		}
		AStarSearch aStar = null;
		if ( this.getObj().getGameLevel() == 0)
		{ 
			aStar = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel0AStarSearch(); 
		} 
		else if (this.getObj().getGameLevel() == 1)
		{	
			aStar = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel1AStarSearch();  
		}
		
		if (aStar == null) 
		{
			return false;
		}
		
		Vec2i me  = swap(this.getObj().getData().getAIGridLocation());
		Vec2i him  = this.getTargetObj().getData().getGameGridLocation(); 
		
		if (DEBUG) 
		{
			System.out.print( " me -> x: " + me.x +  " y: " + me.y + "\n" );
			System.out.print( " him -> x: " + him.x +  " y: " + him.y + "\n" );		
			System.out.print( " AStar -> x: " + aStar.getMaxRow() +  " y: " + aStar.getMaxCol() + "\n" );		
		}
		boolean found = false; 
		
		Integer randomRow = 0; 
		Integer randomCol = 0;
		
		// Get a  random row and column located on the map. Keep on 
		// going until something is found.
		while (found == false)
		{
			randomRow = randomNumber(aStar.getMaxRow(), 0); 
			randomCol = randomNumber(aStar.getMaxCol(), 0);  
			
			if (aStar.getGrid()[randomRow][randomCol] == 1)
			{				
				found = true; 
			}
		}
		this.getObj().setSinglePathList(aStar.starSearch(me,new Vec2i(randomRow,randomCol))); 
		if (DEBUG) {
			System.out.print("Path Step count : " + this.getObj().getSinglePathList().size() + "\n" );		
		}
		return true; 
	}
	Integer randomNumber(Integer max , Integer min) {
		Random randomGenerator = new Random();
		return ( Integer ) randomGenerator.nextInt(max) + min;
	}
}
