package wizard.behaviortree.selector;

import java.util.ArrayList;

import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.behaviortree.Selector;

public class WIZSelector extends  Selector {
	private ArrayList<ArrayList<WIZBehaviorSequence>> _sequence;
	public WIZSelector(String selectorName) {
		super(selectorName);
		this.setSequence(new ArrayList<ArrayList<WIZBehaviorSequence>>());
	}
	public ArrayList<ArrayList<WIZBehaviorSequence>> getSequence() {
		return _sequence;
	}
	public void setSequence(ArrayList<ArrayList<WIZBehaviorSequence>> _sequence) {
		this._sequence = _sequence;
	}

}
