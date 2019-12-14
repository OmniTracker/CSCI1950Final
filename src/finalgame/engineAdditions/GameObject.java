package finalgame.engineAdditions;

import java.util.HashMap;

import support.Vec2d;
import support.debugger.collisions.AABShape;
import support.debugger.collisions.CircleShape;
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
		if(o.hasComponent("TRANSFORM")) {
			TransformComponent other = (TransformComponent) o.getComponent("TRANSFORM");
			this.addComponent("TRANSFORM", new TransformComponent(this, other.getLoc(), other.getDim(), 1.0));			
		}
		if(o.hasComponent("COLLISION")) {
			CollisionComponent oth = (CollisionComponent) o.getComponent("COLLISION");
			switch (oth.getType()) {
			case 0:
				this.addComponent("COLLISION", new CircleCollisionComponent(this, (CircleShape)oth._shape));
				break;
			case 1:
				this.addComponent("COLLISION", new AABCollisionComponent(this, (AABShape)oth._shape));
				break;
			case 2:
				CircleAbilityCollisionComponent ot = (CircleAbilityCollisionComponent) oth;
				this.addComponent("COLLISION", new CircleAbilityCollisionComponent(this, (CircleShape)oth._shape, ot._ability, ot._numTargets));
				break;
			case 3:
				AABAbilityCollisionComponent othe = (AABAbilityCollisionComponent) oth;
				this.addComponent("COLLISION", new AABAbilityCollisionComponent(this, (AABShape)oth._shape, othe._ability, othe._numTargets));
				break;
			}
						
		}
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
		if(_components.containsKey("HEALTH")) {
			_components.get("HEALTH").tick(t);
		}
		if(_components.containsKey("ABILITY_E")) {
			_components.get("ABILITY_E").tick(t);
		}
		if(_components.containsKey("ABILITY_Q")) {
			_components.get("ABILITY_Q").tick(t);
		}
		if(_components.containsKey("ABILITY_F")) {
			_components.get("ABILITY_F").tick(t);
		}
		if(_components.containsKey("ABILITY_CLICK")) {
			_components.get("ABILITY_CLICK").tick(t);
		}
		if(_components.containsKey("ABILITY")) {
			_components.get("ABILITY").tick(t);
		}
	}
	
	public void draw(GraphicsContext g, Affine af) {
		if(_components.containsKey("DRAW")) {
			_components.get("DRAW").draw(g, af);
		}
		if(_components.containsKey("HEALTH")) {
			_components.get("HEALTH").draw(g, af);
		}
		if(_components.containsKey("ABILITY_Q")) {
			_components.get("ABILITY_Q").draw(g, af);
		}
		if(_components.containsKey("ABILITY_E")) {
			_components.get("ABILITY_E").draw(g, af);
		}
		if(_components.containsKey("ABILITY_F")) {
			_components.get("ABILITY_F").draw(g, af);
		}
		if(_components.containsKey("ABILITY_CLICK")) {
			_components.get("ABILITY_CLICK").draw(g, af);
		}
		if(_components.containsKey("ABILITY")) {
			_components.get("ABILITY").draw(g, af);
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
	
	@Override
	public String toString() {
		return _name;
	}
	
}
