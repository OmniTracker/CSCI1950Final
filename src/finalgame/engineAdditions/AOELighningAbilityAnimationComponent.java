package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import support.Vec2d;

public class AOELighningAbilityAnimationComponent extends AnimateAbilityComponent{

	public AOELighningAbilityAnimationComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			//continue showing ability animation
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d charCenter = currTransform.getLoc().plus(currTransform.getDim().sdiv(2));
			Vec2d abilityLoc = charCenter.minus(_dim.sdiv(2));
			g.drawImage(_img,_imageLoc.x+(_currFrame)*_animationIncrement.x, _imageLoc.y, 
					_imageDim.x, _imageDim.y,abilityLoc.x,abilityLoc.y,_dim.x,_dim.y);
		}
	}
}
