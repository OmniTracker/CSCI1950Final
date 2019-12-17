package finalgame.engineAdditions;

import support.Vec2d;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class MouseAbilityAnimationComponent extends AnimateAbilityComponent{
	
	protected double _range;
	protected boolean _showRange;
	protected double _damage;
	protected double _knockback;
	
	protected Vec2d _src;
	protected Vec2d _dir;
	protected Vec2d _activeBulletLoc;
	
	
	public MouseAbilityAnimationComponent(GameObject go, MainGamePlay gw,Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim,
			Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
//		, WizWorld gw  did take gameworld
		super(go, gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_range = range;
		_showRange = false;
		_src = null;
		_dir = null;
		_activeBulletLoc = new Vec2d(0,0);
		_damage = 10;
		_knockback = 2.5;
	}
	
	
	@Override
	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			
			this.spawnHitbox();
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			_src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			PlayerInputComponent inputComp = (PlayerInputComponent) _go.getComponent("INPUT");
			double mouseXLoc = inputComp.getInput("MOUSE.X");
			double mouseYLoc = inputComp.getInput("MOUSE.Y");
			_dir = new Vec2d(mouseXLoc-_src.x, mouseYLoc-_src.y).normalize();
		}
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		if(_active) {
			_activeCounter += (double)nanosSinceLastTick/1000000000.0;
			_currFrame = (int)((_activeCounter/_activeTime)*_numFrames);
			if(_activeCounter > _activeTime) {
				_currFrame = 0;
				_active = false;
				_coolingDown = true;
				_activeCounter = 0;
				this.removeHitBox();
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
			double trajLoc = _activeCounter/_activeTime;
			Vec2d dirVector = _dir.smult(_range).smult(trajLoc);
			Vec2d bulletLoc = dirVector.plus(_src);
			_activeBulletLoc = bulletLoc;
			g.drawImage(_img,_imageLoc.x+(_currFrame%5)*_animationIncrement.x, _imageLoc.y + (_currFrame/5)*_animationIncrement.y, 
					_imageDim.x, _imageDim.y,bulletLoc.x,bulletLoc.y,_dim.x,_dim.y);
		}
		else if(_showRange)  {
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			PlayerInputComponent inputComp = (PlayerInputComponent) _go.getComponent("INPUT");
			double mouseXLoc = inputComp.getInput("MOUSE.X");
			double mouseYLoc = inputComp.getInput("MOUSE.Y");
			Vec2d dir = new Vec2d(mouseXLoc-src.x, mouseYLoc-src.y);
			dir = dir.normalize();
			Vec2d rangeIndicator = dir.smult(_range);
			Vec2d sink = src.plus(rangeIndicator);
			g.strokeLine(src.x,src.y, sink.x, sink.y);
		}		
	}
	
	public void showRangeIndicator() {
		_showRange = true;
	}
	
	public void hideRangeIndicator() {
		_showRange = false;
	}
	
	public Vec2d getDim() {
		return _dim;
	}
	
	public double getRange() {
		return _range;
	}
	
	public void setRange(double range) {
		_range = range;
	}
	
	public double getTravelTime() {
		return _activeTime;
	}
	
	public void setTravelTime(double time) {
		_activeTime = time;
	}


	@Override
	public void onHit(GameObject hitObject) {
		if(hitObject.hasComponent("HEALTH")) {
			HealthComponent hp = (HealthComponent)hitObject.getComponent("HEALTH");
			hp.takeDamage(_damage);
		}
		if(hitObject.hasComponent("TRANSFORM") && !hitObject.getName().contains("BOSS")) {
			TransformComponent tc = (TransformComponent)hitObject.getComponent("TRANSFORM");
			tc.move(_dir.smult(_knockback));
		}
	}


	@Override
	public Vec2d getHitBoxDim() {
		return _dim.sdiv(2);
	}	


	@Override
	public Vec2d getHitBoxLoc() {
		return _activeBulletLoc.plus(_dim.sdiv(2));
	}


	@Override
	public int getHitboxType() {
		return 0;
	}
}
