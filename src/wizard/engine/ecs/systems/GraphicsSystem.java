package wizard.engine.ecs.systems;

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
	private void drawMiniMap(GraphicsContext g) {
		g.setGlobalAlpha(0.3);
		g.setFill(Color.WHITE);
		double x = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin().x + this.getGameWorld().getOrigin().x; 
		double y = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin().y + this.getGameWorld().getOrigin().y;
		double xSize = 0;  
		double ySize = 0; 
		if ( this.getGameWorld().getLevel() == 0) { 
			xSize = 300;  
			ySize = 400; 	
		} else if (this.getGameWorld().getLevel() == 1) {
			xSize = 300;  
			ySize = 380; 
		}
		g.fillRect(x, y, xSize, ySize);
		g.setGlobalAlpha(1.0);
		g.save();
		g.scale(0.15, 0.15);
		
		g.translate(this.getApp().getAspectRatioHandler().calculateUpdatedOrigin().x - 500, 
				this.getApp().getAspectRatioHandler().calculateUpdatedOrigin().y + 400);
		
		this.getGameBoardComponent().onDraw(g);
		this.getGraphicsComponent().onDraw(g);

		g.restore();
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