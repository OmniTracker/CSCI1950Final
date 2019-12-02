package support.debugger.collisions;


import support.Vec2d;
import support.debugger.support.shapes.Shape;
import java.util.ArrayList;


public class PolygonShape extends Shape {
	
	protected Vec2d[] points;
	
	public PolygonShape(Vec2d ... points) {
		this.points = points;
	}
	
	public int getNumPoints() {
		return points.length;
	}
	
	public Vec2d getPoint(int i) {
		return points[i];
	}

	@Override
	public void move(Vec2d distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vec2d getCenter() {
		// TODO Auto-generated method stub
		return null;
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

	public ArrayList<Vec2d> getSortedVertices() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
