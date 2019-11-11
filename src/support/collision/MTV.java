package support.collision;

import support.Vec2d;
public class MTV {

	// AXIS-ALIGNED BOXES
	public static Vec2d collision(AABShape s1, CircleShape s2) {
		if ( Collision.isColliding(s1, s2) == false) {
			return null;
		}
		double X1 = (s2.center.x) - (s1.topLeft.x);
		double X2 = (s2.center.x) - (s1.topLeft.x + s1.size.x);
		double Y1 = (s2.center.y) - (s1.topLeft.y ) ; 
		double Y2 = (s2.center.y) - (s1.topLeft.y + s1.size.y);
		double mtv_X1 = Math.abs(X1);
		double mtvX2  = Math.abs(X2);  
		double mtv_1Y = Math.abs(Y1);
		double mtv2Y  = Math.abs(Y2);
		// Inside AABShape
		if ((s1.topLeft.x < s2.center.x)  &&
				(s2.center.x < (s1.topLeft.x + s1.size.x)) &&
				(s1.topLeft.y < s2.center.y)  && 
				(s2.center.y < (s1.topLeft.y + s1.size.y))) {

			if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
				return new Vec2d(X1 + s2.radius,0);	
			}
			if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
				return new Vec2d(X2 - s2.radius,0);	
			}
			if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
				return new Vec2d(0,Y1 + s2.radius);
			}
			if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
				return new Vec2d(0,Y2 - s2.radius);
			}
		} else {			
			// Outside
			if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
				return new Vec2d(X1 + s2.radius,0);	
			}
			if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
				return new Vec2d(X2 - s2.radius,0);	
			}
			if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
				return new Vec2d(0,Y1 + s2.radius);
			}
			if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
				return new Vec2d(0,Y2 - s2.radius);
			}
		}
		return null;
	}
	public static Vec2d collision(AABShape s1, Vec2d s2) {
		if ( Collision.isColliding(s1, s2) == false) {
			return null;
		}
		// In order for a point to collide with a AABShape, 
		// assume the point within the AABShape.
		double X1 = (s2.x + s2.x) - (s1.topLeft.x);
		double X2 =  (s2.x) - (s1.topLeft.x + s1.size.x);
		double Y1 = (s2.y + s2.y) - (s1.topLeft.y ) ; 
		double Y2 =   s2.y -(s1.topLeft.y + s1.size.y);
		double mtv_X1 = Math.abs(X1);
		double mtvX2  = Math.abs(X2);  
		double mtv_1Y = Math.abs(Y1);
		double mtv2Y  = Math.abs(Y2);
		if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
			return new Vec2d(X1,0);	
		}
		if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
			return new Vec2d( X2,0);	
		}
		if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
			return new Vec2d(0,Y1);
		}
		if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
			return new Vec2d(0,Y2);
		}
		return null;
	}
	// CIRCLES
	public Vec2d collision(CircleShape s1, AABShape s2) {
		Vec2d f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}
	public static Vec2d collision(CircleShape s1, CircleShape s2) {	
		if ( Collision.isColliding(s1, s2) == false) {
			return null;
		}
		double mtvLength = (s1.radius + s2.radius) - (s2.center.dist(s1.center));
		Vec2d coor = s2.center.minus(s1.center).normalize();	
		return coor.pmult(-1 * mtvLength,-1 * mtvLength);
	}

	public static Vec2d collision(CircleShape s1, Vec2d s2) {
		if ( Collision.isColliding(s1, s2) == false) {
			return null;
		}
		double mtvLength = (s1.radius) - (s1.center.dist(s2)); 
		Vec2d coor = s1.center.minus(s2).normalize();		
		return coor.pmult( -1 * mtvLength, -1 * mtvLength);
	}
	public static Vec2d collision(AABShape s1, AABShape s2) {
		if ( Collision.isColliding(s1, s2) == false) {
			return null;
		}
		double X1 = (s2.topLeft.x + s2.size.x) - (s1.topLeft.x);
		double X2 =  (s2.topLeft.x) - (s1.topLeft.x + s1.size.x);
		double Y1 = (s2.topLeft.y + s2.size.y) - (s1.topLeft.y ) ; 
		double Y2 =   s2.topLeft.y -(s1.topLeft.y + s1.size.y);
		double mtv_X1 = Math.abs(X1);
		double mtvX2  = Math.abs(X2);  
		double mtv_1Y = Math.abs(Y1);
		double mtv2Y  = Math.abs(Y2);
		if ((mtv_X1 < mtvX2) && (mtv_X1 < mtv_1Y) && (mtv_X1 < mtv2Y)) {
			return new Vec2d(X1,0);	
		}
		if ((mtvX2 < mtv_X1) && (mtvX2 < mtv_1Y) && (mtvX2 < mtv2Y)) {
			return new Vec2d( X2,0);	
		}
		if ((mtv_1Y < mtv_X1) && (mtv_1Y < mtvX2) && (mtv_1Y < mtv2Y)) {
			return new Vec2d(0,Y1);
		}
		if ((mtv2Y < mtv_X1) && (mtv2Y < mtvX2) && (mtv2Y < mtv_1Y)) {
			return new Vec2d(0,Y2);
		}
		return null;
	}
}
