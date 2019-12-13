package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class ScratchAbilityComponent extends AnimateAbilityComponent{

	private double _damage;
	private double _knockback;
	private int _facing;
	
	public ScratchAbilityComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim, Vec2d animation_increment,
			int numFrames, double active_time, double cooldown) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_damage = 40;
		_knockback = 200;
	}

	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			//continue showing ability animation
			AnimateGraphicsComponent curr = (AnimateGraphicsComponent)_go.getComponent("ANIMATE");
			_facing = curr.getDir();//1up, 2left, 3down, 4right
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d charLoc = currTransform.getLoc();
			_loc = new Vec2d(charLoc.x,charLoc.y);
			Vec2d charDim = currTransform.getDim();
			switch (_facing) {
				case 4:
					_loc = charLoc.plus(new Vec2d(0,-_dim.y));
					break;
				case 2:
					_loc = charLoc.plus(new Vec2d(-_dim.x,0));
					break;
				case 1:
					_loc = charLoc.plus(new Vec2d(0,charDim.y+5));
					break;
				case 3:
					_loc = charLoc.plus(new Vec2d(charDim.x+5,0));
					break;
				default:
					break;
			}
//			g.fillRect(this.getHitBoxLoc().x, this.getHitBoxLoc().y, this.getHitBoxDim().x, this.getHitBoxDim().y);
			g.drawImage(_img,_imageLoc.x+(_currFrame%5)*_animationIncrement.x, _imageLoc.y + (_currFrame/5)*_animationIncrement.y, 
					_imageDim.x, _imageDim.y,_loc.x,_loc.y,_dim.x,_dim.y);
		}
	}


	@Override
	public void onHit(GameObject hitObject) {
		if(hitObject.hasComponent("HEALTH")) {
			HealthComponent hp = (HealthComponent)hitObject.getComponent("HEALTH");
			hp.takeDamage(_damage);
		}
		if(hitObject.hasComponent("TRANSFORM")) {
			TransformComponent tc = (TransformComponent)hitObject.getComponent("TRANSFORM");
			switch (_facing) {
				case 4:
					tc.move(new Vec2d(0,-1).smult(_knockback));
					break;
				case 2:
					tc.move(new Vec2d(-1,0).smult(_knockback));
					break;
				case 1:
					tc.move(new Vec2d(0,1).smult(_knockback));
					break;
				case 3:
					tc.move(new Vec2d(1,0).smult(_knockback));
					break;
				default:
					break;
			}
		}
	}


	@Override
	public Vec2d getHitBoxDim() {
		return _dim;
	}


	@Override
	public Vec2d getHitBoxLoc() {
		return _loc;
	}
		
}
