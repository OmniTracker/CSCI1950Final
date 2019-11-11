package wizard.engine.ecs.component;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import support.Vec2i;
import engine.Application;
import engine.GameWorld;
import engine.systems.Components;

public class GameBoardDataComponent extends Components {
	private Application _app;
	private GameWorld _gameWorld;
	public double sizeX; 
	public double sizeY; 
	public boolean _mini = false;

	protected final Integer COL_0 = 2;

	public void onDraw(GraphicsContext g)
	{
		this.drawGameGrid(g);		
	}
	public void drawGameGrid(GraphicsContext g) 
	{		
		if (this.getGameWorld().getLevel() == 0) 
		{	
			drawMapLevel0(g);
		} 
		else if (this.getGameWorld().getLevel() == 1) 
		{
			drawMapLevel1(g);
		}
	}
	private void drawMapLevel1(GraphicsContext g) 
	{
		Vec2i location;
		Image tron = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getTron();
		ArrayList<String> level1Grid = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel1Map(); 
		int r = 0;
		for (String map : level1Grid ) 
		{
			String[] section = map.split(","); 
			int size = section.length - 1;
			for (int c = 0; c < size; c++)
			{
				int x = (100 * (r)) + (int) this.getGameWorld().getOrigin().x +
						(int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
				int y = (100 * (c)) + (int) this.getGameWorld().getOrigin().y + 
						(int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 


				if (_mini == true) {
					x = (100 * (r)) + ( int ) this.getGameWorld().getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x; 
					y = (100 *(c))  + ( int )  this.getGameWorld().getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y; 
				}

				location = new Vec2i(x,y);
				if (!section[c].equals("x"))
				{
					g.drawImage(tron,0,0,500,500,location.y,location.x,getTileSize(),getTileSize());					
				}
				
				
				if ((r == 23) && (c == 7)) {
					g.setFill(Color.RED);
					g.fillRect(y, x, getTileSize(), getTileSize());
				}
				
				
			}
			r++;
		}
	}
	private void drawMapLevel0(GraphicsContext g) {
		Vec2i location;
		Image lava = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLava(); 
		ArrayList<String> level0Grid = this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel0Map();
		int r = 0;
		int randomFlame = ThreadLocalRandom.current().nextInt(200, 250 + 1);
		int randomStart = ThreadLocalRandom.current().nextInt(0, 30 + 1);
		int randomLocation; 
		for (String map : level0Grid ) 
		{
			String[] section = map.split(","); 	
			int size = section.length - 1;
			for (int c = 0; c < size; c++) 
			{
				randomFlame = ThreadLocalRandom.current().nextInt(200, 210 + 1);
				randomStart = ThreadLocalRandom.current().nextInt(0, 20 + 1);
				randomLocation = ThreadLocalRandom.current().nextInt(-5, 4 + 1);
				int x = (100 * (r)) + (int) this.getGameWorld().getOrigin().x +
						(int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
				int y = (100 * (c)) + (int) this.getGameWorld().getOrigin().y + 
						(int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 
				
				

				if (_mini == true) {
					x = (100 * (r)) + ( int ) this.getGameWorld().getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x; 
					y = (100 *(c))  + ( int )  this.getGameWorld().getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y; 
				}
				
				
				
				location = new Vec2i(x,y);

				if (section[c].equals("x"))  
				{
					g.drawImage(lava,randomStart,randomStart,randomFlame,randomFlame,location.y,location.x,getTileSize() + 10+ randomLocation,getTileSize() + randomLocation + 10);
				}
			}
			r++;
		}		
		r = 0;
		for (String map : this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel0Map() ) {
			String[] section = map.split(","); 	
			int size = section.length;
			for (int c = 0; c < size - 1; c++) 
			{
				int x = (100 * (r)) + (int) this.getGameWorld().getOrigin().x + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2); 
				int y = (100 * (c)) + (int) this.getGameWorld().getOrigin().y + (int) ( this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2); 
				

				if (_mini == true) {
					x = (100 * (r)) + ( int ) this.getGameWorld().getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x; 
					y = (100 *(c))  + ( int )  this.getGameWorld().getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y; 
				}	
				
				
				location = new Vec2i(x,y);
				fillMap(g, location, section, c); 
				
				if ((r == 8) && (c == 23)) {
					g.setFill(Color.BLUE);
					g.fillRect(y, x, getTileSize(), getTileSize());
					
				}
				sizeX = c * 100; 
			}
			r++;
			sizeY = r * 100; 
		}
	}
	private void fillMap(GraphicsContext g, Vec2i location, String[] section, int c) {	
		if (section[c].equals("A")) {
			leftBottomCorner ( g,  location);    
		} else if (section[c].equals("B")) {
			leftTopCorner ( g,  location);
		} else if (section[c].equals("C")) { 
			rightTopCorner ( g,  location);
		} else if (section[c].equals("D")) {
			rightBottomCorner ( g,  location);
		} else if (section[c].equals("E")) {
			leftBottomCornerHallway ( g,  location);
		} else if (section[c].equals("F")) {
			leftTopCornerHallway ( g,  location);
		} else if (section[c].equals("G")) {
			rightTopCornerHallway ( g,  location);
		} else if (section[c].equals("H")) {
			rightBottomCornerHallway ( g,  location);
		} else if (section[c].equals("I")) {
			rightEndingHallway ( g,  location);
		} else if (section[c].equals("J"))  {
			leftEndingHallway ( g,  location); 
		} else if (section[c].equals("K"))  {
			fourWayHallway (g,location);
		} else if (section[c].equals("M")) {
			fourWayHallway ( g,  location);	
		} else if (section[c].equals("L")) {
			verticalHallway ( g,  location);
		} else if (section[c].equals("M")) {
			threeWayHallUpperLimit ( g,  location);
		} else if (section[c].equals("N")) {
			threeWayHallLowerLimit ( g,  location); 
		} else if (section[c].equals("O")) {
			topLeftBottomRightEdge ( g,  location);
		} else if (section[c].equals("P")) { 
			topRightBottomLeftEdge ( g,  location);
		} else if (section[c].equals("Q")) {
			doubleBottomEdge ( g,  location);
		} else if (section[c].equals("R")) {
			doubleTopEdge ( g,  location); 
		} else if (section[c].equals("S")) {
			horizontalHallway ( g,  location);
		}
	}
	public void utilityDraw( GraphicsContext g, Vec2i location, Integer startX, Integer startY) {	
		if ( this.getGameWorld().getLevel() == 0 ) {
			g.drawImage(this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevelO(),startX,startY,IMAGE_SIZE,IMAGE_SIZE,location.y,location.x,getTileSize(),getTileSize());
		} 
		else if (this.getGameWorld().getLevel() == 1) {
			g.drawImage(this.getGameWorld().getWIZDelegateContainer().getWIZMapDelegate().getLevel1(),startX,startY,IMAGE_SIZE,IMAGE_SIZE,location.y,location.x,getTileSize(),getTileSize());
		}
	}
	protected final Integer COL_1 = 78;
	protected final Integer COL_2 = 158;
	protected final Integer COL_3 = 235;
	protected final Integer COL_4 = 315;
	protected final Integer COL_5 = 391;
	protected final Integer COL_6 = 472;
	protected final Integer COL_7 = 554;
	protected final Integer ROW_0 = 2;
	protected final Integer ROW_1 = 78;
	protected final Integer ROW_2 = 158;
	protected final Integer ROW_3 = 235;
	protected final Integer TILE_SIZE = 100;
	protected final Integer IMAGE_SIZE = 66;
	public GameBoardDataComponent() {
		super();
	}
	private void leftBottomCorner(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_3, COL_0);
	}
	private void leftTopCorner(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_3 , COL_7 - 5);
	}
	private void rightTopCorner(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_0, COL_3);
	}
	private void rightBottomCorner(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_0, COL_4);
	}
	private void leftBottomCornerHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_1, COL_1);
	}
	private void leftTopCornerHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_1, COL_6 - 1);
	}
	private void rightTopCornerHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_2 - 2, COL_2);
	}
	private void rightBottomCornerHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_2 - 2, COL_5);
	}
	private void rightEndingHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_2, COL_3);
	}
	private void leftEndingHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_1, COL_0);
	}
	private void fourWayHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_1, COL_2);
	}
	private void verticalHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_2 - 3, COL_0);
	}
	private void threeWayHallUpperLimit(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_0, COL_5);
	}
	public void threeWayHallLowerLimit(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_0, COL_2);
	}
	public void topLeftBottomRightEdge(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_3, COL_3);
	}
	public void topRightBottomLeftEdge(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_3, COL_4);
	}
	public void doubleBottomEdge(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_0, COL_1 - 10);
	}
	public void doubleTopEdge(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_0, COL_6);
	}
	public void horizontalHallway(GraphicsContext g, Vec2i location) {
		this.utilityDraw(g, location, ROW_2, COL_7 - 6);
	}
	public Integer getTileSize() {
		return TILE_SIZE;
	}
	public Application getApp() {
		return _app;
	}
	protected void setApp(Application _app) {
		this._app = _app;
	}
	public GameWorld getGameWorld() {
		return _gameWorld;
	}
	protected void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}