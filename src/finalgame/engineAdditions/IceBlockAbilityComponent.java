package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import support.Vec2d;

public class IceBlockAbilityComponent extends AnimateAbilityComponent{

	private double _healCounter;
	private PlayerHealthComponent _hp;
	
	public IceBlockAbilityComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim,
			Vec2d animation_increment, int numFrames, double active_time, double cooldown) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_hp = (PlayerHealthComponent)_go.getComponent("HEALTH");
		_healCounter = 0;
	}

	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			_hp.setInvulnerable(true);
			PlayerInputComponent temp = (PlayerInputComponent)_go.getComponent("INPUT");
			temp.setImmobile(true);
			AnimateGraphicsComponent anim = (AnimateGraphicsComponent) _go.getComponent("ANIMATE");
			anim.setAnimate(0);
		}
	}
	
	@Override
	public void tick(long nanosSinceLastTick) {
		if(_active) {
			_activeCounter += (double)nanosSinceLastTick/1000000000.0;
			_currFrame = (int)((_activeCounter/_activeTime)*_numFrames);
			if(_healCounter < 1 && _activeCounter < 3.1) {
				_healCounter += (double)nanosSinceLastTick/1000000000.0;
			}
			else if(_healCounter >= 1) {
				_hp.heal(15);
				_healCounter = 0;
			}
			if(_activeCounter > _activeTime) {
				_currFrame = 0;
				_active = false;
				_coolingDown = true;
				_activeCounter = 0;
				_healCounter = 0;
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
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d charCenter = currTransform.getLoc().plus(currTransform.getDim().sdiv(2));
			Vec2d abilityLoc = charCenter.minus(_dim.sdiv(2));
			g.drawImage(_img,_imageLoc.x+(Math.abs(_currFrame-8)%5)*_animationIncrement.x, _imageLoc.y+(Math.abs(_currFrame-8)/5)*_animationIncrement.y, 
					_imageDim.x, _imageDim.y,abilityLoc.x,abilityLoc.y,_dim.x,_dim.y);
			if(_currFrame == 16) {
				this.breakIce();
			}
		}
	}
	
	private void breakIce() {
		PlayerInputComponent temp = (PlayerInputComponent)_go.getComponent("INPUT");
		temp.setImmobile(false);
		_hp.setInvulnerable(false);
	}

	@Override
	public void onHit(GameObject hitObject) {
		//No onhit Effects	
	}
	
}
