package wizard.engine.ecs.component;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class GraphicsComponent  extends Components  {
	private Application _app; 
	private GameWorld _gameWorld;

	public GraphicsComponent(Application app, GameWorld gameWorld) 
	{
		this.setApp(app);
		this.setGameWorld(gameWorld);
		this.setComponentName("Graphics");
	}
	public void onDraw(GraphicsContext g)  
	{
		double x = this.getGameWorld().getOrigin().x + ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
		double y = this.getGameWorld().getOrigin().y + ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 
		
		Vec2d worldOrigin = new Vec2d (x,y);
		
		if (this.getGameWorld().getLevel() == 0) 
		{	
			GameObject enemy0 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy0"); 
			GameObject enemy1 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy1"); 
			if (enemy0.getData().getSystems().contains("Graphics") && enemy0.getData().getComponents().contains("Graphics")) 
			{
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy0").AIDraw(g,worldOrigin);
			}
			if (enemy1.getData().getSystems().contains("Graphics") && enemy1.getData().getComponents().contains("Graphics"))
			{
				
				enemy1._invisble = true;
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy1").AIDraw(g,worldOrigin);
			}
		}		
		if (this.getGameWorld().getLevel() == 1)
		{
			GameObject enemy2 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy2"); 
			GameObject enemy3 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy3"); 
			if (enemy2.getData().getSystems().contains("Graphics") && enemy2.getData().getComponents().contains("Graphics")) 
			{
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy2").AIDraw(g,worldOrigin);
			}
			if (enemy3.getData().getSystems().contains("Graphics") && enemy3.getData().getComponents().contains("Graphics"))
			{
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy3").AIDraw(g,worldOrigin);
			}
		}	
		this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main").draw(g); 
	}
	Application getApp() {
		return _app;
	}
	void setApp(Application _app) {
		this._app = _app;
	}
	GameWorld getGameWorld() {
		return _gameWorld;
	}
	void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
