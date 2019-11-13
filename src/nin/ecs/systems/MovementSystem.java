package nin.ecs.systems;

import javafx.scene.input.KeyEvent;
import nin.ecs.components.MoveMainCharacterComponent;
import engine.Application;
import engine.GameWorld;
import engine.systems.Systems;


import nin.level0.NinGameWorld;

public class MovementSystem extends Systems  {	
	private MoveMainCharacterComponent _moveMainCharacterComponent = null;
	
	public MovementSystem(Application app, GameWorld gameWorld) {
		super(app, gameWorld);
		this.setMoveMainCharacterComponent(new MoveMainCharacterComponent(app,(NinGameWorld)gameWorld));
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getMoveMainCharacterComponent().onTick(nanosSincePreviousTick);
	}
	public void onKeyPressed(KeyEvent e)  {
		this.getMoveMainCharacterComponent().onKeyPressed(e);
	}
	private MoveMainCharacterComponent getMoveMainCharacterComponent() {
		return _moveMainCharacterComponent;
	}
	private void setMoveMainCharacterComponent(
			MoveMainCharacterComponent _moveMainCharacterComponent) {
		this._moveMainCharacterComponent = _moveMainCharacterComponent;
	}
}
