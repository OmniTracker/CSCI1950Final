package nin.level0;

import nin.ecs.systems.GraphicsSystem;
import nin.utils.NINDelegateContainer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;

public class NinGameWorld  extends GameWorld {
	private GraphicsSystem _graphicsSystem = null; 
	private NINDelegateContainer _ninDelegateContainer = null;
	protected NinGameWorld(Application app) {
		super(app);
		this.initializeSystems(app);
	}
	private void initializeSystems (Application app)  {
		this.setGraphicsSystem( new GraphicsSystem(app,this));
	}
	public void onTick(long nanosSincePreviousTick) {
	}
	public void onDraw(GraphicsContext g) {
		this.getGraphicsSystem().onDraw(g);
	}
	public void onMouseClicked(MouseEvent e) {
	}	
	public void onKeyPressed(KeyEvent e)  {
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
}
