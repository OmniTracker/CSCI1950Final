package nin.behaviortree.conditions;

import nin.behaviortree.NinBehaviorSequence;
import engine.ai.behaviortree.Conditions;
import engine.gameobject.GameObject;

public class JumpSignaled extends NinBehaviorSequence  implements Conditions  {
	public boolean _jumpSignaled = false;
	private boolean _saveJumpContex;
	
	private GameObject _mainCharacter;
	
	public JumpSignaled(GameObject mainCharacter) {
		this.setBehaviorType("Condition");
		this.setMainCharacter(mainCharacter);
	}
	
	public boolean check () {
		return ConditionSatisfied();
	}

	@Override
	public boolean ConditionSatisfied() {
		if (this.isJumpSignaled() == true) {
			this.setSaveJumpContex(true);
		}
		return this.isSaveJumpContex();
	}

	private boolean isJumpSignaled() {
		return _jumpSignaled;
	}

	public void setJumpSignaled(boolean _jumpSignaled) {
		this._jumpSignaled = _jumpSignaled;
	}

	GameObject getMainCharacter() {
		return _mainCharacter;
	}

	void setMainCharacter(GameObject _mainCharacter) {
		this._mainCharacter = _mainCharacter;
	}

	public boolean isSaveJumpContex() {
		return _saveJumpContex;
	}

	public void setSaveJumpContex(boolean _saveJumpContex) {
		this._saveJumpContex = _saveJumpContex;
	}
}
