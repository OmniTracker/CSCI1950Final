package finalgame.engineAdditions;

import engine.GameWorld;
import support.Vec2d;

public class CollisionSystem extends GameSystem{
	
	public CollisionSystem(GameWorld gw) {
		super();
	}
	
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("COLLISION")) {
			_objects.add(go);
		}
	}
	
	public void onTick(float nanos) {
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
		if(go1.getName().contains("ENEMY") && go2.getName().equals("PLAYER")) {
			TransformComponent m1 = (TransformComponent) go1.getComponent("TRANSFORM");
			m1.move(mtv.sdiv(1));
		} else if (go1.getName().contains("PLAYER") && go2.getName().contains("ENEMY")) {
			TransformComponent m1 = (TransformComponent) go1.getComponent("TRANSFORM");
			m1.move(mtv.sdiv(1));
		} else if(go1.getName().equals("ABILITY") && go2.getName().contains("ENEMY")) {
			AbilityCollisionComponent curr = (AbilityCollisionComponent) go1.getComponent("COLLISION");
			curr.hit(go2);
		} else if (go1.getName().contains("ENEMY") && go2.getName().contains("ENEMY")) {
			TransformComponent m1 = (TransformComponent) go1.getComponent("TRANSFORM");
			m1.move(mtv.sdiv(1));
		} else if (go1.getName().equals("ENABILITY") && go2.getName().contains("PLAYER")) {
			AbilityCollisionComponent curr = (AbilityCollisionComponent) go1.getComponent("COLLISION");
			curr.hit(go2);
		} 
	}
}
