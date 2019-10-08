package engine.utility;

import support.Vec2d;

public class AspectRatioHandler {
	private double _aspectRatio;
	private Vec2d _initialScreenSize;
	private Vec2d _currentScreenSize;
	public AspectRatioHandler(Vec2d init) {
		this.setInitialScreenSize(init);
		this.setCurrentScreenSize(init);
	}
	public Vec2d calculateUpdatedOrigin() {
		Vec2d updatedScreenSize = this.calculateUpdatedScreenSize(); 
		double y = 0.0;
		double x = 0.0;
		double xDiff = this.getCurrentScreenSize().x - updatedScreenSize.x; 
		double yDiff = this.getCurrentScreenSize().y - updatedScreenSize.y; 
		if (xDiff > yDiff) {
			x = ( ( this.getCurrentScreenSize().x - updatedScreenSize.x ) / 2.0);
		} else {
			y = ( ( this.getCurrentScreenSize().y - updatedScreenSize.y ) / 2.0);
		}
		if (xDiff < 10.0) {
			x = 0.0;
		}
		return new Vec2d(x,y);
	}
	public Vec2d calculateUpdatedScreenSize() {
		double y = this.getCurrentScreenSize().y;
		double x = y * this.getAspectRatio();
		double xDiff = this.getCurrentScreenSize().x - this.getInitialScreenSize().x; 
		double yDiff = this.getCurrentScreenSize().y - this.getInitialScreenSize().y; 
		if (xDiff < yDiff) {	
			x  = this.getCurrentScreenSize().x; 
			y = (x / this.getAspectRatio()); 
		}
		return new Vec2d(x,y);
	}
	private void calculateAspectRatio () {
		this.setAspectRatio( ( this.getInitialScreenSize().x / this.getInitialScreenSize().y ) ); 
	}
	public double getAspectRatio() {
		return _aspectRatio;
	}
	public void setAspectRatio(double _aspectRatio) {
		this._aspectRatio = _aspectRatio;
	}
	public Vec2d getInitialScreenSize() {
		return _initialScreenSize;
	}
	public void setInitialScreenSize(Vec2d _initialScreenSize) {
		this._initialScreenSize = _initialScreenSize;
		this.calculateAspectRatio();
	}
	public Vec2d getCurrentScreenSize() {
		return _currentScreenSize;
	}
	public void setCurrentScreenSize(Vec2d _currentScreenSize) {
		this._currentScreenSize = _currentScreenSize;
	}
}