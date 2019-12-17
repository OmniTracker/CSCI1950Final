package engine.ui;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.CircleShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Slider extends  UIElement {
	private String _name;
	private int _minValue; 
	private int _maxValue;
	private Color _color;
	private double _width;
	private double _height;
	private double _currentPercentage;
	private CircleShape _slidingUnit;
	private Vec2d _sliderStart;
	private AABShape _slidingUnitCollider;
	public Slider(String name, 
			int minValue, 
			int maxValue,
			Color color, 
			Color secondaryColor, 
			double width, 
			double height, 
			double currentPercentage,
			Vec2d center,
			double yOff) {
		this.setName(name);
		this.setMinValue(minValue);
		this.setMaxValue(maxValue);
		this.setColor(color);
		this.setSecondaryColor(secondaryColor);
		this.setWidth(width);
		this.setHeight(height);
		this.setCurrentPercentage(currentPercentage);
		this.setSlidingUnitCollider( new AABShape(new Vec2d (( center.x - (this.getWidth() / 2))+82,center.y + yOff+50), new Vec2d(width-15,height)));
		this.set_slidingUnit(new CircleShape(new Vec2d(center.x+82,center.y+yOff+50),20));
	}
	
	// The center point should be  outside. 
	public void draw (GraphicsContext g, Vec2d centerPoint, double yOffset) {
		Vec2d newSliderStart = new Vec2d (( centerPoint.x - (this.getWidth() / 2)),centerPoint.y + yOffset) ;  
		this.setSliderStart(newSliderStart);
		// Need to draw the actual bar.
		g.setFill(Color.WHITE);
		g.fillRoundRect( newSliderStart.x, newSliderStart.y, this.getWidth(), this.getHeight(), 20, 20);
		// Need to draw the sliding unit
		g.setFill(Color.BLACK);	
		g.fillOval(_slidingUnit.getCenter().x-10,_slidingUnit.getCenter().y, 20, 20);
		// Draw min and max.
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 20 ));
		g.setTextAlign(TextAlignment.CENTER);
		// Min
		g.fillText(String.valueOf(this.getMinValue()), (newSliderStart.x - 25), newSliderStart.y + 20);
		// Max
		g.fillText(String.valueOf(this.getMaxValue()), ((newSliderStart.x + this.getWidth()) + 30) , newSliderStart.y + 20);
		// Label the component. 
		g.fillText(this.getName(), centerPoint.x, (centerPoint.y - 20 + yOffset));
	}
	public boolean clicked (MouseEvent e) {
		Vec2d mouse = new Vec2d (e.getSceneX(), e.getSceneY());  
		return this.isColliding(_slidingUnitCollider, mouse); 
	}
	public boolean isColliding(AABShape s1, Vec2d s2) {
		boolean isCol = pointRect(s2.x,s2.y, s1.getTopLeft().x,  s1.getTopLeft().y, s1.getSize().x, s1.getSize().y); 
		if (isCol) {
			_slidingUnit.setCenter(new Vec2d(s2.x,_slidingUnit.getCenter().y));
			double min = _slidingUnitCollider.getTopLeft().x;
			double max = _slidingUnitCollider.getTopLeft().x+_slidingUnitCollider.getSize().x;
			double diff = max-min;
			double smalldif = s2.x-min;
			this.setCurrentPercentage(smalldif/diff);
		}
		return (isCol);
	}
	boolean pointRect(double px, double py, double rx, double ry, double rw, double rh) {
		if (px >= rx &&        
				px <= rx + rw &&   
				py >= ry &&        
				py <= ry + rh) {  
			return true;
		}
		return false;
	}
	public double percentageBack() { 
		return (this.getMaxValue() / this.getMinValue()) * 100;
	}
	private String getName() {
		return _name;
	}
	private void setName(String _name) {
		this._name = _name;
	}
	private Integer getMinValue() {
		return _minValue;
	}
	private void setMinValue(Integer _minValue) {
		this._minValue = _minValue;
	}
	private Integer getMaxValue() {
		return _maxValue;
	}
	private void setMaxValue(Integer _maxValue) {
		this._maxValue = _maxValue;
	}
	public Color getColor() {
		return _color;
	}
	private double getWidth() {
		return _width;
	}
	private void setWidth(double _width) {
		this._width = _width;
	}
	private double getHeight() {
		return _height;
	}
	private void setHeight(double _height) {
		this._height = _height;
	}
	public void setCurrentPercentage(double _currentPercentage) {
		this._currentPercentage = _currentPercentage;
	}
	public double getCurrentPercentage() {
		return _currentPercentage;
	}
	private void setSliderStart(Vec2d v) {
		if (_sliderStart==null) {
			_sliderStart=v;
		}
		if ((v.x!=_sliderStart.x||v.y!=_sliderStart.y)) {
			_sliderStart = v;
			this.setSlidingUnitCollider(new AABShape(new Vec2d(_sliderStart.x+82,_sliderStart.y+50), new Vec2d(this.getWidth()-15,this.getHeight())));
		}
		
	}
	private void set_slidingUnit(CircleShape circleShape) {
		_slidingUnit=circleShape;
		
	}
	private void setSlidingUnitCollider(AABShape _slidingUnitCollider) {
		this._slidingUnitCollider = _slidingUnitCollider;
	}
}
