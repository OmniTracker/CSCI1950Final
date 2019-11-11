package wizard.level1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import wizard.level0.WizLevel0GameWorld;
import engine.Application;
import engine.GameWorld;
import engine.ui.ViewportHandler;

public class WizLevel1Viewport extends ViewportHandler {
	WizLevel1Viewport(Application parent, WizLevel1GameWorld gameWorld, Vec2d origin, Vec2d size) {
		super(parent, gameWorld, origin, size);		
	}
	public void onDraw(GraphicsContext g) {
		this.drawBackgroundColor(g);
		this.getGameWorld().onDraw(g);
		this.drawBorder(g);		
	}
	public void onKeyPressed(KeyEvent e)  {
		this.getGameWorld().onKeyPressed(e);
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getGameWorld().onTick(nanosSincePreviousTick);
	}
	private void drawBorder(GraphicsContext g)  {
		Vec2d screenSize = this.getAspect().calculateUpdatedScreenSize();
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		g.setGlobalAlpha(0.7);
		g.setFill(Color.DIMGRAY);
		g.fillRect(origin.x , (origin.y + screenSize.y - 200), screenSize.x, 200);
		g.setGlobalAlpha(1.0);
	}
	private void drawBackgroundColor(GraphicsContext g) {
		Vec2d size = this.getAspect().getCurrentScreenSize();
		g.setFill(Color.DARKGREEN);
		g.fillRect(0,0, size.x, size.y );
		g.setGlobalAlpha(0.75);
		g.setFill(Color.BLACK);
		g.fillRect(0,0, size.x, size.y );
		g.setGlobalAlpha(1.0);
	}
}
