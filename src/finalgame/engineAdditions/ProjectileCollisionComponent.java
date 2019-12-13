package finalgame.engineAdditions;

import support.Vec2d;
import support.debugger.collisions.CircleShape;

public class ProjectileCollisionComponent extends CircleCollisionComponent{

	private MouseAbilityAnimationComponent _ability;
	
	public ProjectileCollisionComponent(GameObject go, CircleShape shape,MouseAbilityAnimationComponent ability) {
		super(go, shape);
		_ability = ability;
	}
	
	@Override 
	public Vec2d collide(GameObject o) {
		_circle.setRadius(_ability.getHitBoxDim().x);
		_circle.setCenter(_ability.getHitBoxLoc());
		CollisionComponent other = (CollisionComponent)o.getComponent("COLLISION");
		Vec2d temp = other.collideWithSphere(_circle);
		return other.collideWithSphere(_circle);
	}
	
	public void hit(GameObject hitObject) {
		_ability.onHit(hitObject);
	}
}
