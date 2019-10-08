package alchemy;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import alchemy.systems.CollisionSystem;
import support.Vec2d;
import engine.Application;
import engine.gameobject.GameObject;
import engine.ui.Screen;
import engine.ui.ViewportHandler;


public class ALCGameScreen extends Screen {
	private CollisionSystem _collisionSystem;
	private HashMap<Integer,GameObject> _objs;

	public ALCGameScreen(Application app) {
		super(app);
		this.setViewport(new ALCViewport(app,new GameWorld(app),new Vec2d(0,0),new Vec2d(0,0)));
		this.initializeStaticObjects();
		this.setCollisionSystem( new CollisionSystem());
	}

	private void initializeStaticObjects() {
		this.setObjs(this.getApplication().getFactory().alchemyObjs());
	
		
		
	}
		
		
		
	/*
		
		
		this.setAllGemsFound(this.getFactory().alchemyGemFactory());
		AspectRatioHandler aspect = this.getParent().getAspectRatioHandler();
		Vec2d newOrigin = aspect.calculateUpdatedOrigin();
		double x = (aspect.getCurrentScreenSize().x - newOrigin.x); 
		double y = (newOrigin.y) + 90;
		double factor = ((aspect.getCurrentScreenSize().y  - (newOrigin.y * 2)) / 4) ;
		HashMap<String,Integer> initElements = new HashMap<String,Integer>(); 
		initElements.put("Forest", ELEMENT_1); 
		initElements.put("Heart", ELEMENT_2); 
		initElements.put("Ocean",ELEMENT_3); 
		initElements.put("ZeroLight",ELEMENT_4); 
		AlchemyGem tempGem;
		Image tempImage;
		int factorIndex = 0;

		for (Entry<String, Integer> entry : initElements.entrySet()) 
		{	

			tempImage = this.getAllGemsFound().get(entry.getValue()).getCharacterLarge();
			tempGem = new AlchemyGem(tempImage, entry.getKey(),new Vec2d(x, (y + (factor * factorIndex))));
			tempGem.addComponent( new StaticComponent());
			this.getStaticAlchemyGems().add(tempGem); 
			this.getCollisionSystem().getAllAlchemyGems().add(tempGem);
			factorIndex++; 	


	}
	*/
	

	public void onDraw(GraphicsContext g) {
		this.getViewport().onDraw(g);
		this.getApplication().borders(g,Color.BLACK);
	}

	public void onMouseClicked(MouseEvent e) {
		this.getViewport().onMouseClicked(e);
	}

	public void onMousePressed(MouseEvent e) 
	{ 
		this.getViewport().onMousePressed(e);
	}

	public void onMouseDragged(MouseEvent e) {

		this.getViewport().onMouseDragged(e);			

	}
	public void onMouseWheelMoved(ScrollEvent e){
		this.getViewport().onMouseWheelMoved(e);
	}


	public void onTick(long nanosSincePreviousTick) {
		this.getCollisionSystem().run();
	}

	public CollisionSystem getCollisionSystem() {
		return _collisionSystem;
	}

	public void setCollisionSystem(CollisionSystem _collisionSystem) {
		this._collisionSystem = _collisionSystem;
	}

	private void setObjs(HashMap<Integer,GameObject> _objs) {
		this._objs = _objs;
	}
}

