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
		mainCharacter.setNinBehaviorTree(new NINBehaviorTree());
		mainCharacter.getNinBehaviorTree().setName("Main");

		sequence.add(new JumpSignaled(mainCharacter)); 
		sequence.add(new PhysicsAction(mainCharacter)); 

		// Add Sequence node
		mainCharacter.getNinBehaviorTree().getSequence().add(sequence); 

		// Finally add the character to the ArrayList
		this.getGameCharacters().add(mainCharacter);

		testCharacter0 = new GameObject();
		this.getFactory();
		testCharacter0.getData().setName("0");

		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence0 = new ArrayList<NinBehaviorSequence>(); 
		testCharacter0.setNinBehaviorTree(new NINBehaviorTree());
		testCharacter0.getNinBehaviorTree().setName("0");

		// Add Sequence node
		testCharacter0.getNinBehaviorTree().getSequence().add(sequence0); 

		// Finally add the character to the ArrayList
		this.getGameCharacters().add(testCharacter0);
		testCharacter1 = new GameObject();
		this.getFactory();
		testCharacter1.setName("1");

		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence1 = new ArrayList<NinBehaviorSequence>(); 
		testCharacter1.setNinBehaviorTree(new NINBehaviorTree());
		testCharacter1.getNinBehaviorTree().setName("1");
		// Add Sequence node
		testCharacter1.getNinBehaviorTree().getSequence().add(sequence1); 
		// Finally add the character to the ArrayList
		this.getGameCharacters().add(testCharacter1);

		testCharacter2 = new GameObject();
		this.getFactory();
		testCharacter2.setName("2");
		// Setup Sequence Node
		ArrayList<NinBehaviorSequence> sequence2 = new ArrayList<NinBehaviorSequence>(); 
		testCharacter2.setNinBehaviorTree(new NINBehaviorTree());
		testCharacter2.getNinBehaviorTree().setName("2");
		// Add Sequence node
		testCharacter2.getNinBehaviorTree().getSequence().add(sequence2); 

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
		
		testCharacter0.setImage( Factory.getNinBricks());
		testCharacter0.setPosition(new Vec2d(600,400));
		testCharacter0.setImageSize(new Vec2d(48,48));
		testCharacter0.setImageStart(new Vec2d(0,0));
		testCharacter0.setImageGameSize(new Vec2d(60,60));
		testCharacter0.setBox(new AABShape(testCharacter0.getPosition(), new Vec2d(30,60)));
		testCharacter1.setImage( Factory.getNinBricks());
		testCharacter1.setPosition(new Vec2d(200,400));
		testCharacter1.setImageSize(new Vec2d(48,48));
		testCharacter1.setImageStart(new Vec2d(0,0));
		testCharacter1.setImageGameSize(new Vec2d(60,60));
		testCharacter1.setBox(new AABShape(testCharacter1.getPosition(), new Vec2d(20,120)));
		testCharacter2.setImage( Factory.getNinBricks());
		testCharacter2.setPosition(new Vec2d(800,400));
		testCharacter2.setImageSize(new Vec2d(48,48));
		testCharacter2.setImageStart(new Vec2d(0,0));
		testCharacter2.setImageGameSize(new Vec2d(60,60));
		testCharacter2.setBox(new AABShape(mainCharacter.getPosition(), new Vec2d(40,100)));
		mainCharacter.setImage( Factory.getNinBricks());
		mainCharacter.setPosition(new Vec2d(200,0));
		mainCharacter.setImageSize(new Vec2d(48,48));
		mainCharacter.setImageStart(new Vec2d(0,0));
		mainCharacter.setImageGameSize(new Vec2d(60,60));
		mainCharacter.setBox(new AABShape(mainCharacter.getPosition(), new Vec2d(100,100)));
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
		testCharacter0.setImage( Factory.getNinBricks());
		testCharacter0.setPosition(new Vec2d(600,400));
		testCharacter0.setImageSize(new Vec2d(48,48));
		testCharacter0.setImageStart(new Vec2d(0,0));
		testCharacter0.setImageGameSize(new Vec2d(60,60));
		testCharacter0.setBox(new AABShape(testCharacter0.getPosition(), new Vec2d(30,60)));
		testCharacter1.setImage( Factory.getNinBricks());
		testCharacter1.setPosition(new Vec2d(200,400));
		testCharacter1.setImageSize(new Vec2d(48,48));
		testCharacter1.setImageStart(new Vec2d(0,0));
		testCharacter1.setImageGameSize(new Vec2d(60,60));
		testCharacter1.setBox(new AABShape(testCharacter1.getPosition(), new Vec2d(20,120)));
		testCharacter2.setImage( Factory.getNinBricks());
		testCharacter2.setPosition(new Vec2d(800,400));
		testCharacter2.setImageSize(new Vec2d(48,48));
		testCharacter2.setImageStart(new Vec2d(0,0));
		testCharacter2.setImageGameSize(new Vec2d(60,60));
		testCharacter2.setBox(new AABShape(mainCharacter.getPosition(), new Vec2d(40,100)));
		mainCharacter.setImage( Factory.getNinBricks());
		mainCharacter.setPosition(new Vec2d(200,0));
		mainCharacter.setImageSize(new Vec2d(48,48));
		mainCharacter.setImageStart(new Vec2d(0,0));
		mainCharacter.setImageGameSize(new Vec2d(60,60));
		mainCharacter.setBox(new AABShape(mainCharacter.getPosition(), new Vec2d(100,100)));
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
}
