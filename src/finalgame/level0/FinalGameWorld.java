package finalgame.level0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;

public class FinalGameWorld  extends GameWorld {
	int score = 0;
	protected FinalGameWorld(Application app) {
		super(app);
		// TODO Auto-generated constructor stub
	}
	public void onTick(long nanosSincePreviousTick) {}
	public void onDraw(GraphicsContext g) {}
	public void onMouseClicked(MouseEvent e) {}	
	public void onKeyPressed(KeyEvent e)  {}
	@Override
	public void onShutdown() {
		// write score to file
	}
}
