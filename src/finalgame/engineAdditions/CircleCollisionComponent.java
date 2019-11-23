package finalgame.engineAdditions;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.CircleShape;
import support.collision.PolygonShape;

public class CircleCollisionComponent extends CollisionComponent{
	
	private CircleShape _circle;
	
	public CircleCollisionComponent(GameObject go, CircleShape shape) {
		super(go, shape);
		// TODO Auto-generated constructor stub
		_circle = shape;
	}
	
	@Override 
	public Vec2d collide(GameObject o) {
		CollisionComponent other = (CollisionComponent)o.getComponent("COLLISION");
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
