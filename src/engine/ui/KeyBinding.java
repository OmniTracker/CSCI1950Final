package engine.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import support.Vec2d;
import support.collision.AABShape;

public class KeyBinding extends UIElement {
	private String _controlElementName = "";
	private String _currentKeyBindSetting = "";
	private double _width; 
	private double _height; 
	private AABShape _keyBindingShape = null;

	public KeyBinding (String controlElementName, String currentKeyBindSetting, Vec2d buttonSize, Color mainColor, Color secondaryColor, double width, double height) {
		this.setControlElementName(controlElementName);
		this.setCurrentKeyBindSetting(currentKeyBindSetting);
		this.setSize(buttonSize);
		this.setColor(mainColor);
		this.setSecondaryColor(secondaryColor);
		this.setHeight(height);
		this.setWidth(width);
		this.setKeyBindingShape( new AABShape( new Vec2d(0,0), new Vec2d(0,0)) );
	}
	public void drawKeyBindingStuff(GraphicsContext g, Vec2d center, double yOffset) {		
		double moveUp = -18;
		double xSpacing = 150; 
		g.setFill(Color.WHITE);
		g.fillRoundRect( (center.x - xSpacing) - (this.getWidth() / 2) , (center.y + yOffset) + moveUp, this.getWidth(), this.getHeight(), 15, 15);		
		g.fillRoundRect( (center.x + xSpacing) - (this.getWidth() / 2), (center.y + yOffset)  + moveUp, this.getWidth(), this.getHeight(), 15, 15);
		this.setKeyBindingShape(new AABShape( new Vec2d((center.x + xSpacing) - (this.getWidth() / 2), (center.y + yOffset)  + moveUp), 
				new Vec2d(this.getWidth(), this.getHeight())));

		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 20 ));
		g.setTextAlign(TextAlignment.CENTER);
		g.fillText(this.getControlElementName(), (center.x - xSpacing), (center.y + yOffset));
		g.fillText(this.getCurrentKeyBindSetting(),  (center.x + xSpacing), ( center.y + yOffset));		
	}
	public boolean clicked (MouseEvent e) {
		Vec2d mouse = new Vec2d (e.getSceneX(), e.getSceneY());  
		return this.isColliding(this.getKeyBindingShape(), mouse); 
	}
	public boolean isColliding(AABShape s1, Vec2d s2) {
		return pointRect(s2.x,s2.y, s1.getTopLeft().x,  s1.getTopLeft().y, s1.getSize().x, s1.getSize().y); 
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
	public void alterCurrentKeyBinding (String input) {
		this.setCurrentKeyBindSetting(input);
	}
	public  String getControlElementName() {
		return _controlElementName;
	}
	private void setControlElementName(String _controlElementName) {
		this._controlElementName = _controlElementName;
	}

	public String getCurrentKeyBindSetting() {
		return _currentKeyBindSetting;
	}

	private void setCurrentKeyBindSetting(String _currentKeyBindSetting) {
		this._currentKeyBindSetting = _currentKeyBindSetting;
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

	private AABShape getKeyBindingShape() {
		return _keyBindingShape;
	}

	private void setKeyBindingShape(AABShape _keyBindingShape) {
		this._keyBindingShape = _keyBindingShape;
	}


}
