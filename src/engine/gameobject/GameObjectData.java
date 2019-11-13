package engine.gameobject;

import java.util.ArrayList;

import javafx.scene.image.Image;
import support.Vec2d;
import support.Vec2i;
import support.collision.AABShape;
import wizard.behaviortree.WIZBehaviorTree;
import engine.Application;
import engine.ai.behaviortree.BehaviorTree;

public class GameObjectData {
	public Application _app                 = null;
	public ArrayList<String> _components    = new ArrayList<String>();
	public ArrayList<String> _systems       = new ArrayList<String>();
	public BehaviorTree _behaviorTree       = new BehaviorTree();
	public WIZBehaviorTree _wizBehaviorTree = new WIZBehaviorTree();
	public String _name                     = " ";
	public Vec2d _position                  = new Vec2d(0.0,0.0); 
	public Vec2d _size                      = new Vec2d(0.0,0.0); 
	public Image _image                     = null;
	public Integer _stepX                   = 0;
	public Integer _stepY                   = 0;
	public AABShape _box                    = null;
	public Vec2i _gameLocation              = new Vec2i(0,0); 
	public Vec2d _gameOrigin                = new Vec2d(0.0,0.0); 
	public Vec2d _imageStart                = new Vec2d(0.0,0.0); 
	public Vec2d _imageSize                 = new Vec2d(0.0,0.0); 
	public Vec2d _imageGameSize             = new Vec2d(0.0,0.0); 
	public Vec2d _AIposition                = new Vec2d(0.0,0.0); 
	public Vec2i _AIGridLocation            = new Vec2i(0,0);
	public Vec2d _currentMTV                = new Vec2d(0.0,0.0); 
	public GameObjectData() {}
	
	/*
	 * level 0
	 */
	public boolean _level0RedKeyFound = false;
	public boolean _level0BlueKeyFound = false;
	public boolean _level0GreenKeyFound = false;
	/*
	 * level 1
	 */
	public boolean _level1RedKeyFound = false;
	public boolean _level1BlueKeyFound = false;
	public boolean _level1GreenKeyFound = false;
	
	public boolean _visible = true;
	public boolean _locked = false;
	
	
	
	
	public String getName() {
		return _name;
	}
	public void setName(String _name) {
		this._name = _name;
	}
	public Vec2d getPosition() {
		return _position;
	}
	public void setPosition(Vec2d _position) {
		this._position = _position;
	}
	public Vec2d getSize() {
		return _size;
	}
	public void setSize(Vec2d _size) {
		this._size = _size;
	}
	public Image getImage() {
		return _image;
	}
	public void setImage(Image _image) {
		this._image = _image;
	}
	public Vec2d getGameOrigin() {
		return _gameOrigin;
	}
	public void setGameOrigin(Vec2d _gameOrigin) {
		this._gameOrigin = _gameOrigin;
	}
	public AABShape getBox() {
		return _box;
	}
	public void setBox(AABShape _box) {
		this._box = _box;
	}
	Vec2d getImageStart() {
		return _imageStart;
	}
	public void setImageStart(Vec2d _imageStart) {
		this._imageStart = _imageStart;
	}
	Vec2d getImageSize() {
		return _imageSize;
	}
	public void setImageSize(Vec2d _imageSize) {
		this._imageSize = _imageSize;
	}
	Vec2d getImageGameSize() {
		return _imageGameSize;
	}
	public void setImageGameSize(Vec2d _imageGameSize) {
		this._imageGameSize = _imageGameSize;
	}
	Integer getStepX() {
		return _stepX;
	}
	void setStepX(Integer _stepX) {
		this._stepX = _stepX;
	}
	Integer getStepY() {
		return _stepY;
	}
	void setStepY(Integer _stepY) {
		this._stepY = _stepY;
	}
	public Vec2d getAIposition() {
		return _AIposition;
	}
	public void setAIposition(Vec2d _AIposition) {
		this._AIposition = _AIposition;
	}
	public BehaviorTree getBehaviorTree() {
		return _behaviorTree;
	}
	public void setBehaviorTree(BehaviorTree _behaviorTree) {
		this._behaviorTree = _behaviorTree;
	}
	public Vec2i getGameGridLocation() {
		return _gameLocation;
	}
	public void setGameLocation(Vec2i _gameLocation) {
		this._gameLocation = _gameLocation;
	}
	Application getApp() {
		return _app;
	}
	void setApp(Application _app) {
		this._app = _app;
	}
	public Vec2i getAIGridLocation() {
		return _AIGridLocation;
	}
	public void setAIGridLocation(Vec2i _AIGridLocation) {
		this._AIGridLocation = _AIGridLocation;
	}
	public Vec2d getCurrentMTV() {
		return _currentMTV;
	}
	public  void setCurrentMTV(Vec2d _currentMTV) {
		this._currentMTV = _currentMTV;
	}
	public WIZBehaviorTree getWIZBehaviorTree() {
		return _wizBehaviorTree;
	}
	public void setWIZBehaviorTree(WIZBehaviorTree _wizBehaviorTree) {
		this._wizBehaviorTree = _wizBehaviorTree;
	}
	public ArrayList <String> getComponents() {
		return _components;
	}
	public void setComponents(ArrayList <String> _components) {
		this._components = _components;
	}
	public ArrayList <String> getSystems() {
		return _systems;
	}
	public void setSystems(ArrayList <String> _systems) {
		this._systems = _systems;
	}
}