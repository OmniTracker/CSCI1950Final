package engine.systems;

import wizard.utils.WIZGameObjectDelegate;
import engine.Application;
import engine.GameWorld;

public class Systems {
	private Application _app;
	private GameWorld _gameWorld;
	private String _systemName;
	private WIZGameObjectDelegate _WIZgameWorld;
	protected Systems(Application app, GameWorld gameWorld) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
	}
	protected Systems(Application app, WIZGameObjectDelegate gameWorld) {
		this.setApp(app);
		this.setWIZgameWorld(gameWorld);
	}
	public void run() {
	}
	public String getSystemName() {
		return _systemName;
	}
	public void setSystemName(String _systemName) {
		this._systemName = _systemName;
	}
	public Application getApp() {
		return _app;
	}
	void setApp(Application _app) {
		this._app = _app;
	}
	public GameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	public void onTick(long nanosSincePreviousTick) {
	}
	public WIZGameObjectDelegate getWIZgameWorld() {
		return _WIZgameWorld;
	}
	private void setWIZgameWorld(WIZGameObjectDelegate _WIZgameWorld) {
		this._WIZgameWorld = _WIZgameWorld;
	}
}
