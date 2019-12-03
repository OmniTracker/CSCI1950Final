package wizard.engine.ecs.component;

import support.Vec2d;
import support.Vec2i;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class LevelHandlerComponent extends Components {
	private GameWorld _gameWorld; 
	public LevelHandlerComponent(GameWorld gameWorld) {
		this.setGameWorld(gameWorld);
	}
	public void onTick(long nanosSincePreviousTick) {		
		this.checkLevelUp();
	}
	public void checkLevelUp () 
	{
		if ( this.getGameWorld().getLevel() == 0 )
		{	
			GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Main");
			Vec2i mainPlayerPosition = main.getData().getGameGridLocation(); 
			if ((mainPlayerPosition.x == 8) && (mainPlayerPosition.y == 23)) 
			{
				if (main.getData()._level0RedKeyFound  && main.getData()._level0BlueKeyFound && main.getData()._level0GreenKeyFound) 
				{		
					if ((mainPlayerPosition.x == 8) && (mainPlayerPosition.y == 23)) 
					{
						main.getData()._level0RedKeyFound = false; 
						main.getData()._level0BlueKeyFound = false; 
						main.getData()._level0GreenKeyFound = false;
						main.getData()._level1RedKeyFound = false;  
						main.getData()._level1BlueKeyFound = false;  
						main.getData()._level1GreenKeyFound = false;
						this.getGameWorld().setLevel(1);
						this.getGameWorld().setOrigin(new Vec2d (-700,  -600));
					}	
				}
			}
		} 
		else if ( this.getGameWorld().getLevel() == 1 )
		{
			GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main");
			Vec2i mainPlayerPosition = main.getData().getGameGridLocation(); 
			if ((mainPlayerPosition.x == 23) && (mainPlayerPosition.y == 7)) 
			{	
				if (main.getData()._level1RedKeyFound  && main.getData()._level1BlueKeyFound && main.getData()._level1GreenKeyFound) 
				{	
					if ((mainPlayerPosition.x == 23) && (mainPlayerPosition.y == 7)) 
					{
						main.getData()._level0RedKeyFound = false; 
						main.getData()._level0BlueKeyFound = false; 
						main.getData()._level0GreenKeyFound = false;
						main.getData()._level1RedKeyFound = false;  
						main.getData()._level1BlueKeyFound = false;  
						main.getData()._level1GreenKeyFound = false;
						this.getGameWorld().setLevel(0);
						this.getGameWorld().setOrigin(new Vec2d (-700,  -600));
					}
				}	
			}	
		}
	}

	private GameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}

}
