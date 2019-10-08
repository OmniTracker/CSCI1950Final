package engine.utility;

public class ZoomHandler {
	private  Integer _zoomMaxScale;
	private  Integer _zoomMinScale;
	private  Integer _zoomCount;
	private  double  _zoomCurrentScale;
	private  double  _zoomFactory;
	private  double  _setTimeout;
	private  double  _lastTimeUpdate;
	private  boolean _ableToZoom = true;
	public ZoomHandler(Integer zoomMaxScale, 
			Integer zoomMinScale, 
			Integer zoomCount, 
			double zoomCurrentScale, 
			double zoomFactory, 
			double setTimeout) {
		this.setZoomMaxScale(_zoomMaxScale);
		this.setZoomMinScale(zoomMinScale);
		this.setZoomCount(zoomCount);
		this.setZoomCurrentScale(zoomCurrentScale);
		this.setZoomFactory(zoomFactory);
		this.setSetTimeout(setTimeout);
	}
	public boolean isAbleToZoom (long nanosSincePreviousTick ) { 
		if (nanosSincePreviousTick >= (this.getLastTimeUpdate() + this.getSetTimeout())) {
			this.setLastTimeUpdate(nanosSincePreviousTick);
			return false;
		}
		if (this.getZoomCount() >= this.getZoomMaxScale()) {
			return false;
		}
		if (this.getZoomCount() <= this.getZoomMinScale()) {
			return false;
		}
		return true;
	}
	public Integer getZoomMaxScale() {
		return _zoomMaxScale;
	}
	private void setZoomMaxScale(Integer _zoomMaxScale) {
		this._zoomMaxScale = _zoomMaxScale;
	}
	public Integer getZoomMinScale() {
		return _zoomMinScale;
	}
	private void setZoomMinScale(Integer _zoomMinScale) {
		this._zoomMinScale = _zoomMinScale;
	}
	public Integer getZoomCount() {
		return _zoomCount;
	}
	private void setZoomCount(Integer _zoomCount) {
		this._zoomCount = _zoomCount;
	}
	public double getZoomCurrentScale() {
		return _zoomCurrentScale;
	}
	private void setZoomCurrentScale(double _zoomCurrentScale) {
		this._zoomCurrentScale = _zoomCurrentScale;
	}
	public  double getZoomFactory() {
		return _zoomFactory;
	}
	private void setZoomFactory(double _zoomFactory) {
		this._zoomFactory = _zoomFactory;
	}
	private double getLastTimeUpdate() {
		return _lastTimeUpdate;
	}
	private void setLastTimeUpdate(double _lastTimeUpdate) {
		this._lastTimeUpdate = _lastTimeUpdate;
	}
	public double getSetTimeout() {
		return _setTimeout;
	}
	public void setSetTimeout(double _setTimeout) {
		this._setTimeout = _setTimeout;
	}
	public boolean isAbleToZoom() {
		return _ableToZoom;
	}
	public void setAbleToZoom(boolean _ableToZoom) {
		this._ableToZoom = _ableToZoom;
	}
}
