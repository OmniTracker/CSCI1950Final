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
	@Override
	public Vec2d getCenter() {
		// TODO Auto-generated method stub
		return null;
	}
}
