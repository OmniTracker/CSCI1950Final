package wizard.utils;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import support.Vec2d;
import support.Vec2i;
import support.collision.AABShape;
import wizard.behaviortree.*;  
import wizard.behaviortree.actions.IncreaseSpeed;
import wizard.behaviortree.actions.UpdateDirectedTargetLocation;
import wizard.behaviortree.actions.UpdateRandomTargerLocation;
import wizard.behaviortree.conditions.DoesEnemyHaveTracker;
import wizard.behaviortree.conditions.IsAthletic;
import wizard.behaviortree.conditions.NeedToUpdateStoredPathForCharacter;
import wizard.behaviortree.sequenceNodes.WIZBehaviorSequence;
import engine.Application;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectDelegate;
import engine.utility.Factory;

public class WIZGameObjectDelegate extends GameObjectDelegate {
	Application _app; 
	private HashMap<String ,GameObject> _objslevelO = null;
	private HashMap<String ,GameObject> _objslevel1 = null;
	private WIZSpriteFactory _wizFactory  = null;
	private WIZDelegateContainer _wizDelegateContainer = null; 
	private HashMap<String,Image> _keyMap; 
	private Vec2d _screenSize = null;

	public WIZGameObjectDelegate(Application app) {
		super(app);
		this.setScreenSize(app.getAspectRatioHandler().getCurrentScreenSize());
	}
	public void setup (Application app) {
		this.setApp(app);
		this.setObjsLevelO( new HashMap<String ,GameObject> ());
		this.setObjsLevel1( new HashMap<String ,GameObject> ());
		this.setKeyMap( new HashMap<String,Image>() );
		this.setWizFactory(  new WIZSpriteFactory());
		this.loadWIZGameObjects();
	}
	public void loadWIZGameObjects ()  {		
		// Load all the Keys into game.
		this.generateKeys();
		// Load Game Map Data
		GameObject main   = generateHero();
		GameObject enemy0 = generateEnemy0(main);
		GameObject enemy1 = generateEnemy1(main);
		GameObject enemy2 = generateEnemy2(main);
		GameObject enemy3 = generateEnemy3(main);
		// Dynamically added characters
		GameObject enemy4 = generateEnemy1(main);
		enemy4.getData().setImage( Factory.getZelchSprite());
		GameObject enemy5 = generateEnemy3(main);
		enemy5.getData().setImage(Factory.getZelchSprite());
		// Level 0
		enemy0.setGameLevel(0); 
		enemy1.setGameLevel(0); 
		enemy4.setGameLevel(0); 
		this.getObjsLevelO().put("Main", main);
		this.getObjsLevelO().put("Enemy", enemy4);
		this.getObjsLevelO().put("Enemy0", enemy0);
		this.getObjsLevelO().put("Enemy1", enemy1);
		// Level 1
		enemy2.setGameLevel(1); 
		enemy3.setGameLevel(1); 
		enemy5.setGameLevel(1); 
		this.getObjsLevel1().put("Main", main);
		this.getObjsLevel1().put("Enemy2", enemy2);
		this.getObjsLevel1().put("Enemy3", enemy3);
		this.getObjsLevel1().put("Enemy", enemy5);
	}
	private void generateKeys() {
		this.getKeyMap().put( "red", this.getWizFactory().getRedKey());
		this.getKeyMap().put( "blue", this.getWizFactory().getBlueKey()); 
		this.getKeyMap().put( "green", this.getWizFactory().getGreenKey()); 
	}
	/**
	 * The Main Character will be visible on each of the levels in the game. 
	 * The a lot of the values set for the main character are dummy values.
	 * @return GameObject with all the attributes for initializing the 
	 *         main character
	 */
	private GameObject generateHero() {		
		GameObject main = new GameObject(this.getApp());
		this.getFactory();
		main.getData().setImage( Factory.getEzraSprite());
		// Need to set the main character to the center of the screen.
		Vec2d pos = this.getScreenSize();  
		main.getData().setPosition(new Vec2d(pos.x / 2,pos.y / 2));
		main.getData().setGameLocation( new Vec2i (7,4));
		main.getData().setImageSize(new Vec2d(48,48));
		main.getData().setImageStart(new Vec2d(0,0));
		main.getData().setImageGameSize(new Vec2d(60,60));
		main.getData().setBox(new AABShape(main.getData().getPosition(), new Vec2d(60,60)));
		main.setWizSpeed(10); 
		// Systems
		main.getData().getSystems().add("Graphics"); 
		main.getData().getSystems().add("Behavior"); 
		main.getData().getSystems().add("Collision"); 
		// Components 
		main.getData().getComponents().add("Graphics");
		main.getData().getComponents().add("Collision");
		main.getData().getComponents().add("Movement");
		return main;
	}
	/**
	 * <------------------------- Vulture ------------------------->
	 * He can fly, so he theoretically has a tracker.
	 * 
	 *   - Condition 0  UpdateDirectedTargetLocation()
	 *   - Action    1  Teleport ();
	 *   
	 *   // Teleport
	 * 
	 * @param main
	 * @return
	 */
	private GameObject generateEnemy0(GameObject main) {
		GameObject enemy = new GameObject(this.getApp()); 
		this.getFactory();
		enemy.getData().setImage( Factory.getVultureSprite());
		enemy.getData().setAIposition(new Vec2d(900,1100));
		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));

		enemy.setWizSpeed(5);

		enemy.getData().setBox(new AABShape(main.getData().getPosition(), new Vec2d(60,60)));

		// B Tree
		enemy.getData().setWIZBehaviorTree( new WIZBehaviorTree());

		// First Sequence
		ArrayList<WIZBehaviorSequence> firstSequence = new ArrayList<WIZBehaviorSequence>(); 

		DoesEnemyHaveTracker doesEnemyHaveTracker = new DoesEnemyHaveTracker(enemy,main); 
		doesEnemyHaveTracker.setActiveTracker(true);
		firstSequence.add(doesEnemyHaveTracker); 

		NeedToUpdateStoredPathForCharacter isCharacterOnStoredPath = new NeedToUpdateStoredPathForCharacter(enemy,main);
		firstSequence.add(isCharacterOnStoredPath); 

		// This will always return true
		UpdateDirectedTargetLocation updateDirectedTargetLocation = new UpdateDirectedTargetLocation(enemy,main); 
		firstSequence.add(updateDirectedTargetLocation); 

		// Second Sequence
		ArrayList<WIZBehaviorSequence> secondSequence = new ArrayList<WIZBehaviorSequence>(); 

		IsAthletic isAthletic = new IsAthletic(enemy,main); 
		isAthletic.setStatus(true);
		secondSequence.add(isAthletic); 

		IncreaseSpeed increaseSpeed = new IncreaseSpeed(enemy,main);
		// How many steps at max speed. The main character can only move max for now
		increaseSpeed.setMaxSpeed(5);  
		secondSequence.add(increaseSpeed);

		// Insert sequences	
		enemy.getData().getWIZBehaviorTree().getWIZSelector().getSequence().add(firstSequence);
		enemy.getData().getWIZBehaviorTree().getWIZSelector().getSequence().add(secondSequence);


		// Subscribe to Systems and Component

		// Systems
		enemy.getData().getSystems().add("Graphics"); 
		enemy.getData().getSystems().add("Behavior"); 
		enemy.getData().getSystems().add("Collision"); 
		// Components 
		enemy.getData().getComponents().add("Graphics");
		enemy.getData().getComponents().add("Collision");
		return enemy;
	}

	/**
	 * Archy is the enemy who shall be generated in this function.
	 * He can always find a path to the main character, but his tracker
	 * is off. He always gets a longer path.
	 * 
	 * 	- Condition 0 :  Need To Update Stored Path For Character
	 *  - Action 0    :  Update Random Target Location
	 *  
	 *  - Is Athletic 1: Can he move faster as time pass. 
	 *      
	 *   // If you time to implement projec, add it here.
	 * 
	 *  
	 * @param main
	 * @return
	 */
	private GameObject generateEnemy1(GameObject main) {
		GameObject enemy = new GameObject(this.getApp()); 
		this.getFactory();
		enemy.getData().setImage( Factory.getArchySprite());
		enemy.getData().setAIposition(new Vec2d(1500,300));
		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));
		enemy.getData().setBox(new AABShape(main.getData().getAIposition(), new Vec2d(60,60)));
		// Used to speed up the player in the game
		enemy.setWizSpeed(5);

		// B Tree
		enemy.getData().setWIZBehaviorTree( new WIZBehaviorTree());

		// First Sequence
		ArrayList<WIZBehaviorSequence> firstSequence = new ArrayList<WIZBehaviorSequence>(); 
		DoesEnemyHaveTracker doesEnemyHaveTracker = new DoesEnemyHaveTracker(enemy,main); 
		doesEnemyHaveTracker.setActiveTracker(true);
		firstSequence.add(doesEnemyHaveTracker); 

		NeedToUpdateStoredPathForCharacter isCharacterOnStoredPath = new NeedToUpdateStoredPathForCharacter(enemy,main);
		firstSequence.add(isCharacterOnStoredPath); 

		// This will always return true
		UpdateRandomTargerLocation updateRandomTargerLocation = new UpdateRandomTargerLocation(enemy,main); 
		firstSequence.add(updateRandomTargerLocation); 

		// Second Sequence
		ArrayList<WIZBehaviorSequence> secondSequence = new ArrayList<WIZBehaviorSequence>(); 

		IsAthletic isAthletic = new IsAthletic(enemy,main); 
		isAthletic.setStatus(true);
		secondSequence.add(isAthletic); 

		IncreaseSpeed increaseSpeed = new IncreaseSpeed(enemy,main);
		// How many steps at max speed. The main character can only move max for now
		increaseSpeed.setMaxSpeed(5);  
		secondSequence.add(increaseSpeed);

		// Insert sequences	
		enemy.getData().getWIZBehaviorTree().getWIZSelector().getSequence().add(firstSequence);
		enemy.getData().getWIZBehaviorTree().getWIZSelector().getSequence().add(secondSequence);

		// Subscribe to Systems and Component

		// Systems
		enemy.getData().getSystems().add("Graphics"); 
		enemy.getData().getSystems().add("Behavior"); 
		enemy.getData().getSystems().add("Collision"); 
		// Components 
		enemy.getData().getComponents().add("Graphics");
		enemy.getData().getComponents().add("Collision");
		return enemy;
	}
	/**
	 * Lyla is the enemy who shall be generated in this function.
	 * She isn't athletic, but she is smart enough to find the main
	 * character.
	 * 
	 *  - Condition 0    DoesEnemyHaveTracker()
	 * 	- Condition 1 :  IsCharacterOnStoredPath()
	 *  - Action 1:      UpdateDirectedTargetLocation()
	 *  
	 *  - Condition 2 :  IsAthletic()
	 *  - Action 2:   :  SlowDownSpeed() 
	 * 
	 * @return GameObject associated with Lyla
	 */
	private GameObject generateEnemy2(GameObject main) {
		GameObject enemy = new GameObject(this.getApp()); 
		this.getFactory();
		enemy.getData().setImage( Factory.getLylaSprite());
		enemy.getData().setAIposition(new Vec2d(1700,400));
		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));
		enemy.getData().setBox(new AABShape(enemy.getData().getPosition(), new Vec2d(60,60)));
		enemy.setWizSpeed(5);
		// First Sequence
		ArrayList<WIZBehaviorSequence> firstSequence = new ArrayList<WIZBehaviorSequence>(); 
		DoesEnemyHaveTracker doesEnemyHaveTracker = new DoesEnemyHaveTracker(enemy,main); 
		doesEnemyHaveTracker.setActiveTracker(true);
		firstSequence.add(doesEnemyHaveTracker); 

		NeedToUpdateStoredPathForCharacter isCharacterOnStoredPath = new NeedToUpdateStoredPathForCharacter(enemy,main);
		firstSequence.add(isCharacterOnStoredPath); 

		// This will always return true
		UpdateRandomTargerLocation updateRandomTargerLocation = new UpdateRandomTargerLocation(enemy,main); 
		firstSequence.add(updateRandomTargerLocation); 
		enemy.getData().getWIZBehaviorTree().getWIZSelector().getSequence().add(firstSequence);

		// Subscribe to Systems and Components

		// Systems
		enemy.getData().getSystems().add("Graphics"); 
		enemy.getData().getSystems().add("Behavior"); 
		enemy.getData().getSystems().add("Collision"); 
		// Components 
		enemy.getData().getComponents().add("Graphics");
		enemy.getData().getComponents().add("Collision");
		return enemy;
	}
	/**
	 * Zelch is the enemy who shall be generated in this function.
	 * He left his tracker at home, so he is basically walking around 
	 * blind until he find a path. He might find a path to the
	 * character, but he can easily lose the character with on move. 
	 * Since he works out, he speeds up just enough to make up for his poor 
	 * path planning.
	 *  
	 *  - Condition 0    DoesEnemyHaveTracker()
	 * 	- Condition 1 :  IsCharacterOnStoredPath()
	 *  - Action 1:      UpdateRandomTargerLocation()
	 *  
	 *  - Condition 2 :  IsAthletic()
	 *  - Action 2:   :  IncreaseSpeed() 
	 * 
	 * @return GameObject associated with Lyla
	 */
	/******************************** Sort of tired now. Finish this on Sunday night ****************************/ 
	private GameObject generateEnemy3(GameObject main) {
		// Supporting Characters
		GameObject enemy = new GameObject(this.getApp()); 
		this.getFactory();
		enemy.getData().setImage( Factory.getZelchSprite());
		enemy.getData().setAIposition(new Vec2d(1300,1900));
		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));
		enemy.getData().setBox(new AABShape(enemy.getData().getPosition(), new Vec2d(60,60)));

		// B Tree
		enemy.getData().setWIZBehaviorTree( new WIZBehaviorTree());
		// First Sequence
		ArrayList<WIZBehaviorSequence> firstSequence = new ArrayList<WIZBehaviorSequence>(); 
		DoesEnemyHaveTracker doesEnemyHaveTracker = new DoesEnemyHaveTracker(enemy,main); 
		doesEnemyHaveTracker.setActiveTracker(true);
		firstSequence.add(doesEnemyHaveTracker); 

		NeedToUpdateStoredPathForCharacter isCharacterOnStoredPath = new NeedToUpdateStoredPathForCharacter(enemy,main);
		firstSequence.add(isCharacterOnStoredPath); 

		// This will always return true
		UpdateRandomTargerLocation updateRandomTargerLocation = new UpdateRandomTargerLocation(enemy,main); 
		firstSequence.add(updateRandomTargerLocation); 

		// Second Sequence
		enemy.setWizSpeed(5); 
		enemy.getData().getWIZBehaviorTree().getWIZSelector().getSequence().add(firstSequence);

		// Subscribe to Systems and Components
		// Systems
		enemy.getData().getSystems().add("Graphics"); 
		enemy.getData().getSystems().add("Behavior"); 
		enemy.getData().getSystems().add("Collision"); 
		// Components 
		enemy.getData().getComponents().add("Graphics");
		enemy.getData().getComponents().add("Collision");
		return enemy;
	}
	public HashMap<String ,GameObject> getObjsLevelO() {
		return _objslevelO;
	}
	protected void setObjsLevelO(HashMap<String ,GameObject> _objslevelO) {
		this._objslevelO = _objslevelO;
	}
	public HashMap<String ,GameObject> getObjsLevel1() {
		return _objslevel1;
	}
	protected void setObjsLevel1(HashMap<String ,GameObject> _objslevel1) {
		this._objslevel1 = _objslevel1;
	}
	Vec2d getScreenSize() {
		return _screenSize;
	}
	void setScreenSize(Vec2d _screenSize) {
		this._screenSize = _screenSize;
	}
	public WIZDelegateContainer getWIZDelegateContainer() { 		
		return _wizDelegateContainer;
	}
	public void setWIZDelegateContainer(WIZDelegateContainer _wizDelegateContainer) {
		this._wizDelegateContainer = _wizDelegateContainer;
	}
	public HashMap<String,Image> getKeyMap() {
		return _keyMap;
	}
	private void setKeyMap(HashMap<String,Image> _keyMap) {
		this._keyMap = _keyMap;
	}
	public WIZSpriteFactory getWizFactory() {
		return _wizFactory;
	}
	public void setWizFactory(WIZSpriteFactory _wizFactory) {
		this._wizFactory = _wizFactory;
	}
}