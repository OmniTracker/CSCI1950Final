package wizard.engine.ecs.component;

import engine.Application;
import engine.GameWorld;

/**
 *  The Game Board component doesn't have any subscribers at this point.
 *  The Game board will always be draw first from this component.
 * @author rbaker2
 *
 */
public class GameBoardComponent  extends GameBoardDataComponent  {
	public GameBoardComponent(Application app, GameWorld gameWorld) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
		this.setComponentName("GameBoard");
	}
}
