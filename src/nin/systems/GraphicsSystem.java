package nin.systems;

import nin.components.NinDrawComponent;
import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import engine.Application;
import engine.systems.Systems;

public class GraphicsSystem extends Systems {

	private NinDrawComponent _ninGameBoardComponent;

	public GraphicsSystem(Application app, NinGameWorld gameWorld) {
		super(app, gameWorld);
		this.setSystemName("Graphics");
		this.setNinGameBoardComponent( new NinDrawComponent(app,gameWorld) );

	}

	public void onDraw(GraphicsContext g) {

		this.getNinGameBoardComponent().onDraw(g);
	}
	
	

	private NinDrawComponent getNinGameBoardComponent() {
		return _ninGameBoardComponent;
	}

	private void setNinGameBoardComponent(NinDrawComponent _ninGameBoardComponent) {
		this._ninGameBoardComponent = _ninGameBoardComponent;
	}
}
