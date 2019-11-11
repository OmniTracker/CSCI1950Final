package wizard.engine.ecs.component;

import java.util.HashMap;
import java.util.Map.Entry;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;

public class BehaviorComponent {
	private GameWorld _gameWorld = null;
	private boolean        DEBUG = false;

	public BehaviorComponent(Application app, GameWorld gameWorld){
		this.setGameWorld(gameWorld);
	}
	public void onTick(long nanosSincePreviousTick) {
		HashMap<String ,GameObject> characters = null;
		Integer gameLevel = this.getGameWorld().getLevel();
		if (gameLevel == 0) {
			characters = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO();
		} else if (gameLevel == 1) {
			characters = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1();
		} else  {
			return;
		}
		if (characters == null) {
			return;
		}
		GameObject main = characters.get("Main"); 
		if (main == null ) { 
			return;
		}
		for (Entry<String, GameObject> character : characters.entrySet())  {
			if (character.getKey() != "Main") {	
				
				if (DEBUG == true) {
					System.out.print("\n\n"+ character.getKey() + "\n");
				}
				if ((character.getValue().getData().getAIGridLocation().x == 0)  && 
						( character.getValue().getData().getAIGridLocation().y == 0) ) {
					return;
				}
				
				character.getValue().getData().getWIZBehaviorTree().runTree(this.getGameWorld());  
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
