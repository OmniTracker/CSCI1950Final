package finalgame.engineAdditions;

import java.util.HashMap;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class PlayerInputComponent extends Component{

	private HashMap<String, Double> _input;
	protected boolean _hasFocus;
	protected double _moveMultiplier;
	private String[] keys = new String[8];
	
	public PlayerInputComponent(GameObject go, HashMap<String,Double> input) {
		super(go);
		_input = input;
		_hasFocus = false;
		_moveMultiplier = 1;
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		if(_hasFocus && _go.hasComponent("TRANSFORM")) {
			double mult = nanosSinceLastTick * 0.00000005 * _moveMultiplier;
			boolean moved=false;
			TransformComponent curr = (TransformComponent)_go.getComponent("TRANSFORM");
			if(_input.containsKey(keys[4]) && _input.get(keys[4]) == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(4);
				}
				curr.move(new Vec2d(0,-5.*mult));
				moved = true;
			}
			if(_input.containsKey(keys[7]) && _input.get(keys[7]) == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(1);
				}
				curr.move(new Vec2d(0,5.*mult));
				moved = true;
			}
			if(_input.containsKey(keys[5]) && _input.get(keys[5]) == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(2);
				}
				curr.move(new Vec2d(-5.*mult,0));
				moved = true;
			}
			if(_input.containsKey(keys[6]) && _input.get(keys[6]) == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(3);
				}
				curr.move(new Vec2d(5.*mult,0));
				moved = true;
			}
			if (!moved && _go.hasComponent("ANIMATE")) {
				AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
				anim.setAnimate(0);
			}
			if(_input.containsKey(keys[3]) && _input.get(keys[3]) == 1) {
				if(_go.hasComponent("ABILITY_Q")) {
					AnimateAbilityComponent ability = (AnimateAbilityComponent) _go.getComponent("ABILITY_Q");
					ability.activateAbility();
				}
			}
			if(_input.containsKey(keys[2]) && _input.get(keys[2]) == 1) {
				if(_go.hasComponent("ABILITY_E")) {
					AnimateAbilityComponent ability = (AnimateAbilityComponent) _go.getComponent("ABILITY_E");
					ability.activateAbility();
				}
			}
			if(_input.containsKey(keys[1]) && _input.get(keys[1]) == 1) {
				if(_go.hasComponent("ABILITY_F")) {
					AnimateAbilityComponent ability = (AnimateAbilityComponent) _go.getComponent("ABILITY_F");
					ability.activateAbility();
				}
			}
			if(_input.containsKey(keys[0]) && _input.get(keys[0]) == 1) {
				if(_go.hasComponent("HEALTH")) {
					PlayerHealthComponent health = (PlayerHealthComponent) _go.getComponent("HEALTH");
					health.usePotion();
				}
			}
			if (_input.containsKey("MOUSE_LEFT") && _input.get("MOUSE_LEFT") == 1) {
				if(_go.hasComponent("ABILITY_CLICK")) {
					AnimateAbilityComponent ability = (AnimateAbilityComponent) _go.getComponent("ABILITY_CLICK");
					ability.activateAbility();
				}
			}

			if (_input.containsKey("MOUSE_RIGHT") && _input.get("MOUSE_RIGHT") == 1) {
				if(_go.hasComponent("ABILITY_CLICK")) {
					MouseAbilityAnimationComponent ability = (MouseAbilityAnimationComponent) _go.getComponent("ABILITY_CLICK");
					ability.showRangeIndicator();
				}
			}
			else {
				if(_go.hasComponent("ABILITY_CLICK")) {
					MouseAbilityAnimationComponent ability = (MouseAbilityAnimationComponent) _go.getComponent("ABILITY_CLICK");
					ability.hideRangeIndicator();
				}
			}
		}
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		// TODO Auto-generated method stub
		
	}
	
	public void setInput(HashMap<String, Double> input) {
		_input = input;
	}

	public void setFocus(boolean focus) {
		_hasFocus = focus;
	}
	
	public double getInput(String s) {
		return _input.get(s);
	}
	
	public void setMoveMultiplier(double m) {
		_moveMultiplier = m;
	}
	
	public void setAbilityKeys(String[] p) {
		keys = p;
	}
}
