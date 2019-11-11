package wizard.utils;

import engine.GameWorld;

public class WIZDelegateContainer {	
	private WIZMapDelegate _wizMapDelegate;
	private WIZGameObjectDelegate _wizGameObjectDelegate;
	private GameWorld _gameWorld;
	public WIZDelegateContainer () {		
	}
	public WIZMapDelegate getWIZMapDelegate() {
		return _wizMapDelegate;
	}
	public void setWIZMapDelegate(WIZMapDelegate _wizMapDelegate) {
		this._wizMapDelegate = _wizMapDelegate;
	}
	public WIZGameObjectDelegate getWIZGameObjectDelegate() {
		return _wizGameObjectDelegate;
	}
	public void setWIZGameObjectDelegate(WIZGameObjectDelegate _wizGameObjectDelegate) {
		this._wizGameObjectDelegate = _wizGameObjectDelegate;
	}
	public GameWorld getGameWorld() {
		return _gameWorld;
	}
	public void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
