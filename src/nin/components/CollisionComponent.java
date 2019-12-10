package nin.components;

import java.util.ArrayList;

import support.Vec2d;
import support.collision.Collision;
import javafx.scene.image.Image;
import nin.level0.NinGameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class CollisionComponent  extends Components {

	private NinGameWorld _gameWorld;
	Image characterImg = null; 
	public CollisionComponent (NinGameWorld gameWorld ) {
		this.setGameWorld(gameWorld);
	}
	public void onTick(long nanosSincePreviousTick) {		

		// Check for the collision of the character and coins. Increment coin count
		GameObject mainCharacter = this.getGameWorld().getNinGameObjectDelegate().getGameCharacters().get(0);	
		ArrayList < GameObject > coins = this.getGameWorld().getNinGameObjectDelegate().getMovingCoins();
		for (int index = 0; index < coins.size(); index++) 
		{
			if (( Collision.isColliding(mainCharacter.getData().getBox(), coins.get(index).getData().getBox()) == true)) 
			{
				coins.get(index).getData().setPosition( new Vec2d(-130,0));
				this.getGameWorld().score++;
			}
		}
		ArrayList < GameObject > bullet = this.getGameWorld().getNinGameObjectDelegate().getMovingBullets();

		for (int index = 0; index < bullet.size(); index++)
		{
			if (( Collision.isColliding(mainCharacter.getData().getBox(), bullet.get(index).getData().getBox()) == true)) 
			{
				// Set the bullet out of range. 
				bullet.get(index).getData().setPosition( new Vec2d(-130,0));
				this.getGameWorld().lives--;
			}
		}
		
		if (this.getGameWorld().lives == 0) {
			this.getGameWorld().lives = 5;
			this.getGameWorld().score = 0;
		}

	}	
	private NinGameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
