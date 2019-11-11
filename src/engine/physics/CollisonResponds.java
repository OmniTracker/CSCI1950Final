package engine.physics;

import support.Vec2d;

public class CollisonResponds {

	private double _destitution;

	CollisonResponds () {

	}

	public static Vec2d calculateFinalImpulse (double massA, 
			double massB, 
			double COR, 
			Vec2d velA, 
			Vec2d velB ) {

		double x = ((massA * massB) * (1 + COR)) / (massA + massB);  

		return  velA.minus(velB).pmult(x, x);  
	}

	public static Vec2d calculateStaticImpulse ( double mass, double COR,  Vec2d velA, Vec2d velB ) {

		double x = mass * ( 1 + COR ); 

		return velA.minus(velB).pmult(x, x);
	}

	public static Vec2d finalVelocity (double massA, double massB, double COR, Vec2d velA, Vec2d velB ) {
		
		Vec2d x = velA.pmult(massA, massA); 
		Vec2d y = velB.pmult(massB, massB);
		
		double z0 = massA * COR; 
		Vec2d z1 = velA.minus(velB).minus(z0, z0); 
		
		double q = (massA + massB); 
		
		return x.plus(y).plus(z1).pdiv(q, q); 
	}
	
	private double getDestitution() {
		return _destitution;
	}

	private void setDestitution(double _destitution) {
		this._destitution = _destitution;
	}
}
