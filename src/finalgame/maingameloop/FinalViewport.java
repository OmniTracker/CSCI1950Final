package finalgame.maingameloop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;
import engine.ui.ViewportHandler;
import finalgame.ui.FinalMenuBar;

public class FinalViewport extends ViewportHandler {
	private FinalMenuBar _menuBar = null; 
	public FinalViewport(Application parent, GameWorld gameWorld, Vec2d origin,
			Vec2d size) {
		super(parent, gameWorld, origin, size);
		this.setMenuBar( new FinalMenuBar( this.getAspect()));
		this.getMenuBar().setGameWorld((FinalGameWorld)gameWorld);
	}
	public void onDraw(GraphicsContext g) {		
		this.getGameWorld().onDraw(g);
		this.getMenuBar().onDraw(g);
	}
	public void onMouseClicked(MouseEvent e) {
		this.getMenuBar().onMouseClicked(e);
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onMouseClicked(e);
		}
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getMenuBar().onTick(nanosSincePreviousTick);
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onTick(nanosSincePreviousTick);			
		}
	}
	public void onKeyPressed(KeyEvent e)  {
		this.getMenuBar().onKeyPressed(e);
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onKeyPressed(e);
		}		
	}
	public void onMouseDragged(MouseEvent e) {
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onMouseDragged(e);
		}
	}
	public void onKeyReleased(KeyEvent e) {
		this.getGameWorld().onKeyReleased(e);	
	}
	public void onMousePressed(MouseEvent e) {
		this.getGameWorld().onMousePressed(e);
	}
	public void onMouseReleased(MouseEvent e) {
		this.getGameWorld().onMouseReleased(e);
	}
	public void onMouseMoved(MouseEvent e) {
		this.getGameWorld().onMouseMoved(e);
	}
	public void onMouseWheelMoved(ScrollEvent e) {
		this.getGameWorld().onMouseWheelMoved(e);
	}
	public void onFocusChanged(boolean newVal) {
		this.getGameWorld().onFocusChanged(newVal);
	}
	public void onResize(Vec2d newSize) {
		this.getGameWorld().onResize(newSize);
	}
	public void onStartup() {
		this.getGameWorld().onStartup();
	}
	public FinalMenuBar getMenuBar() {
		return _menuBar;
	}
	public void setMenuBar(FinalMenuBar _menuBar) {
		this._menuBar = _menuBar;
	}
}
