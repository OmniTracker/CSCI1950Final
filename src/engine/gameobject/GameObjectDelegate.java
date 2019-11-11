package engine.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import engine.Application;
import engine.utility.Factory;

public class GameObjectDelegate {
	private Application _app; 
	private ArrayList<GameObject> _objects; 
	private Factory _factory;
	private HashMap<String ,GameObject> _objslevelO;
	private HashMap<String ,GameObject> _objslevel1;
	public GameObjectDelegate(Application app) {
		this.setObjects(new ArrayList<GameObject>());
		this.setFactory(new Factory());
	}
	ArrayList<GameObject> getObjects() {
		return _objects;
	}
	void setObjects(ArrayList<GameObject> _objects) {
		this._objects = _objects;
	}
	void addObject ( GameObject obj ) {
		this.getObjects().add(obj); 
	}
	public Factory getFactory() {
		return _factory;
	}
	private void setFactory(Factory _factory) {
		this._factory = _factory;
	}
	public Application getApp() {
		return _app;
	}
	public void setApp(Application _app) {
		this._app = _app;
	}
	public HashMap<String ,GameObject> getObjsLevelO() {
		return _objslevelO;
	}
	protected void setObjsLevelO(HashMap<String ,GameObject> _objslevelO) {
		this._objslevelO = _objslevelO;
	}
	public HashMap<String ,GameObject> getObjsLevel1() {
		return _objslevel1;
	}
	protected void setObjsLevel1(HashMap<String ,GameObject> _objslevel1) {
		this._objslevel1 = _objslevel1;
	}
}
