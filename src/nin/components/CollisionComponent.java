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
	}	
	private NinGameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
