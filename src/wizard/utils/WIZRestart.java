package wizard.utils;

import engine.GameWorld;
import engine.gameobject.GameObject;
import support.Vec2d;

public class WIZRestart {
	private GameWorld _gameWorld = null;
	private WIZGameObjectDelegate _wizGameObjectDelegate = null;
	public WIZRestart(GameWorld gameWorld,WIZGameObjectDelegate wizGameObjectDelegate) 
	{ 
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
		main.getData()._level0RedKeyFound = false; 
		main.getData()._level0BlueKeyFound = false; 
		main.getData()._level0GreenKeyFound = false;
		main.getData()._level1RedKeyFound = false;  
		main.getData()._level1BlueKeyFound = false;  
		main.getData()._level1GreenKeyFound = false;
		this.getGameWorld().setOrigin(new Vec2d (-700,  -600));
	}
	private void restartLevel1 ( ) 
	{
		GameObject main = this.getWizGameObjectDelegate().getObjsLevel1().get("Main");
		main.getData()._level0RedKeyFound = false; 
		main.getData()._level0BlueKeyFound = false; 
		main.getData()._level0GreenKeyFound = false;
		main.getData()._level1RedKeyFound = false;  
		main.getData()._level1BlueKeyFound = false;  
		main.getData()._level1GreenKeyFound = false;
		this.getGameWorld().setLevel(0);
		this.getGameWorld().setOrigin(new Vec2d (-700,-600));
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
