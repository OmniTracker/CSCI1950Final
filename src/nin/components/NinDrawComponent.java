package nin.components;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.Collision;
import support.collision.MTV;
import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import engine.Application;
import engine.gameobject.GameObject;
import engine.systems.Components;
import engine.ui.Button;

public class NinDrawComponent  extends Components {
	private Application _app;
	private NinGameWorld _gameWorld;

	private Button _restartButton; 
	
	public NinDrawComponent ( Application app, NinGameWorld gameWorld ) {
		this.setApp(app);
		this.setNinGameWorld(gameWorld);
		_restartButton = new Button("RESTART", new Vec2d(500,500), new Vec2d(50,20), Color.ALICEBLUE );
		
		this.getNinGameWorld().setButton( _restartButton );
	}

	public void onDraw(GraphicsContext g)  {
		
		// Temp place for platform
		GameObject platform = this.getNinGameWorld().getNinMapDelegate().getGameBoardPlatforms().get(0); 
		platform.getData().setBox( new AABShape(  new Vec2d(10, 520), new Vec2d(1200, 100))); 	
		
		// Temp Character placement
		GameObject character = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(0);  
		character.getData().setPosition(character.getData().getPosition().plus(0, 5));
		character.getData().getBox().setTopLeft(character.getData().getPosition());
		
		
		g.setFill(Color.WHEAT);
		g.fillRect(character.getData().getBox().getTopLeft().x, character.getData().getBox().getTopLeft().y, character.getData().getBox().getSize().x, character.getData().getBox().getSize().y);

		
		
		if ( Collision.isColliding(platform.getData().getBox(), character.getData().getBox()) == true) {
			Vec2d mtv = MTV.collision(platform.getData().getBox(), character.getData().getBox()); 
			character.getData().setCurrentMTV(mtv);
			try {
				Vec2d newPos = new Vec2d ( character.getData().getPosition().x - mtv.x,  character.getData().getPosition().y - mtv.y); 
				character.getData().setPosition(newPos);
			} catch (Exception e) {
				
			}
		}	

		// Temp Character placement
		GameObject character0 = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(1);  
		character0.getData().getBox().setTopLeft(character0.getData().getPosition());
		g.setFill(Color.GAINSBORO);
		g.fillRect(character0.getData().getBox().getTopLeft().x, character0.getData().getBox().getTopLeft().y, character0.getData().getBox().getSize().x, character0.getData().getBox().getSize().y);

		
		GameObject character1 = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(2);  
		character1.getData().getBox().setTopLeft(character1.getData().getPosition());
		g.setFill(Color.ALICEBLUE);
		g.fillRect(character1.getData().getBox().getTopLeft().x, character1.getData().getBox().getTopLeft().y, character1.getData().getBox().getSize().x, character1.getData().getBox().getSize().y);

		
		GameObject character2 = this.getNinGameWorld().getNinGameObjectDelegate().getGameCharacters().get(3);  
		character2.getData().getBox().setTopLeft(character2.getData().getPosition());
		g.setFill(Color.BLUE);
		g.fillRect(character2.getData().getBox().getTopLeft().x, character2.getData().getBox().getTopLeft().y, character2.getData().getBox().getSize().x, character2.getData().getBox().getSize().y);

		g.drawImage(platform.getData().getImage(), 0, 0, 600, 100, 10, 500, 600, 100);
		g.drawImage(platform.getData().getImage(), 0, 0, 600, 100, 10, 500, 1200, 100);

		
		Vec2d location = this._app.getAspectRatioHandler().getCurrentScreenSize(); 
		_restartButton.setOrigin(new Vec2d(location.x / 2, location.y / 2));
		_restartButton.draw(g);
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
