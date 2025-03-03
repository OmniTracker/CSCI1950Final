package nin.components;

import java.util.ArrayList;
import java.util.Random;

import support.Vec2d;
import nin.behaviortree.conditions.JumpSignaled;
import nin.level0.NinGameWorld;
import nin.systems.MovementSystem;
import javafx.scene.input.KeyEvent;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class MoveMainCharacterComponent  extends Components {
	private MovementSystem _movementSystem;
	private NinGameWorld  _ninGameWorld;
	private int shootCount = 0;
	private int bulletShoot  = 0;

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
		if (keyInput.equals("A")) 
		{
			if (mainCharacter.getData().getPosition().x > 10) 
			{
				mainCharacter.getData().setPosition(mainCharacter.getData().getPosition().minus(15,0));				
			}
			mainCharacter.getData().dir = -1;
			if( signal._jumpSignaled == false) 
			{
				mainCharacter.getData().fileIndex =  (( mainCharacter.getData().fileIndex + 1 == 5) ? 0 : mainCharacter.getData().fileIndex + 1);	
			}						
		} 
		else if (keyInput.equals("D"))  
		{
			if (mainCharacter.getData().getPosition().x < this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x - 120) 
			{
				mainCharacter.getData().setPosition(mainCharacter.getData().getPosition().plus(15,0));
			}
			mainCharacter.getData().dir = 1;
			if( signal._jumpSignaled == false) 
			{
				mainCharacter.getData().fileIndex =  (( mainCharacter.getData().fileIndex + 1 == 5) ? 0 : mainCharacter.getData().fileIndex + 1);	
			}			
		}
		else if (keyInput.equals("S"))  
		{
			GameObject saveBullet = this.getNinGameWorld().getNinGameObjectDelegate().getSaveBullet();

			if ( saveBullet == null ) 
			{
				return;
			}
			
			if (saveBullet.getData().saveBulletMoving == false) 
			{				
				saveBullet.getData().saveBulletMoving = true;
				saveBullet.getData().setPosition(mainCharacter.getData().getPosition());
				saveBullet.getData().bulletDirVar = mainCharacter.getData().dir; 
			}
		}
		mainCharacter.getData().getBox().setTopLeft(mainCharacter.getData().getPosition());
	}
	
	public void onTick(long nanosSincePreviousTick) 
	{		
		// Handle all coin stuff
		ArrayList < GameObject > coins = this.getNinGameWorld().getNinGameObjectDelegate().getMovingCoins();
		this.moveCoins(nanosSincePreviousTick, coins);
		this.resetCoins(coins);
		this.generateCoins(nanosSincePreviousTick, coins);

		// Handle all the bullets.
		ArrayList < GameObject > bullets = this.getNinGameWorld().getNinGameObjectDelegate().getMovingBullets();
		this.moveBullets(nanosSincePreviousTick, bullets);
		this.resetBullets(bullets);
		this.generateBullets(nanosSincePreviousTick, bullets);

		this.moveSaveBullets();
		this.resetSaveBullets();
	}

	private void moveSaveBullets() {	
		GameObject saveBullet = this.getNinGameWorld().getNinGameObjectDelegate().getSaveBullet();
		
		if ( saveBullet == null ) {
			this.getNinGameWorld().getNinGameObjectDelegate().initSaveBullet();
			return;
		}
	
		if (saveBullet.getData().saveBulletMoving == true) 
		{
			
			saveBullet.getData().setPosition(saveBullet.getData().getPosition().plus( saveBullet.getData().saveBulletSpeed * saveBullet.getData().bulletDirVar,0));		
			
			
			saveBullet.getData().getBox().setTopLeft(saveBullet.getData().getPosition());
		}	
	}
	
	private void resetSaveBullets() {
		
		GameObject saveBullet = this.getNinGameWorld().getNinGameObjectDelegate().getSaveBullet();
		
		if ( saveBullet == null ) {
			return;
		}	
		if (saveBullet.getData().saveBulletMoving == true) 
		{
			if (saveBullet.getData().getPosition().x < -120 || 
					saveBullet.getData().getPosition().x > this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x + 120)
			{
				saveBullet.getData().saveBulletMoving =false;
			}

		}		
	}

	private void moveBullets(long nanosSincePreviousTick, ArrayList<GameObject> bullets) {

		for (int index = 0; index < bullets.size(); index++) 
		{	
			if ( bullets.get(index).getData().bulletMoving == true ) 
			{	
				int speed = bullets.get(index).getData().bulletSpeed;

				bullets.get(index).getData().setPosition(bullets.get(index).getData().getPosition().plus(speed,0));		
			}
		}

	}

	private void resetBullets(ArrayList<GameObject> bullets) {

		for (int index = 0; index < bullets.size(); index++) 
		{	
			if ( bullets.get(index).getData().bulletMoving == true ) 
			{	
				if (bullets.get(index).getData().getPosition().x < -120 || 
						bullets.get(index).getData().getPosition().x > this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x + 120)
				{
					bullets.get(index).getData().bulletMoving = false;
				}
			}
		}
	}

	private void generateBullets(long nanosSincePreviousTick, ArrayList<GameObject> bullets) {

		if ((nanosSincePreviousTick % 6) == 0) 
		{
			bulletShoot++;
			if (bulletShoot == 10) 
			{
				bulletShoot = 0;					
				Random r = new Random();
				for (int index = 0; index < bullets.size(); index++) 
				{	
					if ( bullets.get(index).getData().bulletMoving == false ) 
					{	
						// Coming from right or left
						int direction =   r.nextInt((1 - 0) + 1) + 0;
						int maxY = ( int ) this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().y - 250; 
						int randomY =   r.nextInt((maxY - 200) + 1) + 200;
						// Go to right
						if (direction == 1) 
						{
							// Need to do a bunch of randomization.
							bullets.get(index).getData().bulletSpeed = r.nextInt((25 - 5) + 1) + 5;
							bullets.get(index).getData().setPosition(new Vec2d( -100,randomY));		
						}
						// Go to the left
						else if (direction == 0) 
						{
							// Need to do a bunch of randomization.
							bullets.get(index).getData().bulletSpeed = -1 * (r.nextInt((25 - 5) + 1) + 5);
							bullets.get(index).getData().setPosition(new Vec2d( this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x + 100,randomY));		
						}
						else 
						{
							return;
						}
						bullets.get(index).getData().bulletMoving = true; 
						return;
					}
				}
			}
		}
	}


	private void moveCoins(long nanosSincePreviousTick,
			ArrayList<GameObject> coins) {
		for (int index = 0; index < coins.size(); index++) 
		{	
			if ( coins.get(index).getData().coinMoving == true ) 
			{	
				int speed = coins.get(index).getData().coinSpeed;
				coins.get(index).getData().setPosition(coins.get(index).getData().getPosition().plus(speed,0));		
				if (nanosSincePreviousTick % 2 == 0  ) {	
					coins.get(index).getData().fileIndex = (( coins.get(index).getData().fileIndex + 1 == 8) ? 0 : coins.get(index).getData().fileIndex + 1);		
				}
			}
		}
	}

	private void resetCoins(ArrayList<GameObject> coins) {
		for (int index = 0; index < coins.size(); index++) 
		{	
			if ( coins.get(index).getData().coinMoving == true ) 
			{	
				if (coins.get(index).getData().getPosition().x < -120 || 
						coins.get(index).getData().getPosition().x > this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x + 120) {
					coins.get(index).getData().coinMoving = false;
				}
			}
		}
	}


	private void generateCoins(long nanosSincePreviousTick,
			ArrayList<GameObject> coins) {
		if ((nanosSincePreviousTick % 6) == 0) 
		{
			shootCount++;
			if (shootCount == 5) 
			{
				shootCount = 0;
				Random r = new Random();
				for (int index = 0; index < coins.size(); index++) 
				{	
					if ( coins.get(index).getData().coinMoving == false ) 
					{	
						// Coming from right or left
						int direction =   r.nextInt((1 - 0) + 1) + 0;
						int maxY = ( int ) this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().y - 250; 
						int randomY =   r.nextInt((maxY - 30) + 1) + 30;
						// Go to right
						if (direction == 1) 
						{
							// Need to do a bunch of randomization.
							coins.get(index).getData().coinSpeed = r.nextInt((10 - 5) + 1) + 5;
							coins.get(index).getData().setPosition(new Vec2d( -100,randomY));		
						}
						// Go to the left
						else if (direction == 0) 
						{
							// Need to do a bunch of randomization.
							coins.get(index).getData().coinSpeed = -1 * (r.nextInt((10 - 5) + 1) + 5);
							coins.get(index).getData().setPosition(new Vec2d( this.getNinGameWorld().getApplication().getAspectRatioHandler().getCurrentScreenSize().x + 100,randomY));		
						}
						else 
						{
							return;
						}
						coins.get(index).getData().coinMoving = true; 
						return;
					}
				}
			}
		}
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
		if (keyCode.contains("SPACE") || keyCode.equals("W")) {
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
