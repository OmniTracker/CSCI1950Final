package nin.utils;

import java.util.HashMap;

import javafx.scene.image.Image;
import engine.Application;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectDelegate;

public class NINGameObjectDelagate extends GameObjectDelegate {
	private NINXMLParser _ninXMLParser = null;
	private HashMap <Integer,GameObject> _platforms = null;
	private GameObject _character = null;
	private Image _background = null; 
	public NINGameObjectDelagate(Application app) {
		super(app);
		this.setNINXMLParser(new NINXMLParser());
		this.setPlatforms(new HashMap <Integer,GameObject>());
		this.initParsedFiles();
	}
	private void initParsedFiles () {
		this.setBackground(this.getNINXMLParser().readXMLParserBackgroundImage());
		this.setCharacter(this.getNINXMLParser().readXMLParserCharacter());
		this.setPlatforms(this.getNINXMLParser().readXMLParserPlatform());
	}
	public NINXMLParser getNINXMLParser() {
		return _ninXMLParser;
	}
	public void setNINXMLParser(NINXMLParser _ninXMLParser) {
		this._ninXMLParser = _ninXMLParser;
	}
	public HashMap <Integer,GameObject> getPlatforms() {
		return _platforms;
	}
	private void setPlatforms(HashMap <Integer,GameObject> _platforms) {
		this._platforms = _platforms;
	}
	public Image getBackground() {
		return _background;
	}
	private void setBackground(Image _background) {
		this._background = _background;
	}
	public GameObject getCharacter() {
		return _character;
	}
	private void setCharacter(GameObject _character) {
		this._character = _character;
	}
}
