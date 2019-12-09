package finalgame.maingameloop.gameworldmanager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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
import engine.ai.BehaviorTree;
import engine.ai.Selector;
import engine.ai.Sequencer;
import engine.utility.Factory;

import finalgame.maingameloop.FinalGameWorld;
import finalgame.engineAdditions.GameSystem;
import finalgame.ai.MoveToLeader;
import finalgame.ai.NotNearGroup;
import finalgame.ai.TestGI;
import finalgame.engineAdditions.AABCollisionComponent;
import finalgame.engineAdditions.AIBehaviorComponent;
import finalgame.engineAdditions.AnimateGraphicsComponent;
import finalgame.engineAdditions.BehaviorSystem;
import finalgame.engineAdditions.CollisionSystem;
import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.GraphicsSystem;
import finalgame.engineAdditions.HealthComponent;
import finalgame.engineAdditions.MouseAbilityAnimationComponent;
import finalgame.engineAdditions.PlayerInputComponent;
import finalgame.engineAdditions.PlayerInputSystem;
import finalgame.engineAdditions.ScratchAbilityAnimationComponent;
import finalgame.engineAdditions.TeleportAbilityComponent;
import finalgame.engineAdditions.TickSystem;
import finalgame.engineAdditions.TransformComponent;

import javafx.scene.paint.Color;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.ui.EngineFonts;
import finalgame.ui.GamePlayOverlay;
import finalgame.ui.HighScorePanel;


public class MainGamePlay extends GameWorld {

    //Highscore info
    private boolean _highScoreTest = true;
    private HighScorePanel _highScorePanel = null;
    private Button _highScoreTestButton = null;


	private ArrayList<GameSystem> _systems;
	private ArrayList<GameObject> _objects;
	private ArrayList<GameObject> _garbage;

	//Systems to maintain various engine functions
	private GraphicsSystem _graphicsSys;
	private TickSystem _tickSys;
	private PlayerInputSystem _inputSys;
	private CollisionSystem _collisionSys;
	private BehaviorSystem _behaviorSys;

	// Game Play Overlay
	private GamePlayOverlay _gamePlayOverlay;

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

		_systems = new ArrayList<GameSystem>();
		_objects = new ArrayList<GameObject>();
		_garbage = new ArrayList<GameObject>();

		this.initializeSystems();
		this.setupGeneralUI();

		_affine = new Affine();

