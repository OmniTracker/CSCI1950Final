package finalgame.engineAdditions;

import java.util.ArrayList;
import javafx.scene.transform.Affine;

public abstract class GameSystem {
	protected ArrayList<GameObject> _objects;
	
	public GameSystem() {
		_objects = new ArrayList<GameObject>();
	}
	
	public abstract void addObject(GameObject go);
	public void removeObject(GameObject go) {
		_objects.remove(go);
	}
}
