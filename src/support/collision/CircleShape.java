package support.collision;

import support.Vec2d;

public class CircleShape extends Shape {
	
	Vec2d center;
	public double radius;
	
	public CircleShape(Vec2d center, double radius) {
		this.setCenter(center);
		this.radius = radius;
	}
		
	/////
	
	public Vec2d getCenter() {
		return center;
	}

	public void setCenter(Vec2d center) {
		this.center = center;
	}
	
	public double getRadius() {
		return radius;
	}

	public void move(Vec2d distance) {
		// TODO Auto-generated method stub
		
	}

	public boolean atLeftEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean atTopEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean atRightEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean atBottomEdge() {
		// TODO Auto-generated method stub
		return false;
	}

	public void bindToCanvas() {
		// TODO Auto-generated method stub
		
	}
	
}
