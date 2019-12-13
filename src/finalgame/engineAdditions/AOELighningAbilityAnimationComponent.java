package finalgame.engineAdditions;

import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import support.Vec2d;

public class AOELighningAbilityAnimationComponent extends AnimateAbilityComponent{
	
	private PlayerHealthComponent _hp;
	private PlayerInputComponent _input;
	private double _damage;
	private double _shockDistance;
	
	public AOELighningAbilityAnimationComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown) {
		super(go,gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);

		_hp = (PlayerHealthComponent)_go.getComponent("HEALTH");
		_input = (PlayerInputComponent)_go.getComponent("INPUT");
		_damage = 10;
		_shockDistance = 2;
	}
	
	
	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			_hp.setInvulnerable(true);
			_input.setMoveMultiplier(1.5);
			this.spawnHitbox();
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
				_hp.setInvulnerable(true);
				_input.setMoveMultiplier(1.);
			}
		}
		else if(_coolingDown) {
			_cooldownCounter -= (double)nanosSinceLastTick/1000000000.0;
			if(_cooldownCounter <= 0) {
				_cooldownCounter = _cooldown;
				_coolingDown = false;
				this.removeHitBox();
			}
		}
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			//continue showing ability animation
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			_loc = currTransform.getLoc().plus(currTransform.getDim().sdiv(2));
			Vec2d abilityLoc = _loc.minus(_dim.sdiv(2));
			//Visualize Hitbox
//			g.fillOval(getHitBoxLoc().x-getHitBoxDim().x, getHitBoxLoc().y-getHitBoxDim().x, getHitBoxDim().x*2, getHitBoxDim().x*2);
			g.drawImage(_img,_imageLoc.x+(_currFrame)*_animationIncrement.x, _imageLoc.y, 
					_imageDim.x, _imageDim.y,abilityLoc.x,abilityLoc.y,_dim.x,_dim.y);
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
			tc.move(new Vec2d(Math.random(), Math.random()).normalize().smult(_shockDistance));
		}
	}


	@Override
	public Vec2d getHitBoxDim() {
		return _dim.sdiv(2).minus(5, 0);
	}


	@Override
	public Vec2d getHitBoxLoc() {
		return _loc;
	}


	@Override
	public int getHitboxType() {
		return 0;
	}
	
	
}
