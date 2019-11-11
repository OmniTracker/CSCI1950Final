package engine.behaviortree;

import java.util.ArrayList;

import engine.GameWorld;

/**
 *	Class used to generate all the boiler plate for any 
 *	class that extends this class. 
 *
 * @author rbaker2
 *
 */
public abstract class Sequence {
	private ArrayList <SequenceNode> _sequenceNodes;
	private String _sequenceName;
	private boolean DEBUG = false;
	protected Sequence(String sequenceName) {
		this.setSequenceNodes(new ArrayList <SequenceNode>());	
		this.setSequenceName(sequenceName);
	}
	
	public boolean runSequence (GameWorld gameWorld)  {	
		int sequenceNodeCount = this.getSequenceNodes().size();
		if (DEBUG == true) {
			System.out.print("Sequence Name: " + _sequenceName + " Sequence Count: " + sequenceNodeCount + "\n" );
		}
		// Check to see if the user have anything to iterate over
		if (this.getSequenceNodes().isEmpty()) {
			return false;
		} 
		for (int squenceNodeIndex = 0; squenceNodeIndex < this.getSequenceNodes().size(); squenceNodeIndex++) {	
			if (this.getSequenceNodes().get(squenceNodeIndex).runSequenceNode(gameWorld) == false) {
				return false; 
			}		
		}
		return true;
	}
	public ArrayList <SequenceNode> getSequenceNodes() {
		return _sequenceNodes;
	}
	private void setSequenceNodes(ArrayList <SequenceNode> _sequenceNodes) {
		this._sequenceNodes = _sequenceNodes;
	}
	public String getSequenceName() {
		return _sequenceName;
	}
	private void setSequenceName(String _sequenceName) {
		this._sequenceName = _sequenceName;
	}
}
