package nin.utils;

import engine.Application;
import engine.gameobject.GameObjectDelegate;

public class NinMapDelegate extends GameObjectDelegate {
	private NINXMLParser _xmlParser                = null; 
	
	public NinMapDelegate(Application app) {
		super(app);
		this.setXMLParser(new NINXMLParser());
		this.initPlatform();		
	}
	public void initPlatform () {
		this.getXMLParser().readXMLParserPlatform();
		this.getXMLParser().readXMLParserCharacter();
	}

	private NINXMLParser getXMLParser() {
		return _xmlParser;
	}
	private void setXMLParser(NINXMLParser _xmlParser) {
		this._xmlParser = _xmlParser;
	}
}
