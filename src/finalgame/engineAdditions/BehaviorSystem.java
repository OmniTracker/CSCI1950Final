package finalgame.engineAdditions;

public class BehaviorSystem extends GameSystem {

	private int counter = 0;
	
	public BehaviorSystem() {
		super();
	}
	
	public void onTick(long nanosSinceLastTick) {
		counter++;
		if (counter%5==0) {
			for(int i = 0; i<_objects.size();i++) {
				AIBehaviorComponent ai = (AIBehaviorComponent) _objects.get(i).getComponent("BEHAVIOR");
				ai.tick(nanosSinceLastTick);
			}
		}
	}
	
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("BEHAVIOR")) {
			_objects.add(go);
		}
	}

}
