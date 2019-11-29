package nin.systems;

import javafx.scene.input.KeyEvent;
import nin.components.MoveMainCharacterComponent;
import nin.level0.NinGameWorld;
import engine.Application;
import engine.systems.Systems;

public class MovementSystem extends Systems {
	private MoveMainCharacterComponent _moveMainCharacterComponent = null;
	public MovementSystem(Application app, NinGameWorld gameWorld) {
		super(app, gameWorld);
		this.setMoveMainCharacterComponent( new MoveMainCharacterComponent(this, gameWorld));
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
