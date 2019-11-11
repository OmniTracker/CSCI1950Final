package engine.behaviortree;

import java.util.ArrayList;

import engine.GameWorld;
import engine.gameobject.GameObject;

/**
 * 
 * This class is used to extend functionality need for each of the action and condition
 * nodes.
 * 
 * @author rbaker2
 */
public abstract class SequenceNode {
	private String _sequenceNodeName;
	private String _sequenceNodeOperation;
	// Object in which the node is connected to.
	public GameObject _primaryGameObject;
	// Any Objects the current player need to interact with.
	private ArrayList<GameObject> _secondaryGameObjects;
	public SequenceNode(String sequenceNodeName, 
			String sequenceNodeOperation, 
			GameObject primaryGameObject, 
			GameObject secondaryGameObject) {
		this.setSequenceNodeName(sequenceNodeName);
		this.setSequenceNodeOperation(sequenceNodeOperation);
		this.setPrimaryGameObject(primaryGameObject);	
		this.setSecondaryGameObjects(new ArrayList<GameObject>( ));
		this.getSecondaryGameObjects().add(secondaryGameObject); 
	}
	public boolean runSequenceNode(GameWorld gameWorld) {
		
		return false;
	}
	public String getSequenceNodeName() {
		return this._sequenceNodeName;
	}
	private void setSequenceNodeName(String _sequenceNodeName) {
		this._sequenceNodeName = _sequenceNodeName;
	}
	public String getSequenceNodeOperation() {
		return _sequenceNodeOperation;
	}
	private void setSequenceNodeOperation(String _sequenceNodeOperation) {
		this._sequenceNodeOperation = _sequenceNodeOperation;
	}
	public GameObject getPrimaryGameObject() {
		return _primaryGameObject;
	}
	public  void setPrimaryGameObject(GameObject _primaryGameObject) {
		this._primaryGameObject = _primaryGameObject;
	}
	public ArrayList<GameObject> getSecondaryGameObjects() {
		return _secondaryGameObjects;
	}
	private void setSecondaryGameObjects(ArrayList<GameObject> _secondaryGameObjects) {
		this._secondaryGameObjects = _secondaryGameObjects;
	}
}
