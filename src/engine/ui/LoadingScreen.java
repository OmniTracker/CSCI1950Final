package game;

import engine.Application;
import engine.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;

public class LoadingScreen extends Screen {

	private Screen to;
	private static int TOTAL_TIME = 100; // Around 3 seconds
	private Timer _timer = new Timer(this,_a.getWindowSize(),TOTAL_TIME);
	private Image _image;
	
	public LoadingScreen(Application a, Screen to) {
		super(a);
		this.to = to;
		_image = new Image("file:src/resources/Back.gif");
	}
	
	public void transition() {
		_a.setScreen(to);
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		_timer.onTick(nanosSincePreviousTick);
	}

	@Override
	public void onDraw(GraphicsContext g) {
		g.drawImage(_image, 0, 0,_a.getWindowSize().x,_a.getWindowSize().y);
		_timer.onDraw(g);
		
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
	}

	@Override
	public void onMousePressed(MouseEvent e) {
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
	}

	@Override
	public void onMouseDragged(MouseEvent e) {
	}

	@Override
	public void onMouseMoved(MouseEvent e) {
	}

	@Override
	public void onMouseWheelMoved(ScrollEvent e) {
	}

	@Override
	public void onFocusChanged(boolean newVal) {
	}

	@Override
	public void onResize(Vec2d newSize) {
	}

	@Override
	public void setMouseLocation(Vec2d l) {
	}

}
