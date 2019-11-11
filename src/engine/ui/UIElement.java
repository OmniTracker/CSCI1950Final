package engine.ui;

import javafx.scene.paint.Color;
import support.Vec2d;

public abstract class UIElement extends EngineFonts {
	private Vec2d _size;
	private Color _color;
	private Color _secondaryColor;
	protected Vec2d _origin;
	private String _text;
	private EngineFonts _engineFont;
	
	protected UIElement(){ 
			this.setEngineFont(new EngineFonts());
	}
	
	public Color getColor() {
		return _color;
	}
	public void setColor(Color _color) {
		this._color = _color;
	}
	public Vec2d getOrigin() {
		return _origin;
	}
	public void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}
	public Vec2d getSize() {
		return _size;
	}
	public void setSize(Vec2d _size) {
		this._size = _size;
	}
	public String getText() {
		return _text;
	}
	public void setText(String _text) {
		this._text = _text;
	}
	public EngineFonts getEngineFont() {
		return _engineFont;
	}
	public void setEngineFont(EngineFonts _font) {
		this._engineFont = _font;
	}
	public Color getSecondaryColor() {
		return _secondaryColor;
	}
	public void setSecondaryColor(Color _secondaryColor) {
		this._secondaryColor = _secondaryColor;
	}
}

/*
package engine.ui;

import javafx.scene.paint.Color;
import support.Vec2d;

public abstract class UIElement extends EngineFonts {
	private Vec2d _size;
	private Color _color;
	private Color _secondaryColor;
	protected Vec2d _origin;
	private String _text;
	private EngineFonts _engineFont;
	
	protected UIElement(){ 
			this.setEngineFont(new EngineFonts());
	}
	
	public Color getColor() {
		return _color;
	}
	public void setColor(Color _color) {
		this._color = _color;
	}
	public Vec2d getOrigin() {
		return _origin;
	}
	public void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}
	public Vec2d getSize() {
		return _size;
	}
	public void setSize(Vec2d _size) {
		this._size = _size;
	}
	public String getText() {
		return _text;
	}
	public void setText(String _text) {
		this._text = _text;
	}
	public EngineFonts getEngineFont() {
		return _engineFont;
	}
	public void setEngineFont(EngineFonts _font) {
		this._engineFont = _font;
	}
	public Color getSecondaryColor() {
		return _secondaryColor;
	}
	public void setSecondaryColor(Color _secondaryColor) {
		this._secondaryColor = _secondaryColor;
	}
}
*/