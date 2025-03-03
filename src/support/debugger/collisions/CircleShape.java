package support.debugger.collisions;

import support.debugger.support.Vec2f;
import support.debugger.support.shapes.Shape;

public abstract class CircleShape extends Shape {
	
	protected Vec2f center;
	protected float radius;
	
	public CircleShape(Vec2f center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	/////
	
	public Vec2f getCenter() {
		return center;
	}
	
	public float getRadius() {
		return radius;
	}
	
}
