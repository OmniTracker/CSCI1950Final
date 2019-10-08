package wizard.level0;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.ui.Screen;
import engine.utility.AspectRatioHandler;

public class Level0 extends Screen {
	private AspectRatioHandler _aspect; 
	private WIZViewport _viewport; 
	

	public Level0(Application app) {
		super(app);
		this.setAspect(app.getAspectRatioHandler());
		Vec2d screenSize = this.getAspect().calculateUpdatedScreenSize();
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
	
		Vec2d newOrigin = new Vec2d((origin.x + (screenSize.x / 6) - 30),
				(origin.y + (screenSize.y / 10) - 30));
		
		this.setViewport(new WIZViewport(app,new GameWorld(app,newOrigin), new Vec2d(0,0), new Vec2d(0,0)));
	}

	public void onKeyPressed(KeyEvent e)  {
		this.getViewport().onKeyPressed(e);
	}

	public void onTick(long nanosSincePreviousTick) {
		this.getViewport().onTick(nanosSincePreviousTick);
	}

	public void onDraw(GraphicsContext g)  {
		this.getViewport().onDraw(g);
		this.getApplication().borders(g, Color.BLACK);
	}

	public void onMouseClicked(MouseEvent e) {
		this.getViewport().onMouseClicked(e);
	}

	public void onMousePressed(MouseEvent e) {
		this.getViewport().onMousePressed(e);
	}

	public void onMouseDragged(MouseEvent e) {
		this.getViewport().onMouseDragged(e);
	}

	public void onMouseMoved(MouseEvent e) {
		this.getViewport().onMouseMoved(e);
	}

	public void onMouseWheelMoved(ScrollEvent e) {
		this.getViewport().onMouseWheelMoved(e);
	}

	public AspectRatioHandler getAspect() {
		return _aspect;
	}

	private void setAspect(AspectRatioHandler _aspect) {
		this._aspect = _aspect;
	}

	public WIZViewport getViewport() {
		return _viewport;
	}

	private void setViewport(WIZViewport _Viewport) {
		this._viewport = _Viewport;
	}
}
