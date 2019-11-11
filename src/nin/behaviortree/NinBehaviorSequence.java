package nin.behaviortree;

public class NinBehaviorSequence {	
	
	private String _behaviorType;
	
	protected NinBehaviorSequence() {}
	public boolean check () {
		return false; 
	}
	public String getBehaviorType() {
		return _behaviorType;
	}
	public void setBehaviorType(String _behaviorType) {
		this._behaviorType = _behaviorType;
	}

}
