package finalgame.maingameloop.gameworldmanager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import support.Vec2d;
import support.debugger.collisions.AABShape;
import support.debugger.support.Vec2f;
import support.debugger.support.shapes.AABShapeDefine;
import engine.Application;
import engine.GameWorld;
import engine.utility.Factory;
import finalgame.maingameloop.FinalGameWorld;

import finalgame.engineAdditions.GameSystem;
import finalgame.engineAdditions.AABCollisionComponent;
import finalgame.engineAdditions.AnimateGraphicsComponent;
import finalgame.engineAdditions.BehaviorSystem;
import finalgame.engineAdditions.CollisionSystem;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.GraphicsSystem;
import finalgame.engineAdditions.PlayerInputComponent;
import finalgame.engineAdditions.PlayerInputSystem;
import finalgame.engineAdditions.TickSystem;
import finalgame.engineAdditions.TransformComponent;

public class MainGamePlay extends GameWorld {
	

	private ArrayList<GameSystem> _systems;
	private ArrayList<GameObject> _objects;
	private ArrayList<GameObject> _garbage;
	
	//Systems to maintain various engine functions
	private GraphicsSystem _graphicsSys;
	private TickSystem _tickSys;
	private PlayerInputSystem _inputSys;
	private CollisionSystem _collisionSys;
	private BehaviorSystem _behaviorSys;
	
	//Hashmap that keeps track of all user input
	private HashMap<String, Double> _input;
	
	//GameObject that represents the current player
	private GameObject _player;

	private Affine _affine;
	
	private int _selectedCharacter;
	private FinalGameWorld _finalGameWorld;
	
	public MainGamePlay(Application app, FinalGameWorld parent) {
		super(app);
		_finalGameWorld = parent;
		_input = new HashMap<String, Double>();
		this.initializeInputs();
		
		_systems = new ArrayList<GameSystem>();
		_objects = new ArrayList<GameObject>();
		_garbage = new ArrayList<GameObject>();
		
		this.initializeSystems();

		_affine = new Affine();
		
	}
	
	
	private void initializeInputs() {
		//Must include all gameplay buttons, will depend on key bindings
		_input.put("UP", 0.);
		_input.put("DOWN", 0.);
		_input.put("LEFT", 0.);
		_input.put("RIGHT", 0.);
		_input.put("SPACE", 0.);
	}


	private void initializeSystems() {
		_tickSys = new TickSystem();
		_graphicsSys = new GraphicsSystem();
		_inputSys = new PlayerInputSystem();
		_collisionSys = new CollisionSystem(this);
		_behaviorSys = new BehaviorSystem();
		_systems.add(_tickSys);
		_systems.add(_graphicsSys);
		_systems.add(_inputSys);
		_systems.add(_collisionSys);
		_systems.add(_behaviorSys);
	}
	
