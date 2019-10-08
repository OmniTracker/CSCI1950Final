package alchemy;

import java.net.MalformedURLException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.ui.Screen;
import engine.ui.ViewportHandler;
import engine.utility.AspectRatioHandler;

public class ALCViewport extends ViewportHandler {

	private AspectRatioHandler _aspect; 
	private Image _image;


	protected ALCViewport(Application parent, Screen gameWorld, Vec2d origin,
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

	public void onDraw(GraphicsContext g) {
		this.getGameWorld().onDraw(g);
	}
	
	
	public void onMouseDragged(MouseEvent e)
	{
		this.getGameWorld().onMouseDragged(e);
	}
	
	

	public void onMouseWheelMoved(ScrollEvent e) {
		this.getGameWorld().onMouseWheelMoved(e);
	}

	/* 

	private AspectRatioHandler getAspect() {
		return _aspect;
	}

	private void setAspect(AspectRatioHandler _aspect) {
		this._aspect = _aspect;
	}
	*/ 
}