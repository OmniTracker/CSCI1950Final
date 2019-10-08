package wizard.level0;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.Collision;
import wizard.systems.CollisionSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.gameobject.GameObject;
import engine.utility.DrawBox;

public class GameWorld {
	private Application _app;
	private final Integer BOX_ORIGIN_X = 0;
	private final Integer BOX_ORIGIN_Y = 1;
	private final Integer BOX_SIZE_X   = 2;
	private final Integer BOX_SIZE_Y   = 3;
	private Vec2d _gameSize = new Vec2d (1700, 1200);
	private Vec2d _origin;
	private ArrayList <DrawBox> _drawBoxes = null;
	private HashMap <String,GameObject> _characters; 
	private GameObject _currentCharacter; 

	private CollisionSystem _collisionSystem;
	private String _character; 
	private List<List<String>> _walls;
	private String _direction = "UP";
	private Image _brick = null;
	private Image _floor = null;
	private MouseEvent _mouseDrag;

	public GameWorld(Application app, Vec2d origin)
	{
		this.setApp(app);
		this.setOrigin(origin);
		this.loadCharacter();
		this.loadLevel0Board();
	}

	private void loadLevel0Board() 
	{
		try 
		{
			this.setBrick(this.getApp().getFactory().generateBrickWall());
			this.setFloor(this.getApp().getFactory().generateFloor());
			this.setWalls(this.getApp().getFactory().getWizLevel0());
			this.setDrawBoxes(new  ArrayList <DrawBox>());
		} 
		catch (MalformedURLException e) 
		{
			System.out.print("Not able to load map attributes!!! \n");
			e.printStackTrace();
		}

		for (List<String> var : this.getWalls()) 
		{ 
			double xO = Integer.valueOf(var.get(BOX_ORIGIN_X));
			double yO = Integer.valueOf(var.get(BOX_ORIGIN_Y));
			double xS = Integer.valueOf(var.get(BOX_SIZE_X));
			double yS = Integer.valueOf(var.get(BOX_SIZE_Y));
			Vec2d origin = new Vec2d(xO,yO); 
			Vec2d size = new Vec2d(xS,yS); 
			this.getDrawBoxes().add(new DrawBox(origin,size));
		}	

	}

	private void loadCharacter()
	{
		this.setCharacters(this.getApp().getFactory().alchemyCharacterFactory());
		this.setCharacter("Sam");
		this.setCurrentCharacter(this.getCharacters().get(this.getCharacter()));
		double x = this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2;
		double y = this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2;
		this.getCurrentCharacter().setPosition(new Vec2d(x, y));
	}
	
	


	private void subscribeToSystems ()
	{



	}
	
	
	private void drawWalls (GraphicsContext g) 
	{	
		for ( DrawBox box  : this.getDrawBoxes() ) 
		{
			
			double x = this.getCurrentCharacter().getPosition().x + this.getOrigin().x; 
			double y = this.getCurrentCharacter().getPosition().y + this.getOrigin().y; 
			
			Vec2d origin = new Vec2d( x + box.getOrigin().x, y +box.getOrigin().y);
			
			
			Vec2d size   = new Vec2d(box.getSize().x,
					box.getSize().y); 
			
			
			g.drawImage(this.getBrick(),origin.x,origin.y,size.x,size.y);	
			g.setGlobalAlpha(0.4);					
			g.setFill(Color.DARKGOLDENROD);	
			g.fillRect(origin.x,origin.y,size.x,size.y);				
			g.setGlobalAlpha(0.6);					
			g.setFill(Color.BLACK);	
			g.fillRect(origin.x,origin.y,size.x,size.y);
			g.setGlobalAlpha(1.0);		
		}
	}

	
	private void drawFloor(GraphicsContext g) 
	{
		for (int x = 0; x <= this.getGameSize().x; x+= (this.getGameSize().x / 2)) 
		{
			for (int y = 0; y <= this.getGameSize().y; y+= (this.getGameSize().y / 2)) 
			{
				g.drawImage(this.getFloor(), this.getCurrentCharacter().getPosition().x + this.getOrigin().x,
						this.getCurrentCharacter().getPosition().y + this.getOrigin().y, 
						
						(this.getGameSize().x / 2),
						
						(this.getGameSize().y / 2));		
			}
		}
		g.setGlobalAlpha(0.2);					
		g.setFill(Color.DARKGOLDENROD);	
		
		g.fillRect( this.getCurrentCharacter().getPosition().x + this.getOrigin().x, 
				this.getCurrentCharacter().getPosition().y + this.getOrigin().y , this.getGameSize().x,this.getGameSize().y);				
		g.setGlobalAlpha(0.3);					
		g.setFill(Color.BLACK);	
		g.fillRect(this.getCurrentCharacter().getPosition().x + this.getOrigin().x, 
				this.getCurrentCharacter().getPosition().y + this.getOrigin().y, this.getGameSize().x,this.getGameSize().y);		
		g.setGlobalAlpha(1.0);		
	}



