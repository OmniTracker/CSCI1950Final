package finalgame.engineAdditions;

import support.Vec2d;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public abstract class AnimateAbilityComponent extends Component{

	protected MainGamePlay _gw;
	protected GameObject _activeHitboxObj;
	
	protected Image _img;
	protected Vec2d _imageLoc;
	protected Vec2d _imageDim;
	protected Vec2d _loc;
	protected Vec2d _dim;
	protected Vec2d _animationIncrement;	
	protected int _numFrames;
	protected int _currFrame;
	protected double _activeTime;
	protected double _activeCounter;
	protected double _cooldown;
	protected double _cooldownCounter;
	
	protected boolean _active;
	protected boolean _coolingDown;
	
	
	public AnimateAbilityComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown) {
		super(go);
		_gw = gw;
		_img = img;
		_imageLoc = imgLoc;
		_imageDim = imgDim;
		_animationIncrement = animation_increment;
		
		_activeHitboxObj = null;
		
		//values specific to the ability, location relative to character using ability and dimensions of the drawn image
		_loc = loc;
		_dim = dim;
		
		_numFrames = numFrames;
		_currFrame = 0;
		
		_cooldown = cooldown;
		_cooldownCounter = cooldown;
		_coolingDown = false;
		_activeTime = active_time;
		_activeCounter = 0.;
		_active = false;
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
	
	protected void spawnHitbox() {
		_activeHitboxObj = _gw.spawnAbilityHitbox(this);
	}
	
	protected void removeHitBox() {
		try {
			_gw.removeObject(_activeHitboxObj);
		} catch(NullPointerException e) {
		}
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		
	}
	
	public double getCurrCooldown() {
		return _cooldownCounter;
	}
	
	public double getCooldown() {
		return _cooldown;
	}
	
	public boolean isCoolingDown() {
		return _coolingDown;
	}
	
	public boolean isActive() {
		return _active;
	}
	
	public abstract void onHit(GameObject hitObject);
	
	public abstract Vec2d getHitBoxDim();
	public abstract Vec2d getHitBoxLoc();
	public abstract int getHitboxType();
	
	@Override
	public void remove() {
		this.removeHitBox();
	}
	
}
