package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import support.Vec2d;

public class FireWaveAbilityComponent extends AnimateAbilityComponent{

	private Vec2d _dir;
	private Vec2d _offsetDir;
	private Vec2d _src;
	private double _range;
	private double _rotation;
	private int _facing;
	
	
	public FireWaveAbilityComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim,
			Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_range = range;
		_offsetDir = new Vec2d(0,-1);
	}
	
	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			_src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			
			AnimateGraphicsComponent curr = (AnimateGraphicsComponent)_go.getComponent("ANIMATE");
			_facing = curr.getDir();//1up, 2left, 3down, 4right
			switch (_facing) {
				case 4:
					_dir = new Vec2d(0,-1);
					_rotation = 270;
					break;
				case 2:
					_dir = new Vec2d(-1,0);
					_rotation = 180;
					break;
				case 1:
					_dir = new Vec2d(0,1);
					_rotation = 90;
					break;
				case 3:
					_dir = new Vec2d(1,0);
					_rotation = 0;
					break;
				default:
					break;
			}
		}
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			double trajLoc = _activeCounter/_activeTime;
			Vec2d dirVector = _dir.smult(_range).smult(trajLoc);
			_loc = dirVector.plus(_src);
			//Visualize Hitbox
			//g.fillRect(getHitBoxLoc().x, getHitBoxLoc().y, getHitBoxDim().x, getHitBoxDim().y);
			g.save();
			Rotate r = new Rotate(_rotation, _loc.x, _loc.y);
	        g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
			g.drawImage(_img,_imageLoc.x+(_currFrame%2)*_animationIncrement.x, _imageLoc.y, 
					_imageDim.x, _imageDim.y,_loc.x,_loc.plus(_offsetDir.smult(_dim.x*1.6)).y,_dim.x,_dim.y);
			g.restore(); // back to original state (before rotation)
			}
	}

	@Override
	public void onHit(GameObject hitObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vec2d getHitBoxDim() {
		if(_dir.y != 0) {
			return new Vec2d(_dim.y, _dim.x);
		}
		return _dim;
	}

	@Override
	public Vec2d getHitBoxLoc() {
		Vec2d hitBoxOffset = null;
		switch (_facing) {//1up, 2left, 3down, 4right
			case 4:
				hitBoxOffset = new Vec2d(-_dim.sdiv(2).y+5, -_dim.x);
				break;
			case 2:
				hitBoxOffset = new Vec2d(-_dim.x, -_dim.y/2-5);
				break;
			case 1://-_dim.sdiv(2).x, +_dim.sdiv(2).y
				hitBoxOffset = new Vec2d(-_dim.y/2-5, 0);
				break;
			case 3:
				hitBoxOffset = new Vec2d(0,-_dim.y/2+5);
				break;
			default:
				break;
		}
		//.plus(_offsetDir.smult(_dim.x*1.6))
		return new Vec2d(_loc.x,_loc.y).plus(hitBoxOffset);
	}
}
