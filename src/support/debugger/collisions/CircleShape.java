package support.debugger.collisions;

import support.Vec2d;
import support.debugger.support.shapes.Shape;

public class CircleShape extends Shape {
	
	protected Vec2d center;
	protected float radius;
	
	public CircleShape(Vec2d center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	/////
	
	public Vec2d getCenter() {
		return center;
	}
	
	public void setCenter(Vec2d cent) {
		this.center = cent;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public void setRadius(double r) {
		this.radius = (float)r;
	}

	@Override
	public void move(Vec2d distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean atLeftEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atTopEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atRightEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atBottomEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bindToCanvas() {
		// TODO Auto-generated method stub
		
	}
	
}
