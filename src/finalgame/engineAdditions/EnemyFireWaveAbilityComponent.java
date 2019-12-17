package finalgame.engineAdditions;

import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.scene.image.Image;
import support.Vec2d;

public class EnemyFireWaveAbilityComponent extends FireWaveAbilityComponent {

	public EnemyFireWaveAbilityComponent(GameObject go, MainGamePlay gw, Image img, Vec2d imgLoc, Vec2d imgDim,
			Vec2d loc, Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown,
			double range) {
		super(go, gw, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown, range);
		_damage = 5;
	}
	
	@Override
	protected void spawnHitbox() {
		_activeHitboxObj = _gw.spawnEnemyAbilityHitbox(this);
	}
	
	public boolean canUse() {
		if(_coolingDown || _active) {
			return false;
		}
		return true;
	}

}
