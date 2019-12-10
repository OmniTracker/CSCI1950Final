package nin.components;

import java.util.ArrayList;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.Collision;
import support.collision.MTV;
import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import engine.Application;
import engine.gameobject.GameObject;
import engine.systems.Components;
import engine.utility.Factory;
import javafx.scene.text.Font;

public class NinDrawComponent  extends Components {
	private Application _app;
	private NinGameWorld _gameWorld;
	Image characterImg = null; 
	public NinDrawComponent ( Application app, NinGameWorld gameWorld ) {
		this.setApp(app);
		this.setNinGameWorld(gameWorld);
	}
	public void onDraw(GraphicsContext g)  {	
		GameObject platform = this.getNinGameWorld().getNinMapDelegate().getGameBoardPlatforms().get(0); 
		platform.getData().setBox( new AABShape(  new Vec2d(10, 600), new Vec2d(1200, 100))); 	

		GameObject character = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(0);  
		if (characterImg == null) { 
			characterImg = Factory.getGenericImage("resources/terrain/runSprite.png"); 
			character.getData().setImage(characterImg);			
		}
		character.getData().setPosition(character.getData().getPosition().plus(0, 5));
		character.getData().getBox().setTopLeft(character.getData().getPosition());
		int increment = 0;
		if (character.getData().dir == -1) {
			increment += 100; 
		}
		int xIndex = 0;
		if (character.getData().fileIndex == 1) {
			xIndex = 240;
		} else if (character.getData().fileIndex == 2) {
			xIndex = 530;
		} else if (character.getData().fileIndex == 3) {
			xIndex = 810;
		} else if (character.getData().fileIndex == 4) {
			xIndex = 1100; 
		}
		g.drawImage(character.getData().getImage(), xIndex, 0 , 255, 300, 
				character.getData().getBox().getTopLeft().x + increment, 
				character.getData().getBox().getTopLeft().y - 30, 
				character.getData().dir * character.getData().getBox().getSize().x + (character.getData().dir * 20), 
				character.getData().getBox().getSize().y + 20);
		if ( Collision.isColliding(platform.getData().getBox(), character.getData().getBox()) == true) {
			Vec2d mtv = MTV.collision(platform.getData().getBox(), character.getData().getBox()); 
			character.getData().setCurrentMTV(mtv);
			try 
			{
				Vec2d newPos = new Vec2d ( character.getData().getPosition().x - mtv.x,  character.getData().getPosition().y - mtv.y); 
				character.getData().setPosition(newPos);
			} catch (Exception e) {

			}
		}
		g.setFill(Color.BLACK);
		g.fillRect(0, 600, this._app.getAspectRatioHandler().getCurrentScreenSize().x, 400);
		g.drawImage(platform.getData().getImage(), 0, 140, 600, 100, 0, 570, 600, 100);
		g.drawImage(platform.getData().getImage(), 0, 140, 600, 100, 600, 570, 600, 100);
		this.drawCoins(g);	
		this.drawBullets(g);
		this.drawScore(g);
		this.drawLives(g);
		this.drawGameName(g);
	}
	private void drawGameName (GraphicsContext g) {
		Vec2d origin = this._app.getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(10, 60);
		g.setFill(Color.DARKBLUE);
		g.fillRoundRect(roundOrigin.x , roundOrigin.y, 500, 50, 5, 5);
		g.setFill(Color.WHITE);
		g.fillRoundRect(roundOrigin.x + 5, roundOrigin.y + 5, 500 - 10, 50 - 10, 5, 5);
		g.setFill(Color.BLACK);		
		g.setFont(Font.font("Ethnocentric", 40 ));
		g.fillText("Bitcoin Bandit", roundOrigin.x + 250, roundOrigin.y + 40);

	}
	private void drawScore (GraphicsContext g) {	
		Vec2d origin = this._app.getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(20, 120);
		this.labelHelper(g,roundOrigin, "Coins: " + this.getNinGameWorld().score); 
	}

