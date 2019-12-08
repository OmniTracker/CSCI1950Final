package support.debugger.collisions;

import support.debugger.support.Vec2f;
import support.debugger.support.interfaces.Week6Reqs;

public final class Week6 extends Week6Reqs {
	Week2 week2 = new Week2();


	@Override
	public Vec2f collision(AABShape s1, CircleShape s2) {
		if ( week2.isColliding(s1, s2) == false) {
			return null;
		}
		float X1 = (s2.center.x) - (s1.topLeft.x);
		float X2 = (s2.center.x) - (s1.topLeft.x + s1.size.x);
		float Y1 = (s2.center.y) - (s1.topLeft.y ) ; 
		float Y2 = (s2.center.y) - (s1.topLeft.y + s1.size.y);
		float mtv_X1 = Math.abs(X1);
		float mtvX2  = Math.abs(X2);  
		float mtv_1Y = Math.abs(Y1);
		float mtv2Y  = Math.abs(Y2);
		// Inside AABShape
		if ((s1.topLeft.x < s2.center.x)  &&
				(s2.center.x < (s1.topLeft.x + s1.size.x)) &&
				(s1.topLeft.y < s2.center.y)  && 
				(s2.center.y < (s1.topLeft.y + s1.size.y))) {

			if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
				return new Vec2f(X1 + s2.radius,0);	
			}
			if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
				return new Vec2f(X2 - s2.radius,0);	
			}
			if ((mtv_1Y <=  mtv_X1) && (mtv_1Y <= mtvX2) && (mtv_1Y <= mtv2Y)) {
				return new Vec2f(0,Y1 + s2.radius);
			}
			if ((mtv2Y <= mtv_X1) && (mtv2Y <= mtvX2) && (mtv2Y <= mtv_1Y)) {
				return new Vec2f(0,Y2 - s2.radius);
			}
		} else {			
			// Outside
			if ((mtv_X1 <= mtvX2) && (mtv_X1 <= mtv_1Y) && (mtv_X1 <= mtv2Y)) {
				return new Vec2f(X1 + s2.radius,0);	
			}
			if ((mtvX2 <= mtv_X1) && (mtvX2 <= mtv_1Y) && (mtvX2 <= mtv2Y)) {
				return new Vec2f(X2 - s2.radius,0);	
			}
			if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
				return new Vec2f(0,Y1 + s2.radius);
			}
			if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
				return new Vec2f(0,Y2 - s2.radius);
			}
		}
		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, Vec2f s2) {
		if ( week2.isColliding(s1, s2) == false) {
			return null;
		}
		// In order for a point to collide with a AABShape, 
		// assume the point within the AABShape.
		float X1 = (s2.x + s2.x) - (s1.topLeft.x);
		float X2 =  (s2.x) - (s1.topLeft.x + s1.size.x);
		float Y1 = (s2.y + s2.y) - (s1.topLeft.y ) ; 
		float Y2 =   s2.y -(s1.topLeft.y + s1.size.y);
		float mtv_X1 = Math.abs(X1);
		float mtvX2  = Math.abs(X2);  
		float mtv_1Y = Math.abs(Y1);
		float mtv2Y  = Math.abs(Y2);
		if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
			return new Vec2f(X1,0);	
		}
		if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
			return new Vec2f( X2,0);	
		}
		if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
			return new Vec2f(0,Y1);
		}
		if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
			return new Vec2f(0,Y2);
		}
		return null;
	}

	// CIRCLES
	@Override
	public Vec2f collision(CircleShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}
	@Override
	public Vec2f collision(CircleShape s1, CircleShape s2) {	
		if ( week2.isColliding(s1, s2) == false) {
			return null;
		}
		float mtvLength = (s1.radius + s2.radius) - (s2.center.dist(s1.center));
		Vec2f coor = s2.center.minus(s1.center).normalize();	
		return coor.pmult(-1 * mtvLength,-1 * mtvLength);
	}

	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		if ( week2.isColliding(s1, s2) == false) {
			return null;
		}
		float mtvLength = (s1.radius) - (s1.center.dist(s2)); 
		Vec2f coor = s1.center.minus(s2).normalize();		
		return coor.pmult( -1 * mtvLength, -1 * mtvLength);
	}

	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		if ( week2.isColliding(s1, s2) == false) {
			return null;
		}
		float X1 = (s2.topLeft.x + s2.size.x) - (s1.topLeft.x);
		float X2 =  (s2.topLeft.x) - (s1.topLeft.x + s1.size.x);
		float Y1 = (s2.topLeft.y + s2.size.y) - (s1.topLeft.y ) ; 
		float Y2 =   s2.topLeft.y -(s1.topLeft.y + s1.size.y);
		float mtv_X1 = Math.abs(X1);
		float mtvX2  = Math.abs(X2);  
		float mtv_1Y = Math.abs(Y1);
		float mtv2Y  = Math.abs(Y2);
		if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
			return new Vec2f(X1,0);	
		}
		if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
			return new Vec2f(X2,0);	
		}
		if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
			return new Vec2f(0,Y1);
		}
		if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
			return new Vec2f(0,Y2);
		}
		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, PolygonShape s2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec2f collision(CircleShape s1, PolygonShape s2) {
		// TODO Auto-generated method stub
		return null;
	}


	// POLYGONS

	@Override
	public Vec2f collision(PolygonShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2f collision(PolygonShape s1, CircleShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2f collision(PolygonShape s1, Vec2f s2) {
		return null;
	}

	@Override
	public Vec2f collision(PolygonShape s1, PolygonShape s2) {
		return null;
	}

	// RAYCASTING
	@Override
	public float raycast(CircleShape s1, Ray s2) {
		return -1;
	}

	@Override
	public float raycast(AABShape s1, Ray s2) {
		// Top left corner
		Vec2f point0 = s1.getTopLeft(); 
		// Bottom Left
		Vec2f point1 = s1.getTopLeft().plus(0,s1.size.y); 
		// Bottom right corner
		Vec2f point2 = s1.getTopLeft().plus(s1.getSize()); 
		// Top right
		Vec2f point3 = s1.getTopLeft().plus(s1.size.x,0); 
		return raycast(new PolygonShape(point0,point1,point2,point3),s2);
	}

	@Override
	public float raycast(PolygonShape s1, Ray s2) 
	{
		Vec2f m, n, a, b; 
		Vec2f polyEdge; 
		// Ray Variables
		Vec2f p =  s2.src; 
		Vec2f d =  s2.dir;
		float crossProduct1 = 0; 
		float crossProduct2 = 0; 
		float intersection_t_length = Float.MAX_VALUE; 
		// Index points used to index polygon points
		int point_a, point_b; 
		for (int index = 0; index < s1.getNumPoints(); index++) {
			// Get Edge points
			point_a =   index;
			point_b = ( index + 1 ) == s1.getNumPoints() ? 0 :  index + 1  ; 
			a = s1.getPoint(point_a); 
			b = s1.getPoint(point_b); 
			// Edge is defined by two end points, a and b
			polyEdge = a.minus(b); 
			// m  is direction of the segment (normalized)
			m = polyEdge.normalize(); 
			// n  is the perpendicular to the segment (normalized)
			n = m.perpendicular().normalize();
			// (a - p ) X d 
			crossProduct1 = a.minus(p).cross(d); 
			// (b - p ) X d 
			crossProduct2 =  b.minus(p).cross(d); 
			// If the product of the two cross products is 
			// greater than 0, there is no intersection
			if ( ( crossProduct1 * crossProduct2 ) < 0 ) 
			{
				// Secondly, determine where the two lines intersect
				// Point of intersection
				float numerator =  b.minus(p).dot(n);
				float denominator = d.dot(n); 	
				float t_temp = numerator / denominator; 
				if ( (t_temp) < intersection_t_length) {
					intersection_t_length = t_temp;
				}
			}
		}		
		return intersection_t_length;
	}

}
