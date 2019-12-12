package finalgame.engineAdditions;

import support.Vec2d;
import support.debugger.collisions.AABShape;
import support.debugger.collisions.CircleShape;
import support.debugger.collisions.PolygonShape;

public abstract class CircleAbilityCollisionComponent extends AbilityCollisionComponent{

	protected CircleShape _circle;
	
	public CircleAbilityCollisionComponent(GameObject go, CircleShape shape, AnimateAbilityComponent ability,
			int numTargets) {
		super(go, shape, ability, numTargets);
		_circle = shape;
	}

	@Override
	public abstract void hit(GameObject go);

	@Override
	public Vec2d collide(GameObject go) {
		_circle.setRadius(_ability.getHitBoxDim().x);
		_circle.setCenter(_ability.getHitBoxLoc());
		CollisionComponent other = (CollisionComponent)go.getComponent("COLLISION");
		return other.collideWithSphere(_circle);
	}

	@Override
	public Vec2d collideWithSphere(CircleShape s1) {
		return this.colliding(s1,_circle);
	}

	@Override
	public Vec2d collideWithAAB(AABShape s1) {
		return this.colliding(s1, _circle);
	}

	@Override
	public Vec2d collideWithPolygon(PolygonShape s1) {
		return this.colliding(_circle, s1);
	}

}