	public void onDraw(GraphicsContext g)  
	{
		double x = this.getApp().getAspectRatioHandler().getCurrentScreenSize().x / 2;
		double y = this.getApp().getAspectRatioHandler().getCurrentScreenSize().y / 2;
		this.getCurrentCharacter().setPosition(new Vec2d(x,y));
		this.drawFloor(g);
		this.drawWalls(g);
		
		
		this.drawStartingArea(g);
		this.drawEndingArea(g);			
		this.getCurrentCharacter().smallCharacterDraw(g, this.getDirection());		
	}

	private void moveCharacter(KeyEvent e)
	{
		String direction = e.getCode().toString();	
		double x = this.getOrigin().x;
		double y = this.getOrigin().y;
		double stepSize = 8;
		if (direction == "UP") {	
			y += stepSize;
		} else if  (direction == "DOWN") {
			y -= stepSize;
		} else if (direction == "LEFT") {
			x += stepSize;
		} else if (direction == "RIGHT") {
			x -= stepSize; 
		} else {
			return;
		}
		this.setOrigin( new Vec2d(x,y));
		this.setDirection(direction);
		this.getCurrentCharacter().incrementStep();
	}
	

	/* 

	@Override
	public void onMouseClicked(MouseEvent e) 
	{

	}

	 */
	private boolean checkForColllsion ()
	{	
		GameObject currentCharacter = this.getCharacters().get(this.getCharacter());

		/* 
		for (DrawBox box : this.getDrawBoxes()) 
		{
			if ( Collision.isColliding(box.getBox(),currentCharacter.getSmallBoundingBox()) == true)
			{
				System.out.print("here \n");
				return true;
			}		
		}	*/
		return false;
	}


	public void onKeyPressed(KeyEvent e)
	{
		moveCharacter(e);
	}


	public void onTick(long nanosSincePreviousTick)
	{

		// this.getCollisionSystem().run();
		this.checkForColllsion();
	}



	private void drawStartingArea(GraphicsContext g)
	{
		g.setGlobalAlpha(0.6);					
		g.setFill(Color.RED);	
		// g.fillRect(origin.x,origin.y,screenFloor.x + 200,screenFloor.y + 200);
		g.setGlobalAlpha(1.0);	
	}

	private void drawEndingArea(GraphicsContext g) 
	{
		g.setGlobalAlpha(0.6);					
		g.setFill(Color.BLUE);	
		// g.fillRect(origin.x,origin.y,screenFloor.x + 200,screenFloor.y + 200);
		g.setGlobalAlpha(1.0);	
	}

	public void handleSceneMouseDrag (MouseEvent e)
	{
		if (Collision.withinRange(new Vec2d(this.getMouseDrag().getSceneX(),this.getMouseDrag().getSceneY()),
				new Vec2d(e.getSceneX(), e.getSceneY()), 20.0)) {
			this.isGameWorldInViewportRange(e);
			this.setMouseDrag(e);
		} 
		else 
		{
			this.setMouseDrag(null);
		}
	}

