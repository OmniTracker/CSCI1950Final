package engine.behaviortree;

import java.util.ArrayList;

public abstract class BehaviorTree {
	
	private ArrayList<Selector> _selectorList; 
	
	protected BehaviorTree() {
		this.setSelectorList( new ArrayList<Selector>());
		
	}

	public ArrayList<Selector> getSelectorList() {
		return _selectorList;
	}

	private void setSelectorList(ArrayList<Selector> _selectorList) {
		this._selectorList = _selectorList;
	}
}
