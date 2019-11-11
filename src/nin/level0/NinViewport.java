package nin.level0;

import nin.ui.NINMenuBar;
import nin.utils.NINDelegateContainer;
import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;
import engine.ui.ViewportHandler;

public class NinViewport extends ViewportHandler {
	private NINMenuBar _menuBar = null; 
	
	public NinViewport(Application parent, GameWorld gameWorld, Vec2d origin,
			Vec2d size) {
		super(parent, gameWorld, origin, size);
		this.setMenuBar( new NINMenuBar( this.getAspect()));
	}
	
	public void onDraw(GraphicsContext g) {		
		this.getGameWorld().onDraw(g);
		this.getMenuBar().onDraw(g);
	}
	
	public void onMouseClicked(MouseEvent e) {
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onMouseClicked(e);
		}
		this.getMenuBar().onMouseClicked(e);

	}

	public void onTick(long nanosSincePreviousTick) {
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onTick(nanosSincePreviousTick);			
		}
		this.getMenuBar().onTick(nanosSincePreviousTick);

	}
	
	public void onKeyPressed(KeyEvent e)  {
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onKeyPressed(e);
		}		
		this.getMenuBar().onKeyPressed(e);
	}
	
	public void onMouseDragged(MouseEvent e) {
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onMouseDragged(e);
		}
		this.getGameWorld().onMouseDragged(e);
	}
	
	public void onShutdown() {
		this.getGameWorld().onShutdown();
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onShutdown();
		}
	}

	public void onStartup() {
		this.getGameWorld().onStartup();
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onStartup();
		}
	}

	
	private NINMenuBar getMenuBar() {
		return _menuBar;
	}
	
	public void setMenuBar(NINMenuBar _menuBar) {
		this._menuBar = _menuBar;
	}
	
	
}
