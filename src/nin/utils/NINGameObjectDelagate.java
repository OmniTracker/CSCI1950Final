package nin.utils;

import java.util.HashMap;

import javafx.scene.image.Image;
import engine.Application;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectDelegate;

public class NINGameObjectDelagate extends GameObjectDelegate {
	private NINXMLParser _ninXMLParser = null;
	private HashMap <String,GameObject> _platforms = null;
	private HashMap <String,GameObject> _characters = null;
	private Image _background = null; 
	
	public NINGameObjectDelagate(Application app) {
		super(app);
		this.setNINXMLParser(new NINXMLParser());
		this.setPlatforms(new HashMap <String,GameObject>());
		this.setCharacters(new HashMap <String,GameObject>());
		this.initParsedFiles();
	}
	
	private void initParsedFiles () {
		this.setBackground(this.getNINXMLParser().readXMLParserBackgroundImage());
	}
	
	public NINXMLParser getNINXMLParser() {
		return _ninXMLParser;
	}
	public void setNINXMLParser(NINXMLParser _ninXMLParser) {
		this._ninXMLParser = _ninXMLParser;
	}
	public HashMap <String,GameObject> getPlatforms() {
		return _platforms;
	}
	private void setPlatforms(HashMap <String,GameObject> _platforms) {
		this._platforms = _platforms;
	}
	public HashMap <String,GameObject> getCharacters() {
		return _characters;
	}
	private void setCharacters(HashMap <String,GameObject> _characters) {
		this._characters = _characters;
	}

	public Image getBackground() {
		return _background;
	}

	private void setBackground(Image _background) {
		this._background = _background;
	}
}
