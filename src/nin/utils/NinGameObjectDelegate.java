package nin.utils;

import java.util.ArrayList;

import nin.behaviortree.NinBehaviorSequence;
import nin.behaviortree.NINBehaviorTree;
import nin.behaviortree.actions.PhysicsAction;
import nin.behaviortree.conditions.IsColliding;
import nin.behaviortree.conditions.JumpSignaled;
import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectDelegate;
import engine.utility.Factory;

public class NinGameObjectDelegate extends GameObjectDelegate {

	private ArrayList<GameObject> _gameCharacters = null;
	private ArrayList<GameObject> _movingUnits = null;
	private ArrayList<GameObject> _movingCoins = null;
	private ArrayList<GameObject> _movingBullets = null;
	private GameObject _saveBullet = null;
	
	GameObject mainCharacter; 
	GameObject testCharacter0; 
	GameObject testCharacter1; 
	GameObject testCharacter2;
	GameObject _static; 
	PhysicsAction p0; 
	PhysicsAction p1; 
	PhysicsAction p2;
	PhysicsAction p3;

	public NinGameObjectDelegate(Application app) {
		super(app);	
		this.setGameCharacters(new ArrayList<GameObject>());
		this.setMovingUnits(new ArrayList<GameObject>());
		this.setMovingCoins( new ArrayList<GameObject>());
		this.setMovingBullets(  new ArrayList<GameObject>());
	}

	public void initMovingCoins () {
		// Only allow 10 coins at a time.
		for (int i = 0;  i < 10  ;i++) {
			GameObject coin = new GameObject();
			coin.getData().setImage( Factory.getGenericImage("resources/terrain/bitcoin.png"));
			coin.getData().setSize(new Vec2d(75,75));
			coin.getData().setPosition( new Vec2d(-200,30));
			coin.getData().setBox(new AABShape(coin.getData().getPosition(), coin.getData().getSize()));
			this.getMovingCoins().add(coin);
		}
	}
	
	public void initMovingBullets ( ) {
		// Anti bullets
		for (int x = 0;  x < 6 ; x++) 
		{	
			GameObject bullet = new GameObject();
			bullet.getData().setImage( Factory.getGenericImage("resources/terrain/BulletBill.png"));
			bullet.getData().setSize(new Vec2d(50,25));
			bullet.getData().setPosition( new Vec2d(-200,30));
			bullet.getData().setBox(new AABShape(bullet.getData().getPosition(), bullet.getData().getSize()));
			this.getMovingBullets().add(bullet); 
		}		
	}
	
	public void initSaveBullet ( ) {
		setSaveBullet(new GameObject());
		getSaveBullet().getData().setImage( Factory.getGenericImage("resources/terrain/wave.png"));
		getSaveBullet().getData().setSize(new Vec2d(100,100));
		getSaveBullet().getData().setPosition( new Vec2d(-200,30));
		getSaveBullet().getData().setBox(new AABShape(getSaveBullet().getData().getPosition(), getSaveBullet().getData().getSize()));	
	}

