package finalgame.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import support.Vec2d;
import engine.ui.Panel;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class EndGamePanel extends Panel implements EventHandler {
	private boolean _gameQuit  = false; 

	public EndGamePanel(AspectRatioHandler app) {
		super(app);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub

	}
	public void onDraw(GraphicsContext g) {	
		this.drawRounded(g);
		Vec2d size   = this.getSize();
		Vec2d origin = this.getOrigin();
		Vec2d center = 	origin.plus( (size.x / 2),(size.y / 2));
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 40 ));
		g.fillText("Are you sure?", center.x, center.y - 20);
		this.drawYes(g);
	}
	public void onKeyTyped(KeyEvent e) {}
	public void onKeyPressed(KeyEvent e) {}
	public void onKeyReleased(KeyEvent e) {}
	public void onMousePressed(MouseEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}
	public boolean isGameQuit() {
		return _gameQuit;
	}
	public void setGameQuit(boolean _gameQuit) {
		this._gameQuit = _gameQuit;
	}
}