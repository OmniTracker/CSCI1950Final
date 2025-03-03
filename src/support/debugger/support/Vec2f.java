package support.debugger.support;

import java.io.Serializable;

/**
 * A class representing a two-dimensional vector of {@code float}s. Useful when
 * working with positions and sizes in a continuous space, such as a game world.
 * 
 * {@code Vec2f} instances, like {@link String}s, are immutable, and should be
 * treated in a similar way. Specifically, note that all methods will return a
 * new instance rather than modifying the same instance, so the return value
 * must be used or saved.
 * 
 * For performance, this class and all of its methods have been marked
 * {@code final}.
 * 
 * @author zdavis
 */
public final class Vec2f implements Serializable {
	private static final long serialVersionUID = 4786212184803008441L;

	public static final Vec2f ORIGIN = new Vec2f(0f, 0f);

	public static final Vec2f min(Vec2f a, Vec2f b) {
		return a.mag2() > b.mag2() ? b : a;
	}

	public static final Vec2f max(Vec2f a, Vec2f b) {
		return a.mag2() < b.mag2() ? b : a;
	}

	/**
	 * Since {@link Vec2f} instances are immutable, their x and y fields may be
	 * accessed without getters.
	 */
	public final float x, y;

	/**
	 * Constructor. Creates a new instance from a value
	 * 
	 * @param val
	 *            the x and y components of the vector
	 */
	public Vec2f(float val) {
		assert !Float.isNaN(val);
		this.x = val;
		this.y = val;
	}
	
	public Vec2f(double val) {
		assert !Double.isNaN(val);
		this.x = (float) val;
		this.y = (float) val;
	}

	/**
	 * Constructor. Creates a new instance from an x and y component.
	 * 
	 * @param x
	 *            the x-component of the vector
	 * @param y
	 *            the y-component of the vector
	 */
	public Vec2f(float x, float y) {
		assert !Float.isNaN(x);
		assert !Float.isNaN(y);
		this.x = x;
		this.y = y;
	}
	
	public Vec2f(double x, double y) {
		assert !Double.isNaN(x);
		assert !Double.isNaN(y);
		this.x = (float) x;
		this.y = (float) y;
	}

	/**
	 * Constructor. Creates a new instance from an existing Vec2f {@code float}
	 * s.
	 * 
	 * @param f
	 *            the {@code Vec2f} from which to copy the values
	 */
	public Vec2f(Vec2f f) {
		this.x = f.x;
		this.y = f.y;
	}

	/**
	 * Constructor. Creates a new instance by converting the components of a
	 * {@link Vec2i} to {@code float}s.
	 * 
	 * @param i
	 *            the {@code Vec2i} from which to copy the values
	 */
	public Vec2f(Vec2i i) {
		this.x = i.x;
		this.y = i.y;
	}

	/**
	 * Factory method that creates a Vec2f from the given polar coordinates.
	 * 
	 * @param radians
	 *            the angle, in radians, of the vector from the x axis
	 * @param magnitude
	 *            the magnitude of the vector
	 * @return a {@code Vec2f} that forms a {@code radians}-radian angle
	 *         counter- clockwise from the x axis with a magnitude of
	 *         {@code magnitude}
	 */
	public final static Vec2f fromPolar(float radians, float magnitude) {
		return new Vec2f((float) (magnitude * Math.cos(radians)),
				(float) (magnitude * Math.sin(radians)));
	}

	/*
	 * Vector ops
	 */

	/**
	 * Multiplies the vector by a scalar.
	 * 
	 * @param s
	 *            the scalar by which to multiply this vector
	 * @return a new {@link Vec2f} instance where each component has been
	 *         multiplied by {@code s}
	 */
	public final Vec2f smult(float s) {
		return new Vec2f(this.x * s, this.y * s);
	}

	/**
	 * Multiplies the vector piecewise by another vector. NOT A DOT PRODUCT.
	 * 
	 * @param v
	 *            the vector by which to multiply this vector
	 * @return a new {@link Vec2f} instance where each component has been
	 *         multiplied by the corresponding component in {@code v}
	 */
	public final Vec2f pmult(Vec2f v) {
		return new Vec2f(this.x * v.x, this.y * v.y);
	}

	/**
	 * Primitive version of {@link #pmult(Vec2f)}.
	 */
	public final Vec2f pmult(float x, float y) {
		return new Vec2f(this.x * x, this.y * y);
	}

	/**
	 * Divides the vector by a scalar.
	 * 
	 * @param s
	 *            the scalar by which to divide this vector
	 * @return a new {@link Vec2f} instance where each component has been
	 *         divided by {@code s}
	 */
	public final Vec2f sdiv(float s) {
		return new Vec2f(this.x / s, this.y / s);
	}

	/**
	 * Divides the vector piecewise by another vector.
	 * 
	 * @param v
	 *            the vector by which to divide this vector
	 * @return a new {@link Vec2f} instance where each component has been
	 *         divided by the corresponding component in {@code v}
	 */
	public final Vec2f pdiv(Vec2f v) {
		return new Vec2f(this.x / v.x, this.y / v.y);
	}

