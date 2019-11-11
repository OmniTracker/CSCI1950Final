package support.debugger.collisions;

import support.debugger.support.Vec2f;
import support.debugger.support.interfaces.Week5Reqs;

public final class Week5 extends Week5Reqs {
	// AXIS-ALIGNED BOXES
	Week3 _collider = new Week3();
	// AXIS-ALIGNED BOXES
	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		return  _collider.collision(s1, s2); 
	}
	@Override
	public Vec2f collision(AABShape s1, CircleShape s2) {
		return  _collider.collision(s1, s2);
	}
	@Override
	public Vec2f collision(AABShape s1, Vec2f s2) {
		return  _collider.collision(s1, s2);
	}
	// CIRCLES
	@Override
	public Vec2f collision(CircleShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}
	@Override
	public Vec2f collision(CircleShape s1, CircleShape s2) {
		return  _collider.collision(s1, s2);
	}
	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		return  _collider.collision(s1, s2);
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
	// FILL IN THE REST
	@Override
	public Vec2f collision(PolygonShape s1, PolygonShape s2) {
		polycollisionHelper(s1, s2);
		return null;
	}
	@Override
	public Vec2f collision(AABShape s1, PolygonShape s2) {
		// Convert AABShape to PolygonShape
		Vec2f point0 = s1.topLeft; 
		Vec2f point1 = s1.topLeft.plus(s1.size); 
		Vec2f point2 = s1.topLeft.plus(s1.size.x,0);  
		Vec2f point3 = s1.topLeft.plus(0,s1.size.y); 
		PolygonShape s3 = new PolygonShape(point0,point1, point2, point3);  
		return polycollisionHelper(s2, s3); 
	}
	public Vec2f edgeVector (Vec2f s1, Vec2f s2) {		
		return s2.minus(s1).normalize(); 
	}


	@Override
	public Vec2f collision(CircleShape s1, PolygonShape s2) {
		double min1 = 0; 
		double max1 = 0; 
		double min2 = 0; 
		double max2 = 0; 
		// Need to figure how to set the min and max.
		for (int a = 0; a < s2.getNumPoints(); a++) 
		{	
			for (int b = 0; b < s2.getNumPoints(); b++) 
			{
				if (a != b) 
				{
					s2.getPoint(a);
					s2.getPoint(b);

					// The points for the circle will just be the radius

					min1 =  - s1.radius;

					max1 =  + s1.radius;	

					if (min1 <= max2 && min2 <= min1) 
					{
						return null;
					}			
				}
			}
		}
		return null;
	}

	@Override
	public Vec2f collision(PolygonShape s1, Vec2f s2) {
		Vec2f v = null;
		float crossProduct;
		// Iterate over the edges (counterclockwise)
		for (int x = 0; x < s1.getNumPoints(); x++) 
		{	
			if ((x + 1) == s1.getNumPoints())  
			{
				v = edgeVector (s1.getPoint(0), s1.getPoint(x)); 
			} 
			else 
			{
				v = edgeVector (s1.getPoint(x), s1.getPoint(x + 1)); 
			}
			Vec2f p = null;
			crossProduct = v.cross(p); 
			// If any cross-product is positive, it’s outside
			if (crossProduct > 0) 
			{
				return new Vec2f(0,0); 
			} 
		}		
		return new Vec2f(0,0);
	}

	@SuppressWarnings("unused")
	private Vec2f polycollisionHelper(PolygonShape s1, PolygonShape s2) {
		double min1 = 0; 
		double max1 = 0; 
		double min2 = 0; 
		double max2 = 0; 

		Vec2f cpV0 = null;		
		Vec2f cpV1 = null;

		Vec2f pA0 = null;
		Vec2f pA1 = null;
		Vec2f pB0 = null;
		Vec2f pB1 = null;
		
		Vec2f projectA0 = null;
		Vec2f projectA1 = null;
		Vec2f projectB0 = null;
		Vec2f projectB1 = null;
		

		// Need to figure how to set the min and max.
		for (int a = 0; a < s1.getNumPoints(); a++) 
		{	
			for (int b = 0; b < s1.getNumPoints(); b++) 
			{
				if (a != b) 
				{ 
					for (int c = 0; c < s2.getNumPoints() ; c++) 
					{
						for (int d = 0; d < s2.getNumPoints() ; d++) 
						{
							if (c != d) { 

								min1 = 0; 
								max1 = 0; 
								min2 = 0; 
								max2 = 0; 	

								// Each loop will be calculate two projection for each shape
								pA0 = s1.getPoint(a); 
								pA1 = s1.getPoint(b);
								pB0 = s2.getPoint(c); 
								pB1 = s2.getPoint(d); 

								// Perpendicular 
								cpV0 = pA1.minus(pA0); 
								
								projectB0 = cpV0.projectOntoLine(pB0, cpV0);
								projectB1 = cpV0.projectOntoLine(pB1, cpV0);
								
								if (min1 <= max2 && min2 <= max1) 
								{
									return null;
								}
								min1 = 0; 
								max1 = 0; 
								min2 = 0; 
								max2 = 0; 	
								
								// Perpendicular  								
								cpV1 = pB1.minus(pB0); 
								
								projectA0 = cpV1.projectOntoLine(pA0, cpV1);
								projectA1 = cpV1.projectOntoLine(pA1, cpV1);

								if (min1 <= max2 && min2 <= max1) 
								{
									return null;
								}								

							}
						}
					}
				}
			}
		}
		return null;
	}

}