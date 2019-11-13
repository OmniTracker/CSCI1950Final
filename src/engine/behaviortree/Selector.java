package engine.behaviortree;

import java.util.ArrayList;

import engine.GameWorld;

/**
 * 
 * This class is used to hold all the sequence node and run them.
 * The selector will be responsible for start the run process sequence up.
 * 
 * @author rbaker2
 *
 */
public abstract class Selector {
	private ArrayList<Sequence> _sequenceList = null; 
	private String _sequenceName; // This mainly be used for debugging purposes. 
	
	private boolean DEBUG = false;
	
	protected Selector(String selectorName) {

		this.setSelectorName(selectorName);
		this.setSequenceList(new ArrayList<Sequence>());
	}
	
	protected Selector() {

		// this.setSelectorName(selectorName);
		this.setSequenceList(new ArrayList<Sequence>());
	}

	public boolean runSequence (GameWorld gameWorld) {
		int sequenceListCount = this.getSequenceList().size();
		
		if (DEBUG) 
		{
			System.out.print("Sequnce Count: " + sequenceListCount + "\n");			
		}
		
		for (int index = 0;  index < this.getSequenceList().size() ;index++) 
		{	
			if (DEBUG) 
			{
				System.out.print("Sequnce Name: " + this.getSequenceList().get(index).getSequenceName() + "\n");			
			}

			if (this.getSequenceList().get(index).runSequence(gameWorld) == false) 
			{
				return false;
			}
		}
		return true;
	} 

	public String getSelecterName() {
		return _sequenceName;
	}
	private void setSelectorName(String _sequenceName) {
		this._sequenceName = _sequenceName;
	}

	public ArrayList<Sequence> getSequenceList() {
		return _sequenceList;
	}

	private void setSequenceList(ArrayList<Sequence> _sequenceList) {
		this._sequenceList = _sequenceList;
	}
}
