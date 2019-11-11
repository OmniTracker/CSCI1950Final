package wizard.engine.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyEvent;
import wizard.engine.ecs.component.CollisionComponent;
import engine.Application;
import engine.GameWorld;
import engine.gameobject.GameObject;
import engine.systems.Systems;

public class CollisionSystem extends Systems {	
	private List<GameObject> _obj;
	private CollisionComponent _collisionComponent;

	public CollisionSystem (Application app, GameWorld gameWorld) {
		super(app,gameWorld);
		this.setSystemName("Collision");
		this.setObj(new ArrayList<GameObject>());
		this.setCollisionComponent( new CollisionComponent(app,gameWorld) );
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getCollisionComponent().onTick(nanosSincePreviousTick);
	}
	public void onKeyPressed(KeyEvent e) {
	}
	private List<GameObject> getObj() {
		return _obj;
	}
	private void setObj(List<GameObject> _obj) {
		this._obj = _obj;
	}
	public void subscribe(GameObject obj) {
		this.getObj().add(obj);
	}
	private CollisionComponent getCollisionComponent() {
		return _collisionComponent;
	}
	private void setCollisionComponent(CollisionComponent _collisionComponent) {
		this._collisionComponent = _collisionComponent;
	}
}
