package alchemy.level0;

import java.net.MalformedURLException;

import alchemy.systems.CollisionSystem;
import alchemy.systems.GraphicSystem;
import support.Vec2d;
import support.collision.AABShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.utility.Factory;

public class AlcGameWorld  extends GameWorld {
	private CollisionSystem _collisionSystem;
	private GraphicSystem _graphicSystem;
	private Factory _factory;
	private ScrollEvent _lastMouseMovement;
	double factor = 1; 
	
	protected AlcGameWorld(Application app){
		super(app);
		this.setCollisionSystem(new CollisionSystem(app, this));
		this.setGraphicSystem(new GraphicSystem(app, this));
		this.setFactory(new Factory()); 
		this.setLastMouseMovement(null);
	}
	public void onTick(long nanosSincePreviousTick) {
		if (this.getCollisionSystem().getGameObjects().size() == 0) {
			Image kimImg = null;
			try {
				kimImg = this.getFactory().getKimSprite();
			} catch (MalformedURLException e) {
				System.out.print("Where are you Kim? Can't Find you!!!");
				e.printStackTrace();
				return;
			} 
			GameObject kimObj = new GameObject();
			kimObj.setImage(kimImg);
			Vec2d screenSize = this.getApplication().getAspectRatioHandler().getCurrentScreenSize();
			double x = screenSize.x / 2; 
			double y = screenSize.y / 2; 
			kimObj.setPosition(new Vec2d(x - 60,y - 100));
			kimObj.setSize(new Vec2d(140,200));
			kimObj.setBox(new AABShape(kimObj.getPosition(),kimObj.getSize()));
			this.getCollisionSystem().addGameObject(kimObj);
			this.getGraphicSystem().addGameObject(kimObj);
		}
	}
	public void onDraw(GraphicsContext g) {
		this.drawScaledGameWorld(g);
	}
	private void drawScaledGameWorld (GraphicsContext g) {		
		if (this.getLastMouseMovement() != null) {
				g.save();				
				double mousex = this.getLastMouseMovement().getSceneX();
				double mousey = this.getLastMouseMovement().getSceneY();
				double originx = ( mousex/(factor) - mousex/1);
				double originy = ( mousey/(factor) - mousey/1);
				g.scale(factor, factor);
				g.translate(originx ,originy);				
				this.getGraphicSystem().onDraw(g);		
				g.restore();
		} else {
			this.getGraphicSystem().onDraw(g);		
		}
	}
	public void onMouseDragged(MouseEvent e) {
		this.getCollisionSystem().onMouseDragged(e);
	}
	public void onMouseClicked(MouseEvent e) {
		GameObject possibleObj = this.getGraphicSystem().onMouseClicked(e);
		if (possibleObj != null) {
			this.getCollisionSystem().getGameObjects().add(possibleObj);
		}
	}
	public void onMouseWheelMoved(ScrollEvent e) {
		if (this.getLastMouseMovement() == null) {
			this.setLastMouseMovement(e);
			return;
		}
		this.setLastMouseMovement(e);
		if (this.getLastMouseMovement().getDeltaY() < 10) {
			factor+= .1;
		} else if (this.getLastMouseMovement().getDeltaY() > -10) {
			factor-= .1;
		}
	}
	CollisionSystem getCollisionSystem() {
		return _collisionSystem;
	}
	void setCollisionSystem(CollisionSystem _collisionSystem) {
		this._collisionSystem = _collisionSystem;
	}
	private Factory getFactory() {
		return _factory;
	}
	private void setFactory(Factory _factory) {
		this._factory = _factory;
	}
	private GraphicSystem getGraphicSystem() {
		return _graphicSystem;
	}
	private void setGraphicSystem(GraphicSystem _graphicSystem) {
		this._graphicSystem = _graphicSystem;
	}
	private ScrollEvent getLastMouseMovement() {
		return _lastMouseMovement;
	}
	private void setLastMouseMovement(ScrollEvent e) {
		this._lastMouseMovement = e;
	}
}
