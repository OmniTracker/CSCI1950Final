package alchemy.systems;

import java.util.ArrayList;
import java.util.List;

import support.Vec2d;
import support.collision.Collision;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Systems;

public class CollisionSystem extends Systems {
	private List<GameObject> _gameObjects;
	private Vec2d _lastMouseDrag = null;
	public CollisionSystem(Application app, GameWorld gameWorld) {
		super(app,gameWorld);
		this.setGameObjects( new ArrayList<GameObject>());
		this.setSystemName("Collision");
	}
	public void onMouseDragged(MouseEvent e) {
		Vec2d mousePoint = new Vec2d(e.getSceneX(),e.getSceneY());
		moveObjects(mousePoint);		
	}
	private void moveObjects(Vec2d mousePoint) {
		if (this.getLastMouseDrag() == null) {
			this.setLastMouseDrag(mousePoint);
			return;
		}
		if (!Collision.withinRange(mousePoint,this.getLastMouseDrag(), 50)) {
			this.setLastMouseDrag(null);
			return;
		}
		for (GameObject obj: this.getGameObjects()) {
			if ( Collision.isColliding(obj.getData().getBox(), mousePoint) ) {	
				double xDiff = obj.getData().getPosition().x - (this.getLastMouseDrag().x - mousePoint.x); 
				double yDiff = obj.getData().getPosition().y - (this.getLastMouseDrag().y - mousePoint.y);
				obj.getData().setPosition(new Vec2d(xDiff,yDiff));
				obj.getData().getBox().setTopLeft(new Vec2d(xDiff,yDiff));
				this.setLastMouseDrag(mousePoint);
			}	
		}
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
	private Vec2d getLastMouseDrag() {
		return _lastMouseDrag;
	}
	private void setLastMouseDrag(Vec2d _lastMouseDrag) {
		this._lastMouseDrag = _lastMouseDrag;
	}
}
