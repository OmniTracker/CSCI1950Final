package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import support.Vec2d;

public class TeleportAbilityComponent extends AnimateAbilityComponent{
	
	private boolean _toTeleport;
	private double _travelDist;

	public TeleportAbilityComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim,
			Vec2d animation_increment, int numFrames, double active_time, double cooldown, double dist) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
		//will use active_time as casttime for the teleport and only update location at the end or right before the end
		//of the active time;
		_travelDist = dist;
	}
	
	@Override
	public void activateAbility() {
		if(!_coolingDown && !_active) {
			_active = true;
			_toTeleport = true;
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

			Vec2d charLoc = currTransform.getLoc();
			Vec2d charDim = currTransform.getDim();
			
			if(_currFrame > 28 && _toTeleport) {
				//update player location
				AnimateGraphicsComponent curr = (AnimateGraphicsComponent)_go.getComponent("ANIMATE");
				int facing = curr.getDir();//1up, 2left, 3down, 4right
				Vec2d tpDir = new Vec2d(0, 0);
				switch (facing) {
					case 4:
						tpDir = new Vec2d(0,-1);
						break;
					case 2:
						tpDir = new Vec2d(-1,0);
						break;
					case 1:
						tpDir = new Vec2d(0,1);
						break;
					case 3:
						tpDir = new Vec2d(1,0);
						break;
					default:
						break;
				}
				currTransform.move(tpDir.smult(_travelDist));
				_toTeleport = false;
			}
			
			g.drawImage(_img,_imageLoc.x+(_currFrame%8)*_animationIncrement.x, _imageLoc.y + (_currFrame/8)*_animationIncrement.y, 
					_imageDim.x, _imageDim.y,charLoc.x-10,charLoc.y-10,charDim.x+20,charDim.y+20);
		}
	}
	
}
