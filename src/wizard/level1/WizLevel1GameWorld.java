package wizard.level1;

import support.Vec2d;
import wizard.engine.ecs.systems.BehaviorSystem;
import wizard.engine.ecs.systems.GraphicsSystem;
import wizard.engine.ecs.systems.CollisionSystem;
import wizard.engine.ecs.systems.MovementSystem;
import wizard.utils.WIZDelegateContainer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import engine.Application;
import engine.GameWorld; 

public class WizLevel1GameWorld extends GameWorld {
	private CollisionSystem _collisionSystem           = null;
	private GraphicsSystem _graphicsSystem             = null;
	private BehaviorSystem _behaviorSystem             = null;
	private MovementSystem _movementSystem             = null; 
	private WIZDelegateContainer _wizDelegateContainer;

	protected WizLevel1GameWorld(Application app) {
		super(app);
		this.setLevel(1);
		this.setGameSize(new Vec2d (1200, 1800));
		this.setOrigin(new Vec2d (-700, -130));	
		this.initializeSystems(app);
	}
	private void initializeSystems (Application app)  {
		this.setCollisionSystem(new CollisionSystem(app,this));	
		this.setGraphicsSystem(new GraphicsSystem(app,this));
	 	this.setBehaviorSystem(new BehaviorSystem(app,this));
		this.setMovementSystem(new MovementSystem(app,this));
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getCollisionSystem().onTick(nanosSincePreviousTick);
		this.getBehaviorSystem().onTick(nanosSincePreviousTick);
		this.getMovementSystem().onTick(nanosSincePreviousTick);
	}
	public void onKeyPressed(KeyEvent e) {
		this.getMovementSystem().onKeyPressed(e);
	}
	public void onDraw(GraphicsContext g)  {
		this.getGraphicsSystem().onDraw(g);
	}
	private CollisionSystem getCollisionSystem() {
		return _collisionSystem;
	}
	private void setCollisionSystem(CollisionSystem _collisionSystem) {
		this._collisionSystem = _collisionSystem;
	}
	private GraphicsSystem getGraphicsSystem() {
		return _graphicsSystem;
	}
	private void setGraphicsSystem(GraphicsSystem _graphicsSystem) {
		this._graphicsSystem = _graphicsSystem;
	}
	public BehaviorSystem getBehaviorSystem() {
		return _behaviorSystem;
	}
	private void setBehaviorSystem(BehaviorSystem _behaviorSystem) {
		this._behaviorSystem = _behaviorSystem;
	}
	public MovementSystem getMovementSystem() {
		return _movementSystem;
	}
	private void setMovementSystem(MovementSystem _movementSystem) {
		this._movementSystem = _movementSystem;
	}
	public WIZDelegateContainer getWizDelegateContainer() {
		return _wizDelegateContainer;
	}
	public void setWizDelegateContainer(WIZDelegateContainer _wizDelegateContainer) {
		this._wizDelegateContainer = _wizDelegateContainer;
	}
}
