package support.collision;

import support.Vec2d;

public class AABShape extends Shape {
	
	Vec2d topLeft;
	public Vec2d size;

	public AABShape(Vec2d topLeft, Vec2d size) {
		this.setTopLeft(topLeft);
		this.size = size;
	}
	/////
	public Vec2d getTopLeft() {
		return topLeft;
	}
	public Vec2d getSize() {
		return size;
	}
	public void setTopLeft(Vec2d topLeft) {
		this.topLeft = topLeft;
	}
}
