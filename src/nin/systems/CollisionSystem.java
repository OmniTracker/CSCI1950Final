package nin.systems;

import nin.components.CollisionComponent;
import nin.level0.NinGameWorld;
import engine.Application;
import engine.GameWorld;
import engine.systems.Systems;

public class CollisionSystem extends Systems {
	private CollisionComponent _collisionComponent;
	public CollisionSystem(Application app, GameWorld gameWorld) {
		super(app, gameWorld);
		this.setSystemName(" Collision");
		this.setCollisionComponent( new CollisionComponent((NinGameWorld) gameWorld));
	}	
	public void onTick(long nanosSincePreviousTick) {	
		this.getCollisionComponent().onTick(nanosSincePreviousTick);
	}	
	private CollisionComponent getCollisionComponent() {
		return _collisionComponent;
	}
	private void setCollisionComponent(CollisionComponent _collisionComponent) {
		this._collisionComponent = _collisionComponent;
	}
}
