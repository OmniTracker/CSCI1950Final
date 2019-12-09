package nin.level0;

import nin.ui.NINMenuBar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;
import engine.ui.ViewportHandler;
import engine.utility.Factory;

public class NinViewport extends ViewportHandler {
	private NINMenuBar _menuBar = null; 
	private Image _sky = null; 
	
	public NinViewport(Application parent, GameWorld gameWorld, Vec2d origin,
			Vec2d size) {
		super(parent, gameWorld, origin, size);
		this.setMenuBar( new NINMenuBar( this.getAspect()));
		this.getMenuBar().setGameWorld((NinGameWorld)gameWorld);
	}
	public void onDraw(GraphicsContext g) {
		this.genericBackground(g);		
		this.getGameWorld().onDraw(g);	
		this.getMenuBar().onDraw(g);
	}
	private void genericBackground(GraphicsContext g) {
		
		if (_sky == null) {
			_sky = Factory.getGenericImage("resources/terrain/sky.jpg"); 
		}
		
		
		g.drawImage(_sky, 
				0, 
				0, 
				this.getAspect().calculateUpdatedScreenSize().x,
				this.getAspect().calculateUpdatedScreenSize().y);
	
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
	private NINMenuBar getMenuBar() {
		return _menuBar;
	}
	private void setMenuBar(NINMenuBar _menuBar) {
		this._menuBar = _menuBar;
	}
}