	public void initCharacter () {
		_static = new GameObject();
		_static.getData().setName("static");

		// Make main Character 
		mainCharacter = new GameObject();
		this.getFactory();
		mainCharacter.getData().setName("Main");


		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence = new ArrayList<NinBehaviorSequence>(); 
		mainCharacter.getData().setNinBehaviorTree(new NINBehaviorTree());
		mainCharacter.getData().getNinBehaviorTree().setName("Main");

		sequence.add(new JumpSignaled(mainCharacter)); 
		sequence.add(new PhysicsAction(mainCharacter)); 

		// Add Sequence node
		mainCharacter.getData().getNinBehaviorTree().getSequence().add(sequence); 

		// Finally add the character to the ArrayList
		this.getGameCharacters().add(mainCharacter);

		testCharacter0 = new GameObject();
		this.getFactory();
		testCharacter0.getData().setName("0");

		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence0 = new ArrayList<NinBehaviorSequence>(); 
		testCharacter0.getData().setNinBehaviorTree(new NINBehaviorTree());
		testCharacter0.getData().getNinBehaviorTree().setName("0");

		// Add Sequence node
		testCharacter0.getData().getNinBehaviorTree().getSequence().add(sequence0); 

		// Finally add the character to the ArrayList
		this.getGameCharacters().add(testCharacter0);
		testCharacter1 = new GameObject();
		this.getFactory();
		testCharacter1.getData().setName("1");

		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence1 = new ArrayList<NinBehaviorSequence>(); 
		testCharacter1.getData().setNinBehaviorTree(new NINBehaviorTree());
		testCharacter1.getData().getNinBehaviorTree().setName("1");
		// Add Sequence node
		testCharacter1.getData().getNinBehaviorTree().getSequence().add(sequence1); 
		// Finally add the character to the ArrayList
		this.getGameCharacters().add(testCharacter1);

		testCharacter2 = new GameObject();
		this.getFactory();
		testCharacter2.getData().setName("2");
		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence2 = new ArrayList<NinBehaviorSequence>(); 
		testCharacter2.getData().setNinBehaviorTree(new NINBehaviorTree());
		testCharacter2.getData().getNinBehaviorTree().setName("2");
		// Add Sequence node
		testCharacter2.getData().getNinBehaviorTree().getSequence().add(sequence2); 

		// Finally add the character to the ArrayList
		this.getGameCharacters().add(testCharacter2);

		// Add for collision checking
		IsColliding c0 = new IsColliding(testCharacter0); 
		c0.getOtherCharacter().add(testCharacter1); 
		c0.getOtherCharacter().add(testCharacter2); 

		IsColliding c1 = new IsColliding(testCharacter1); 
		c1.getOtherCharacter().add(testCharacter0); 
		c1.getOtherCharacter().add(testCharacter2); 

		IsColliding c2 = new IsColliding(testCharacter2); 
		c2.getOtherCharacter().add(testCharacter0); 
		c2.getOtherCharacter().add(testCharacter1); 

		sequence0.add(c0); 
		sequence1.add(c1); 
		sequence2.add(c2); 

		p0 = new PhysicsAction(testCharacter0); 
		p1 = new PhysicsAction(testCharacter1); 
		p2 = new PhysicsAction(testCharacter2);
		p3 = new PhysicsAction(_static);

		sequence0.add(p0); 
		sequence1.add(p1); 
		sequence2.add(p2); 
		reset();
	}

	public void reset() {
		p0.getOtherCharacter().add(testCharacter1); 
		p0.getOtherCharacter().add(testCharacter2); 
		p0.setMass(30);
		p0.setCOR(0.5);
		p0.setWalkVel(0.5);
		p0.setVelocity(new Vec2d(0,0) );

		p1.getOtherCharacter().add(testCharacter0); 
		p1.getOtherCharacter().add(testCharacter2); 
		p1.setMass(50);
		p1.setCOR(0.3);
		p1.setWalkVel(0.7);
		p1.setVelocity(new Vec2d(0,0) );
		p2.getOtherCharacter().add(testCharacter0); 
		p2.getOtherCharacter().add(testCharacter1); 
		p2.setMass(90);
		p2.setCOR(0.2);
		p2.setWalkVel(-0.3);
		p2.setVelocity(new Vec2d(0,0) );

		testCharacter0.getData().setImage( Factory.getNinBricks());
		testCharacter0.getData().setPosition(new Vec2d(600,400));
		testCharacter0.getData().setImageSize(new Vec2d(48,48));
		testCharacter0.getData().setImageStart(new Vec2d(0,0));
		testCharacter0.getData().setImageGameSize(new Vec2d(60,60));
		testCharacter0.getData().setBox(new AABShape(testCharacter0.getData().getPosition(), new Vec2d(30,60)));
		testCharacter1.getData().setImage( Factory.getNinBricks());
		testCharacter1.getData().setPosition(new Vec2d(200,400));
		testCharacter1.getData().setImageSize(new Vec2d(48,48));
		testCharacter1.getData().setImageStart(new Vec2d(0,0));
		testCharacter1.getData().setImageGameSize(new Vec2d(60,60));
		testCharacter1.getData().setBox(new AABShape(testCharacter1.getData().getPosition(), new Vec2d(20,120)));
		testCharacter2.getData().setImage( Factory.getNinBricks());
		testCharacter2.getData().setPosition(new Vec2d(800,400));
		testCharacter2.getData().setImageSize(new Vec2d(48,48));
		testCharacter2.getData().setImageStart(new Vec2d(0,0));
		testCharacter2.getData().setImageGameSize(new Vec2d(60,60));
		testCharacter2.getData().setBox(new AABShape(mainCharacter.getData().getPosition(), new Vec2d(40,100)));
		mainCharacter.getData().setImage( Factory.getNinBricks());
		mainCharacter.getData().setPosition(new Vec2d(200,0));
		mainCharacter.getData().setImageSize(new Vec2d(48,48));
		mainCharacter.getData().setImageStart(new Vec2d(0,0));
		mainCharacter.getData().setImageGameSize(new Vec2d(60,60));
		mainCharacter.getData().setBox(new AABShape(mainCharacter.getData().getPosition(), new Vec2d(100,100)));
	}


