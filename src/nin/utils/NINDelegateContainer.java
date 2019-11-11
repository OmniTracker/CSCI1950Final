package nin.utils;

import engine.Application;
import engine.gameobject.GameObjectDelegate;

public class NINDelegateContainer extends GameObjectDelegate {
	private NinMapDelegate _ninMapDelegate               = null; 
	private NINGameObjectDelagate _ninGameObjectDelagate = null;
	
	public NINDelegateContainer(Application app) {
		super(app);
		this.setNINMapDelegate(new NinMapDelegate(app));
		this.setNINGameObjectDelagate( new NINGameObjectDelagate(app));
	}
	public NinMapDelegate getNINMapDelegate() {
		return _ninMapDelegate;
	}
	private void setNINMapDelegate(NinMapDelegate _ninMapDelegate) {
		this._ninMapDelegate = _ninMapDelegate;
	}
	public NINGameObjectDelagate getNINGameObjectDelagate() {
		return _ninGameObjectDelagate;
	}
	private void setNINGameObjectDelagate(NINGameObjectDelagate _ninGameObjectDelagate) {
		this._ninGameObjectDelagate = _ninGameObjectDelagate;
	}
}
