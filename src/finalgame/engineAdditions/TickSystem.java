package finalgame.engineAdditions;

public class TickSystem extends GameSystem{
	
	public void onTick(long nanosSinceLastTick) {
		for(int i = 0; i<_objects.size();i++) {
			_objects.get(i).tick(nanosSinceLastTick);
		}
	}
	
	public void addObject(GameObject go) {
		if(go.hasComponent("TICK")) {
			_objects.add(go);
		}
		else if(go.hasComponent("PHYSICS")) {
			_objects.add(go);
		}
	}
}
