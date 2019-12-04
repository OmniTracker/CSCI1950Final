package engine.ai;

import java.util.ArrayList;

import finalgame.engineAdditions.CollisionSystem;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.GameSystem;
import engine.GameWorld;
import finalgame.engineAdditions.CollisionComponent;

public class CopyWorld extends GameWorld {

	public GameObject curr;
	private CollisionSystem _cs;
	
	public CopyWorld(GameWorld copy, GameObject real) {
		_gameObjects = new ArrayList<GameObject>();
		_cs = new CollisionSystem(this);
		_systems = new ArrayList<GameSystem>();
		_systems.add(_cs);
		for (GameObject o: copy.getObjects()) {

			CollisionComponent cc = (CollisionComponent) o.getComponent("COLLISION");
			if (cc!=null) {
				GameObject co = new GameObject(o);
				this.addToSystems(co);
				if (o.equals(real)) {
					curr = co;
				}
				_gameObjects.add(co);
			}
			
		}
	}
	
	private void addToSystems(GameObject go) {
		for(int i=0; i<_systems.size(); i++) {
			_systems.get(i).addObject(go);
		}
	}
	
}
