package support.debugger.support.interfaces;

import support.debugger.collisions.AABShape;
import support.debugger.collisions.CircleShape;
import support.debugger.collisions.PolygonShape;
import support.debugger.collisions.Ray;
import support.debugger.support.Vec2f;

public abstract class Week2Reqs implements CollisionFunctions {
	
	public abstract boolean isColliding(AABShape s1, AABShape s2);
	public abstract boolean isColliding(AABShape s1, CircleShape s2);
	public abstract boolean isColliding(AABShape s1, Vec2f s2);
	
	public abstract boolean isColliding(CircleShape s1, AABShape s2);
	public abstract boolean isColliding(CircleShape s1, CircleShape s2);
	public abstract boolean isColliding(CircleShape s1, Vec2f s2);
	
	public final Vec2f collision(AABShape s1, AABShape s2) {
		return isColliding(s1, s2) ? new Vec2f(0, 0) : null;
	}
	public final Vec2f collision(AABShape s1, CircleShape s2) {
		return isColliding(s1, s2) ? new Vec2f(0, 0) : null;
	}
	public final Vec2f collision(AABShape s1, Vec2f s2) {
		return isColliding(s1, s2) ? new Vec2f(0, 0) : null;
	}
	public final Vec2f collision(AABShape s1, PolygonShape s2) {
		
		System.out.print("dqioj iw\n");
		return null;
	}
	
	public final Vec2f collision(CircleShape s1, AABShape s2) {
		return isColliding(s1, s2) ? new Vec2f(0, 0) : null;
	}
	public final Vec2f collision(CircleShape s1, CircleShape s2) {
		return isColliding(s1, s2) ? new Vec2f(0, 0) : null;
	}
	public final Vec2f collision(CircleShape s1, Vec2f s2) {
		return isColliding(s1, s2) ? new Vec2f(0, 0) : null;
	}
	public final Vec2f collision(CircleShape s1, PolygonShape s2) {
		return null;
	}
	
	public final Vec2f collision(PolygonShape s1, AABShape s2) {
		return null;
	}
	public final Vec2f collision(PolygonShape s1, CircleShape s2) {
		return null;
	}
	public final Vec2f collision(PolygonShape s1, Vec2f s2) {
		return null;
	}
	public final Vec2f collision(PolygonShape s1, PolygonShape s2) {
		return null;
	}
	
	public final float raycast(AABShape s1, Ray s2) {
		return -1;
	}
	public final float raycast(CircleShape s1, Ray s2) {
		return -1;
	}
	public final float raycast(PolygonShape s1, Ray s2) {
		return -1;
	}

	

}
