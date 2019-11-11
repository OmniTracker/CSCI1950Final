package nin.utils;

import engine.Application;
import engine.gameobject.GameObjectDelegate;

public class NINDelegateContainer extends GameObjectDelegate {
	private NINXMLParser _ninXMLParser = null;
	private NinMapDelegate _ninMapDelegate = null; 
	private NINFactory _ninFactory = null; 
	
	public NINDelegateContainer(Application app) {
		super(app);
		this.setNINXMLParser( new NINXMLParser());
		this.setNINFactory( new NINFactory());
		this.setNINMapDelegate(new NinMapDelegate(app));
		this.getNINMapDelegate().setNINFactory(getNINFactory());
	}
	public NinMapDelegate getNINMapDelegate() {
		return _ninMapDelegate;
	}
	private void setNINMapDelegate(NinMapDelegate _ninMapDelegate) {
		this._ninMapDelegate = _ninMapDelegate;
	}
	public NINXMLParser getNINXMLParser() {
		return _ninXMLParser;
	}
	private void setNINXMLParser(NINXMLParser _ninXMLParser) {
		this._ninXMLParser = _ninXMLParser;
	}
	private NINFactory getNINFactory() {
		return _ninFactory;
	}
	private void setNINFactory(NINFactory _ninFactory) {
		this._ninFactory = _ninFactory;
	}
}
