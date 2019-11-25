package support;

public final class Interval {
	public double _min;
	public double _max;
	
	public Interval(double min, double max) {
		_min = min;
		_max = max;
	}
	
	public boolean overlap(Interval other) {
		if(other._max >= _min && other._min <= _max) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double overlapVal(Interval other) {
		if(this.overlap(other)) {
			double full_length = other._max-other._min + this._max-this._min;
			double stacked_length = Math.max(this._max, other._max) - Math.min(this._min, other._min);
			return Math.abs(full_length - stacked_length);
		}
		else {
			return 0;
		}
	}
}