	private void drawLives (GraphicsContext g) {	
		Vec2d origin = this._app.getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(220, 120);
		this.labelHelper(g,roundOrigin, "Lives: " + this.getNinGameWorld().lives); 
	}

	private void labelHelper(GraphicsContext g,Vec2d roundOrigin, String text) {
		if (this.getNinGameWorld().lives == 5) {
			g.setFill(Color.GREEN);
		} else if (this.getNinGameWorld().lives > 2) {
			g.setFill(Color.GOLD);
		} else {
			g.setFill(Color.RED);			
		}
		g.fillRoundRect(roundOrigin.x , roundOrigin.y, 180, 35, 5, 5);
		g.setFill(Color.WHITE);
		g.fillRoundRect(roundOrigin.x + 5, roundOrigin.y + 5, 180 - 10, 35 - 10, 5, 5);
		g.setFill(Color.BLACK);		
		g.setFont(Font.font("Ethnocentric", 20 ));
		g.fillText(text, roundOrigin.x + 90, roundOrigin.y + 25);
	}

	private void drawCoins (GraphicsContext g) {	
		ArrayList<GameObject> movingCoins = this.getNinGameWorld().getNinGameObjectDelegate().getMovingCoins(); 
		if ( movingCoins.size() == 0) {
			this.getNinGameWorld().getNinGameObjectDelegate().initMovingCoins();
		}
		for (int i = 0; i < movingCoins.size(); i++) {

			movingCoins.get(i).getData().getBox().setTopLeft(movingCoins.get(i).getData().getPosition());
			int xIndex = 0;
			if (movingCoins.get(i).getData().fileIndex == 1) {
				xIndex = 122;
			} else if (movingCoins.get(i).getData().fileIndex == 2) {
				xIndex = 244;
			} else if (movingCoins.get(i).getData().fileIndex == 3) {
				xIndex = 366;
			} else if (movingCoins.get(i).getData().fileIndex == 4) {
				xIndex = 488; 
			} else if (movingCoins.get(i).getData().fileIndex == 5) {
				xIndex = 610; 
			} else if (movingCoins.get(i).getData().fileIndex == 6) {
				xIndex = 732; 
			} else if (movingCoins.get(i).getData().fileIndex == 7) {
				xIndex = 840; 
			}
			g.drawImage(movingCoins.get(i).getData().getImage(), 
					xIndex, 
					0, 
					105, 
					100, 
					movingCoins.get(i).getData().getBox().getTopLeft().x,
					movingCoins.get(i).getData().getBox().getTopLeft().y, 
					movingCoins.get(i).getData().getBox().size.x,
					movingCoins.get(i).getData().getBox().size.y);
		}
	}
	private void drawBullets (GraphicsContext g) {	
		ArrayList<GameObject> movingBullet = this.getNinGameWorld().getNinGameObjectDelegate().getMovingBullets();
		if ( movingBullet.size() == 0)
		{
			this.getNinGameWorld().getNinGameObjectDelegate().initMovingBullets();			
		}
		for (int i = 0; i < movingBullet.size(); i++) 
		{
			movingBullet.get(i).getData().getBox().setTopLeft(movingBullet.get(i).getData().getPosition());
			int x = 1;
			int offSet = 0;
			if (movingBullet.get(i).getData().bulletSpeed > 0) {
				x *= -1;
				offSet = 120;
			}
			g.drawImage(movingBullet.get(i).getData().getImage(), 0, 0, 600, 375, 
					movingBullet.get(i).getData().getBox().getTopLeft().x + offSet,
					movingBullet.get(i).getData().getBox().getTopLeft().y, 
					(movingBullet.get(i).getData().getBox().size.x * x),
					movingBullet.get(i).getData().getBox().size.y);
		}
	}
	private void setApp(Application _app) {
		this._app = _app;
	}
	private NinGameWorld getNinGameWorld() {
		return _gameWorld;
	}
	private void setNinGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