/*
private Application _parent;
Viewport _viewport;
private Factory _factory;

private HashMap<Integer,GameObject> _allGemsFound;
private ArrayList<AlchemyGem> _staticAlchemyGems;
private ArrayList<AlchemyGem> _movableAlchemyGems;
CircleTraking _circleTraking;
private Collision isColliding;
private EventHandlerTimeOut _timeout;
private CollisionSystem _collisionSystem;

private static final Integer ELEMENT_1 = 0;   
private static final Integer ELEMENT_2 = 1;  
private static final Integer ELEMENT_3 = 2;  
private static final Integer ELEMENT_4 = 3;

private static final Integer ELEMENT_5 = 4;  
private static final Integer ELEMENT_6 = 5;  
private static final Integer ELEMENT_7 = 6;  

public ALCGameScreen(Application parent) {

	super(parent);

	this.setParent(parent);
	this.initViewport(); 
	this.setFactory(new Factory());
	setIsColliding(new Collision()); 
	// this._circleTraking = new CircleTraking();
	setStaticAlchemyGems(new ArrayList<AlchemyGem>());
	this.setMovableAlchemyGems(new ArrayList<AlchemyGem>());
	this.setTimeout(new EventHandlerTimeOut());
	// this.setCollisionSystem( new CollisionSystem());
}

private void initViewport() {
	double x = 750 ; 
	double y = x / this.getParent().getAspectRatioHandler().getAspectRatio(); 
	Vec2d size = new Vec2d(x,y);
	Vec2d origin = this.getParent().getAspectRatioHandler().calculateUpdatedOrigin().plus(20, 20);  
// 	this.setViewport( new Viewport(new Vec2d(origin.x, origin.y),size,new IntroductionGameWorld(getParent()))); 
	this.getViewport().setParent(getParent());
}


private void initializePanelGems() {
	this.setAllGemsFound(this.getFactory().alchemyGemFactory());
	AspectRatioHandler aspect = this.getParent().getAspectRatioHandler();
	Vec2d newOrigin = aspect.calculateUpdatedOrigin();
	double x = (aspect.getCurrentScreenSize().x - newOrigin.x); 
	double y = (newOrigin.y) + 90;
	double factor = ((aspect.getCurrentScreenSize().y  - (newOrigin.y * 2)) / 4) ;
	HashMap<String,Integer> initElements = new HashMap<String,Integer>(); 
	initElements.put("Forest", ELEMENT_1); 
	initElements.put("Heart", ELEMENT_2); 
	initElements.put("Ocean",ELEMENT_3); 
	initElements.put("ZeroLight",ELEMENT_4); 
	AlchemyGem tempGem;
	Image tempImage;
	int factorIndex = 0;

	for (Entry<String, Integer> entry : initElements.entrySet()) 
	{	

		tempImage = this.getAllGemsFound().get(entry.getValue()).getCharacterLarge();
		tempGem = new AlchemyGem(tempImage, entry.getKey(),new Vec2d(x, (y + (factor * factorIndex))));
		tempGem.addComponent( new StaticComponent());
		this.getStaticAlchemyGems().add(tempGem); 
		this.getCollisionSystem().getAllAlchemyGems().add(tempGem);
		factorIndex++; 	

	}
}

private void drawPanelGems(GraphicsContext g) 
{
	if (this.getStaticAlchemyGems().size() < 3) 
	{
		this.initializePanelGems();
		return;
	} 
	else 
	{
		this.drawGemPanel(g);
	}
	Vec2d newOrigin = this.getParent().getAspectRatioHandler().calculateUpdatedOrigin();
	double x = (this.getParent().getAspectRatioHandler().getCurrentScreenSize().x - newOrigin.x) - 80; 
	double y = (newOrigin.y) + 120;
	double factor = ( ((this.getParent().getAspectRatioHandler().getCurrentScreenSize().y - (newOrigin.y * 2)) - 120 ) / 4) ;
	int index = 0; 


	// Draw the static gems
	for (AlchemyGem gem : this.getStaticAlchemyGems()) 
	{	
		gem.drawGem(g, new Vec2d(x, (y +(factor * index))));
		gem.setLocation(new Vec2d(x, (y +(factor * index))));
		index++;
	}
}

private void drawGemPanel(GraphicsContext g) {
	AspectRatioHandler aspect = this.getParent().getAspectRatioHandler(); 
	Vec2d newOrigin = aspect.calculateUpdatedOrigin(); 	
	// g.setFill(this.getParent().ALC_1); 
	g.fillRect((aspect.getCurrentScreenSize().x-newOrigin.x)-160,newOrigin.y,aspect.getCurrentScreenSize().x,aspect.getCurrentScreenSize().y);
}

private boolean CreateNewMovableGem(MouseEvent e) {



	for (AlchemyGem staticGems : this.getStaticAlchemyGems()) 
	{

		if ( this.getIsColliding().isColliding(staticGems.getCircleShape(), new Vec2d(e.getSceneX(), e.getSceneY()))) 
		{

			for (AlchemyGem activeGems : this.getMovableAlchemyGems()) 
			{	
				if (this.getIsColliding().isColliding(activeGems.getCircleShape(), staticGems.getCircleShape())) 
				{		
					return false;
				} 
			}

			Image img = this.getAllGemsFound().get(staticGems.getGemIndex()).getCharacterLarge();

			AlchemyGem newGem = new AlchemyGem(img, staticGems.getTag(), 
					new Vec2d ((staticGems.getLocation().x - 150),(staticGems.getLocation().y))); 

			newGem.addComponent(new MovableCompoent());

			this.getCollisionSystem().getAllAlchemyGems().add(newGem);

			this.getMovableAlchemyGems().add(newGem);
		}
	}


	return true;
}

private boolean dragMovableGems(MouseEvent e) 
{
	if (previousDrag != null) 
	{
		if ( !this.getIsColliding().withinRange(new Vec2d(previousDrag.getSceneX(), previousDrag.getSceneY()), 
				new Vec2d(e.getSceneX(), e.getSceneY()) , 15)) 
		{
			previousDrag = e;

		}	
	}

	for (AlchemyGem movingGem : this.getMovableAlchemyGems()) 
	{
		if ( this.getIsColliding().isColliding(movingGem.getCircleShape(), new Vec2d(e.getSceneX(), e.getSceneY()))) {

			if (previousDrag != null) 
			{
				Vec2d diff = new Vec2d ((previousDrag.getSceneX() - e.getSceneX()), ( previousDrag.getSceneY() - e.getSceneY()));
				Vec2d updatedLocation = new Vec2d (movingGem.getLocation().x - diff.x , movingGem.getLocation(). y - diff.y ); 
				movingGem.setLocation(updatedLocation);
				previousDrag = e;	
			}
			previousDrag = e;
			return true;
		}
	}

	return false;
}

private void checkMovableGemCollision() {
	AlchemyGem deleteGem1 = null;
	AlchemyGem deleteGem2 = null;
	AlchemyGem newGem = null;
	for (AlchemyGem movableGem1 : this.getMovableAlchemyGems()) 
	{	
		movableGem1.setBackColor(Color.RED);

	}
	for (AlchemyGem movableGem1 : this.getMovableAlchemyGems()) 
	{	
		for (AlchemyGem movableGem2 : this.getMovableAlchemyGems()) 
		{	
			if (movableGem1.getTag() != movableGem2.getTag()) 
			{	

				if (this.getIsColliding().isColliding(movableGem1.getCircleShape(), movableGem2.getCircleShape())) 
				{
					movableGem1.setBackColor(Color.FUCHSIA);
					movableGem2.setBackColor(Color.FUCHSIA);
					if ((newGem = this.createEvolvedGem (movableGem1 ,movableGem2)) != null) {
						deleteGem1 = movableGem1;
						deleteGem2 = movableGem2;
						break;	
					}
				}

			}
		}
	}
	if (deleteGem1 != null && deleteGem2 != null && newGem != null) {
		this.getMovableAlchemyGems().remove(deleteGem1);	
		this.getMovableAlchemyGems().remove(deleteGem2);
		// this.getCollisionSystem().getAllAlchemyGems().remove(deleteGem1);
		// this.getCollisionSystem().getAllAlchemyGems().remove(deleteGem2);
		//  newGem.addComponent(new MovableCompoent());
		this.getMovableAlchemyGems().add(newGem);
		//  this.getCollisionSystem().getAllAlchemyGems().add(newGem);
	}
}

private AlchemyGem createEvolvedGem (AlchemyGem movableGem1 , AlchemyGem movableGem2 ) {
	String combineGems = movableGem1.getTag() + movableGem2.getTag(); 
	String evolvedTag;
	Integer evolvedIndex;

	if (combineGems.contains("Forest") && combineGems.contains("Heart")) 
	{
		System.out.print("Generating NightStorm\n");
		evolvedTag = "NightStorm"; 
		evolvedIndex = ELEMENT_5;
	} 
	else if (combineGems.contains("Ocean") && combineGems.contains("ZeroLight")) 
	{
		// Fix this as soon as possible
		System.out.print("Generating LizardEye\n");
		evolvedTag = "LizardEye";
		evolvedIndex = ELEMENT_7;
	} 
	else if (combineGems.contains("NightStorm") && combineGems.contains("LizardEye")) 
	{
		System.out.print("Generating NightStorm\n");
		evolvedTag = "NightStorm";
		evolvedIndex = ELEMENT_6;
	}
	else 
	{
		return null;
	}
	Image evolvedGem = null; // this.getAllGemsFound().get(evolvedIndex).getCharacterLarge();
	Vec2d newGemLocation = new Vec2d(((movableGem1.getLocation().x + movableGem2.getLocation().x) / 2),
			((movableGem1.getLocation().y + movableGem2.getLocation().y) / 2));

	return null; 
// return new AlchemyGem(evolvedGem, evolvedTag,newGemLocation);

}

public void onDraw(GraphicsContext g) {

	this.getViewport().setAlchemyGem(this.getMovableAlchemyGems());
	this.getViewport().onDraw(g);
	this.drawPanelGems(g);
	this.borders(g); 
}

public void onMouseClicked(MouseEvent e) {
	this.getViewport().onMouseClicked(e);
}

public void onMousePressed(MouseEvent e) 
{
	CreateNewMovableGem(e); 
	this.getViewport().onMousePressed(e);
}

public void onMouseReleased(MouseEvent e) {
	this.getViewport().onMouseReleased(e);
}

private MouseEvent previousDrag = null;

public void onMouseDragged(MouseEvent e) {
	if( dragMovableGems(e) == false ) 
	{
		this.getViewport().onMouseDragged(e);			
	}
}
public void onMouseWheelMoved(ScrollEvent e){
	this.getViewport().onMouseWheelMoved(e);
}

public void onMouseMoved(MouseEvent e) {
	this.getViewport().onMouseMoved(e);
}

public void onTick(long nanosSincePreviousTick) {
	checkMovableGemCollision();

	//  this.getCollisionSystem().tick();
}

Factory getFactory() {
	return _factory;
}

void setFactory(Factory _factory) {
	this._factory = _factory;
}

private void setViewport(Viewport viewport) {
	this._viewport = viewport;
}

private Viewport getViewport () {
	return this._viewport;
}

public Application getParent() {
	return _parent;
}

public void setParent(Application _parent) {
	this._parent = _parent;
}

private Collision getIsColliding() {
	return isColliding;
}

private void setIsColliding(Collision isColliding) {
	this.isColliding = isColliding;
}

HashMap<Integer,GameObject> getAllGemsFound() {
	return _allGemsFound;
}

void setAllGemsFound(HashMap<Integer,GameObject> _gems) {
	this._allGemsFound = _gems;
}	

private ArrayList<AlchemyGem> getStaticAlchemyGems() {
	return _staticAlchemyGems;
}

private void setStaticAlchemyGems(ArrayList<AlchemyGem> _statisAlchemyGems) {
	this._staticAlchemyGems = _statisAlchemyGems;
}

private ArrayList<AlchemyGem> getMovableAlchemyGems() {
	return _movableAlchemyGems;
}

private void setMovableAlchemyGems(ArrayList<AlchemyGem> _movableAlchemyGems) {
	this._movableAlchemyGems = _movableAlchemyGems;
}

EventHandlerTimeOut getTimeout() {
	return _timeout;
}

void setTimeout(EventHandlerTimeOut _timeout) {
	this._timeout = _timeout;
}

CollisionSystem getCollisionSystem() {
	return _collisionSystem;
}

void setCollisionSystem(CollisionSystem _collisionSystem) {
	this._collisionSystem = _collisionSystem;
}

 */