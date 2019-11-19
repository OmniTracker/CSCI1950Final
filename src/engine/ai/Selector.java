package engine.finalai;

public class Selector extends Composite {

	private BTNode last;
	
	@Override
	public Status update(float seconds) {
		for (BTNode n: _children) {
			last = n;
			Status update = n.update(seconds);
			if (update.equals(Status.SUCCESS)) {
				return Status.SUCCESS;
			} else if (update.equals(Status.RUNNING)) {
				return Status.RUNNING;
			}
		}
		return Status.FAILURE;
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
