package nin.ui;

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

public class SaveGamePanel extends Panel implements EventHandler {	
	private boolean _saveGame  = false; 

	protected SaveGamePanel(AspectRatioHandler app) {
		super(app);
	}
	public void onDraw(GraphicsContext g) {	
		this.drawRounded(g);
		Vec2d size   = this.getSize();
		Vec2d origin = this.getOrigin();
		Vec2d center = 	origin.plus( (size.x / 2),(size.y / 2));
		g.setFill(Color.BLACK);	
		g.setFont(Font.font("Ethnocentric", 40 ));
		g.fillText("Are you sure?", center.x, center.y - 20);
		this.drawYes(g);
	}
	public void onTick(long nanosSincePreviousTick) {}
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
	public boolean isSaveGame() {
		return _saveGame;
	}
	public void setSaveGame(boolean _saveGame) {
		this._saveGame = _saveGame;
	}

}
