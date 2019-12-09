package finalgame.engineAdditions;

public class TickSystem extends GameSystem{
	
	public void onTick(long nanosSinceLastTick) {
		for(int i = 0; i<_objects.size();i++) {
			_objects.get(i).tick(nanosSinceLastTick);
			System.out.println("ADDED" + _objects.get(i).getName());
		}
	}
	
	public void addObject(GameObject go) {
		if(go.hasComponent("TICK")) {
			_objects.add(go);
		}
		if(go.hasComponent("PHYSICS")) {
			_objects.add(go);
		}
		if(go.hasComponent("ABILITY_E")) {
			System.out.println("ADDED" + go.getName());
			_objects.add(go);
		}
	}
}
