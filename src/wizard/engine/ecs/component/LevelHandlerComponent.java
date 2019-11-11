package wizard.engine.ecs.component;

import support.Vec2d;
import support.Vec2i;
import engine.GameWorld;
import engine.systems.Components;

public class LevelHandlerComponent extends Components {
	private GameWorld _gameWorld; 
	public LevelHandlerComponent(GameWorld gameWorld) {
		this.setGameWorld(gameWorld);
	}
	public void onTick(long nanosSincePreviousTick) {		
		this.checkLevelUp();

	}
	public void checkLevelUp () {
		if ( this.getGameWorld().getLevel() == 0 ){
			Vec2i mainPlayerPosition = this.getGameWorld().
					getWIZDelegateContainer().
					getWIZGameObjectDelegate().
					getObjsLevel1().
					get("Main").
					getData().
					getGameGridLocation(); 
			
			if ((mainPlayerPosition.x == 8) && (mainPlayerPosition.y == 23)) {
				this.getGameWorld().setLevel(1);
				this.getGameWorld().setOrigin(new Vec2d (-700,  -600));
			}
		}
		
		
		if ( this.getGameWorld().getLevel() == 1 ){
			Vec2i mainPlayerPosition = this.getGameWorld().
					getWIZDelegateContainer().
					getWIZGameObjectDelegate().
					getObjsLevel1().
					get("Main").
					getData().
					getGameGridLocation(); 
			
			if ((mainPlayerPosition.x == 23) && (mainPlayerPosition.y == 7)) {
				this.getGameWorld().setLevel(0);
				this.getGameWorld().setOrigin(new Vec2d (-700,  -600));
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
