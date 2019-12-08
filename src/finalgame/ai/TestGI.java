package finalgame.ai;

import finalgame.engineAdditions.GameObject;
import engine.ai.GroupInformation;

public class TestGI extends GroupInformation {

	private GameObject _leader;
	
	public TestGI() {
		super();	
	}
	
	public void setLeader(GameObject o) {
		if (!_group.contains(o)) {
			_group.add(o);
		}
		
		_leader = o;
	}
	
	public boolean isLeader(GameObject o) {
		if (o.equals(_leader)) {
			return true;
		} else {
			return false;
		}
	}
	
	public GameObject getLeader() {
		return _leader;
	}
	
}
