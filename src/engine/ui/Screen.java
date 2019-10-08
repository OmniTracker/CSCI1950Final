package engine.ui;

import engine.Application;
import engine.utility.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;

public class Screen implements EventHandler {
	public Application _application;
	private EngineFonts _engineFont;
	private ViewportHandler _viewport;
	
	protected Screen(Application app){
		this.setApplication(app);
		this.setEngineFont(new EngineFonts());
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(GraphicsContext g)  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFocusChanged(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartup() {
		// TODO Auto-generated method stub
		
	}

	public Application getApplication() {
		return _application;
	}

	public void setApplication(Application _application) {
		this._application = _application;
	}

	public EngineFonts getEngineFont() {
		return _engineFont;
	}

	public void setEngineFont(EngineFonts _engineFont) {
		this._engineFont = _engineFont;
	}

	public ViewportHandler getViewport() {
		return _viewport;
	}

	public void setViewport(ViewportHandler _viewport) {
		this._viewport = _viewport;
	}
}
