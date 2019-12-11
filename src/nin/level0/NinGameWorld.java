package nin.level0;

import java.util.List;

import nin.systems.*;
import nin.utils.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.utility.Factory;

public class NinGameWorld  extends GameWorld {

	private NinGameObjectDelegate _ninGameObjectDelegate = null;
	private NinMapDelegate _ninMapDelegate = null;
	private GraphicsSystem _graphicsSystem = null;
	private MovementSystem _movementSystem = null;
	private CollisionSystem  _collisionSystem = null;
	
	private Button _button = null; 

	public int score = 0;
	public int lives = 5;
	public int high  = 0; 

	public int storedLives = 0;
	public int storedScore = 0;
	
	protected NinGameWorld(Application app) {
		super(app);
		this.setGraphicsSystem(new GraphicsSystem(app,this));
		this.setMovementSystem(new MovementSystem(app,this));
		this.setNinGameObjectDelegate( new NinGameObjectDelegate(app) );
		this.setNinMapDelegate( new NinMapDelegate(app));
		this.setCollisionSystem( new CollisionSystem(app,this));
		// Set High Score
		Factory.readNin();
		List<String> info = Factory.readNin().get(0);		
		high  = Integer.valueOf(info.get(2));
	}
	
	public void onTick(long nanosSincePreviousTick) {
		if ( this.getNinGameObjectDelegate().getGameCharacters().size() != 0) {
			// Run the Decision Tree. This will run on every Tick.
			this.getNinGameObjectDelegate().getGameCharacters().get(0).getData().getNinBehaviorTree().runTree();
			this.getNinGameObjectDelegate().getGameCharacters().get(1).getData().getNinBehaviorTree().runTree();
			this.getNinGameObjectDelegate().getGameCharacters().get(2).getData().getNinBehaviorTree().runTree();
			this.getNinGameObjectDelegate().getGameCharacters().get(3).getData().getNinBehaviorTree().runTree();
			
			// Need to reset this once I leave the this scene.
			
			
			
			this.getApplication().stage.setMaxHeight(this.getApplication().getAspectRatioHandler().getInitialScreenSize().y + 100);
			this.getApplication().stage.setMaxWidth(this.getApplication().getAspectRatioHandler().getInitialScreenSize().x + 100);
		}
		this.getMovementSystem().onTick(nanosSincePreviousTick);
		this.getCollisionSystem().onTick(nanosSincePreviousTick);
	}
	
	public void onDraw(GraphicsContext g) {
		if ( this.getNinGameObjectDelegate().getGameCharacters().size() == 0) {
			this.getNinGameObjectDelegate().initCharacter();
		}
		if (this.getNinMapDelegate().getGameBoardPlatforms().size() == 0 ) {
			this.getNinMapDelegate().initPlatform();
		}
		this.getGraphicsSystem().onDraw(g);
	}
	
	public void onMouseClicked(MouseEvent e) {
		if (_button != null) 
		{
			if ( _button.clicked(e) == true) 
			{	
				this.getNinGameObjectDelegate().resetStatic();
			}
		}
	}
	
	public void onKeyPressed(KeyEvent e)  {
		
		this.getMovementSystem().onKeyPressed(e);
	}
	public NinMapDelegate getNinMapDelegate() {
		return _ninMapDelegate;
	}
	private void setNinMapDelegate(NinMapDelegate _ninMapDelegate) {
		this._ninMapDelegate = _ninMapDelegate;
	}
	public NinGameObjectDelegate getNinGameObjectDelegate() {
		return _ninGameObjectDelegate;
	}
	private void setNinGameObjectDelegate(NinGameObjectDelegate _ninGameObjectDelegate) {
		this._ninGameObjectDelegate = _ninGameObjectDelegate;
	}
	private GraphicsSystem getGraphicsSystem() {
		return _graphicsSystem;
	}
	private void setGraphicsSystem(GraphicsSystem _graphicsSystem) {
		this._graphicsSystem = _graphicsSystem;
	}
	private MovementSystem getMovementSystem() {
		return _movementSystem;
	}
	private void setMovementSystem(MovementSystem _movementSystem) {
		this._movementSystem = _movementSystem;
	}
	public Button getButton() {
		return _button;
	}
	public void setButton(Button _button) {
		this._button = _button;
	}

	private CollisionSystem getCollisionSystem() {
		return _collisionSystem;
	}

	private void setCollisionSystem(CollisionSystem _collisionSystem) {
		this._collisionSystem = _collisionSystem;
	}
}
