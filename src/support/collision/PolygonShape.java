package support.collision;

import java.util.ArrayList;
import java.util.Collections;

import support.Vec2d;

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
	
	public ArrayList<Vec2d> getSortedVertices() {
		ArrayList<Vec2d> sortedVerts = new ArrayList<Vec2d>();
		
		Vec2d centroid = this.getCenter();
		
		for(int i =0; i < points.length; i++) {
			sortedVerts.add(points[i]);
		}
		
		Collections.sort(sortedVerts, (a, b) -> {
	        double a1 = (Math.toDegrees(Math.atan2(a.x - centroid.x, a.y - centroid.y)) + 360) % 360;
	        double a2 = (Math.toDegrees(Math.atan2(b.x - centroid.x, b.y - centroid.y)) + 360) % 360;
	        return (int) (a1-a2);
	    });
		return sortedVerts;
	}
	
	public Vec2d getCenter() {
		Vec2d sum = new Vec2d(0,0);
		for(int i =0; i < points.length; i++) {
			sum = sum.plus(points[i]);
		}
		return sum.sdiv(points.length);
	}
}
