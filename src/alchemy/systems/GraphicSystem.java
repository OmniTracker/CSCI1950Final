package alchemy.systems;

import java.util.ArrayList;
import java.util.List;

import support.Vec2d;
import support.collision.AABShape;
import support.collision.Collision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Systems;

public class GraphicSystem  extends Systems {
	private List<GameObject> _gameObjects;
	public GraphicSystem(Application app, GameWorld gameWorld) {
		super(app,gameWorld);
		this.setGameObjects( new ArrayList<GameObject>());
		this.setSystemName("Graphics");
	}
	public void onDraw(GraphicsContext g) {		
		for (GameObject obj : this.getGameObjects()) {
			obj.draw(g);
		}
	}
	public GameObject onMouseClicked(MouseEvent e) {
		Vec2d mousePoint = new Vec2d(e.getSceneX(),e.getSceneY());
		GameObject newObj = null;
		for (GameObject obj : this.getGameObjects()) {
			if (Collision.isColliding(obj.getBox(), mousePoint)) {
				newObj = this.clone(obj);
				break;
			}
		}
		if (newObj != null) {
			this.addGameObject(newObj);
		}
		return newObj;
	}
	public GameObject clone(GameObject obj) {
		GameObject clone = new GameObject(); 
		clone.setPosition(obj.getPosition());
		clone.setSize(obj.getSize());
		clone.setImage(obj.getImage());
		clone.setBox(new AABShape(obj.getPosition(),obj.getSize()));
		return clone;
	}	
	public void addGameObject(GameObject obj ) {
		this.getGameObjects().add(obj);
	}
	public List<GameObject> getGameObjects() {
		return _gameObjects;
	}
	public void setGameObjects(List<GameObject> list) {
		this._gameObjects = list;
	}
}
