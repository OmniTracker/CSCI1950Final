package engine.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;
import support.collision.AABShape;
import support.collision.Collision;
import engine.Application;
import engine.GameWorld;
import engine.utility.AspectRatioHandler;

public abstract class ViewportHandler {
	protected Vec2d _origin;
	protected Vec2d _size;
	protected GameWorld _gameWorld;
	protected Application _parent;
	private AspectRatioHandler _aspect;
	protected AABShape _viewBound; 
	protected Collision _collision;
	public ViewportHandler (Application parent, 
			GameWorld gameWorld,
			Vec2d origin, 
			Vec2d size) {
		this.setParent(parent);
		this.setGameWorld(gameWorld);
		this.setOrigin(origin);
		this.setSize(size);
		this.setViewBound(new AABShape(origin,size));
		this.setCollision(new Collision());
		this.setAspect(this.getParent().getAspectRatioHandler());
	}
	protected boolean isMouseInFrame (MouseEvent e) {
		double x = e.getSceneX(); 
		double y = e.getSceneY(); 
		this.getCollision();
		return Collision.isColliding(this.getViewBound(),new Vec2d(x,y)); 
	}
	protected boolean isMouseInFrame (ScrollEvent e) {
		double x = e.getSceneX(); 
		double y = e.getSceneY(); 
		this.getCollision();
		return Collision.isColliding(this.getViewBound(),new Vec2d(x,y)); 
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getGameWorld().onTick(nanosSincePreviousTick);
	}
	public void onDraw(GraphicsContext g)  {
		this.getGameWorld().onDraw(g);
	}
	public void onMouseClicked(MouseEvent e) {
		if (isMouseInFrame(e)) {
			this.getGameWorld().onMouseClicked(e);
		}
	}
	public void onKeyPressed(KeyEvent e)  {
		this.getGameWorld().onKeyPressed(e);
	}
	public void onMousePressed(MouseEvent e) {
		this.getGameWorld().onMousePressed(e);
	}
	public void onMouseReleased(MouseEvent e) {
		this.getGameWorld().onMouseReleased(e);
	}
	public void onMouseWheelMoved(ScrollEvent e) {
		this.getGameWorld().onMouseWheelMoved(e);
	}
	public void onMouseDragged(MouseEvent e) {
		this.getGameWorld().onMouseDragged(e);
	}
	public void onMouseMoved(MouseEvent e) {
		this.getGameWorld().onMouseMoved(e);
	}
	protected Vec2d getOrigin() {
		return _origin;
	}
	public void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}
	protected Vec2d getSize() {
		return _size;
	}
	protected void setSize(Vec2d _size) {
		this._size = _size;
	}
	protected GameWorld getGameWorld() {
		return _gameWorld;
	}
	protected void setGameWorld(GameWorld gameWorld) {
		this._gameWorld = gameWorld;
	}
	protected Application getParent() {
		return _parent;
	}
	protected void setParent(Application _parent) {
		this._parent = _parent;
	}
	protected AABShape getViewBound() {
		return _viewBound;
	}
	public void setViewBound(AABShape _viewBound) {
		this._viewBound = _viewBound;
	}
	protected Collision getCollision() {
		return _collision;
	}
	protected void setCollision(Collision _collision) {
		this._collision = _collision;
	}
	protected AspectRatioHandler getAspect() {
		return _aspect;
	}
	protected void setAspect(AspectRatioHandler _aspect) {
		this._aspect = _aspect;
	}
}
