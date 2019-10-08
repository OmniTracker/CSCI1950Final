package engine.gameobject;

import java.util.ArrayList;

import engine.systems.Component;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;
import support.collision.AABShape;

public class GameObject {
	private String _name;
	private Vec2d _position;
	private Vec2d _size;
	private ArrayList<engine.systems.Component> _components;
	private Image _image; 
	private Integer _step = 0;
	private Vec2d _gameOrigin = new Vec2d(0,0);
	

	protected GameObject() {
		this.setPosition(new Vec2d(0.0,0.0)); 
		this.setSize(new Vec2d(0.0,0.0));  
		this.setSmallBoundingBox(new AABShape( new Vec2d(0,0), new Vec2d(0,0)));
		this.setComponents(new ArrayList<Component>());
	}

	public void addComponent(Component c) {
		this.getComponents().add(c);
	}

	public void removeComponent(Component c) {
		this.getComponents().remove(c);
	}
	
	public Component getComponent(String tag) {
		Component getter = null;
		for (Component c : this.getComponents()) {
			if (c.getComponentName() == tag) {
				getter = c; 
				break;
			}
		}
		return getter;
	}
	
	public void draw(GraphicsContext g) {
		
	}
	
	public void smallCharacterDraw (GraphicsContext g, String direction) {
		
	}
	

	public void tick(long t) {

	}
	
	
	public void onMouseClicked(MouseEvent e) {

	}
	public void onMousePressed(MouseEvent e) {

	}
	public void onMouseReleased(MouseEvent e) {

	}
	public void onMouseDragged(MouseEvent e) {

	}
	public void onMouseWheelMoved(ScrollEvent e){

	}
	public void onMouseMoved(MouseEvent e) {

	}
	
	
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
	public ArrayList<Component> getComponents() {
		return _components;
	}
	private void setComponents(ArrayList<Component> _components) {
		this._components = _components;
	}
	public Image getImage() {
		return _image;
	}
	public void setImage(Image _image) {
		this._image = _image;
	}
	public void incrementStep() {
		if (this._step == 2 ){
			this._step = 0;
		} else {
			this._step++;
		}
	} 
	
	public Integer getStep() {
		return this._step;
	}

	public Vec2d getGameOrigin() {
		return _gameOrigin;
	}

	public void setGameOrigin(Vec2d _gameOrigin) {
		this._gameOrigin = _gameOrigin;
	}

	public AABShape getBigBoundingBox() {
		return new AABShape(new Vec2d(0,0),new Vec2d(0,0));
	}
	public AABShape getSmallBoundingBox() {
		return new AABShape(new Vec2d(0,0),new Vec2d(0,0));
	}
	
	public void setBigBoundingBox(AABShape _bigBoundingBox) {
		
	}
	
	public void setSmallBoundingBox(AABShape _smallBoundingBox) {
		
	}
}