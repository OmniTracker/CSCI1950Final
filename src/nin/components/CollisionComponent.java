package nin.components;

import java.util.ArrayList;
import java.util.List;

import support.Vec2d;
import support.collision.Collision;
import javafx.scene.image.Image;
import nin.level0.NinGameWorld;
import engine.gameobject.GameObject;
import engine.systems.Components;
import engine.utility.Factory;

public class CollisionComponent  extends Components {

	private NinGameWorld _gameWorld;
	Image characterImg = null; 
	
	public CollisionComponent (NinGameWorld gameWorld ) 
	{
		this.setGameWorld(gameWorld);
	}
	public void onTick(long nanosSincePreviousTick) 
	{		
		// Check for the collision of the character and coins. Increment coin count
		GameObject mainCharacter = this.getGameWorld().getNinGameObjectDelegate().getGameCharacters().get(0);	
		ArrayList < GameObject > coins = this.getGameWorld().getNinGameObjectDelegate().getMovingCoins();
		
		GameObject saveBullet = this.getGameWorld().getNinGameObjectDelegate().getSaveBullet();

		
		for (int index = 0; index < coins.size(); index++) 
		{
			if (( Collision.isColliding(mainCharacter.getData().getBox(), coins.get(index).getData().getBox()) == true)) 
			{
				coins.get(index).getData().setPosition( new Vec2d(-130,0));
				this.getGameWorld().score++;
				if ( this.getGameWorld().high < this.getGameWorld().score) {
					this.getGameWorld().high = this.getGameWorld().score;
				}
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

			if (saveBullet != null) 
			{
				if (saveBullet.getData().saveBulletMoving == true) 
				{
					if (( Collision.isColliding(saveBullet.getData().getBox(), bullet.get(index).getData().getBox()) == true)) 
					{
						// Set the bullet out of range. 
						bullet.get(index).getData().setPosition( new Vec2d(-130,0));
						this.getGameWorld().score += 10;
					}
				}

			}
		}
		
		if (this.getGameWorld().lives == 0) 
		{
			List<String> info = Factory.readNin().get(0);	
			int tempScores = Integer.valueOf(info.get(0));
			int tempLives  = Integer.valueOf(info.get(1));
			String stats = 	String.valueOf(tempScores) + "," + String.valueOf(tempLives) + "," + String.valueOf(_gameWorld.high);  
			Factory.writeNin(stats);
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
