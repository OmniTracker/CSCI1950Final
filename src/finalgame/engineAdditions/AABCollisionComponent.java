package finalgame.engineAdditions;


import support.debugger.collisions.AABShape;
import support.Vec2d;
import support.debugger.collisions.CircleShape;
import support.debugger.collisions.PolygonShape;

public class AABCollisionComponent extends CollisionComponent{

	protected AABShape _aab;
	
	public AABCollisionComponent(GameObject go, AABShape shape) {
		super(go, shape);
		_aab = shape;
	}
	
	@Override 
	public Vec2d collide(GameObject o) {
		TransformComponent curr = (TransformComponent)_go.getComponent("TRANSFORM");
		_aab.setSize(new Vec2d(curr.getDim().x, curr.getDim().y));
		_aab.setTopLeft(new Vec2d(curr.getLoc().x, curr.getLoc().y));
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
		return 1;
	}


}
