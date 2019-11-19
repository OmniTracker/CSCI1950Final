package engine.finalai;

public class Sequencer extends Composite {

	private BTNode last;
	
	@Override
	public Status update(float seconds) {
		for (BTNode n: _children) {
			last = n;
			Status update = n.update(seconds);
			if (update.equals(Status.FAILURE)) {
				
				return Status.FAILURE;
			} else if (update.equals(Status.RUNNING)) {
				return Status.RUNNING;
			}
		}
		return Status.SUCCESS;
	}

	@Override
	public BTNode getLastRunning() {
		return last;
	}
	
	@Override
	public void addChildren(BTNode node) {
		_children.add(node);
	}

}
