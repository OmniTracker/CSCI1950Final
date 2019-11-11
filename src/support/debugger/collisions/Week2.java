package support.debugger.collisions;

import support.debugger.support.Vec2f;
import support.debugger.support.interfaces.Week2Reqs;

public final class Week2 extends Week2Reqs {
	// AXIS-ALIGNED BOXES
	public boolean isColliding(AABShape s1, AABShape s2) {
		float s1_x = s1.topLeft.x;
		float s1_y = s1.topLeft.y; 
		float s1_w = s1.size.x; 
		float s1_h = s1.size.y; 
		float s2_x = s2.topLeft.x;
		float s2_y = s2.topLeft.y;
		float s2_w = s2.size.x;
		float s2_h = s2.size.y; 
		return ABB_AABCollider(s1_x,s1_y,s1_w,s1_h,s2_x,s2_y,s2_w,s2_h); 
	}
	 public boolean ABB_AABCollider(float s1_x, float s1_y, float s1_w, float s1_h,
			float s2_x, float s2_y, float s2_w, float s2_h) {
		if ( (s1_x + s1_w >= s2_x) && (s1_x <= s2_x + s2_w) &&
				( s1_y + s1_h >= s2_y ) &&( s1_y <= s2_y + s2_h)) {    
			return true;
		}
		return false;
	}
	public boolean isColliding(AABShape s1, CircleShape s2) {
		float aab_origin_x =  s1.topLeft.x; 
		float aab_origin_y = s1.topLeft.y;
		float aab_width = s1.size.x; 
		float aab_height = s1.size.y; 
		float circle_x = s2.getCenter().x; 
		float circle_y = s2.getCenter().y;
		float radius = s2.radius; 
		return CircleAABCollider(aab_origin_x,aab_origin_y, aab_width, 
				aab_height, circle_x, circle_y, radius); 
	}
	static public boolean CircleAABCollider(float aab_origin_x, 
			float aab_origin_y, 
			float aab_width, 
			float aab_height, 
			float circle_x, 
			float circle_y, 
			float radius) {
		float tempX = circle_x;
		float tempY = circle_y;
		if (circle_x < aab_origin_x)    {
			tempX = aab_origin_x;   
		}
		else if (circle_x > aab_origin_x+aab_width){
			tempX = aab_origin_x+aab_width; 
		}
		if (circle_y < aab_origin_y) {
			tempY = aab_origin_y;     
		}
		else if (circle_y > aab_origin_y+aab_height) {
			tempY = aab_origin_y+aab_height;
		}
		float distX = circle_x-tempX;
		float distY = circle_y-tempY;
		float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );

		if (distance <= radius) {
			return true;
		}
		return false;
	}
	public boolean isColliding(AABShape s1, Vec2f s2) {
		return pointRect(s2.x,s2.y, s1.topLeft.x,  s1.topLeft.y, s1.size.x, s1.size.y); 
	}
	static boolean pointRect(float px, float py, float rx, float ry, float rw, float rh) {
		if (px >= rx &&        
				px <= rx + rw &&   
				py >= ry &&        
				py <= ry + rh) {  
			return true;
		}
		return false;
	}
	// CIRCLES
	public boolean isColliding(CircleShape s1, AABShape s2) {
		return isColliding(s2, s1);
	}
	public boolean isColliding(CircleShape s1, CircleShape s2) {
		float x = s2.getCenter().x - s1.getCenter().x; 
		x = (float) Math.pow(x,2); 
		float y = s2.getCenter().y - s1.getCenter().y; 
		y = (float) Math.pow(y,2); 
		float r = s1.radius + s2.radius;
		r = (float) Math.pow(r, 2);
		if ( (x + y) < r ) {
			return true;
		}	
		return false;
	}
	public boolean isColliding(CircleShape s1, Vec2f s2) {
		float distanceBetweenCirclesSquared = (s1.getCenter().x - s2.x) * 
				(s1.getCenter().x - s2.x) + 
				(s1.getCenter().y - s2.y) * 
				(s1.getCenter().y  - s2.y);		
		if (distanceBetweenCirclesSquared < s1.radius * s1.radius ) {
			return true;
		}
		return false;
	}
	static public boolean withinRange(Vec2f s1, Vec2f s2, float range) {
		float distance = (s1.x - s2.x) * (s1.x - s2.x) + (s1.y - s2.y) * (s1.y  - s2.y);		
		if (distance < (range* range) ) {
			return true;
		}
		return false;
	}
}
