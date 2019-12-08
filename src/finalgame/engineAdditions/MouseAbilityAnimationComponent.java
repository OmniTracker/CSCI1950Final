package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class MouseAbilityAnimationComponent extends AnimateAbilityComponent{
	
	private double _range;
	private boolean _showRange;
	
	private Vec2d _src;
	private Vec2d _dir;
	private Vec2d _activeBulletLoc;
	
	
	public MouseAbilityAnimationComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim,
			Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
//		, WizWorld gw  did take gameworld
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_range = range;
		_showRange = false;
		_src = null;
		_dir = null;
		_activeBulletLoc = null;
	}
	
	
	@Override
	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			
			//_gw.spawnProjectile(this);
			
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			_src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			PlayerInputComponent inputComp = (PlayerInputComponent) _go.getComponent("INPUT");
			double mouseXLoc = inputComp.getInput("MOUSE.X");
			double mouseYLoc = inputComp.getInput("MOUSE.Y");
			_dir = new Vec2d(mouseXLoc-_src.x, mouseYLoc-_src.y).normalize();
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
	
	public void setDim(Vec2d dim) {
		_dim = dim;
	}
	
	public Vec2d getBulletLoc() {
		if (_active) {
			return _activeBulletLoc.plus(_dim.sdiv(2));
		}
		else {
			return new Vec2d(-10,-10);
		}
	}
	
	public void hit() {
		_currFrame = 0;
		_active = false;
		_coolingDown = true;
		_activeCounter = 0;
		_activeBulletLoc = new Vec2d(-10,-10);
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
}