	private void loadCharacter() {
		_selectedCharacter =_finalGameWorld.getCharacterSelection();
		_player = new GameObject("PLAYER");
		Vec2d gameSpawnLoc = toGameSpace(130, 100);
		//_player.addComponent("DRAW", new UnitGraphicsComponent(_player, _characterImage, new Vec2d(336,144), new Vec2d(32,48)));
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(_player, this.getPlayerImage(_selectedCharacter), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		_player.addComponent("DRAW", animate);
		_player.addComponent("ANIMATE", animate);
		_player.addComponent("TRANSFORM", new TransformComponent(_player, gameSpawnLoc, new Vec2d(40,60), 1.0));
		_player.addComponent("INPUT", new PlayerInputComponent(_player, _input));
		_player.addComponent("COLLISION", new AABCollisionComponent(_player, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		_objects.add(_player);
		this.addToSystems(_player);
		PlayerInputComponent curr = (PlayerInputComponent)_player.getComponent("INPUT");
		curr.setFocus(true);
	}
	
	private void loadMap() {
		
	}
	
	private void loadEnemies() {
		
	}




	/*
	 * 
	 * 
	 * 
	 * 		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));
		enemy.getData().setBox(new AABShape(main.getData().getAIposition(), new Vec2d(60,60)));
	 * 
	 * 
	 * 

	 */
	
	
	public Vec2d toGameSpace(double x, double y) {
		Point2D temp = null;
		try {
			temp = _affine.inverseTransform(x, y);
		} catch (NonInvertibleTransformException e1) {
			e1.printStackTrace();
		}
		return new Vec2d(temp.getX(), temp.getY());
	}
	
	public void setCharacter(int character) {
		AnimateGraphicsComponent temp = (AnimateGraphicsComponent)_player.getComponent("ANIMATE");
		temp.setCharacter(this.getPlayerImage(character));
	}
	
	public Image getPlayerImage(int character) {
		switch(character) {
			case 0:
				return getLylaSprite();
			case 1: 
				return getEzraSprite();
			case 2:
				return getSamSprite();
			case 3:
				return getArchySprite();
			default:
				return getArchySprite();
		}
		
	}
	
	public static Image getEzraSprite (){

		Image out = null;
		try{
			out = new Image(new File("resources/characters/ezra/little/ezra.png").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public static Image getZelchSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/zelch/zelch.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public static Image getArchySprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/archy/little/archyWalk.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public static Image getLylaSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/lyla/little/lyla.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public static Image getSamSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/sam/little/sam.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}

	/*
	 * 
	public static Image getVultureSprite () {
		try {
			return new Image(new File("resources/characters/vulture/vulture.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.print("Dafuq \n");
		return null;
	}


	 */
	@Override
	public void onTick(long nanosSincePreviousTick) {
		//System.out.print("Main Game Play \n");
		//Tick and split up into component ticks if any given tick is too long
		if(nanosSincePreviousTick > 50000000) {
			int n = (int)(nanosSincePreviousTick/50000000.);
			long lastTick = nanosSincePreviousTick%50000000;
			for(int i = 0; i < n; i++) {
				this.purge();
				_tickSys.onTick(50000000);
				_collisionSys.onTick(50000000);
				_inputSys.onTick(50000000);
				_behaviorSys.onTick(50000000);
			}
			this.purge();
			_tickSys.onTick(lastTick);
			_collisionSys.onTick(lastTick);
			_inputSys.onTick(lastTick);
			_behaviorSys.onTick(lastTick);
		}
		else {
			this.purge();
			_tickSys.onTick(nanosSincePreviousTick);
			_collisionSys.onTick(nanosSincePreviousTick);
			_inputSys.onTick(nanosSincePreviousTick);
			_behaviorSys.onTick(nanosSincePreviousTick);
		}
		
	}

	@Override
	public void onDraw(GraphicsContext g)  {
		_affine = g.getTransform();
		//Draw some boarder for the game to look nice
//		g.save();
//		g.rect(_pos.x*_windowSize.x, _pos.y*_windowSize.y, _dim.x*_windowSize.x, _dim.y*_windowSize.y);
//		g.strokeRect(_pos.x*_windowSize.x, _pos.y*_windowSize.y, _dim.x*_windowSize.x, _dim.y*_windowSize.y);
//		_affine.appendTranslation(_pos.x*_windowSize.x, _pos.y*_windowSize.y);
//		_affine.appendScale(_scale.x, _scale.y);
//		_affine.appendTranslation(_translation.x, _translation.y);
//		g.setTransform(_affine);
//		_gw.onDraw(g, _affine);
		
		_graphicsSys.onDraw(g, _affine);
		g.restore();
	}
	@Override
	public void onKeyTyped(KeyEvent e) {}
	@Override
	public void onKeyPressed(KeyEvent e) {
		_input.put(e.getCode().toString(), 1.);
		this.onInput();
	}
	@Override
	public void onKeyReleased(KeyEvent e) {
		_input.put(e.getCode().toString(), 0.);
		this.onInput();
	}
	@Override
	public void onMouseClicked(MouseEvent e) {}

	@Override
	public void onMousePressed(MouseEvent e) {
		_input.put("MOUSE_LEFT", 1.);
		this.onInput();		
	}
	@Override
	public void onMouseReleased(MouseEvent e) {
		_input.put("MOUSE_LEFT", 0.);
		this.onInput();
	}
	@Override
	public void onMouseDragged(MouseEvent e) {}
	@Override
	public void onMouseMoved(MouseEvent e) {
		_input.put("MOUSE.X", e.getX());
		_input.put("MOUSE.Y", e.getY());
		this.onInput();
	}
	@Override
	public void onMouseWheelMoved(ScrollEvent e) {}
	@Override
	public void onFocusChanged(boolean newVal) {}
	@Override
	public void onResize(Vec2d newSize) {}
	@Override
	public void onShutdown() {}
	@Override
	public void onStartup() {}
	
	public void onInput() {
		_inputSys.onInput(_input);
	}
	
	protected void purge() {
		for(int i = 0; i < _garbage.size();i++) {
			for(int j=0; j<_systems.size(); j++) {
				_systems.get(j).removeObject(_garbage.get(i));
			}
			_objects.remove(_garbage.get(i));
		}
		_garbage.clear();
	}
	
	private void addToSystems(GameObject go) {
		for(int i=0; i<_systems.size(); i++) {
			_systems.get(i).addObject(go);
		}
	}
	
	public void load() {
		this.loadMap();
		this.loadCharacter();
		this.loadEnemies();
	}
}
