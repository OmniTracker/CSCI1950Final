package nin.level0;

import nin.ecs.systems.GraphicsSystem;
import nin.ecs.systems.MovementSystem;
import nin.utils.NINDelegateContainer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;

public class NinGameWorld  extends GameWorld {
	private GraphicsSystem _graphicsSystem = null;
	private MovementSystem _movementSystem = null; 
	private NINDelegateContainer _ninDelegateContainer = null;
	protected NinGameWorld(Application app) {
		super(app);
		this.initializeSystems(app);
	}
	private void initializeSystems (Application app)  {
		this.setGraphicsSystem( new GraphicsSystem(app,this));
		this.setMovementSystem( new MovementSystem(app,this) ); 
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getMovementSystem().onTick(nanosSincePreviousTick);
	}
	public void onDraw(GraphicsContext g) {
		this.getGraphicsSystem().onDraw(g);
	}
	public void onMouseClicked(MouseEvent e) {
	}	
	public void onKeyPressed(KeyEvent e)  {
		this.getMovementSystem().onKeyPressed(e);
	}
	public void onShutdown() {
		this.getGraphicsSystem().onShutdown();
	}
	public void onStartup() {
		this.getGraphicsSystem().onStartup();
	}	
	private GraphicsSystem getGraphicsSystem() {
		return _graphicsSystem;
	}
	private void setGraphicsSystem(GraphicsSystem _graphicsSystem) {
		this._graphicsSystem = _graphicsSystem;
	}
	public NINDelegateContainer getNINDelegateContainer() {
		return _ninDelegateContainer;
	}
	public void setNINDelegateContainer(NINDelegateContainer _ninDelegateContainer) {
		this._ninDelegateContainer = _ninDelegateContainer;
	}
	public MovementSystem getMovementSystem() {
		return _movementSystem;
	}
	public void setMovementSystem(MovementSystem _movementSystem) {
		this._movementSystem = _movementSystem;
	}
}
