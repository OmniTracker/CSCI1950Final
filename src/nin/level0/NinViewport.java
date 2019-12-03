package nin.level0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;
import engine.ui.ViewportHandler;

public class NinViewport extends ViewportHandler{

	public NinViewport(Application parent, GameWorld gameWorld, Vec2d origin,
			Vec2d size) {
		super(parent, gameWorld, origin, size);
	}
	
	private void genericBackground(GraphicsContext g) {
		g.setFill(Color.RED);
		Vec2d size = this.getAspect().getCurrentScreenSize();
		g.fillRect(0,0, size.x, size.y );
	}
	
	
	public void onMouseClicked(MouseEvent e) {
		this.getGameWorld().onMouseClicked(e);
		
		System.out.print("view \n");

	}
	
	
	public void onDraw(GraphicsContext g) {
		this.genericBackground(g);		
		this.getGameWorld().onDraw(g);	
	}
	public void onTick(long nanosSincePreviousTick) {		
		this.getGameWorld().onTick(nanosSincePreviousTick);
	}
	
	public void onKeyPressed(KeyEvent e)  {
		this.getGameWorld().onKeyPressed(e);
	}
}