	public void isGameWorldInViewportRange(MouseEvent e) 
	{

		/*
		Vec2d screenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();

		// Get the viewport information.
		Vec2d viewportOrigin = new Vec2d((origin.x +  (screenSize.x / 6)),(origin.y + (screenSize.y / 10))); 
		Vec2d vieportSize = new Vec2d( (screenSize.x - ((screenSize.x / 6) * 2)),(screenSize.y - ((screenSize.y / 10) * 2))); 

		double xDiff = e.getSceneX()- this.getMouseDrag().getSceneX();
		double yDiff = e.getSceneY() - this.getMouseDrag().getSceneY();

		Vec2d addedOrigin = this.getOrigin().plus(xDiff, yDiff);

		if ((viewportOrigin.x > addedOrigin.x + 20) && (this.getScreenSize().x + addedOrigin.x) > (viewportOrigin.x + vieportSize.x) ) 
		{
			x = xDiff;
		}
		if ((viewportOrigin.y > addedOrigin.y + 20) &&  (this.getScreenSize().y + addedOrigin.y) > (viewportOrigin.y + vieportSize.y)) 
		{
			y = yDiff;
		}

		GameObject currentCharacter = this.getCharacters().get(this.getCharacter());

		 */


		double xDiff = e.getSceneX()- this.getMouseDrag().getSceneX();
		double yDiff = e.getSceneY() - this.getMouseDrag().getSceneY();


		this.setOrigin(this.getOrigin().plus(xDiff, yDiff));

		this.getCurrentCharacter().setPosition(this.getCurrentCharacter().getPosition().plus(xDiff, yDiff));

		this.getCurrentCharacter().setSmallBoundingBox(new AABShape(new Vec2d(xDiff,yDiff), this.getCurrentCharacter().getSize()));
	}


	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub

	}

	public void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void onMouseDragged(MouseEvent e)
	{
		if (this.getMouseDrag() == null) {			
			this.setMouseDrag(e);
			return;
		}
		this.handleSceneMouseDrag(e);		
	}

	private MouseEvent getMouseDrag() {
		return _mouseDrag;
	}

	private void setMouseDrag(MouseEvent _mouseDrag) {
		this._mouseDrag = _mouseDrag;
	}
	public ArrayList <DrawBox> getDrawBoxes() {
		return _drawBoxes;
	}
	public void setDrawBoxes(ArrayList <DrawBox> _drawBoxes) {
		this._drawBoxes = _drawBoxes;
	}

	public HashMap <String,GameObject> getCharacters() {
		return _characters;
	}

	public void setCharacters(HashMap <String,GameObject> _characters) {
		this._characters = _characters;
	}

	public String getCharacter() {
		return _character;
	}

	public void setCharacter(String _character) {
		this._character = _character;
	}

	private Image getBrick() {
		return _brick;
	}

	private void setBrick(Image _brick) {
		this._brick = _brick;
	}

	private Image getFloor() {
		return _floor;
	}

	private void setFloor(Image _floor) {
		this._floor = _floor;
	}

	CollisionSystem getCollisionSystem() {
		return _collisionSystem;
	}

	void setCollisionSystem(CollisionSystem _collisionSystem) {
		this._collisionSystem = _collisionSystem;
	}

	private String getDirection() {
		return _direction;
	}

	private void setDirection(String _direction) {
		this._direction = _direction;
	}

	private Vec2d getOrigin() {
		return _origin;
	}

	private void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}

	private List<List<String>> getWalls() {
		return _walls;
	}

	private void setWalls(List<List<String>> _walls) {
		this._walls = _walls;
	}

	private GameObject getCurrentCharacter() {
		return _currentCharacter;
	}

	private void setCurrentCharacter(GameObject _currentCharacter) {
		this._currentCharacter = _currentCharacter;
	}

	private Application getApp() {
		return _app;
	}

	private void setApp(Application _app) {
		this._app = _app;
	}

	private Vec2d getGameSize() {
		return _gameSize;
	}
}


