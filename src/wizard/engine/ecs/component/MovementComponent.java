package wizard.engine.ecs.component;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

import support.Vec2d;
import support.Vec2i;
import javafx.scene.input.KeyEvent;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class MovementComponent extends Components {
	private Application _app; 
	private GameWorld _gameWorld; 
	private long _lastTimeUpdate = 0;
	private boolean DEBUG = false;

	public MovementComponent (Application app, GameWorld gameWorld)
	{
		this.setApp(app);
		this.setGameWorld(gameWorld);
		this.setComponentName("Movement");
	}
	public void onTick(long nanosSincePreviousTick) 
	{
		this.updateMainCharacterPosition();
		this.updateEnemyPosition(nanosSincePreviousTick);
	}
	public void onKeyPressed(KeyEvent e) {
		this.moveMainCharacter(e);
	}
	// This more so used to keep the main character in the middle of the screen. This function doesn't move the
	// character in context to the rest of the game. 
	private void updateMainCharacterPosition() 
	{	
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main");	
		Vec2d pos = this.getApp().getAspectRatioHandler().getCurrentScreenSize(); 
		main.getData().setPosition( new Vec2d( ( pos.x / 2), (pos.y / 2)) );
		Vec2d boxPos = main.getData().getPosition().minus(-10, -15); 
		main.getData().getBox().setTopLeft(boxPos);
	}

	private void moveMainCharacter(KeyEvent e) {
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main");	
		if (main == null) {
			return;
		}
		String direction = e.getCode().toString();
		double x = 0; 
		double y = 0; 
		if (direction.contains("UP"))  {
			x = 5;
		} else if (direction.contains("DOWN")){
			x = -5;
		} else if (direction.contains("LEFT")) {
			y = 5;
		} else if (direction.contains("RIGHT")) {
			y = -5;
		}	
		this.getGameWorld().setOrigin(this.getGameWorld().getOrigin().plus( new Vec2d(x,y)));
		int xGrid = (int) (7 - Math.floor(((this.getGameWorld().getOrigin().x + 700 + 50) / 100)));
		int yGrid = (int) (1 - Math.floor(((this.getGameWorld().getOrigin().y + 130 + 50) / 100)));
		main.getData().setGameLocation( new Vec2i (xGrid,yGrid));		
		main.characterMove(direction);
	}
	// The AI will calculate the path the Enemy needs to take in order to get to the main 
	// character.
	public void updateEnemyPosition(long nanosSincePreviousTick) 
	{	
		if ( Math.abs(this.getLastTimeUpdate() - nanosSincePreviousTick)   > 1000)  
		{			
			this.setLastTimeUpdate(nanosSincePreviousTick);
			// Need to write something for if the main character is close to the enemy.
			HashMap<String ,GameObject> characters = null;
			Integer gameLevel = this.getGameWorld().getLevel();
			
			// 
			if (gameLevel == 0) 
			{
				characters = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO();
			} 
			else if (gameLevel == 1) 
			{
				characters = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1();
			} 
			else 
			{
				return;
			}
			for (Entry<String, GameObject> character : characters.entrySet())  
			{
				if (character.getKey() != "Main")
				{	
					// Used for sprite animation
					if (character.getValue().getAIStepCount() == 2)
					{
						character.getValue().setAIStepCount(0);
					} 
					else 
					{
						character.getValue().setAIStepCount(character.getValue().getAIStepCount() + 1) ;
					}					
					Stack<Vec2i> singlePathList = character.getValue().getSinglePathList(); 
					if (singlePathList == null)
					{
						// Need to set a flag to let the AI know to update it's flag.
						return;
					}
					if (singlePathList.size() == 0) 
					{
						// Set a flag letting the AI know it needs to look for a  different path.
						return;
					}
					Vec2i last = singlePathList.lastElement().pmult(100, 100); 
					Vec2d AIPosition = character.getValue().getData().getAIposition(); 
					
					if (((last.x  - AIPosition.y) == 0) &&  ((last.y  - AIPosition.x) == 0)) 
					{
						character.getValue().getSinglePathList().pop(); 
					}
					double x = 0; 
					double y = 0 ; 
					String faceDir = ""; 
					if (((last.x  - AIPosition.y) != 0.0) &&  ((last.y  - AIPosition.x) == 0.0)) 
					{
						if (((last.x  - AIPosition.y)/100) >= 0.0) 
						{
							faceDir = "DOWN";
							y += character.getValue().getWizSpeed(); 
						}
						if (((last.x  - AIPosition.y)/100) <= 0.0 ) 
						{
							faceDir = "UP";
							y -= character.getValue().getWizSpeed(); 
						}
					}
					if (((last.y  - AIPosition.x) != 0.0 ) &&  ((last.x - AIPosition.y) == 0.0)) 
					{
						if (((last.y  - AIPosition.x)/100) >= 0.0) 
						{
							faceDir = "RIGHT";
							x += character.getValue().getWizSpeed(); 
						}
						if (((last.y  - AIPosition.x)/100) <= 0.0 )
						{
							faceDir = "LEFT";
							x -= character.getValue().getWizSpeed(); 
						}
					}
					character.getValue().getData().setAIposition(character.getValue().getData().getAIposition().plus(x,y));
					character.getValue().characterAIMove(faceDir);
					if (DEBUG == true) 
					{
						System.out.print( "Direction walk: "+  faceDir +  " Direction x: "  + last.x + " y: "  + last.y  + 
								" Position: y: "  + AIPosition.y + " y: "  + AIPosition.x  + "\n "); 
					}
				} 
			}
		}
	}
	Application getApp() {
		return _app;
	}
	void setApp(Application _app) {
		this._app = _app;
	}
	private GameWorld getGameWorld() {
		return this._gameWorld;
	}
	private void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	private long getLastTimeUpdate() {
		return _lastTimeUpdate;
	}
	private void setLastTimeUpdate(long _lastTimeUpdate) {
		this._lastTimeUpdate = _lastTimeUpdate;
	}
}
