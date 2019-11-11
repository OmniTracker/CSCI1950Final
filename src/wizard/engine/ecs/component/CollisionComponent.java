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
	public CollisionComponent (Application app, GameWorld gameWorld) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
		this.setBoxList( new ArrayList <AABShape>());
	}
	public void onTick(long nanosSincePreviousTick) {		
		this.createBoundingBoxes();	
		this.checkCollision();
		this.main_VS_enemy();
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

	private void checkCollision () {
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
	private void main_VS_enemy() {
		// Check for collision between the main character and the enemy. Get everything for current level
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
					this.getGameWorld().setOrigin(new Vec2d (-700, -130));
					/* Finsh for WIZ 2 retry
					Vec2d mtv = MTV.collision(character.getValue().getData().getBox(), main.getData().getBox()); 

					if ( mtv != null ) 
					{
						Vec2d newOrigin = new Vec2d(this.getGameWorld().getOrigin().x + (mtv.y), this.getGameWorld().getOrigin().y + (mtv.x) );
						this.getGameWorld().setOrigin(newOrigin);
					}
					 */ 
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