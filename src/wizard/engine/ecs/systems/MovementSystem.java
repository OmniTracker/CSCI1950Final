package wizard.engine.ecs.systems;

import wizard.engine.ecs.component.LevelHandlerComponent;
import wizard.engine.ecs.component.MovementComponent;
import javafx.scene.input.KeyEvent;
import engine.Application;
import engine.GameWorld;
import engine.systems.Systems;

public class MovementSystem  extends Systems {	
	private MovementComponent _mainMovementComponent; 
	private LevelHandlerComponent _levelHandlerComponent;
	public MovementSystem(Application app, GameWorld gameWorld) {
		super(app, gameWorld);
		this.setSystemName("Movement");
		this.setMainMovementComponent( new MovementComponent(app,gameWorld));
		this.setLevelHandlerComponent( new LevelHandlerComponent(gameWorld));
	}
	public void onKeyPressed(KeyEvent e) {				
		this.getMainMovementComponent().onKeyPressed(e);
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getMainMovementComponent().onTick(nanosSincePreviousTick);
		this.getLevelHandlerComponent().onTick(0);
	}
	private MovementComponent getMainMovementComponent() {
		return _mainMovementComponent;
	}
	private void setMainMovementComponent(MovementComponent _mainMovementComponent) {
		this._mainMovementComponent = _mainMovementComponent;
	}
	private LevelHandlerComponent getLevelHandlerComponent() {
		return _levelHandlerComponent;
	}
	private void setLevelHandlerComponent(LevelHandlerComponent _levelHandlerComponent) {
		this._levelHandlerComponent = _levelHandlerComponent;
	}
}
