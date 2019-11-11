package wizard.engine.ecs.systems;

import wizard.engine.ecs.component.BehaviorComponent;
import engine.Application;
import engine.GameWorld;
import engine.systems.Systems;

public class BehaviorSystem extends Systems {
	private BehaviorComponent _behaviorComponent = null;
	public BehaviorSystem(Application app, GameWorld gameWorld) {
		super(app, gameWorld);
		this.setSystemName("Behavior");
		this.setBehaviorComponent(new BehaviorComponent(app, gameWorld) );
	}	
	public void onTick(long nanosSincePreviousTick) {
		this.getBehaviorComponent().onTick(nanosSincePreviousTick);
	}
	private BehaviorComponent getBehaviorComponent() {
		return _behaviorComponent;
	}
	private void setBehaviorComponent(BehaviorComponent _behaviorComponent) {
		this._behaviorComponent = _behaviorComponent;
	}
}
