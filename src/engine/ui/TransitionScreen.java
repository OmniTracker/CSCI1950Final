package game;

import engine.Application;
import engine.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;

public class TransitionScreen extends Screen {

	private Screen from;
	private Screen to;
	private static double TOTAL_TIME = 50; // Around 1.5 seconds
	private double time = 0;
	
	public TransitionScreen(Application a, Screen from, Screen to) {
		super(a);
		this.from = from;
		this.to = to;
	}
	
	public void transition() {
		_a.setScreen(to);
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		time++;
		if (time>TOTAL_TIME) {
			_a.setScreen(to);
		}
		
		if (time<TOTAL_TIME/2) {
			this.from.onTick(nanosSincePreviousTick);
		} else {
			this.to.onTick(nanosSincePreviousTick);
		}
	}

	@Override
	public void onDraw(GraphicsContext g) {
		
		if (time<TOTAL_TIME) {
			g.setGlobalAlpha((TOTAL_TIME-time)/TOTAL_TIME);
			this.from.onDraw(g);
			
		} else {
			g.setGlobalAlpha(1);
			this.to.onDraw(g);
		}
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
