package support.collision;

import support.Vec2d;

public class AABShape extends Shape {
	
	public Vec2d topLeft;
	public Vec2d size;

	public AABShape(Vec2d topLeft, Vec2d size) {
		this.topLeft = topLeft;
		this.size = size;
	}
	
	/////
	
	public Vec2d getTopLeft() {
		return topLeft;
	}
	
	public Vec2d getSize() {
		return size;
	}

}
