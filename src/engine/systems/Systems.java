package engine.systems;

import java.util.ArrayList;
import engine.gameobject.GameObject;

public class Systems {
	private ArrayList<GameObject> _gameObjects; 
	private String _systemName;
	
	protected Systems() {
		this.setGameObjects(new ArrayList<GameObject>());
	}
	
	public void run() {
		
	}
	
	public void sortGameObjects (ArrayList<GameObject> _gameObjects) {
		
	}
	
	public void addGameObject(GameObject gameObject) {
		this.getGameObjects().add(gameObject);
	}

	private ArrayList<GameObject> getGameObjects() {
		return _gameObjects;
	}

	private void setGameObjects(ArrayList<GameObject> _gameObjects) {
		this._gameObjects = _gameObjects;
	}

	public String getSystemName() {
		return _systemName;
	}

	public void setSystemName(String _systemName) {
		this._systemName = _systemName;
	}
}
