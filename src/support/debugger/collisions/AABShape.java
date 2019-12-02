package support.debugger.collisions;

import support.Vec2d;
import support.debugger.support.shapes.Shape;

public abstract class AABShape extends Shape {
	
	public Vec2d topLeft;
	protected Vec2d size;

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
	
	public void setSize(Vec2d s) {
		size = s;
	}
	public void setTopLeft(Vec2d topLeft) {
		this.topLeft = topLeft;
	}
	
	public Vec2d getPoint(int i) {
		// TODO Auto-generated method stub
		switch(i) {
			case 0:
				return this.getTopLeft();
			case 1:
				return this.getTopLeft().plus(this.getSize().pmult(new Vec2d(0,1)));
			case 2:
				return this.getTopLeft().plus(this.getSize());
			case 3:
				return this.getTopLeft().plus(this.getSize().pmult(new Vec2d(1,0)));
			default:
				return null;
		}
	}

}
