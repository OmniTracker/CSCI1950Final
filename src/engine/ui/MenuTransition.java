package engine.ui;

import javafx.scene.paint.Color;

public class MenuTransition  extends EngineFonts {
	private Color _fadeInTransitionColor;  
	private double _fadeInTransitionTime;
	private Color _fadeOutTransitionColor;  
	private double _fadeOutTransitionTime;
	boolean _fadeInFlag = false;
	boolean _fadeOutFlag = false;
	MenuTransition () {}
	MenuTransition (Color fadeInTransitionColor, double fadeInTransitionTime) {
		this.setFadeInTransitionColor(fadeInTransitionColor);
		this.setFadeInTransitionTime(fadeInTransitionTime);
	}
	MenuTransition ( Color fadeInTransitionColor, double fadeInTransitionTime, 
			Color fadeOutTransitionColor, double fadeOutTransitionTime) {
		this.setFadeInTransitionColor(fadeInTransitionColor);
		this.setFadeInTransitionTime(fadeInTransitionTime);
		this.setFadeOutTransitionColor(fadeOutTransitionColor);
		this.setFadeOutTransitionTime(fadeOutTransitionTime);
	}
	
	public void triggerFadeIn () {
		
	}
	
	public void triggerFadeOut () {
		
	}
	
	private Color getFadeInTransitionColor() {
		return _fadeInTransitionColor;
	}
	private void setFadeInTransitionColor(Color _fadeInTransitionColor) {
		this._fadeInTransitionColor = _fadeInTransitionColor;
	}
	private double getFadeInTransitionTime() {
		return _fadeInTransitionTime;
	}
	private void setFadeInTransitionTime(double _fadeInTransitionTime) {
		this._fadeInTransitionTime = _fadeInTransitionTime;
	}
	private Color getFadeOutTransitionColor() {
		return _fadeOutTransitionColor;
	}
	private void setFadeOutTransitionColor(Color _fadeOutTransitionColor) {
		this._fadeOutTransitionColor = _fadeOutTransitionColor;
	}
	private double getFadeOutTransitionTime() {
		return _fadeOutTransitionTime;
	}
	private void setFadeOutTransitionTime(double _fadeOutTransitionTime) {
		this._fadeOutTransitionTime = _fadeOutTransitionTime;
	}	
}