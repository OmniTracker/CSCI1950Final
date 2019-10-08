package engine.utility;

import engine.gameobject.GameObject;
import support.Vec2d;
import support.collision.AABShape;

public class DrawBox extends GameObject {
	private Vec2d _origin; 
	private Vec2d _size;
	private AABShape _box;
	
	public DrawBox () {
		super(); 
		
	}
	
	public DrawBox ( Vec2d origin, Vec2d size) {
		this.setOrigin(origin);
		this.setSize(size);
		this.setBox( new AABShape(origin,size));
	}
	
	public AABShape getBox() {
		return _box;
	}
	public void setBox(AABShape _box) {
		this._box = _box;
	}
	public Vec2d getOrigin() {
		return _origin;
	}
	public void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}
	public Vec2d getSize() {
		return _size;
	}
	public void setSize(Vec2d _size) {
		this._size = _size;
	}
}
