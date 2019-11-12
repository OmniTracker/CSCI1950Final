package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import engine.UIElement;
import support.Vec2d;

public class Timer extends UIElement {
	
	private LoadingScreen _parent;
	private Vec2d _location;
	private Vec2d _size;
	private double _timerRatio = 0;
	private double total_time;

	public Timer(LoadingScreen parent, Vec2d windowSize, int time) {
		super(parent, windowSize);
		_parent = parent;
		double length = .75*Math.min(windowSize.x,windowSize.y);
		_size = new Vec2d(length,length/10);
		_location = new Vec2d(windowSize.sdiv(2).minus(new Vec2d(.5*_size.x,-windowSize.y*.35)));
		total_time = time;
	}

	@Override
	protected void onTick(long nanosSincePreviousTick) {
		
		_timerRatio += 1/total_time;
		if (_timerRatio>=1) {
			_parent.transition();
		}
	}

	@Override
	protected void onDraw(GraphicsContext g) {
		
		g.setStroke(Color.WHITE);
		g.strokeRect(_location.x, _location.y, _size.x, _size.y);
		
		g.setFill(Color.WHITE);
		g.fillRect(_location.x,_location.y,_timerRatio*_size.x,_size.y);	
	}

	@Override
	protected void onKeyTyped(KeyEvent e) {
	}

	@Override
	protected void onKeyPressed(KeyEvent e) {
	}

	@Override
	protected void onKeyReleased(KeyEvent e) {
	}

	@Override
	protected void onMouseClicked(MouseEvent e) {
	}

	@Override
	protected void onMousePressed(MouseEvent e) {
	}

	@Override
	protected void onMouseReleased(MouseEvent e) {
	}

	@Override
	protected void onMouseDragged(MouseEvent e) {
	}

	@Override
	protected void onMouseMoved(MouseEvent e) {
	}

	@Override
	protected void onMouseWheelMoved(ScrollEvent e) {
	}

	@Override
	protected void onFocusChanged(boolean newVal) {
	}

	@Override
	protected void onResize(Vec2d newSize) {
		double length = .75*Math.min(newSize.x,newSize.y);
		_size = new Vec2d(length,length/10);
		_location = new Vec2d(newSize.sdiv(2).minus(new Vec2d(.5*_size.x,-newSize.y*.35)));
	}

	@Override
	protected Vec2d getLocation() {
		return _location;
	}

	@Override
	protected Vec2d getSize() {
		return _size;
	}
}