	/**
	 * Primitive version of {@link #pdiv(Vec2f)}.
	 */
	public final Vec2f pdiv(float x, float y) {
		return new Vec2f(this.x / x, this.y / y);
	}

	/**
	 * Adds another vector to this vector.
	 * 
	 * @param v
	 *            the vector to add to this vector
	 * @return a new {@link Vec2f} instance where each component has added the
	 *         corresponding component in {@code v}
	 */
	public final Vec2f plus(Vec2f v) {
		return new Vec2f(this.x + v.x, this.y + v.y);
	}

	/**
	 * Primitive version of {@link #plus(Vec2f)}.
	 */
	public final Vec2f plus(float x, float y) {
		return new Vec2f(this.x + x, this.y + y);
	}

	/**
	 * Subtracts another vector from this vector.
	 * 
	 * @param v
	 *            the vector to subtract from this vector
	 * @return a new {@link Vec2f} instance where each component has subtracted
	 *         the corresponding component in {@code v}
	 */
	public final Vec2f minus(Vec2f v) {
		return new Vec2f(this.x - v.x, this.y - v.y);
	}

	/**
	 * Primitive version of {@link #minus(Vec2f)}.
	 */
	public final Vec2f minus(float x, float y) {
		return new Vec2f(this.x - x, this.y - y);
	}
	/**
	 * Rotates the vector 90 degrees counterclockwise about the origin
	 * @return a new {@link Vec2f} instance of the form (-y, x)
	 */
	public final Vec2f rotate() {
		return new Vec2f(-this.y, this.x);
	}

	/**
	 * Rotates the vector counterclockwise about the origin
	 * @param r
	 * 				the amount to rotate, in radians
	 * @return a new rotated {@link Vec2f} instance
	 */
	public final Vec2f rotate(float r) {
		return new Vec2f(1, 0).smult((float)Math.cos(r) * x)
				.plus(new Vec2f(0, 1).smult((float)Math.sin(r) * x))
				.plus(new Vec2f(1, 0).smult((float)-Math.sin(r) * y))
				.plus(new Vec2f(0, 1).smult((float)Math.cos(r) * y));
	}

	/**
	 * Takes the {@linkplain Math#floor(double) floor} of the components of this
	 * vector.
	 * 
	 * @return a new {@link Vec2f} instance where each component is the floor of
	 *         the components of this vector
	 */
	public final Vec2f floor() {
		return new Vec2f((float) Math.floor(this.x), (float) Math.floor(this.y));
	}

	/**
	 * Takes the {@linkplain Math#ceil(double) ceiling} of the components of
	 * this vector.
	 * 
	 * @return a new {@link Vec2f} instance where each component is the ceiling
	 *         of the components of this vector
	 */
	public final Vec2f ceil() {
		return new Vec2f((float) Math.ceil(this.x), (float) Math.ceil(this.y));
	}

	/**
	 * Linearly interpolates between two vectors. For example, a {@code frac}
	 * value of 0 would return a vector with the same value as this vector,
	 * whereas a value of 1 would return a vector with the same value as
	 * {@code dest} and a value of .4 would return a vector four tenths of the
	 * way from this vector to {@code dest}.
	 * 
	 * @param dest
	 *            the vector to which to linearly interpolate from this vector
	 * @param frac
	 *            the fraction of the distance from this vector to {@code dest}
	 *            to go
	 * @return a vector that is {@code frac} amount of the distance between this
	 *         vector and {@code dest}
	 */
	public final Vec2f lerpTo(Vec2f dest, float frac) {
		return new Vec2f(this.x + (dest.x - this.x) * frac, this.y
				+ (dest.y - this.y) * frac);
	}

	/**
	 * Returns a normalized (same direction but with a magnitude of 1) version
	 * of this vector.
	 * 
	 * @return a normalized version of this vector.
	 */
	public final Vec2f normalize() {
		assert !isZero();
		float mag = this.mag();
		return new Vec2f(this.x / mag, this.y / mag);
	}

	/**
	 * Returns some vector perpendicular to this one (preserves magnitude)
	 * 
	 * @return a perpendicular vector
	 */
	public final Vec2f perpendicular() {
		return new Vec2f(this.y, -this.x);
	}

	/**
	 * Returns the vector after being reflected about the origin.
	 * 
	 * @return vector reflected about the origin
	 */
	public final Vec2f reflect() {
		return new Vec2f(-this.x, -this.y);
	}

	/**
	 * Returns the projection of the point represented by this vector onto a
	 * line specified by {@code p1} and {@code p2}, two points on the line.
	 * 
	 * @param p1
	 *            a point on the line onto which to project
	 * @param p2
	 *            another point on the line onto which to project
	 * @return the projection of the point represented by this vector onto a
	 *         line specified by {@code p1} and {@code p2}
	 */
	public final Vec2f projectOntoLine(Vec2f p1, Vec2f p2) {
		Vec2f between = p2.minus(p1);
		return p1.plus(between.smult(this.minus(p1).dot(between)
				/ between.mag2()));
	}