	public void resetStatic() {

		p0.getOtherCharacter().add(testCharacter1); 
		p0.getOtherCharacter().add(testCharacter2); 
		p0.setMass(30);
		p0.setCOR(0.5);
		p0.setWalkVel(0.6);
		p0.setVelocity(new Vec2d(.6,0) );
		p1.getOtherCharacter().add(testCharacter0); 
		p1.getOtherCharacter().add(testCharacter2); 
		p1.setMass(50);
		p1.setCOR(0.3);
		p1.setWalkVel(0.8);
		p1.setVelocity(new Vec2d(0.0,0) );

		p2.getOtherCharacter().add(testCharacter0); 
		p2.getOtherCharacter().add(testCharacter1); 
		p2.setMass(30);
		p2.setCOR(0.0);
		p2.setWalkVel(0);
		p2.setVelocity(new Vec2d(0,0) );
		p2.setStaticFlag(false);

		testCharacter0.getData().setImage( Factory.getNinBricks());
		testCharacter0.getData().setPosition(new Vec2d(600,400));
		testCharacter0.getData().setImageSize(new Vec2d(48,48));
		testCharacter0.getData().setImageStart(new Vec2d(0,0));
		testCharacter0.getData().setImageGameSize(new Vec2d(60,60));
		testCharacter0.getData().setBox(new AABShape(testCharacter0.getData().getPosition(), new Vec2d(30,60)));

		testCharacter1.getData().setImage( Factory.getNinBricks());
		testCharacter1.getData().setPosition(new Vec2d(200,400));
		testCharacter1.getData().setImageSize(new Vec2d(48,48));
		testCharacter1.getData().setImageStart(new Vec2d(0,0));
		testCharacter1.getData().setImageGameSize(new Vec2d(60,60));
		testCharacter1.getData().setBox(new AABShape(testCharacter1.getData().getPosition(), new Vec2d(20,120)));

		testCharacter2.getData().setImage( Factory.getNinBricks());
		testCharacter2.getData().setPosition(new Vec2d(800,400));
		testCharacter2.getData().setImageSize(new Vec2d(48,48));
		testCharacter2.getData().setImageStart(new Vec2d(0,0));
		testCharacter2.getData().setImageGameSize(new Vec2d(60,60));
		testCharacter2.getData().setBox(new AABShape(mainCharacter.getData().getPosition(), new Vec2d(40,100)));

		mainCharacter.getData().setImage( Factory.getNinBricks());
		mainCharacter.getData().setPosition(new Vec2d(200,0));
		mainCharacter.getData().setImageSize(new Vec2d(48,48));
		mainCharacter.getData().setImageStart(new Vec2d(0,0));
		mainCharacter.getData().setImageGameSize(new Vec2d(60,60));
		mainCharacter.getData().setBox(new AABShape(mainCharacter.getData().getPosition(), new Vec2d(100,100)));
	}

	public ArrayList<GameObject> getGameCharacters() {
		return _gameCharacters;
	}
	public void setGameCharacters(ArrayList<GameObject> _gameCharacters) {
		this._gameCharacters = _gameCharacters;
	}

	public ArrayList<GameObject> getMovingUnits() {
		return _movingUnits;
	}

	public void setMovingUnits(ArrayList<GameObject> _movingUnits) {
		this._movingUnits = _movingUnits;
	}

	public ArrayList<GameObject> getMovingCoins() {
		return _movingCoins;
	}

	private void setMovingCoins(ArrayList<GameObject> _movingCoins) {
		this._movingCoins = _movingCoins;
	}

	public ArrayList<GameObject> getMovingBullets() {
		return _movingBullets;
	}

	public void setMovingBullets(ArrayList<GameObject> _movingBullets) {
		this._movingBullets = _movingBullets;
	}

	public GameObject getSaveBullet() {
		return _saveBullet;
	}

	private void setSaveBullet(GameObject _saveBullet) {
		this._saveBullet = _saveBullet;
	}	
}
