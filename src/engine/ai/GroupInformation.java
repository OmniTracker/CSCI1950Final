package engine.ai;

import java.util.ArrayList;

import finalgame.engineAdditions.GameObject;

public abstract class GroupInformation {

	public ArrayList<GameObject> _group;
	// Flags are used to fork conditions in the Behavior Tree. Can be used for intragroup communication.
	public ArrayList<Boolean> _flags;
	
	public GroupInformation() {
		_group = new ArrayList<GameObject>();
	}
	
	public GroupInformation(GameObject ... gameObjects) {
		_group = new ArrayList<GameObject>();
		for (GameObject obj: gameObjects) {
			_group.add(obj);
		}
		_flags = new ArrayList<Boolean>();
	}
	
	// Check conditions on each object.
	public abstract void tick(long nanos);
	
	public void addObjects(GameObject ... gameObjects) {
		for (GameObject obj: gameObjects) {
			_group.add(obj);
		}
	}
	
	public void addObject(GameObject o) {
		_group.add(o);
	}
	
	public void removeObject(GameObject obj) {
		_group.remove(obj);
	}
	
	public void addFlag(boolean flag) {
		_flags.add(flag);
	}
	
	public void removeFlag(boolean flag) {
		_flags.remove(flag);
	}

}
