package wizard.utils;

import engine.GameWorld;
import engine.gameobject.GameObject;
import support.Vec2d;

public class WIZRestart {
	private GameWorld _gameWorld = null;
	private WIZGameObjectDelegate _wizGameObjectDelegate = null;
	
	public WIZRestart(GameWorld gameWorld,WIZGameObjectDelegate wizGameObjectDelegate) { 
		this.setWizGameObjectDelegate(wizGameObjectDelegate);
		this.setGameWorld(gameWorld);
	}
	
	public void restartLevel () 
	{
		if (this.getGameWorld() != null && this.getWizGameObjectDelegate() != null) 
		{
			if ( this.getGameWorld().getLevel() == 0) 
			{
				this.restartLevel0();	
			} 
			else if (this.getGameWorld().getLevel() == 1) 
			{	
				this.restartLevel1();
			}	
		}
	}
	
	private void restartLevel0 ( ) 
	{	
		GameObject main = this.getWizGameObjectDelegate().getObjsLevelO().get("Main");
		GameObject enemy1 = this.getWizGameObjectDelegate().getObjsLevelO().get("Enemy1");
		// GameObject enemy4 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy4");
		// Reset Main
		main.getData()._level0RedKeyFound = false; 
		main.getData()._level0BlueKeyFound = false; 
		main.getData()._level0GreenKeyFound = false;
		main.getData()._level1RedKeyFound = false;  
		main.getData()._level1BlueKeyFound = false;  
		main.getData()._level1GreenKeyFound = false;
		this.getGameWorld().setOrigin(new Vec2d (-700,  -600));
		// Reseting Enemy introduces a bug in the code. 

		// Reset Enemy 1

		enemy1.getData().setAIposition(new Vec2d(1500,300));
		enemy1._invisble = true;


		// Reset Enemy 4 
		// enemy4.getData().setAIposition(new Vec2d(1500,300));
	}
	
	private void restartLevel1 ( ) 
	{
		GameObject main = this.getWizGameObjectDelegate().getObjsLevel1().get("Main");
		GameObject enemy2 = this.getWizGameObjectDelegate().getObjsLevel1().get("Enemy2"); 
		GameObject enemy3 = this.getWizGameObjectDelegate().getObjsLevel1().get("Enemy3");
		// GameObject enemy5 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy5");
		// Reset main
		main.getData()._level0RedKeyFound = false; 
		main.getData()._level0BlueKeyFound = false; 
		main.getData()._level0GreenKeyFound = false;
		main.getData()._level1RedKeyFound = false;  
		main.getData()._level1BlueKeyFound = false;  
		main.getData()._level1GreenKeyFound = false;
		this.getGameWorld().setLevel(0);
		this.getGameWorld().setOrigin(new Vec2d (-700,-600));
		// Reset Enemy 2		
		enemy2.getData().setAIposition(new Vec2d(1700,400));
		// Reset Enemy 3
		enemy3.getData().setAIposition(new Vec2d(1300,1900));
		// Reset Enemy 5
		// enemy5.getData().setAIposition(new Vec2d(1300,1900));
	}
	GameWorld getGameWorld() {
		return _gameWorld;
	}
	void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	private WIZGameObjectDelegate getWizGameObjectDelegate() {
		return _wizGameObjectDelegate;
	}
	private void setWizGameObjectDelegate(WIZGameObjectDelegate _wizGameObjectDelegate) {
		this._wizGameObjectDelegate = _wizGameObjectDelegate;
	}
}
