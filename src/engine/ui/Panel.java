package engine.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import support.collision.AABShape;
import engine.utility.AspectRatioHandler;

public abstract class Panel extends UIElement {	
	private AspectRatioHandler _aspectRatio;
	private AABShape _collisionBox;
	private String _fontName = ""; 
	private Button _closeButton;
	private double _boarderSize = 0; 
	private boolean _showing;
	private Button _applyButton;
	private Button _okButton;
	
	protected Panel (AspectRatioHandler app) {
		this.setAspectRatio(app);
		this.setCloseButton(new Button());
		this.setApplyButton(new Button());
		this.setOKButton(new Button());
		this.setFontName(EngineFonts.getWiz());
		this.setCollisionBox( new AABShape(new Vec2d(0,0), new Vec2d(0,0)));
	}
	
	public void drawRounded(GraphicsContext g) {	
		// If this is drawing, this is showing 
		this.setShowing(true);				
		Vec2d sceneSize     = this.getAspectRatio().calculateUpdatedScreenSize();
		Vec2d sceneMidPoint = this.getAspectRatio().calculateUpdatedOrigin().plus( (sceneSize.x / 2) , (sceneSize.y / 2));		
		this.setOrigin(sceneMidPoint.minus((this.getSize().x / 2),(this.getSize().y / 2)));
		// Draw Boarder
		g.setFill(this.getSecondaryColor());
		g.fillRoundRect(this.getOrigin().x - this.getBoarderSize(),
				this.getOrigin().y - this.getBoarderSize(), 
				this.getSize().x + (this.getBoarderSize() * 2), 
				this.getSize().y + (this.getBoarderSize() * 2),
				20,
				20);
		
		// Draw main panel
		g.setFill(this.getColor());
		g.fillRoundRect(this.getOrigin().x,this.getOrigin().y, this.getSize().x , this.getSize().y,20,20);
		this.setCollisionBox( new AABShape(this.getOrigin(),this.getSize()));
		
		// Draw close button. I will assume the close button will always be to the right
		// bottom of the Panel View.		
		this.getCloseButton().setColor(Color.WHITE);
		this.getCloseButton().setSize(new Vec2d(50,20));
		this.getCloseButton().setOrigin( this.getOrigin().plus(this.getSize()).minus(60, 30));
		this.getCloseButton().setFontName(EngineFonts.getWiz());
		this.getCloseButton().setText("Close");
		this.getCloseButton().drawRounded(g);
	}
	public void drawApply(GraphicsContext g) {
		this.getApplyButton().setColor(Color.WHITE);
		this.getApplyButton().setSize(new Vec2d(50,20));
		this.getApplyButton().setOrigin( this.getOrigin().plus(this.getSize()).minus(140, 30));
		this.getApplyButton().setFontName(EngineFonts.getWiz());
		this.getApplyButton().setText("Apply");
		this.getApplyButton().drawRounded(g);
	}
	public void drawYes(GraphicsContext g) {
		this.getOKButton().setColor(Color.WHITE);
		this.getOKButton().setSize(new Vec2d(50,20));
		this.getOKButton().setOrigin( this.getOrigin().plus(this.getSize()).minus(140, 30));
		this.getOKButton().setFontName(EngineFonts.getWiz());
		this.getOKButton().setText("Yes");
		this.getOKButton().drawRounded(g);
	}
	public void drawSubmit(GraphicsContext g) {
		this.getOKButton().setColor(Color.WHITE);
		this.getOKButton().setSize(new Vec2d(50,20));
		this.getOKButton().setOrigin( this.getOrigin().plus(this.getSize()).minus(140, 30));
		this.getOKButton().setFontName(EngineFonts.getWiz());
		this.getOKButton().setText("Submit");
		this.getOKButton().drawRounded(g);
	}
	public void onMouseClicked(MouseEvent e) {		
		if (this.checkPanelCollision(e) == true) {
			if ( this.getCloseButton().clicked(e)) {
				this.setShowing(false);				
			}
		}
	}
	public boolean checkPanelCollision (MouseEvent e) {
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
	private AspectRatioHandler getAspectRatio() {
		return _aspectRatio;
	}
	private void setAspectRatio(AspectRatioHandler _aspectRatio) {
		this._aspectRatio = _aspectRatio;
	}
	protected Button getCloseButton() {
		return _closeButton;
	}
	private void setCloseButton(Button _closeButton) {
		this._closeButton = _closeButton;
	}
	protected Button getApplyButton() {
		return _applyButton;
	}
	private void setApplyButton(Button _closeButton) {
		this._applyButton = _closeButton;
	}
	public String getFontName() {
		return _fontName;
	}
	public void setFontName(String _fontName) {
		this._fontName = _fontName;
	}
	private double getBoarderSize() {
		return _boarderSize;
	}
	public void setBoarderSize(double _boarderSize) {
		this._boarderSize = _boarderSize;
	}
	private AABShape getCollisionBox() {
		return _collisionBox;
	}
	private void setCollisionBox(AABShape _collisionBox) {
		this._collisionBox = _collisionBox;
	}
	public boolean isShowing() {
		return _showing;
	}
	public void setShowing(boolean _showing) {
		this._showing = _showing;
	}
	public Button getOKButton() {
		return _okButton;
	}
	private void setOKButton(Button _okButton) {
		this._okButton = _okButton;
	}	
}