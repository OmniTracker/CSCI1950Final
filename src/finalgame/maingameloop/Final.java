package finalgame.maingameloop;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.Screen;

public class Final extends Screen {
	private FinalViewport _finalViewport 		= null;
	private FinalGameWorld _finalGameWorld 		= null;
	
	boolean startUP = false;
	public Final(Application app) {
		super(app);
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getFinalViewport().onTick(nanosSincePreviousTick);
	}
	public void onMouseDragged(MouseEvent e) {
		this.getFinalViewport().onMouseDragged(e);		
	}
	public void onDraw(GraphicsContext g) {
		if (startUP == false) {
			 onStartup();
		}
		this.getFinalViewport().onDraw(g);
		this.getApplication().borders(g, Color.BLACK);
	}
	public void onKeyPressed(KeyEvent e) {
		this.getFinalViewport().onKeyPressed(e);
	}
	public void onMouseClicked(MouseEvent e) {
		this.getFinalViewport().onMouseClicked(e);
	}
	public void onStartup() {
		this.setFinalGameWorld( new FinalGameWorld(this.getApplication()));
		this.setFinalViewport(new FinalViewport(this.getApplication(), 
				this.getFinalGameWorld(),
				new Vec2d(0,0), 
				new Vec2d(0,0)));

		startUP = true;
		this.getFinalViewport().onStartup();
	}
	public void onKeyReleased(KeyEvent e) {
		this.getFinalViewport().onKeyReleased(e);	
	}
	public void onMousePressed(MouseEvent e) {
		this.getFinalViewport().onMousePressed(e);
	}
	public void onMouseReleased(MouseEvent e) {
		this.getFinalViewport().onMouseReleased(e);
	}
	public void onMouseMoved(MouseEvent e) {
		this.getFinalViewport().onMouseMoved(e);
	}
	public void onMouseWheelMoved(ScrollEvent e) {
		this.getFinalViewport().onMouseWheelMoved(e);
	}
	public void onFocusChanged(boolean newVal) {
	}
	public void onResize(Vec2d newSize) {
		this.getFinalViewport().onResize(newSize);
	}
	private FinalGameWorld getFinalGameWorld() {
		return _finalGameWorld;
	}
	private void setFinalGameWorld(FinalGameWorld _finalGameWorld) {
		this._finalGameWorld = _finalGameWorld;
	}
	private FinalViewport getFinalViewport() {
		return _finalViewport;
	}
	private void setFinalViewport(FinalViewport _finalViewport) {
		this._finalViewport = _finalViewport;
	}
	@Override
	public void onShutdown() {
	}
}
