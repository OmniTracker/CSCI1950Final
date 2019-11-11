package nin.ecs.components;

import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import engine.Application;
import engine.GameWorld;
import engine.systems.Components;

public class DrawGameSceneComponent  extends Components {
	private Application _app;
	private NinGameWorld _gameWorld;

	
	
	public DrawGameSceneComponent(Application app, NinGameWorld gameWorld) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
		
	}
	
	public void onDraw(GraphicsContext g) {

	}

	private NinGameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	private Application getApp() {
		return _app;
	}
	private void setApp(Application _app) {
		this._app = _app;
	}
}
