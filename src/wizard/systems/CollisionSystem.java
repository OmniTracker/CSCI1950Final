package wizard.systems;

import java.util.ArrayList;

import engine.gameobject.GameObject;
import engine.systems.Component;
import engine.systems.Systems;

public class CollisionSystem extends Systems {
	private ArrayList<GameObject> _gameMovableObjects; 
	private ArrayList<GameObject> _gameStaticObjects; 

	public CollisionSystem() {
		this.setSystemName("Collision");
	}

	public void sortGameObjects (ArrayList<GameObject> gameObjects) {
		for(GameObject obj : gameObjects ) {		
			this.sort(obj, obj.getComponents(), 0, obj.getComponents().size(), false);
		}
	}

	void sort(GameObject obj, ArrayList<Component> arrayList, int l, int r, boolean found) 
	{
		if (found == true) {
			return;
		}	
		if( (l < r) && (l - r) == 1) {
			if ( arrayList.get(l).getComponentName() == "Movable") 
			{
				this.getGameMovableObjects().add(obj);
				found = true;
				
			} 
			else if (arrayList.get(l).getComponentName() == "Static" ) 
			{
				this.getGameMovableObjects().add(obj);
				found = true;
			}
			return;
		} 
		else if ((l == r)) 
		{
			return;
		}
		//  Find the middle point 
        int m = ( l + r) / 2; 
        // Sort first and second halves 
        sort(obj, arrayList, l, m, found); 
        sort(obj, arrayList , m + 1, r, found);
        return; 
	}
	
	public boolean checkCollision (GameObject movableObj, ArrayList<GameObject> staticObj, int l, int r) {
		if ( ( l < r ) && ( l - r ) == 1 ) {
			return false;
		} 
		else if (l == r)  {
			return false;
		}
		
		//  Find the middle point 
        int m = ( l + r) / 2; 
        // Sort first and second halves 
        if( checkCollision(movableObj, staticObj, l, m) == false) { 
        	return checkCollision(movableObj, staticObj , m + 1, r);
        }
        return false;
	}

	private ArrayList<GameObject> getGameMovableObjects() {
		return _gameMovableObjects;
	}

	private void setGameMovableObjects(ArrayList<GameObject> _gameMovableObjects) {
		this._gameMovableObjects = _gameMovableObjects;
	}

	private ArrayList<GameObject> getGameStaticObjects() {
		return _gameStaticObjects;
	}

	private void setGameStaticObjects(ArrayList<GameObject> _gameStaticObjects) {
		this._gameStaticObjects = _gameStaticObjects;
	}
}
