package wizard.engine.ecs.systems;

import support.Vec2d;
import wizard.engine.ecs.component.GameBoardDataComponent;
import wizard.engine.ecs.component.GameBoardComponent;
import wizard.engine.ecs.component.GraphicsComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import engine.Application;
import engine.GameWorld;
import engine.systems.Systems;

public class GraphicsSystem extends Systems  {
	private GameBoardDataComponent _gameBoardComponent;
	private GraphicsComponent  _graphicsComponent;
	public GraphicsSystem (Application app, GameWorld gameWorld){
		super(app,gameWorld);
		this.setSystemName("Graphics");
		this.setGameBoardComponent( new GameBoardComponent(app,gameWorld));
		this.setGraphicsComponent( new GraphicsComponent(app,gameWorld));
	}
	public void onDraw(GraphicsContext g)  {
		this.run(g);
	}
	public void run(GraphicsContext g) {
		this.getGameBoardComponent().onDraw(g);
		this.getGraphicsComponent().onDraw(g);
		this.drawMiniMap(g);
	}
	private void drawMiniMap(GraphicsContext g) 
	{
		Vec2d temp = new Vec2d( this.getGameWorld().getOrigin());
		this.getGameWorld().setOrigin(new Vec2d(0,0));
		
		g.setFill(Color.WHITE);
		if (this.getGameWorld().getLevel() == 0) 	
		{
			g.setGlobalAlpha(0.75);
			Vec2d mapFrame = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin().plus(-50,this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize().y - 300); 
			g.fillRect(mapFrame.x  + (this.getGameWorld().getOrigin().y * 0.15), mapFrame.y + (this.getGameWorld().getOrigin().x * 0.15), 550, 400);
			g.setGlobalAlpha(1);
			g.save();
			g.translate(mapFrame.x + 20, mapFrame.y);
		}
		
		if (this.getGameWorld().getLevel() == 1) 
		{
			g.setGlobalAlpha(0.75);
			Vec2d mapFrame = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin().plus(-70,this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize().y - 400); 
			g.fillRect(mapFrame.x  + (this.getGameWorld().getOrigin().y * 0.15), mapFrame.y + (this.getGameWorld().getOrigin().x * 0.15), 450, 400);
			g.setGlobalAlpha(1);
			g.save();
			g.translate(mapFrame.x + 10, mapFrame.y - 50);
		}
		g.scale(0.15, 0.15);
		
		this.getGameBoardComponent().onDraw(g);
		
		this.getGraphicsComponent()._mini = true;
		this.getGraphicsComponent().onDraw(g);
		this.getGraphicsComponent()._mini = false;
		g.restore();
		
		this.getGameWorld().setOrigin(temp);
	}
	private GameBoardDataComponent getGameBoardComponent() {
		return _gameBoardComponent;
	}
	private void setGameBoardComponent(GameBoardDataComponent _gameBoardComponent) {
		this._gameBoardComponent = _gameBoardComponent;
	}
	private GraphicsComponent getGraphicsComponent() {
		return _graphicsComponent;
	}
	private void setGraphicsComponent(GraphicsComponent _graphicsComponent) {
		this._graphicsComponent = _graphicsComponent;
	}
}