package finalgame.engineAdditions;

import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import support.Vec2d;

public class MeleeMouseAbilityComponent extends MouseAbilityAnimationComponent{

	private double _damage;
	private double _knockback;
	private Vec2d _middleSwordLoc;
	
	public MeleeMouseAbilityComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
		super(go,gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown, range);
		_damage = 50;
		_knockback = 50;
		_middleSwordLoc = new Vec2d(0,0);
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
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			_middleSwordLoc = src.plus(_dir.smult(_range));
			Vec2d swordDrawLoc = _middleSwordLoc.minus(_dim.sdiv(2));
			double rotationAngle = 360 - _currFrame * 10;

//			g.fillOval(getHitBoxLoc().x-getHitBoxDim().x, getHitBoxLoc().y-getHitBoxDim().x, getHitBoxDim().x*2, getHitBoxDim().x*2);
			g.save();
			Rotate r = new Rotate(rotationAngle, _middleSwordLoc.x, _middleSwordLoc.y);
	        g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
			g.drawImage(_img,_imageLoc.x, _imageLoc.y, 
					_imageDim.x, _imageDim.y,swordDrawLoc.x,swordDrawLoc.y,_dim.x,_dim.y);
	        g.restore(); // back to original state (before rotation)
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
			_middleSwordLoc = sink;
			g.strokeLine(src.x,src.y, sink.x, sink.y);
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
			Vec2d dir = tc.getLoc().minus(((TransformComponent)_go.getComponent("TRANSFORM")).getLoc()).normalize();
			tc.move(dir.smult(_knockback));
			
		}
	}
	
	@Override
	public Vec2d getHitBoxDim() {
		return _dim.sdiv(2);
	}	


	@Override
	public Vec2d getHitBoxLoc() {
		return _middleSwordLoc;
	}


	@Override
	public int getHitboxType() {
		return 0;
	}
	
}
