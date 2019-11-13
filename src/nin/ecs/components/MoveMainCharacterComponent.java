package nin.ecs.components;

import support.Vec2d;
import nin.level0.NinGameWorld;
import javafx.scene.input.KeyEvent;
import engine.Application;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class MoveMainCharacterComponent  extends Components {
	private Application _app = null;
	private NinGameWorld _gameWorld = null;
	private GameObject _mainCharacter = null;

	private boolean DEBUG = true;

	public MoveMainCharacterComponent (Application app, NinGameWorld gameWorld) { 
		this.setApp(app);
		this.setGameWorld(gameWorld);
	}

	public void onTick(long nanosSincePreviousTick) {
		if (this.getMainCharacter() == null) {
			
			this.setMainCharacter(this.getGameWorld()
					.getNINDelegateContainer()
					.getNINGameObjectDelagate()
					.getCharacter());
		} 
	}

	public void onKeyPressed(KeyEvent e) {		
		if (this.getMainCharacter() != null) {
			
			double movementValue = 10;
			
			
			// Remember to add the physics stuff.
			String direction = e.getCode().toString();
			// Move to the right
			if (direction.contains("LEFT")) 
			{
				if (DEBUG == true) 
				{
					System.out.print("LEFT \n");
				}
				
				this.getMainCharacter().getData().setPosition(new Vec2d (
						this.getMainCharacter().getData().getPosition().x - movementValue,
						this.getMainCharacter().getData().getPosition().y));
			} 
			else if (direction.contains("RIGHT")) 
			{
				if (DEBUG == true) 
				{
					System.out.print("RIGHT  \n");
				}
				
				this.getMainCharacter().getData().setPosition(new Vec2d (
						this.getMainCharacter().getData().getPosition().x + movementValue,
						this.getMainCharacter().getData().getPosition().y));
			} 
			else if (direction.contains("SPACE")) 
			{
				if (DEBUG == true) 
				{
					System.out.print("SPACE \n");
				}	
			}
		}
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

	private GameObject getMainCharacter() {
		return _mainCharacter;
	}

	private void setMainCharacter(GameObject _mainCharacter) {
		this._mainCharacter = _mainCharacter;
	}
}
