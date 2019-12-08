package support.debugger.collisions;

import support.debugger.support.Vec2f;
import support.debugger.support.interfaces.Week3Reqs;

public final class Week3 extends Week3Reqs {
	// AXIS-ALIGNED BOXES
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

}