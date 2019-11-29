package wizard.engine.ecs.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import support.Vec2d;
import support.Vec2i;
import support.collision.AABShape;
import support.collision.Collision;
import support.collision.MTV;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class CollisionComponent  extends Components  {
	private Application _app;
	private GameWorld _gameWorld;	
	private ArrayList <AABShape> _boxList; 
	private boolean DEBUG = false;

	protected final Integer LEVEL_0_RED_C 	= 18;
	protected final Integer LEVEL_0_RED_R 	= 7;
	protected final Integer LEVEL_0_GREEN_C = 23;
	protected final Integer LEVEL_0_GREEN_R = 11;
	protected final Integer LEVEL_0_BLUE_C 	= 9;
	protected final Integer LEVEL_0_BLUE_R 	= 5;

	protected final Integer LEVEL_1_RED_C = 19;
	protected final Integer LEVEL_1_RED_R = 3;
	protected final Integer LEVEL_1_GREEN_C = 19;
	protected final Integer LEVEL_1_GREEN_R = 19;
	protected final Integer LEVEL_1_BLUE_C = 13;
	protected final Integer LEVEL_1_BLUE_R = 17;
	
	public CollisionComponent (Application app, GameWorld gameWorld) 
	{
		this.setApp(app);
		this.setGameWorld(gameWorld);
		this.setBoxList( new ArrayList <AABShape>());
	}
	public void onTick(long nanosSincePreviousTick) 
	{		
		this.createBoundingBoxes();	
		this.checkCollision();
		this.main_VS_enemy();
		if (this.getGameWorld().getLevel() == 0) 
		{
			this.checkKey0Collision();
		} 
		else if (this.getGameWorld().getLevel() == 1) 
		{
			this.checkKey1Collision();
		}
	}
	private void checkKey0Collision () 
	{
		@SuppressWarnings("unused")
		double sizeX = 0; 
		@SuppressWarnings("unused")
		double sizeY = 0; 
		Vec2i location;
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Main");

		int r = 0;
		for (String map : this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel0Map()) {
			String[] section = map.split(","); 	
			int size = section.length;
			for (int c = 0; c < size - 1; c++) 
			{
				int x = (100 * (r)) + (int) this.getGameWorld().getOrigin().x + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
				int y = (100 * (c)) + (int) this.getGameWorld().getOrigin().y + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 
				location = new Vec2i(y,x);
				if ( (r == LEVEL_0_RED_R)  && ( c == LEVEL_0_RED_C )  ) 
				{
					AABShape box = new AABShape(new Vec2d( location ), new Vec2d(100,100));  

					if ( Collision.isColliding(box, main.getData().getBox()) == true) 
					{
						main.getData()._level0RedKeyFound = true;
						if (DEBUG) 
						{
							System.out.print("RED Collision \n" );
						}						
					}
				}

				if ( ( r ==  LEVEL_0_BLUE_R)  && ( c == LEVEL_0_BLUE_C)  ) 
				{						
					AABShape box = new AABShape(new Vec2d( location ), new Vec2d(100,100));  
					if ( Collision.isColliding(box, main.getData().getBox()) == true) 
					{
						main.getData()._level0BlueKeyFound = true;
						if (DEBUG) 
						{
							System.out.print("BLUE Collision \n" );
						}
					}
				}
				// Draw the green
				if ( ( r == LEVEL_0_GREEN_R )  && ( c == LEVEL_0_GREEN_C )  ) 
				{	
					AABShape box = new AABShape(new Vec2d( location ), new Vec2d(100,100));  

					if ( Collision.isColliding(box, main.getData().getBox()) == true) 
					{
						main.getData()._level0GreenKeyFound = true;
						if (DEBUG) 
						{
							System.out.print("GREEN Collision \n" );
						}
					}
				}
				sizeX = c * 100; 
			}
			r++;
			sizeY = r * 100; 
		}
	}
	private void checkKey1Collision () 
	{		
		@SuppressWarnings("unused")
		double sizeX = 0; 
		@SuppressWarnings("unused")
		double sizeY = 0; 
		@SuppressWarnings("unused")
		Vec2i location;
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Main");		
		int r = 0;
		for (String map : this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel1Map() ) {
			String[] section = map.split(","); 	
			int size = section.length;
			for (int c = 0; c < size - 1; c++) 
			{
				int x = (100 * (r)) + (int) this.getGameWorld().getOrigin().x + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
				int y = (100 * (c)) + (int) this.getGameWorld().getOrigin().y + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 
				location = new Vec2i(y,x);
				// Draw the Red
				if ( (r == LEVEL_1_RED_R)  && ( c == LEVEL_1_RED_C )  ) 
				{	
					if ((r == main.getData().getGameGridLocation().x )  && ( c == main.getData().getGameGridLocation().y) ) 
					{
						main.getData()._level1RedKeyFound = true;
					}			
				}
				// Draw the blue
				if ( ( r ==  LEVEL_1_BLUE_R)  && ( c == LEVEL_1_BLUE_C)  ) 
				{						
					if ((r == main.getData().getGameGridLocation().x )  && ( c == main.getData().getGameGridLocation().y) ) 
					{
						main.getData()._level1BlueKeyFound = true;	
					}	
				}
				// Draw the green
				if ( ( r == LEVEL_1_GREEN_R )  && ( c == LEVEL_1_GREEN_C )  ) 
				{	
					if ((r == main.getData().getGameGridLocation().x )  && ( c == main.getData().getGameGridLocation().y) ) 
					{
						main.getData()._level1GreenKeyFound = true;
					}
				}
				sizeX = c * 100; 
			}
			r++;
			sizeY = r * 100; 
		}
	}
	private void createBoundingBoxes () 
	{
		this.getBoxList().clear();
		ArrayList<String> gameLevelGrid = null; 
		if (this.getGameWorld().getLevel() == 0)
		{	
			gameLevelGrid = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel0Map(); 
		} 
		else if (this.getGameWorld().getLevel() == 1) 
		{
			gameLevelGrid = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel1Map();
		}
		Vec2i location;
		int r = 0;
		if (this.getGameWorld() == null) 
		{
			return;
		}
		for (String map : gameLevelGrid )  
		{
			String[] section = map.split(","); 	
			int size = section.length - 1;
			for (int c = 0; c < size; c++)
			{
				int x = (100 * (r)) + (int) this.getGameWorld().getOrigin().x + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
				int y = (100 * (c)) + (int) this.getGameWorld().getOrigin().y + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 
				location = new Vec2i(x,y);
				if (section[c].equals("x")) 
				{
					this.getBoxList().add( new AABShape(new Vec2d (location.y, location.x), new Vec2d(100,100))); 		
				}
			}
			r++; 
		}
	}
	private void checkCollision () 
	{
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Main");
		if (main == null) 
		{
			return;
		}
		if (main.getData().getBox() == null) 
		{
			return;
		}
		for (AABShape box  : this.getBoxList())
		{ 			
			if ( box != null ) 
			{
				if ( Collision.isColliding(box, main.getData().getBox()) == true) 
				{
					Vec2d mtv = MTV.collision(box,  main.getData().getBox()); 

					if ( mtv != null ) 
					{
						Vec2d newOrigin = new Vec2d(this.getGameWorld().getOrigin().x + mtv.y, this.getGameWorld().getOrigin().y + mtv.x);
						this.getGameWorld().setOrigin(newOrigin);
					}
				}
			}
		}
	}
	private void main_VS_enemy() 
	{
		GameObject main = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO().get("Main");
		if (main == null) 
		{
			return; 
		}
		HashMap<String ,GameObject> enemy = new HashMap<String ,GameObject>(); 
		if (this.getGameWorld().getLevel() == 0) 
		{
			enemy = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevelO(); 
		} 
		else if (this.getGameWorld().getLevel() == 1) 
		{
			enemy = this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate().getObjsLevel1(); 
		}
		if (enemy == null) 
		{
			return;
		}
		for (Entry<String, GameObject> character : enemy.entrySet())  
		{
			if (character.getKey() != "Main") 
			{				
				if ( Collision.isColliding(character.getValue().getData().getBox(), main.getData().getBox()) == true) 
				{
					// All Broke loose
					if (character.getKey() == "Enemy") 
					{	
						if (this.getGameWorld().getLevel() == 0) 
						{
							if (main.getData()._level0RedKeyFound  		&& 
									main.getData()._level0BlueKeyFound 	&& 
									main.getData()._level0GreenKeyFound ) 
							{	
								this.getGameWorld().setOrigin(new Vec2d (-700, -330));	
							}
						}
						if (this.getGameWorld().getLevel() == 1)
						{
							if (main.getData()._level1RedKeyFound  		&& 
									main.getData()._level1BlueKeyFound 	&& 
									main.getData()._level1GreenKeyFound ) 
							{	
								this.getGameWorld().setOrigin(new Vec2d (-700, -330));	
							}
						}
					} 
					else 
					{
						this.getGameWorld().setOrigin(new Vec2d (-700, -330));	
					}
				}
			} 
		}

	}
	private Application getApp() {
		return _app;
	}
	private void setApp(Application _app) {
		this._app = _app;
	}
	private GameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	private ArrayList <AABShape> getBoxList() {
		return _boxList;
	}
	private void setBoxList(ArrayList <AABShape> _boxList) {
		this._boxList = _boxList;
	}
}