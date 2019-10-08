package wizard.level0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.ui.Screen;
import engine.ui.ViewportHandler;

public class WIZViewport extends ViewportHandler {

	WIZViewport(Application parent, GameWorld gameWorld, Vec2d origin, Vec2d size) 
	{
		super(parent, gameWorld, origin, size);
	}

	public void onDraw(GraphicsContext g) 
	{
		this.getGameWorld().onDraw(g);
		drawBorder(g);		
	}

	private void drawBorder(GraphicsContext g) 
	{
		Vec2d screenSize = this.getAspect().calculateUpdatedScreenSize();
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();

		g.setFill(Color.FORESTGREEN);

		g.fillRect(origin.x,
				origin.y, 
				(screenSize.x / 6),
				screenSize.y);
		
		g.fillRect(origin.x + ( (screenSize.x / 6) * 5), 
				origin.y, 
				(screenSize.x / 6), 
				screenSize.y);
		
		double buffer = 10;
		g.fillRect((screenSize.x / 6) + origin.x - buffer, 
				origin.y, 
				(buffer * 2) + ((screenSize.x / 6) * 4)
				,screenSize.y / 10); 
		
		
		g.fillRect((screenSize.x / 6) + origin.x - buffer,   
				origin.y + (screenSize.y / 10) * 9,  
				(buffer * 2) + ((screenSize.x / 6) * 4), 
				screenSize.y / 10);
	}
	
	public void onTick(long nanosSincePreviousTick)
	{
		this.calcWizViewportBounds();
		this.getGameWorld().onTick(nanosSincePreviousTick);
	}

	private void calcWizViewportBounds () 
	{
		Vec2d screenSize = this.getAspect().calculateUpdatedScreenSize();
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		this.setOrigin(new Vec2d((origin.x +  (screenSize.x / 6)),
				(origin.y + (screenSize.y / 10))));
		this.setSize(new Vec2d( (screenSize.x - ((screenSize.x / 6) * 2)), 
				(screenSize.y - ((screenSize.y / 10) * 2))));
		this.setViewBound(new AABShape(this.getOrigin(),this.getSize()));
	}
}
