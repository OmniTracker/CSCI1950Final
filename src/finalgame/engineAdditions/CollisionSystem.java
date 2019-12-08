package finalgame.engineAdditions;

import engine.GameWorld;
import support.Vec2d;

public class CollisionSystem extends GameSystem{

	private GameWorld _gw;
	
	public CollisionSystem(GameWorld gw) {
		super();
		_gw = gw;
	}
	
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("COLLISION")) {
			_objects.add(go);
		}
	}
	
	public void onTick(float nanos) {
		boolean ground = false;
		for(int i = 0; i < _objects.size();i++) {
			for(int j = 0; j < _objects.size(); j++) {
				if(i==j) {
					continue;
				}
				Vec2d temp = _objects.get(i).isColliding(_objects.get(j));
				if(temp!=null) {
					this.collidePair(_objects.get(i), _objects.get(j), temp);
				}
			}
		}
	}
	
	private void collidePair(GameObject go1, GameObject go2, Vec2d mtv) {
		TransformComponent m1 = (TransformComponent) go1.getComponent("TRANSFORM");
		TransformComponent m2 = (TransformComponent) go2.getComponent("TRANSFORM");
		m1.move(mtv.sdiv(1));
		//m2.move(mtv.sdiv(-2));
	}
}
