package finalgame.engineAdditions;

import java.util.HashMap;

import support.Vec2d;
import support.debugger.support.shapes.AABShapeDefine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class GameObject {
	private HashMap<String, Component> _components;
	private String _name;
	
	public GameObject() {
		_name = "";
		_components = new HashMap<String, Component>();
	}
	
	public GameObject(String name) {
		_name = name;
		_components = new HashMap<String, Component>();
	}
	
	public GameObject(GameObject o) {
		_name = o.getName();
		_components = new HashMap<String, Component>();
		TransformComponent other = (TransformComponent) o.getComponent("TRANSFORM");
		this.addComponent("TRANSFORM", new TransformComponent(this, other.getLoc(), other.getDim(), 1.0));
		CollisionComponent oth = (CollisionComponent) o.getComponent("COLLISION");
		this.addComponent("COLLISION", new AABCollisionComponent(this, (AABShapeDefine)oth._shape));
	}
	
	public void addComponent(String tag, Component c) {
		_components.put(tag, c);
	}
	
	public void removeComponent(String tag) {
		_components.remove(tag);
	}
	
	public Component getComponent(String tag) {
		return _components.get(tag);
	}
	
	public boolean hasComponent(String tag) {
		return _components.containsKey(tag);
	}
	
	public void tick(long t) {
		if(_components.containsKey("TICK")) {
			_components.get("TICK").tick(t);
		}
		if(_components.containsKey("ABILITY_E")) {
			_components.get("ABILITY_E").tick(t);
		}
		if(_components.containsKey("ABILITY_CLICK")) {
			_components.get("ABILITY_CLICK").tick(t);
		}
	}
	
	public void draw(GraphicsContext g, Affine af) {
		if(_components.containsKey("DRAW")) {
			_components.get("DRAW").draw(g, af);
		}
		if(_components.containsKey("HEALTH")) {
			_components.get("HEALTH").draw(g, af);
		}
		if(_components.containsKey("ABILITY_E")) {
			_components.get("ABILITY_E").draw(g, af);
		}
		if(_components.containsKey("ABILITY_CLICK")) {
			_components.get("ABILITY_CLICK").draw(g, af);
		}
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public Vec2d isColliding(GameObject o) {
		CollisionComponent curr = (CollisionComponent)(_components.get("COLLISION"));
		return curr.collide(o);
	}
	
}
