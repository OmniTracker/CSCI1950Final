package finalgame.engineAdditions;

import java.util.ArrayList;

public class BehaviorSystem extends GameSystem{

	private ArrayList<AIBehaviorComponent> _components;
	private int counter = 0;
	
	public BehaviorSystem() {
		super();
		_components = new ArrayList<AIBehaviorComponent>();
	}
	
	public void onTick(long nanosSinceLastTick) {
		counter++;
		if (counter%5==0) {
			for(int i = 0; i<_components.size();i++) {
				_components.get(i).tick(nanosSinceLastTick);
			}
		}
	}
	
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("BEHAVIOR")) {
			_objects.add(go);
			_components.add((AIBehaviorComponent) go.getComponent("BEHAVIOR"));
		}
	}

}
