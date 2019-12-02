package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

import support.Vec2d;
import support.debugger.collisions.AABShape;
import support.debugger.collisions.CircleShape;
import support.debugger.collisions.PolygonShape;
import support.Interval;
import support.debugger.support.shapes.Shape;
import javafx.scene.transform.Affine;

public abstract class CollisionComponent extends Component{

	Shape _shape;
	
	public CollisionComponent(GameObject go, Shape shape) {
		super(go);
		// TODO Auto-generated constructor stub
		_shape = shape;
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract Vec2d collide(GameObject go);
	
	public boolean pointCollide(Vec2d pt) {
		TransformComponent curr = (TransformComponent)_go.getComponent("TRANSFORM");
		if(pt.x > curr.getLoc().x && pt.x < curr.getLoc().x + curr.getDim().x 
				&& pt.y > curr.getLoc().y && pt.y < curr.getLoc().y + curr.getDim().y ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Vec2d colliding(AABShape s1, AABShape s2) {
		Vec2d x_axis = new Vec2d(1,0);
		Vec2d s1_min_x = s1.getTopLeft().projectOnto(x_axis);
		Vec2d s1_max_x = s1.getTopLeft().plus(s1.getSize()).projectOnto(x_axis);
		
		Vec2d s2_min_x = s2.getTopLeft().projectOnto(x_axis);
		Vec2d s2_max_x = s2.getTopLeft().plus(s2.getSize()).projectOnto(x_axis);
		//y proj
		Vec2d y_axis = new Vec2d(0,1);
		Vec2d s1_min_y = s1.getTopLeft().projectOnto(y_axis);
		Vec2d s1_max_y = s1.getTopLeft().plus(s1.getSize()).projectOnto(y_axis);
		
		Vec2d s2_min_y = s2.getTopLeft().projectOnto(y_axis);
		Vec2d s2_max_y = s2.getTopLeft().plus(s2.getSize()).projectOnto(y_axis);
		
		Interval s1_x = new Interval(s1_min_x.x, s1_max_x.x);
		Interval s1_y = new Interval(s1_min_y.y, s1_max_y.y);
		Interval s2_x = new Interval(s2_min_x.x, s2_max_x.x);
		Interval s2_y = new Interval(s2_min_y.y, s2_max_y.y);
		if(s1_x.overlap(s2_x) && s1_y.overlap(s2_y)) {
			double move_up = Math.abs(s1_y._max-s2_y._min);
			double move_down = Math.abs(s1_y._min-s2_y._max);
			double move_left = Math.abs(s1_x._max-s2_x._min);
			double move_right = Math.abs(s1_x._min-s2_x._max);
			double min = Math.min(Math.min(Math.min(move_up, move_down),move_left),move_right);
			if(min == move_up) {
				return new Vec2d(0,-move_up);
			}
			else if(min == move_down) {
				return new Vec2d(0,move_down);
			}
			else if(min == move_left) {
				return new Vec2d(-move_left, 0);
			}
			else {
				return new Vec2d(move_right,0);
			}
		}
		return null;
	}
	
	public Vec2d colliding(AABShape s1, CircleShape s2) {
		if(this.isColliding(s1, s2)) {
			if(this.isColliding(s1, s2.getCenter())) {
				double dist_to_left = s2.getCenter().x - s1.getTopLeft().x;
				double dist_to_right = s1.getTopLeft().x+s1.getSize().x -s2.getCenter().x;
				double dist_to_top = s2.getCenter().y - s1.getTopLeft().y;
				double dist_to_bottom = s1.getTopLeft().y+s1.getSize().y - s2.getCenter().y;
				double min = Math.min(Math.min(Math.min(dist_to_top, dist_to_bottom), 
						dist_to_left), dist_to_right);
				if(min == dist_to_left) {
					return new Vec2d(s2.getRadius()+dist_to_left,0);
				}
				else if(min == dist_to_right) {
					return new Vec2d(-s2.getRadius()-dist_to_right,0);
				}
				else if(min == dist_to_top) {
					return new Vec2d(0,s2.getRadius()+dist_to_top);
				}
				else {
					return new Vec2d(0,-(s2.getRadius()+dist_to_bottom));	
				}
			}
			else {
				Vec2d nearest_point = new Vec2d(this.clamp(s2.getCenter().x, 
						s1.getTopLeft().x, s1.getTopLeft().plus(s1.getSize()).x),
						this.clamp(s2.getCenter().y, s1.getTopLeft().y, s1.getTopLeft().plus(s1.getSize()).y));
				double mag = s2.getRadius()-s2.getCenter().dist(nearest_point);
				Vec2d dir = s2.getCenter().minus(nearest_point).normalize();
				return new Vec2d(-dir.x*mag, -dir.y*mag);
			}
		}
		else {
			return null;
		}
	}
	
	public Vec2d colliding(CircleShape s1, CircleShape s2) {
		if(this.isColliding(s1, s2)) {
			double mag = s1.getRadius()+s2.getRadius()-s1.getCenter().dist(s2.getCenter());
			Vec2d dir = s1.getCenter().minus(s2.getCenter()).normalize();
			return new Vec2d(mag*dir.x, mag*dir.y);
		}
		return null;
	}
	
	public Vec2d colliding(AABShape s1, PolygonShape s2) {
		Vec2d smallest = null;
		double overlap = 1000;
		ArrayList<Vec2d> s1_axes = this.getAxes(s1);
		ArrayList<Vec2d> s2_axes = this.getAxes(s2);
		
		for(int i = 0; i < s1_axes.size(); i++) {
			Vec2d axis = s1_axes.get(i);
			Interval p1 = this.project(s1, axis);
			Interval p2 = this.project(s2, axis);
			if(!p1.overlap(p2)) {
				return null;
			}
			else {
				double o = p1.overlapVal(p2);
				if (overlap>o){
					overlap = o;
					smallest = axis;
				}
			}
		}
		for(int i = 0; i < s2_axes.size(); i++) {
			Vec2d axis = s2_axes.get(i);
			Interval p1 = this.project(s2, axis);
			Interval p2 = this.project(s1, axis);
			if(!p1.overlap(p2)) {
				return null;
			}
			else {
				double o = p1.overlapVal(p2);
				if (overlap>o){
					overlap = o;
					smallest = axis;
				}
			}
		}
		
		Vec2d mtv = smallest.smult(overlap);
		
		double center_to_center = s1.getCenter().dist2(s2.getCenter());
		double center_to_mtvC = s1.getCenter().dist2(s2.getCenter().plus(mtv.smult((double) 0.001)));
		if(center_to_center < center_to_mtvC) {
			mtv = mtv.smult(-1);
		}
		
		return new Vec2d(mtv.x, mtv.y);
	}
	
	public Vec2d colliding(CircleShape s1, PolygonShape s2) {
		Vec2d nearest_pt = s2.getPoint(0);
		double min_dist = s1.getCenter().minus(nearest_pt).mag();
		for(int i = 0; i < s2.getNumPoints(); i++) {
			if(s1.getCenter().minus(s2.getPoint(i)).mag() < min_dist) {
				nearest_pt = s2.getPoint(i);
				min_dist = s1.getCenter().minus(s2.getPoint(i)).mag();
			}
		}		
		ArrayList<Vec2d> s2_axes = this.getAxes(s2);
		
		double overlap = 1000;
		Vec2d smallest = null;
		for(int i = 0; i < s2_axes.size(); i++) {
			Vec2d axis = s2_axes.get(i);
			Interval p1 = this.project(s1, axis);
			Interval p2 = this.project(s2, axis);
			if(!p1.overlap(p2)) {
				return null;
			}
			else {
				double o = p1.overlapVal(p2);
				if (overlap>o){
					overlap = o;
					smallest = axis;
				}
			}
		}
		Vec2d mtv = smallest.smult(overlap);
		
		double center_to_center = s1.getCenter().dist2(s2.getCenter());
		double center_to_mtvC = s1.getCenter().dist2(s2.getCenter().plus(mtv.smult((double) 0.001)));
		if(center_to_center < center_to_mtvC) {
			mtv =  mtv.smult(-1);
		}
		
		return new Vec2d(mtv.x, mtv.y);
	}
	
	public Vec2d colliding(PolygonShape s1, PolygonShape s2) {
		Vec2d smallest = null;
		double overlap = 1000;
		ArrayList<Vec2d> s1_axes = this.getAxes(s1);
		ArrayList<Vec2d> s2_axes = this.getAxes(s2);
		for(int i = 0; i < s1_axes.size(); i++) {
			Vec2d axis = s1_axes.get(i);
			Interval p1 = this.project(s1, axis);
			Interval p2 = this.project(s2, axis);
			if(!p1.overlap(p2)) {
				return null;
			}
			else {
				double o = p1.overlapVal(p2);
				if (o<overlap){
					overlap = o;
					smallest = axis;
				}
			}
		}
		for(int i = 0; i < s2_axes.size(); i++) {
			Vec2d axis = s2_axes.get(i);
			Interval p1 = this.project(s1, axis);
			Interval p2 = this.project(s2, axis);
			if(!p1.overlap(p2)) {
				return null;
			}
			else {
				double o = p1.overlapVal(p2);
				if (o<overlap){
					overlap = o;
					smallest = axis;
				}
			}
		}

		Vec2d mtv = smallest.smult(overlap);
		double center_to_center = s1.getCenter().dist2(s2.getCenter());
		double center_to_mtvC = s1.getCenter().dist2(s2.getCenter().plus(mtv.smult((double) 0.001)));
		if(center_to_center < center_to_mtvC) {
			mtv = mtv.smult(-1);
		}
		return new Vec2d(mtv.x, mtv.y);
	}
	
	public boolean isColliding(AABShape s1, AABShape s2) {
		//x proj
		Vec2d x_axis = new Vec2d(1,0);
		Vec2d s1_min_x = s1.getTopLeft().projectOnto(x_axis);
		Vec2d s1_max_x = s1.getTopLeft().plus(s1.getSize()).projectOnto(x_axis);
		
		Vec2d s2_min_x = s2.getTopLeft().projectOnto(x_axis);
		Vec2d s2_max_x = s2.getTopLeft().plus(s2.getSize()).projectOnto(x_axis);
		//y proj
		Vec2d y_axis = new Vec2d(0,1);
		Vec2d s1_min_y = s1.getTopLeft().projectOnto(y_axis);
		Vec2d s1_max_y = s1.getTopLeft().plus(s1.getSize()).projectOnto(y_axis);
		
		Vec2d s2_min_y = s2.getTopLeft().projectOnto(y_axis);
		Vec2d s2_max_y = s2.getTopLeft().plus(s2.getSize()).projectOnto(y_axis);
		
		Interval s1_x = new Interval(s1_min_x.x, s1_max_x.x);
		Interval s1_y = new Interval(s1_min_y.y, s1_max_y.y);
		Interval s2_x = new Interval(s2_min_x.x, s2_max_x.x);
		Interval s2_y = new Interval(s2_min_y.y, s2_max_y.y);
		if(s1_x.overlap(s2_x) && s1_y.overlap(s2_y)) {
			return true;
		}
		return false;
	}

	public boolean isColliding(AABShape s1, CircleShape s2) {
		Vec2d nearest_point = new Vec2d(this.clamp(s2.getCenter().x, 
				s1.getTopLeft().x, s1.getTopLeft().plus(s1.getSize()).x),
				this.clamp(s2.getCenter().y, s1.getTopLeft().y, s1.getTopLeft().plus(s1.getSize()).y));
		return isColliding(s2, nearest_point);
	}

	public boolean isColliding(AABShape s1, Vec2d s2) {
		if(s1.getTopLeft().x <= s2.x && s2.x <= s1.getTopLeft().x + s1.getSize().x
				&& s1.getTopLeft().y <= s2.y && s2.y <= s1.getTopLeft().y + s1.getSize().y) {
			return true;
		}
		return false;
	}

	// CIRCLES
	
	public boolean isColliding(CircleShape s1, AABShape s2) {
		return isColliding(s2, s1);
	}

	public boolean isColliding(CircleShape s1, CircleShape s2) {
		if(s1.getCenter().dist2(s2.getCenter()) <= (s1.getRadius()+ s2.getRadius())*(s1.getRadius() + s2.getRadius())) {
			return true;
		}
		return false;
	}

	public boolean isColliding(CircleShape s1, Vec2d s2) {
		if(s1.getCenter().dist2(s2) <= s1.getRadius()*s1.getRadius()) {
			return true;
		}
		return false;
	}

	public abstract Vec2d collideWithSphere(CircleShape s1);
	
	public abstract Vec2d collideWithAAB(AABShape s1);
	
	public abstract Vec2d collideWithPolygon(PolygonShape s1);
	
	private double clamp(double val, double min, double max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	private ArrayList<Vec2d> getAxes(PolygonShape s1) {
		ArrayList<Vec2d> axes = new ArrayList<Vec2d>();
		ArrayList<Vec2d> sorted_pts = s1.getSortedVertices();
		for(int i = 0; i < s1.getNumPoints(); i++) {
			Vec2d pt_a = sorted_pts.get(i);
			Vec2d pt_b = sorted_pts.get((i+1)%s1.getNumPoints());
			Vec2d currEdge = pt_a.minus(pt_b);
			Vec2d normal = currEdge.perpendicular().normalize();
			axes.add(normal);
		}
		return axes;
	}
	
	private ArrayList<Vec2d> getAxes(AABShape s1) {
		ArrayList<Vec2d> axes = new ArrayList<Vec2d>();
		for(int i = 0; i < 4; i++) {
			Vec2d pt_a = s1.getPoint(i);
			Vec2d pt_b = s1.getPoint((i+1)%4);
			Vec2d currEdge = pt_a.minus(pt_b);
			Vec2d normal = currEdge.perpendicular().normalize();
			axes.add(normal);
		}
		return axes;
	}
	
	private Interval project(PolygonShape s1, Vec2d axis) {
		ArrayList<Vec2d> sorted_pts = s1.getSortedVertices();
		double min = axis.dot(sorted_pts.get(0));
		double max = axis.dot(sorted_pts.get(0));
		for(int i=1; i < s1.getNumPoints(); i++) {
			double p = axis.dot(sorted_pts.get(i));
			if(p<min) {
				min=(double) p;
			}else if(p>max) {
				max=(double)p;
			}
		}
		return new Interval(min,max);
	}
	
	private Interval project(AABShape s1, Vec2d axis) {
		double min = axis.dot(s1.getPoint(0));
		double max = axis.dot(s1.getPoint(0));
		for(int i=1; i < 4; i++) {
			double p = axis.dot(s1.getPoint(i));
			if(p<min) {
				min=(double) p;
			}else if(p>max) {
				max=(double)p;
			}
		}
		return new Interval(min,max);
	}
	
	private Interval project(CircleShape s1, Vec2d axis) {
		double min = axis.dot(s1.getCenter())-s1.getRadius();
		double max = axis.dot(s1.getCenter())+s1.getRadius();
		
		return new Interval(min,max);
	}
	
}
