package finalgame.engineAdditions;

import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import support.Vec2d;

public class EnemyRangedAbilityComponent extends MouseAbilityAnimationComponent{

	GameObject target;
	
	MediaPlayer _lzrPlayer;
	
	public EnemyRangedAbilityComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
		super(go, gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown, range);
		_damage = 10;
		_knockback = 0;
		_lzrPlayer = _gw.get_soundSys().getLzrPlayer();
		target = _gw.get_player();
	}
	
	@Override
	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			
			this.spawnHitbox();
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			_src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			TransformComponent playerTransform = (TransformComponent) _gw.get_player().getComponent("TRANSFORM");
			Vec2d playerCenter = playerTransform.getLoc().plus(playerTransform.getDim().sdiv(2));
			_dir = new Vec2d(playerCenter.x-_src.x, playerCenter.y-_src.y).normalize();
			_lzrPlayer.seek(Duration.ZERO);
			this.spawnSound();
		}
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		
		if(_active && _activeCounter<_activeTime) {
			double trajLoc = _activeCounter/_activeTime;
			Vec2d dirVector = _dir.smult(_range).smult(trajLoc);
			Vec2d bulletLoc = dirVector.plus(_src);
			_activeBulletLoc = bulletLoc;
			double rotationAngle = 360 - _currFrame * 10;

//			g.fillOval(getHitBoxLoc().x-getHitBoxDim().x, getHitBoxLoc().y-getHitBoxDim().x, getHitBoxDim().x*2, getHitBoxDim().x*2);
			
			g.save();
			Rotate r = new Rotate(rotationAngle, bulletLoc.x+_dim.x/2, bulletLoc.y+_dim.y/2);
	        g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
			g.drawImage(_img,_imageLoc.x+(_currFrame%5)*_animationIncrement.x, _imageLoc.y + (_currFrame/5)*_animationIncrement.y, 
					_imageDim.x, _imageDim.y,bulletLoc.x,bulletLoc.y,_dim.x,_dim.y);
	        g.restore(); // back to original state (before rotation)
			
		}
	}
	
	@Override
	public void onHit(GameObject hitObject) {
		if(hitObject.hasComponent("HEALTH")) {
			HealthComponent hp = (HealthComponent)hitObject.getComponent("HEALTH");
			hp.takeDamage(_damage);
		}
		this.removeHitBox();
		_activeCounter = _activeTime+1;
		if(hitObject.hasComponent("TRANSFORM")) {
			TransformComponent tc = (TransformComponent)hitObject.getComponent("TRANSFORM");
			tc.move(_dir.smult(_knockback));
		}
	}
	
	@Override
	protected void spawnHitbox() {
		_activeHitboxObj = _gw.spawnEnemyAbilityHitbox(this);
	}
	
	public void spawnSound() {
		TransformComponent tC =  (TransformComponent) target.getComponent("TRANSFORM");
		TransformComponent tCo =  (TransformComponent) _go.getComponent("TRANSFORM");
		Vec2d dif = tC.getLoc().minus(tCo.getLoc());
		_lzrPlayer.setVolume(.15);
		double angle = dif.angle()*180/Math.PI;
		double bal = 0;
		//System.out.println(angle);
		if (angle>90 && angle<270) {
			if (angle<180) {
				angle = angle-90;
				bal = angle/90;
			}else {
				angle = angle-180;
				bal = 1-angle/90;
			}
			
			//System.out.println(bal);
			_lzrPlayer.setBalance(bal);
		}else {
			if (angle > 270) {
				angle=angle-270;
				bal = -(angle/90);
			}else {
				bal=-(1-(angle/90));
			}
			
			//System.out.println(bal);
			_lzrPlayer.setBalance(bal);
		}
		_lzrPlayer.play();
	}
	
	public GameObject getTarget() {
		return target;
	}
	
}
