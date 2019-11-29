package nin.behaviortree.conditions;

import java.util.ArrayList;
import java.util.Iterator;

import support.collision.Collision;
import nin.behaviortree.NinBehaviorSequence;
import engine.ai.behaviortree.Conditions;
import engine.gameobject.GameObject;

public class IsColliding extends NinBehaviorSequence  implements Conditions  {
	private GameObject _character; 
	private ArrayList<GameObject> _otherCharacter; 
	
	public IsColliding(GameObject character) {
		this.setBehaviorType("Condition");
		this.setCharacter(character);
		this.setOtherCharacter(new ArrayList<GameObject>());
	}
		
	public boolean verifyCondition() {
		Iterator<GameObject> collider = _otherCharacter.iterator();
        while (collider.hasNext()) {
        	GameObject other = collider.next();        	
        	if ( Collision.isColliding(_character.getData().getBox(), other.getData().getBox()) ) {
        		
        		return true;
        	}
        }		
		return false;
	}	
	
	public boolean check () {
		return verifyCondition();
	}

	@Override
	public boolean ConditionSatisfied() {
		// TODO Auto-generated method stub
		return false;
	}

	GameObject getCharacter() {
		return _character;
	}

	void setCharacter(GameObject _character) {
		this._character = _character;
	}

	public ArrayList<GameObject> getOtherCharacter() {
		return _otherCharacter;
	}

	private void setOtherCharacter(ArrayList<GameObject> _otherCharacter) {
		this._otherCharacter = _otherCharacter;
	}
}
