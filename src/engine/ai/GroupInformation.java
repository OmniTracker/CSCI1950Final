package engine.ai;

import java.util.ArrayList;

import finalgame.engineAdditions.GameObject;

public class GroupInformation {

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
	
	// Check conditions on each object.
	public void tick(long nanos) {
		return;
	}
	
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
