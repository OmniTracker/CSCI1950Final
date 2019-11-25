package engine.ui;

import support.Vec2d;
import support.collision.AABShape;
import javafx.scene.canvas.GraphicsContext;
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
	private double _slidingUnit;
	private AABShape _slidingUnitCollider;
	public Slider(String name, 
			int minValue, 
			int maxValue,
			Color color, 
			Color secondaryColor, 
			double width, 
			double height, 
			double currentPercentage) {
		this.setName(name);
		this.setMinValue(minValue);
		this.setMaxValue(maxValue);
		this.setColor(color);
		this.setSecondaryColor(secondaryColor);
		this.setWidth(width);
		this.setHeight(height);
		this.setCurrentPercentage(currentPercentage);
		this.setSlidingUnitCollider( new AABShape(new Vec2d(0,0), new Vec2d(0,0)));
	}
	// The center point should be  outside. 
	public void draw (GraphicsContext g, Vec2d centerPoint, double yOffset) {
		Vec2d newSliderStart = new Vec2d (( centerPoint.x - (this.getWidth() / 2)),centerPoint.y + yOffset) ;  
		// Need to draw the actual bar.
		g.setFill(Color.WHITE);
		g.fillRoundRect( newSliderStart.x, newSliderStart.y, this.getWidth(), this.getHeight(), 20, 20);
		// Need to draw the sliding unit
		g.setFill(Color.BLACK);	
		g.fillOval(centerPoint.x,centerPoint.y + yOffset, 20, 20);
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
	private void setCurrentPercentage(double _currentPercentage) {
		this._currentPercentage = _currentPercentage;
	}
	private void setSlidingUnitCollider(AABShape _slidingUnitCollider) {
		this._slidingUnitCollider = _slidingUnitCollider;
	}
}