		// Used to display game view overlay
		set_gamePlayOverlay(new GamePlayOverlay(app,parent));
		get_gamePlayOverlay().setKeyValues();
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
		this.addSpecificCharacterComponents();
		_objects.add(_player);
		this.addToSystems(_player);
		PlayerInputComponent curr = (PlayerInputComponent)_player.getComponent("INPUT");
		curr.setFocus(true);
	}

	private void addSpecificCharacterComponents() {
		switch(_selectedCharacter) {
			case 0:
				//LYLA
//				_player.addComponent("HEALTH", new HealthComponent(_player, 100));
				break;
			case 1:
				//EZRA
//				_player.addComponent("HEALTH", new HealthComponent(_player, 150));
				break;
			case 2:
				//SAM
//				_player.addComponent("HEALTH", new HealthComponent(_player, 125));
				break;
			case 3:
				//ARCHY
				_player.addComponent("HEALTH", new HealthComponent(_player, 200));

				_player.addComponent("ABILITY_E", new ScratchAbilityAnimationComponent(_player, getElectricScratchImage(), new Vec2d(0,0),
						new Vec2d(192, 192), new Vec2d(0,0), new Vec2d(50,50), new Vec2d(192, 192),
						11, 1, 2));

				_player.addComponent("ABILITY_F", new TeleportAbilityComponent(_player, getTeleportImage(), new Vec2d(0,0),
						new Vec2d(128, 128), new Vec2d(0,0), new Vec2d(0,0), new Vec2d(128, 128),
						58, 3, 2, 200));
				
				_player.addComponent("ABILITY_CLICK", new MouseAbilityAnimationComponent(_player, getBulletImage(), new Vec2d(135,123),
						new Vec2d(23, 23), new Vec2d(0,0), new Vec2d(10,10), new Vec2d(0, 0),1, 1.0, 0, 250.));
				break;
			default:
				break;
		}
	}

	private void loadMap() {

	}

	private void loadEnemies() {
		double follow_dist = 5000;
		GameObject g = new GameObject("ENEMY");
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(g, this.getPlayerImage(_selectedCharacter), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,100), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		TestGI gi = new TestGI();
		BehaviorTree bt = new BehaviorTree(new Selector(),gi, this, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotNearGroup(g,follow_dist));
		bt.addBehavior(1, new MoveToLeader(follow_dist));
		gi.setLeader(_player);
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_objects.add(g);
		this.addToSystems(g);

		g = new GameObject("ENEMY");
		animate = new AnimateGraphicsComponent(g, this.getPlayerImage(_selectedCharacter), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,200), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		bt = new BehaviorTree(new Selector(),gi, this, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotNearGroup(g,follow_dist));
		bt.addBehavior(1, new MoveToLeader(follow_dist));
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_objects.add(g);
		this.addToSystems(g);

		g = new GameObject("ENEMY");
		animate = new AnimateGraphicsComponent(g, this.getPlayerImage(_selectedCharacter), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,300), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		bt = new BehaviorTree(new Selector(),gi, this, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotNearGroup(g,follow_dist));
		bt.addBehavior(1, new MoveToLeader(follow_dist));
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_objects.add(g);
		this.addToSystems(g);

		g = new GameObject("ENEMY");
		animate = new AnimateGraphicsComponent(g, this.getPlayerImage(_selectedCharacter), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,400), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		bt = new BehaviorTree(new Selector(),gi, this, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotNearGroup(g,follow_dist));
		bt.addBehavior(1, new MoveToLeader(follow_dist));
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_objects.add(g);
		this.addToSystems(g);
	}




	/*
	 * 		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));
		enemy.getData().setBox(new AABShape(main.getData().getAIposition(), new Vec2d(60,60)));
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



	private void setupGeneralUI ()
	{
		// Options Panel
		HighScorePanel highScorePanel = new HighScorePanel( this.getApplication().getAspectRatioHandler());
		highScorePanel.setColor(Color.DARKGRAY);
		highScorePanel.setSecondaryColor(Color.DARKGREEN);
		highScorePanel.setSize( new Vec2d(500,300));
		highScorePanel.setOrigin(new Vec2d(0,0));
		highScorePanel.setBoarderSize(10);
		this.setHighScorePanel(highScorePanel);
		if (_highScoreTest == true)
		{
			// Options Button
			Button highScoreTestButton = new Button();
			highScoreTestButton.setText("Test High Score");
			highScoreTestButton.setSize( new Vec2d(250,70));
			highScoreTestButton.setColor(Color.BLUE);
			highScoreTestButton.setFontName(EngineFonts.getAlc());
			this.setHighScoreTestButton(highScoreTestButton);
		}
	}


    public static Image getEzraSprite (){
        Image out = null;
		try{
			out =  new Image(new File("resources/characters/ezra/little/ezra.png").toURI().toURL().toExternalForm());
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
	public static Image getElectricScratchImage() {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/ArchyAbilities/electricScratch.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	public static Image getBulletImage() {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/ArchyAbilities/simpleProjectile.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	public static Image getTeleportImage() {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/ArchyAbilities/teleport.png").toURI().toURL().toExternalForm());
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
		else
		{
			this.getHighScorePanel().onMouseClicked(e);
		}
	}
	 */
	@Override
	public void onTick(long nanosSincePreviousTick) {
//		System.out.print("Main Game Play \n");
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
        //Highscore Related Draw calls
        if (this.getHighScoreTestButton() != null)
        {
            Vec2d origin   = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin();
            Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize();
            Vec2d buttonOrigin = origin.plus( (size.x / 2), (size.y / 2));
            this.getHighScoreTestButton().setOrigin(buttonOrigin);
            this.getHighScoreTestButton().drawRounded(g);
        }

        if (this.getHighScorePanel().isShowing())
        {
            this.getHighScorePanel().onDraw(g);
        }

		_affine = g.getTransform();
		//Draw some border for the game to look nice

		_graphicsSys.onDraw(g, _affine);
		g.restore();

		// Draw
		get_gamePlayOverlay().drawOverlay(g);
	}


	@Override
	public void onKeyTyped(KeyEvent e) {}


	@Override
	public void onKeyPressed(KeyEvent e) {
		if ( this.getHighScorePanel().isShowing() == true)
		{
			this.getHighScorePanel().onKeyPressed(e);
		}
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
		if(e.getButton() == MouseButton.PRIMARY) {
			_input.put("MOUSE_LEFT", 1.);
		}
		else if (e.getButton() == MouseButton.SECONDARY) {
			_input.put("MOUSE_RIGHT", 1.);
		}
		this.onInput();
	}
	@Override
	public void onMouseReleased(MouseEvent e) {
		if(e.getButton() == MouseButton.PRIMARY) {
			_input.put("MOUSE_LEFT", 0.);
		}
		else if (e.getButton() == MouseButton.SECONDARY) {
			_input.put("MOUSE_RIGHT", 0.);
		}
		this.onInput();
	}

    @Override
    public void onMouseDragged(MouseEvent e) {
		_input.put("MOUSE.X", e.getX());
		_input.put("MOUSE.Y", e.getY());
		this.onInput();
	}
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

	private Button getHighScoreTestButton() {
		return _highScoreTestButton;
	}
	private void setHighScoreTestButton(Button _highScoreTestButton) {
		this._highScoreTestButton = _highScoreTestButton;
	}
	private HighScorePanel getHighScorePanel() {
		return _highScorePanel;
	}
	private void setHighScorePanel(HighScorePanel _highScorePanel) {
		this._highScorePanel = _highScorePanel;
	}

	@Override
	public void tickCollision() {
		_collisionSys.onTick(0);
	}

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

	@Override
	public ArrayList<GameObject> getObjects() {
		return _objects;
	}


	public GamePlayOverlay get_gamePlayOverlay() {
		return _gamePlayOverlay;
	}


	public void set_gamePlayOverlay(GamePlayOverlay _gamePlayOverlay) {
		this._gamePlayOverlay = _gamePlayOverlay;
	}


}
