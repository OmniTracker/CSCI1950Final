package nin.components;

import nin.behaviortree.conditions.JumpSignaled;
import nin.level0.NinGameWorld;
import nin.systems.MovementSystem;
import javafx.scene.input.KeyEvent;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class MoveMainCharacterComponent  extends Components {

	private MovementSystem _movementSystem;
	private NinGameWorld  _ninGameWorld;

	public MoveMainCharacterComponent(MovementSystem movementSystem,  NinGameWorld ninGameWorld) {
		this.setMovementSystem(movementSystem);
		this.setNinGameWorld(ninGameWorld);
	}
	
	
	
	

	public void moveMain (KeyEvent e) {
		GameObject mainCharacter = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(0);		
		String keyInput = e.getCode().toString(); 
		JumpSignaled signal = (JumpSignaled) this.getNinGameWorld().
				getNinGameObjectDelegate().
				getGameCharacters().
				get(0).getData().
				getNinBehaviorTree().
				getSequence().
				get(0).
				get(0); 
		if (keyInput.contains("LEFT")) {
			if (mainCharacter.getData().getPosition().x > 10) {
				mainCharacter.getData().setPosition(mainCharacter.getData().getPosition().minus(10,0));				
			}
			mainCharacter.getData().dir = -1;
			if( signal._jumpSignaled == false) {
				mainCharacter.getData().fileIndex =  (( mainCharacter.getData().fileIndex + 1 == 5) ? 0 : mainCharacter.getData().fileIndex + 1);	
			}						
		} else if (keyInput.contains("RIGHT"))  {
			if (mainCharacter.getData().getPosition().x < this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x - 120) {
				mainCharacter.getData().setPosition(mainCharacter.getData().getPosition().plus(10,0));
			}
			mainCharacter.getData().dir = 1;
			if( signal._jumpSignaled == false) {
				mainCharacter.getData().fileIndex =  (( mainCharacter.getData().fileIndex + 1 == 5) ? 0 : mainCharacter.getData().fileIndex + 1);	
			}			
		}
		mainCharacter.getData().getBox().setTopLeft(mainCharacter.getData().getPosition());
	}

	public void onKeyPressed(KeyEvent e)  {
		this.moveMain(e);
		String keyCode = e.getCode().toString(); 
		// Send signal to the Behavior tree. This is done here because it invokes movement
		JumpSignaled signal = (JumpSignaled) this.getNinGameWorld().
				getNinGameObjectDelegate().
				getGameCharacters().
				get(0).getData().
				getNinBehaviorTree().
				getSequence().
				get(0).
				get(0); 
		GameObject mainCharacter = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(0);		
		// Use the MTV to allow jumping to happen.
		if (keyCode.contains("SPACE")) {
			if (mainCharacter.getData().getCurrentMTV() != null) {				
				signal.setJumpSignaled(true);
				mainCharacter.getData().fileIndex = 0; 
				return;
			}
		}		
	}

	MovementSystem getMovementSystem() {
		return _movementSystem;
	}
	void setMovementSystem(MovementSystem _movementSystem) {
		this._movementSystem = _movementSystem;
	}
	private NinGameWorld getNinGameWorld() {
		return _ninGameWorld;
	}
	private void setNinGameWorld(NinGameWorld _ninGameWorld) {
		this._ninGameWorld = _ninGameWorld;
	}
}
