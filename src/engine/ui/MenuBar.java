package engine.ui;

import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import support.collision.AABShape;
import engine.utility.AspectRatioHandler;

public abstract class MenuBar extends UIElement {
	private boolean _menuActivated = false;
	private double _height = 20;
	// White is just the default saved color.
	private AABShape _collisionBox = null;
	// All the buttons that need to be placed on the menu bar
	private HashMap<Integer,Panel> _panelViews = null; 
	private HashMap<String,Button> _button;
	private AspectRatioHandler _aspectRatio; 
	protected MenuBar(AspectRatioHandler aspect) {
		this.setAspectRatio(aspect);	
		this.setButton(new HashMap<String,Button>());
		this.setPanelViews(new HashMap<Integer,Panel>());
		this.setCollisionBox( new AABShape(new Vec2d(0,0), new Vec2d(0,0)));
	}
	MenuBar(AspectRatioHandler aspect, Color color) {
		this.setAspectRatio(aspect);
		this.setColor(color);
		this.setButton( new HashMap<String,Button>());
		this.setPanelViews(new HashMap<Integer,Panel>());
		this.setCollisionBox( new AABShape(new Vec2d(0,0), new Vec2d(0,0)));
	}
	protected MenuBar(AspectRatioHandler aspect, double height ,Color color) {
		this.setAspectRatio(aspect);
		this.setColor(color);
		this.setHeight(height);
		this.setButton( new HashMap<String,Button>());
		this.setPanelViews(new HashMap<Integer,Panel>());
		this.setCollisionBox( new AABShape(new Vec2d(0,0), new Vec2d(0,0)));
	}
	public void draw(GraphicsContext g) {
		this.drawMainBar(g);
		this.drawMenuButtons(g);

		if (this.isMenuActivated()) {
			this.drawPanelView(g);
		}
	}
	private void drawMainBar(GraphicsContext g) {
		if (this.getColor() == null) {
			return;
		}
		g.setFill(Color.DARKGRAY);
		// Set the size of the menu.
		Vec2d menuSize = new Vec2d(this.getAspectRatio().
				calculateUpdatedScreenSize()
				.x, 
				this.getHeight());
		this.setSize(menuSize);
		// Set the position of menu.
		Vec2d menuOrigin = this.getAspectRatio().calculateUpdatedOrigin();
		this.setOrigin(menuOrigin);
		g.fillRect(this.getOrigin().x,
				this.getOrigin().y, 
				this.getSize().x, 
				this.getSize().y);
		// Update Collision boxes 
		this.setCollisionBox( new AABShape(menuOrigin, menuSize));
	}
	public void insertButton(String key, Button button) {	
		this.getButton().put(key, button); 
	}
	public void insertPanel(Integer key, Panel panel) {		
		this.getPanelViews().put(key, panel); 
	}
	private void drawMenuButtons (GraphicsContext g) {		
		Button newButton = null;
		Vec2d  origin = this.getAspectRatio().calculateUpdatedOrigin().plus(5,5); 
		Vec2d  updatedOrigin = origin; 	
		for (Entry<String, Button> mapElement : this.getButton().entrySet()) { 
			newButton  = mapElement.getValue();
			newButton.setOrigin(updatedOrigin);
			newButton.drawRounded(g);
			updatedOrigin = updatedOrigin.plus(( newButton.getSize().x + 5) ,0);   
		} 
	}
	public String checkButtonCollision (MouseEvent e)  {
		for (Entry<String, Button> mapElement : this.getButton().entrySet()) { 
			String key = mapElement.getKey();
			if ( mapElement.getValue().clicked(e) ) {
				return key;             	
			}
		} 
		return ""; 
	}
	public boolean checkMenuCollision (MouseEvent e) {
		Vec2d mouse = new Vec2d (e.getSceneX(), e.getSceneY());  
		return this.isColliding(this.getCollisionBox(), mouse); 
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
	// This function should written in the class inheriting this one
	private void drawPanelView(GraphicsContext g) { }

	public AspectRatioHandler getAspectRatio() {
		return _aspectRatio;
	}
	void setAspectRatio(AspectRatioHandler _aspectRatio) {
		this._aspectRatio = _aspectRatio;
	}
	public double getHeight() {
		return _height;
	}
	public void setHeight(double _height) {
		this._height = _height;
	}
	public AABShape getCollisionBox() {
		return _collisionBox;
	}
	private void setCollisionBox(AABShape _collisionBox) {
		this._collisionBox = _collisionBox;
	}
	private HashMap<String,Button> getButton() {
		return _button;
	}
	private void setButton(HashMap<String,Button> _button) {
		this._button = _button;
	}
	public boolean isMenuActivated() {
		return _menuActivated;
	}
	protected void setMenuActivated(boolean _menuActivated) {
		this._menuActivated = _menuActivated;
	}

	public HashMap<Integer,Panel> getPanelViews() {
		return _panelViews;
	}

	private void setPanelViews(HashMap<Integer,Panel> _panelViews) {
		this._panelViews = _panelViews;
	}
}