package finalgame.engineAdditions;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerInputSystem extends GameSystem{
	
	ArrayList<PlayerInputComponent> _components;
	public PlayerInputSystem() {
		super();
		_components = new ArrayList<PlayerInputComponent>();
	}
	
	public void onTick(long nanosSinceLastTick) {
		for(int i = 0; i<_components.size();i++) {
			_components.get(i).tick(nanosSinceLastTick);
		}
	}
	
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("INPUT")) {
			_objects.add(go);
			_components.add((PlayerInputComponent) go.getComponent("INPUT"));
		}
	}

	
	public void onInput(HashMap<String, Double> input) {
		for(int i=0; i<_components.size();i++) {
			_components.get(i).setInput(input);
		}
	}
}
