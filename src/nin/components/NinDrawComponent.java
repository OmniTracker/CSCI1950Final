package nin.components;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.Collision;
import support.collision.MTV;
import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import engine.Application;
import engine.gameobject.GameObject;
import engine.systems.Components;
import engine.utility.Factory;

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
		platform.getData().setBox( new AABShape(  new Vec2d(10, 520), new Vec2d(1200, 100))); 	
		
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
			try {
				Vec2d newPos = new Vec2d ( character.getData().getPosition().x - mtv.x,  character.getData().getPosition().y - mtv.y); 
				character.getData().setPosition(newPos);
			} catch (Exception e) {
				
			}
		}	
		g.drawImage(platform.getData().getImage(), 0, 0, 600, 100, 0, 500, 600, 100);
		g.drawImage(platform.getData().getImage(), 0, 0, 600, 100, 0, 500, 1200, 100);
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
