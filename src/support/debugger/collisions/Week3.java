package support.debugger.collisions;

import support.Vec2d;
import support.debugger.support.Vec2f;
import support.debugger.support.interfaces.Week3Reqs;

public final class Week3 extends Week3Reqs {

	// AXIS-ALIGNED BOXES

	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		
		if (isColliding(s1, s2)) {
			
		}
		float ret = 0f;		
		float test1 =  s1.topLeft.x              - (s2.topLeft.x + s2.size.x);
		float test2 = (s1.topLeft.x + s1.size.x) - s2.topLeft.x;
		float test3 = s1.topLeft.y               - (s2.topLeft.y + s2.size.y); 
		float test4 = (s1.topLeft.y + s1.size.y) - s2.topLeft.y;
		
		ret = test1;
		
		if (ret > test2) {
			ret = test2;
		} 

		if (ret > test3) {
			ret = test3;
		} 

		if (ret > test4) {
			ret = test4;
		} 
		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, CircleShape s2) {
		
		


		/*
		– If Box contains circle center
		• Find p = closest point on AAB edge from circle center
		• Length of MTV is Radius + dist(center, p);
		• MTV is parallel to the X or Y axis
		*/
		
		/*
		– Otherwise
		• Clamp circle center to Box
		• Lengh of MTV is Radius - dist(center, clampedPoint)
		• MTV is parallel to line connecting
		 */





		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, Vec2f s2) {

		
		/*
		  If Box contains circle center
		• Find p = closest point on AAB edge from circle center
		• Length of MTV is Radius + dist(center, p);
		• MTV is parallel to the X or Y axis
		 */


		/*
		Otherwise
		• Clamp circle center to Box
		• Lengh of MTV is Radius - dist(center, clampedPoint)
		• MTV is parallel to line connecting
		 */



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
		/* sqrt((x2 − x1)^2 + (y2 − y1)^2) − (r2 + r1)*/
		float x = s2.center.x - s1.center.x; 
		float y = s2.center.y - s1.center.y; 
		float r = s2.radius + s1.radius;
		x = x * x;
		y = y * y;
		float root = (float) Math.sqrt(x + y);
		float dist = root - r;

		/* MTV is parallel to line connecting centers */
		
		
		return null;
	}

	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		/* sqrt((x2 − x1)^2 + (y2 − y1)^2) − (r2 + r1)*/
		float x = s2.x - s1.center.x; 
		float y = s2.y - s1.center.y; 
		float r = ( 0 ) + s1.radius;
		x = x * x;
		y = y * y;
		float root = (float) Math.sqrt(x + y);

		float dist = root - r;
		
		

		return null;
	}

}
