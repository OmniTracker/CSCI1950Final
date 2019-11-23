package finalgame.engineAdditions;

import java.util.HashMap;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class PlayerInputComponent extends Component{

	private HashMap<String, Double> _input;
	protected boolean _hasFocus;
	protected double _moveMultiplier;
	
	public PlayerInputComponent(GameObject go, HashMap<String,Double> input) {
		super(go);
		// TODO Auto-generated constructor stub
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
			if(_input.get("UP") == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(1);
				}
				curr.move(new Vec2d(0,-5.*mult));
				moved = true;
			}
			if(_input.get("DOWN") == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(3);
				}
				curr.move(new Vec2d(0,5.*mult));
				moved = true;
			}
			if(_input.get("LEFT") == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(2);
				}
				curr.move(new Vec2d(-5.*mult,0));
				moved = true;
			}
			if(_input.get("RIGHT") == 1) {
				if(_go.hasComponent("ANIMATE")) {
					AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
					anim.setAnimate(4);
				}
				curr.move(new Vec2d(5.*mult,0));
				moved = true;
			}
			if (!moved && _go.hasComponent("ANIMATE")) {
				AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
				anim.setAnimate(0);
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
}
