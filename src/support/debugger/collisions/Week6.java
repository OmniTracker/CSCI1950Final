package support.debugger.collisions;

import support.debugger.support.Vec2f;
import support.debugger.support.interfaces.Week6Reqs;

public final class Week6 extends Week6Reqs {
	
	Week2 _collider = new Week2();
	// AXIS-ALIGNED BOXES
	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		return _collider.collision(s1, s2);
	}
	@Override
	public Vec2f collision(AABShape s1, CircleShape s2) {
		return _collider.collision(s1, s2);
	}
	@Override
	public Vec2f collision(AABShape s1, Vec2f s2) {
		return _collider.collision(s1, s2);
	}
	// CIRCLES
	@Override
	public Vec2f collision(CircleShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}
	@Override
	public Vec2f collision(CircleShape s1, CircleShape s2) {
		return _collider.collision(s1, s2);
	}
	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		return _collider.collision(s1, s2);
	}
	@Override
	public Vec2f collision(PolygonShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}
	@Override
	public Vec2f collision(PolygonShape s1, CircleShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}
	Week5 _collider5 = new Week5();
	@Override
	public Vec2f collision(PolygonShape s1, Vec2f s2) {
		return _collider5.collision(s1, s2);
	}
	@Override
	public Vec2f collision(PolygonShape s1, PolygonShape s2) {
		return _collider5.collision(s1, s2);
	}
	@Override
	public Vec2f collision(AABShape s1, PolygonShape s2) {
		return _collider5.collision(s1, s2);
	}
	@Override
	public Vec2f collision(CircleShape s1, PolygonShape s2) {
		return _collider5.collision(s1, s2);
	}
	// POLYGONS - FILL IN THE REST
	
	
	// RAYCASTING
	
	@Override
	public float raycast(AABShape s1, Ray s2) {
		// For polygons and AABs, intersect each edge and
		// use the closest
		
		return -1;
	}
	
	@Override
	public float raycast(CircleShape s1, Ray s2) {
		
		// First project center onto ray. This value is used in
		// every calculation.
		Vec2f projection = s1.center.projectOnto(s2.src); 
		
		
		if (_collider.isColliding(s1, projection) == false) {
			// The projection point is not within the circle.
			return 0;
		}
		
		
		if (_collider.isColliding(s1, s2.src) == false) {
			// The source point is within the circle.
			// So there will be a value that comes from this
			
			return -1; 
		}
		
		
		// Everything below will assume the ray is out side the circle
		
		
		if (_collider.isColliding(s1,projection) == false) {
			// Projection is not within the circle
			
		
			// Check if the projection point is within the circle	
		}
		
		
		return -1;
	}
	
	@Override
	public float raycast(PolygonShape s1, Ray s2) {
		// For polygons and AABs, intersect each edge and
		// use the closest
		
		
		return -1;
	}

}
