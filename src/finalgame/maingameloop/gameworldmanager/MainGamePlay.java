package finalgame.maingameloop.gameworldmanager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;

public class MainGamePlay extends GameWorld {

	public MainGamePlay(Application app, GameWorld parent) {
		super(app);
	}

	public void onTick(long nanosSincePreviousTick) {

	}
	
	public void onDraw(GraphicsContext g)  {}
	
	public void onKeyTyped(KeyEvent e) {}
	
	public void onKeyPressed(KeyEvent e) {}
	
	public void onKeyReleased(KeyEvent e) {}
	
	public void onMouseClicked(MouseEvent e) {}
	
	public void onMousePressed(MouseEvent e) {}
	
	public void onMouseReleased(MouseEvent e) {}
	
	public void onMouseDragged(MouseEvent e) {}
	
	public void onMouseMoved(MouseEvent e) {}
	
	public void onMouseWheelMoved(ScrollEvent e) {}
	
	public void onFocusChanged(boolean newVal) {}
	
	public void onResize(Vec2d newSize) {}
	
	public void onShutdown() {}
	
	public void onStartup() {}
}
