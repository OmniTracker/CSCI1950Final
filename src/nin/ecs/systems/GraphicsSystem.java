package nin.ecs.systems;

import nin.ecs.components.DrawGameSceneComponent;
import nin.ecs.components.NinDrawComponent;
import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import engine.Application;
import engine.systems.Systems;

public class GraphicsSystem extends Systems {

	private DrawGameSceneComponent _drawGameSceneComponent = null;
	private NinDrawComponent _ninDrawComponent = null;

	public GraphicsSystem(Application app, NinGameWorld gameWorld) {
		super(app, gameWorld);
		this.setSystemName("Graphics");
		this.setDrawGameSceneComponent( new DrawGameSceneComponent(app,gameWorld)); 
		this.setNINDrawComponent(new NinDrawComponent(app,gameWorld));
	}

	public void onDraw(GraphicsContext g) {
		this.getDrawGameSceneComponent().onDraw(g);
		this.getNINDrawComponent().onDraw(g);
	}

	private DrawGameSceneComponent getDrawGameSceneComponent() {
		return _drawGameSceneComponent;
	}

	private void setDrawGameSceneComponent(DrawGameSceneComponent _drawGameSceneComponent) {
		this._drawGameSceneComponent = _drawGameSceneComponent;
	}

	private NinDrawComponent getNINDrawComponent() {
		return _ninDrawComponent;
	}

	private void setNINDrawComponent(NinDrawComponent _ninDrawComponent) {
		this._ninDrawComponent = _ninDrawComponent;
	}
}
