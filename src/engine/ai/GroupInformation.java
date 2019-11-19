package engine.finalai;

import java.util.ArrayList;
import engine.gameobject.GameObject;

public abstract class GroupInformation {

	public ArrayList<GameObject> _group;
	// Flags are used to fork conditions in the Behavior Tree. Can be used for intragroup communication.
	public ArrayList<String> _flags;
	
	public GroupInformation() {
		_group = new ArrayList<GameObject>();
		_flags = new ArrayList<String>();
	}
	
	public GroupInformation(GameObject ... gameObjects) {
		_group = new ArrayList<GameObject>();
		for (GameObject obj: gameObjects) {
			_group.add(obj);
		}
		_flags = new ArrayList<String>();
	}
	
	// Check conditions on each object and update flags.
	public abstract void tick(long nanos);
	
	public void addObjects(GameObject ... gameObjects) {
		for (GameObject obj: gameObjects) {
			_group.add(obj);
		}
	}
	
	public void removeObject(GameObject obj) {
		_group.remove(obj);
	}
	
	public void addFlag(String flag) {
		_flags.add(flag);
	}
	
	public void removeFlag(String flag) {
		_flags.remove(flag);
	}

}
