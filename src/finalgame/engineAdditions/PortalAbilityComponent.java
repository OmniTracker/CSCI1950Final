package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import support.Vec2d;

public class PortalAbilityComponent extends AnimateAbilityComponent{
	
	private Image _imgCast;
	private Vec2d _siteLoc;
	private boolean _teleporting;
	private double _teleportingCounter;
	private boolean _delayDone;
	private double _delayCounter;
	private boolean _teleported;
	
	public PortalAbilityComponent(GameObject go, Image imgSite, Image imgCast, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim,
			Vec2d animation_increment, int numFrames, double active_time, double cooldown) {
		super(go, imgSite, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		_imgCast = imgCast;
		_siteLoc = null;
		_teleporting = false;
		_teleportingCounter = 0;
		_delayDone = false;
		_delayCounter = 0;
	}
	
	@Override
	public void activateAbility() {
		if(!_active) {
			_active = true;
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			_siteLoc = currTransform.getLoc().plus(-10., currTransform.getDim().y-15);
		}
		else if(_delayDone && _active) {
			_teleporting = true;
		}
	}
	
	@Override
	public void tick(long nanosSinceLastTick) {
		if(_active && !_delayDone) {
			_delayCounter += (double)nanosSinceLastTick/1000000000.0;
			if(_delayCounter > 0.5) {
				_delayDone = true;
				_delayCounter = 0;
			}
		}
		else if(_teleporting) {
			_teleportingCounter +=(double)nanosSinceLastTick/1000000000.0;
			if(_teleportingCounter > 0.2) {
				_active = false;
				_delayCounter = 0;
				_delayDone = false;
				_teleporting = false;
				_teleported = false;
				_teleportingCounter = 0;
			}
		}
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			g.drawImage(_img,_imageLoc.x, _imageLoc.y, 
					_imageDim.x, _imageDim.y,_siteLoc.x,_siteLoc.y,_dim.x,_dim.y);
		}
		if(_teleporting) {
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			g.drawImage(_imgCast,0, 0, 400, 500,
					currTransform.getLoc().x-10,currTransform.getLoc().y-10,currTransform.getDim().x+20,currTransform.getDim().y+20);
			if(_teleportingCounter >0.15 && !_teleported) {
				currTransform.setLoc(_siteLoc.minus(-10., currTransform.getDim().y));
				_teleported = true;
			}
		}
	}
	
}
