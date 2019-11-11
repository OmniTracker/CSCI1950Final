package nin.ecs.components;


import nin.level0.NinGameWorld;
import nin.utils.NINDelegateContainer;
import javafx.scene.canvas.GraphicsContext;
import engine.Application;
import engine.systems.Components;

public class NinDrawComponent  extends Components {
	private Application _app = null;
	private NinGameWorld _gameWorld = null;
	private NINDelegateContainer _ninDelegateContainer = null;

	public NinDrawComponent ( Application app, NinGameWorld gameWorld ) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
	}

	public void onDraw(GraphicsContext g)  {
		
	}

	private Application getApp() {
		return _app;
	}

	private void setApp(Application _app) {
		this._app = _app;
	}

	private NinGameWorld getGameWorld() {
		return _gameWorld;
	}

	private void setGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}

	private NINDelegateContainer getNINDelegateContainer() {
		return _ninDelegateContainer;
	}

	public void setNINDelegateContainer(NINDelegateContainer _ninDelegateContainer) {
		this._ninDelegateContainer = _ninDelegateContainer;
	}
}
