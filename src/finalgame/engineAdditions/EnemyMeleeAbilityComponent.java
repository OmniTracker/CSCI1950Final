package finalgame.engineAdditions;

import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import support.Vec2d;

public class EnemyMeleeAbilityComponent extends EnemyRangedAbilityComponent{
	
	protected Vec2d _middleSwordLoc;
	
	public EnemyMeleeAbilityComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
		super(go, gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown, range);

		_damage = 20;
		_knockback = 30;
		_middleSwordLoc = new Vec2d(0,0);
		
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
	}
	
	@Override
	public void onHit(GameObject hitObject) {
		if(hitObject.hasComponent("HEALTH")) {
			HealthComponent hp = (HealthComponent)hitObject.getComponent("HEALTH");
			hp.takeDamage(_damage);
		}
		if(hitObject.hasComponent("TRANSFORM")) {
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
		return _middleSwordLoc;
	}

}
