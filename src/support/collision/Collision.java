package support.collision;

import support.collision.AABShape;
import support.collision.CircleShape;
import support.Vec2d;

public class Collision  {

	public Collision() {

	}

	static public boolean isColliding(AABShape s1, AABShape s2) {
		double s1_x = s1.topLeft.x;
		double s1_y = s1.topLeft.y; 
		double s1_w = s1.size.x; 
		double s1_h = s1.size.y; 
		double s2_x = s2.topLeft.x;
		double s2_y = s2.topLeft.y;
		double s2_w = s2.size.x;
		double s2_h = s2.size.y; 
		return ABB_AABCollider(s1_x,s1_y,s1_w,s1_h,s2_x,s2_y,s2_w,s2_h); 
	}

	static public boolean ABB_AABCollider(double s1_x, double s1_y, double s1_w, double s1_h,
			double s2_x, double s2_y, double s2_w, double s2_h) {
		if ( (s1_x + s1_w >= s2_x) && (s1_x <= s2_x + s2_w) &&
				( s1_y + s1_h >= s2_y ) &&( s1_y <= s2_y + s2_h)) {    
			return true;
		}
		return false;
	}

	static public boolean isColliding(AABShape s1, CircleShape s2) {
		double aab_origin_x =  s1.topLeft.x; 
		double aab_origin_y = s1.topLeft.y;
		double aab_width = s1.size.x; 
		double aab_height = s1.size.y; 
		double circle_x = s2.getCenter().x; 
		double circle_y = s2.getCenter().y;
		double radius = s2.radius; 
		return CircleAABCollider(aab_origin_x,aab_origin_y, aab_width, 
				aab_height, circle_x, circle_y, radius); 
	}

	static public boolean CircleAABCollider(double aab_origin_x, 
			double aab_origin_y, 
			double aab_width, 
			double aab_height, 
			double circle_x, 
			double circle_y, 
			double radius) {
		double tempX = circle_x;
		double tempY = circle_y;
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
		double distX = circle_x-tempX;
		double distY = circle_y-tempY;
		double distance = Math.sqrt( (distX*distX) + (distY*distY) );

		if (distance <= radius) {
			return true;
		}
		return false;
	}

	static public boolean isColliding(AABShape s1, Vec2d s2) {
		return pointRect(s2.x,s2.y, s1.topLeft.x,  s1.topLeft.y, s1.size.x, s1.size.y); 
	}

	static boolean pointRect(double px, double py, double rx, double ry, double rw, double rh) {
		if (px >= rx &&        
				px <= rx + rw &&   
				py >= ry &&        
				py <= ry + rh) {  
			return true;
		}
		return false;
	}

	static public boolean isColliding(CircleShape s1, AABShape s2) {
		return isColliding(s2, s1);
	}

	static public boolean isColliding(CircleShape s1, CircleShape s2) {
		double x = s2.getCenter().x - s1.getCenter().x; 
		x = Math.pow(x,2); 
		double y = s2.getCenter().y - s1.getCenter().y; 
		y = Math.pow(y,2); 
		double r = s1.radius + s2.radius;
		r = Math.pow(r, 2);
		if ( (x + y) < r ) {
			return true;
		}	
		return false;
	}

	static public boolean isColliding(CircleShape s1, Vec2d s2) {
		double distanceBetweenCirclesSquared = (s1.getCenter().x - s2.x) * 
				(s1.getCenter().x - s2.x) + 
				(s1.getCenter().y - s2.y) * 
				(s1.getCenter().y  - s2.y);		
		if (distanceBetweenCirclesSquared < s1.radius * s1.radius ) {
			return true;
		}
		return false;
	}

	static public boolean withinRange(Vec2d s1, Vec2d s2, double range) {
		double distance = (s1.x - s2.x) * (s1.x - s2.x) + (s1.y - s2.y) * (s1.y  - s2.y);		
		if (distance < (range* range) ) {
			return true;
		}
		return false;
	}
}
