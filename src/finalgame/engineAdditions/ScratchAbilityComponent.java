package finalgame.engineAdditions;

import support.Vec2d;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class ScratchAbilityComponent extends AnimateAbilityComponent{

	private double _damage;
	private double _knockback;
	private int _facing;
	private boolean _hitSpawned;
	
	public ScratchAbilityComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim, Vec2d animation_increment,
			int numFrames, double active_time, double cooldown) {
		super(go, gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_damage = 40;
		_knockback = 200;
		_hitSpawned = false;
	}

	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
		}
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		if(_active) {
			_activeCounter += (double)nanosSinceLastTick/1000000000.0;
			_currFrame = (int)((_activeCounter/_activeTime)*_numFrames);
			if(_currFrame <= 3 && !_hitSpawned) {
				this.spawnHitbox();
				_hitSpawned = true;
			}
			if(_activeCounter > _activeTime) {
				_currFrame = 0;
				_active = false;
				_coolingDown = true;
				_activeCounter = 0;
				this.removeHitBox();
				_hitSpawned = false;
			}
		}
		else if(_coolingDown) {
			_cooldownCounter -= (double)nanosSinceLastTick/1000000000.0;
			if(_cooldownCounter <= 0) {
				_cooldownCounter = _cooldown;
				_coolingDown = false;
			}
		}
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
		if(hitObject.hasComponent("TRANSFORM") && !hitObject.getName().contains("BOSS")) {
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


	@Override
	public int getHitboxType() {
		return 1;
	}
		
}
