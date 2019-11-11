package alchemy.level0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;
import engine.ui.ViewportHandler;

public class ALCViewport extends ViewportHandler {
	protected ALCViewport(Application parent, GameWorld gameWorld, Vec2d origin,
			Vec2d size) {
		super(parent, gameWorld, origin, size);
		this.setAspect(this.getParent().getAspectRatioHandler());
	}
	public void drawViewportFrame(GraphicsContext g) {
		g.setFill(Color.DARKMAGENTA);
		g.fillRect(0 , 0  ,this.getAspect().getCurrentScreenSize().x,this.getAspect().getCurrentScreenSize().y);
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getGameWorld().onTick(nanosSincePreviousTick);
	}
	public void onMouseClicked(MouseEvent e) {
		this.getGameWorld().onMouseClicked(e);
	}
	public void onDraw(GraphicsContext g) {
		this.drawViewportFrame(g);
		this.getGameWorld().onDraw(g);
	}
	public void onMouseDragged(MouseEvent e){
		this.getGameWorld().onMouseDragged(e);
	}
	public void onMouseWheelMoved(ScrollEvent e) {
		this.getGameWorld().onMouseWheelMoved(e);
	}
}