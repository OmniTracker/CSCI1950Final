package wizard.behaviortree.sequenceNodes;

import engine.GameWorld;
import engine.gameobject.GameObject;

public class WIZBehaviorSequence {
	private String _behaviorType;
	private String _behaviorName;
	private GameObject _obj;
	private GameObject _TargetObj;
	private GameWorld gameWorld = null; 
	protected WIZBehaviorSequence(GameObject obj,GameObject targetObj) {
		this.setObj(obj);
		this.setTargetObj(targetObj);
	}
	public boolean run () {
		return false;
	}
	public boolean check () {
		return false; 
	}
	public String getBehaviorType() {
		return _behaviorType;
	}
	public void setBehaviorType(String _behaviorType) {
		this._behaviorType = _behaviorType;
	}
	public GameObject getObj() {
		return _obj;
	}
	public void setObj(GameObject _obj) {
		this._obj = _obj;
	}
	public  GameObject getTargetObj() {
		return _TargetObj;
	}
	public void setTargetObj(GameObject _TargetObj) {
		this._TargetObj = _TargetObj;
	}
	public String getBehaviorName() {
		return _behaviorName;
	}
	public void setBehaviorName(String _behaviorName) {
		this._behaviorName = _behaviorName;
	}
	public GameWorld getGameWorld() {
		return gameWorld;
	}
	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}
}