	/**
	 * Returns the component of this vector along the axis specified by
	 * {@code other}.
	 * 
	 * @param other
	 *            the axis onto which to project this vector
	 * @return the projection of this vector in the axis specified by
	 *         {@code other}
	 */
	public final Vec2f projectOnto(Vec2f other) {
		return other.smult(this.dot(other) / other.mag2());
	}

	/*
	 * Scalar ops
	 */

	/**
	 * Gets the angle this vector forms with the x axis.
	 * 
	 * @return the angle this vector forms with the x axis, in the range
	 *         [0,2*PI)
	 */
	public final float angle() {
		assert !this.isZero();
		float angle = (float) Math.atan2(this.y, this.x);
		return angle < 0 ? angle + (float) (2 * Math.PI) : angle;
	}

	/**
	 * Returns the dot product of this vector and {@code v}.
	 * 
	 * @param v
	 *            the vector with which to take the dot product
	 * @return the dot product of this vector and {@code v}
	 */
	public final float dot(Vec2f v) {
		return this.x * v.x + this.y * v.y;
	}

	/**
	 * Primitive version of {@link #dot(Vec2f)}
	 */
	public final float dot(float x, float y) {
		return x * this.x + y * this.y;
	}

	/**
	 * Returns the two-dimensional cross product of this vector and {@code v},
	 * which will be positive if the result is in the positive z-direction
	 * ("out of the plane") and negative if the result is in the negative z-
	 * direction ("into the plane").
	 * 
	 * @param v
	 *            the vector with which to take the cross product
	 * @return the cross-product of this vector and {@code v}
	 */
	public final float cross(Vec2f v) {
		return this.x * v.y - this.y * v.x;
	}

	/**
	 * Primitive version of {@link #cross(Vec2f)}.
	 */
	public final float cross(float x, float y) {
		return this.x * y - this.y * x;
	}

	/**
	 * Returns whether or not the magnitude of this vector is zero.
	 * 
	 * @return true if the magnitude of this vector is zero, false otherwise
	 */
	public final boolean isZero() {
		return this.x == 0 && this.y == 0;
	}

	/**
	 * Returns the SQUARED magnitude (also called the length) of this vector.
	 * Useful for a performance increase whenever magnitudes are only being
	 * compared, relatively, rather than when the actual magnitude is needed.
	 * 
	 * @return the magnitude (length) of this vector, squared
	 */
	public final float mag2() {
		return this.x * this.x + this.y * this.y;
	}

	/**
	 * Returns the magnitude (also called the length) of this vector. If the
	 * magnitude will only be used for comparison (i.e. if the actual magnitude
	 * is not needed), consider using {@link #mag2() mag2} instead.
	 * 
	 * @return the magnitude (length) of this vector
	 */
	public final float mag() {
		return (float) Math.sqrt(this.mag2());
	}

	/**
	 * Returns the SQUARED distance between this vector and {@code v}. Useful
	 * for a performance increase whenever distances are only being compared,
	 * relatively, rather than when the actual distance is needed.
	 * 
	 * @param v
	 *            the vector to get the squared distance between
	 * @return the distance between this vector and {@code v}, squared
	 */
	public final float dist2(Vec2f v) {
		float dx = this.x - v.x;
		float dy = this.y - v.y;
		return dx * dx + dy * dy;
	}

	/**
	 * Primitive version of {@link #dist2(Vec2f)}
	 */
	public final float dist2(float x, float y) {
		float dx = this.x - x;
		float dy = this.y - y;
		return dx * dx + dy * dy;
	}

	/**
	 * Returns the distance between this vector and {@code v}. If this distance
	 * will only be used for comparison (i.e. if the actual distance is not
	 * needed), consider using {@link #dist2(Vec2f) dist2} instead.
	 * 
	 * @param v
	 *            the vector to get the distance between
	 * @return the distance between this vector and {@code v}
	 */
	public final float dist(Vec2f v) {
		return (float) Math.sqrt(this.dist2(v));
	}

	/**
	 * Primitive version of {@link #dist(Vec2f)}
	 */
	public final float dist(float x, float y) {
		return (float) Math.sqrt(this.dist2(x, y));
	}

	/*
	 * Object overrides
	 */

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.x);
		result = prime * result + Float.floatToIntBits(this.y);
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj == null || obj.getClass() != Vec2f.class)
			return false;
		Vec2f other = (Vec2f) obj;
		return Float.floatToIntBits(this.x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(this.y) == Float
						.floatToIntBits(other.y);
	}

	@Override
	public final String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	public Vec2i toVec2i() {
		return new Vec2i((int) x, (int) y);
	}
}
