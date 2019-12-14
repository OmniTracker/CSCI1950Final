package finalgame.engineAdditions;

import support.Vec2d;
import support.debugger.collisions.AABShape;
import support.debugger.collisions.CircleShape;
import support.debugger.collisions.PolygonShape;
import support.debugger.support.shapes.Shape;

public class AABAbilityCollisionComponent extends AbilityCollisionComponent{

	protected AABShape _aab;
	
	public AABAbilityCollisionComponent(GameObject go, AABShape shape, AnimateAbilityComponent ability, int numTargets) {
		super(go, shape, ability, numTargets);
		_aab = shape;
	}

	@Override 
	public Vec2d collide(GameObject o) {
		_aab.setSize(_ability.getHitBoxDim());
		_aab.setTopLeft(_ability.getHitBoxLoc());
		CollisionComponent other = (CollisionComponent)o.getComponent("COLLISION");
		return other.collideWithAAB(_aab);
	}

	@Override
	public Vec2d collideWithSphere(CircleShape s1) {
		return this.colliding(_aab, s1);
	}

	@Override
	public Vec2d collideWithAAB(AABShape s1) {
		return this.colliding(s1, _aab);
	}

	@Override
	public Vec2d collideWithPolygon(PolygonShape s1) {
		return this.colliding(_aab, s1);
	}

	@Override
	public int getType() {
		return 3;
	}
	
}
