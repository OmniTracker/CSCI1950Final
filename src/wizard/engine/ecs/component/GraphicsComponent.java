package wizard.engine.ecs.component;

import support.Vec2d;
import wizard.utils.WIZGameObjectDelegate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class GraphicsComponent  extends Components  {
	private Application _app; 
	private GameWorld _gameWorld;
	private static final String WIZ   = "Unsteady Oversteer"; 
	public boolean _mini = false;
	/**
	 * Level 0
	 */
	protected final Integer LEVEL_0_RED_C 	= 18;
	protected final Integer LEVEL_0_RED_R 	= 7;
	protected final Integer LEVEL_0_GREEN_C = 23;
	protected final Integer LEVEL_0_GREEN_R = 11;
	protected final Integer LEVEL_0_BLUE_C 	= 9;
	protected final Integer LEVEL_0_BLUE_R 	= 5;
	/**
	 * Level 1
	 */
	protected final Integer LEVEL_1_RED_C = 19;
	protected final Integer LEVEL_1_RED_R = 3;
	protected final Integer LEVEL_1_GREEN_C = 19;
	protected final Integer LEVEL_1_GREEN_R = 19;
	protected final Integer LEVEL_1_BLUE_C = 13;
	protected final Integer LEVEL_1_BLUE_R = 17;
	public GraphicsComponent(Application app, GameWorld gameWorld) 
	{
		this.setApp(app);
		this.setGameWorld(gameWorld);
		this.setComponentName("Graphics");
	}
	public void onDraw(GraphicsContext g)   
	{
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main");; 
		int x = (int) ((int) this.getGameWorld().getOrigin().x + ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2)); 
		int y = (int) ((int) this.getGameWorld().getOrigin().y + ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2)); 
		WIZGameObjectDelegate gObj = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate(); 
		Vec2d worldOrigin = new Vec2d (x,y);
		if (this.getGameWorld().getLevel() == 0) 
		{	
			int xKey = 0;  
			int yKey = 0;
			if ( main.getData()._level0BlueKeyFound == false)  
			{
				xKey = (100 * (LEVEL_0_BLUE_R)) + x + 20; 
				yKey = (100 * (LEVEL_0_BLUE_C)) + y + 15;
				g.drawImage(gObj.getKeyMap().get("blue"),  yKey, xKey, 60, 60);
			}
			if ( main.getData()._level0GreenKeyFound == false)
			{
				xKey = (100 * (LEVEL_0_GREEN_R)) + x + 20;
				yKey = (100 * (LEVEL_0_GREEN_C)) + y + 15;
				g.drawImage(gObj.getKeyMap().get("green"), yKey, xKey,  60, 60);
			}
			if ( main.getData()._level0RedKeyFound == false)  
			{
				xKey = (100 * (LEVEL_0_RED_R)) + x + 20;
				yKey = (100 * (LEVEL_0_RED_C)) + y + 15;	
				g.drawImage(gObj.getKeyMap().get("red"), yKey, xKey, 60, 60);	
			}

			GameObject enemy0 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy0"); 
			GameObject enemy1 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy1");
			GameObject enemy4 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy");

			enemy0.mini = _mini; 
			enemy1.mini = _mini;
			enemy4.mini = _mini;


			if (enemy0.getData().getSystems().contains("Graphics") && enemy0.getData().getComponents().contains("Graphics")) 
			{
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy0").AIDraw(g,worldOrigin);
			}

			if (enemy1.getData().getSystems().contains("Graphics") && enemy1.getData().getComponents().contains("Graphics")) {
				enemy1._invisble = true;
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy1").AIDraw(g,worldOrigin);
			}

			if (enemy4.getData().getSystems().contains("Graphics") && enemy4.getData().getComponents().contains("Graphics"))
			{			
				boolean spawn; 
				if ((main.getData()._level0BlueKeyFound      == true)  &&  
						(main.getData()._level0GreenKeyFound == true)  && 
						(main.getData()._level0RedKeyFound   == true)) 
				{
					spawn = true;
				} else 
				{
					spawn = false;
				}
				// Trying to fix restart bug
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy").spawn = spawn;
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Enemy").AIDraw(g,worldOrigin);
			}
		}		
		if (this.getGameWorld().getLevel() == 1) 
		{
			int xKey = 0;  
			int yKey = 0;
			if ( main.getData()._level1BlueKeyFound == false)  
			{
				xKey = (100 * (LEVEL_1_BLUE_R)) + x + 20; 
				yKey = (100 * (LEVEL_1_BLUE_C)) + y + 15;
				g.drawImage(gObj.getKeyMap().get("blue"),  yKey, xKey, 60, 60);
			}

			if ( main.getData()._level1GreenKeyFound == false)
			{
				xKey = (100 * (LEVEL_1_GREEN_R)) + x + 20;
				yKey = (100 * (LEVEL_1_GREEN_C)) + y + 15;
				g.drawImage(gObj.getKeyMap().get("green"), yKey, xKey,  60, 60);
			}

			if ( main.getData()._level1RedKeyFound == false)  
			{
				xKey = (100 * (LEVEL_1_RED_R)) + x + 20;
				yKey = (100 * (LEVEL_1_RED_C)) + y + 15;	
				g.drawImage(gObj.getKeyMap().get("red"),   yKey, xKey, 60, 60);	
			}
			GameObject enemy2 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy2"); 
			GameObject enemy3 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy3"); 
			GameObject enemy4 = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy"); 


			enemy2.mini = _mini; 
			enemy3.mini = _mini; 
			enemy4.mini = _mini; 

			if (enemy2.getData().getSystems().contains("Graphics") && enemy2.getData().getComponents().contains("Graphics")) 
			{
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy2").AIDraw(g,worldOrigin);
			}
			if (enemy3.getData().getSystems().contains("Graphics") && enemy3.getData().getComponents().contains("Graphics")) 
			{
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy3").AIDraw(g,worldOrigin);
			}

			if (enemy4.getData().getSystems().contains("Graphics") && enemy4.getData().getComponents().contains("Graphics"))
			{			
				boolean spawn; 	
				if ((main.getData()._level1BlueKeyFound  == true)  		&&  
						(main.getData()._level1GreenKeyFound == true)  	&& 
						(main.getData()._level1RedKeyFound   == true)) 
				{
					spawn = true;
				} else 
				{
					spawn = false;
				}
				// Trying to fix restart bug
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy").spawn = spawn;
				this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Enemy").AIDraw(g,worldOrigin);
			}
		}	
		if ( this._mini == false) 
		{
			this.drawRewardBar(g);
			this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main").draw(g); 
		}
	}

	private void drawRewardBar (GraphicsContext g) {
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1().get("Main");; 
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d size = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();
		Vec2d textOrigin = new Vec2d(origin.x + 40,origin.y + 10);

		if (this.getGameWorld().getLevel() == 0) 
		{	
			if (main.getData()._level0RedKeyFound  		&& 
					main.getData()._level0BlueKeyFound 	&& 
					main.getData()._level0GreenKeyFound ) 
			{	
				g.setFill(Color.GREEN);
				g.fillRect(origin.x, origin.y + 40, size.x, 40);
				g.setFill(Color.WHITE);
				g.setFont(Font.font(WIZ, 40));
				g.setTextAlign(TextAlignment.CENTER);
				g.fillText("NEXT LEVEL",   textOrigin.x  + (size.x * 0.45), textOrigin.y + 60);
			}	
			else 
			{	
				g.setFill(Color.WHITE);
				g.fillRect(origin.x, origin.y + 40, size.x, 40);
				g.setFill(Color.BLACK);
				g.setFont(Font.font(WIZ, 40));
				g.setTextAlign(TextAlignment.CENTER);
				g.fillText("RED KEY",   textOrigin.x  + (size.x * 0.2), textOrigin.y + 60);
				g.fillText("BLUE KEY",  textOrigin.x  + (size.x * 0.45), textOrigin.y + 60);
				g.fillText("GREEN KEY", textOrigin.x  + (size.x * 0.70), textOrigin.y + 60);
				if (main.getData()._level0RedKeyFound == true) 
				{		
					g.setFill(Color.RED);
					g.fillText("RED KEY",   textOrigin.x  + (size.x * 0.2), textOrigin.y + 60);
				}
				if (main.getData()._level0BlueKeyFound == true) 
				{ 
					g.setFill(Color.BLUE);
					g.fillText("BLUE KEY",  textOrigin.x  + (size.x * 0.45), textOrigin.y + 60);
				}
				if (main.getData()._level0GreenKeyFound == true) 
				{
					g.setFill(Color.GREEN);
					g.fillText("GREEN KEY", textOrigin.x  + (size.x * 0.70), textOrigin.y + 60);
				}
			}
		} 
		else if (this.getGameWorld().getLevel() == 1) 
		{

			if (main.getData()._level1RedKeyFound  		&& 
					main.getData()._level1BlueKeyFound 	&& 
					main.getData()._level1GreenKeyFound ) 
			{	
				g.setFill(Color.GREEN);
				g.fillRect(origin.x, origin.y + 40, size.x, 40);
				g.setFill(Color.WHITE);
				g.setFont(Font.font(WIZ, 40));
				g.setTextAlign(TextAlignment.CENTER);
				g.fillText("NEXT LEVEL",   textOrigin.x  + (size.x * 0.45), textOrigin.y + 60);
			}	
			else 
			{	
				g.setFill(Color.WHITE);
				g.fillRect(origin.x, origin.y + 40, size.x, 40);
				g.setFill(Color.BLACK);
				g.setFont(Font.font(WIZ, 40));
				g.setTextAlign(TextAlignment.CENTER);
				g.fillText("RED KEY",   textOrigin.x  + (size.x * 0.2), textOrigin.y + 60);
				g.fillText("BLUE KEY",  textOrigin.x  + (size.x * 0.45), textOrigin.y + 60);
				g.fillText("GREEN KEY", textOrigin.x  + (size.x * 0.70), textOrigin.y + 60);
				if (main.getData()._level1RedKeyFound == true) 
				{		
					g.setFill(Color.RED);
					g.fillText("RED KEY",   textOrigin.x  + (size.x * 0.2), textOrigin.y + 60);
				}
				if (main.getData()._level1BlueKeyFound == true) 
				{ 
					g.setFill(Color.BLUE);
					g.fillText("BLUE KEY",  textOrigin.x  + (size.x * 0.45), textOrigin.y + 60);
				}
				if (main.getData()._level1GreenKeyFound == true) 
				{
					g.setFill(Color.GREEN);
					g.fillText("GREEN KEY", textOrigin.x  + (size.x * 0.70), textOrigin.y + 60);
				}
			}
		}
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
