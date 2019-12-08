package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class ScratchAbilityAnimationComponent extends AnimateAbilityComponent{

	public ScratchAbilityAnimationComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc, Vec2d dim, Vec2d animation_increment,
			int numFrames, double active_time, double cooldown) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown);
	}

	
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			//continue showing ability animation
			AnimateGraphicsComponent curr = (AnimateGraphicsComponent)_go.getComponent("ANIMATE");
			int facing = curr.getDir();//1up, 2left, 3down, 4right
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d charLoc = currTransform.getLoc();
			Vec2d abilityLoc = new Vec2d(charLoc.x,charLoc.y);
			Vec2d charDim = currTransform.getDim();
			switch (facing) {
				case 4:
					abilityLoc = charLoc.plus(new Vec2d(0,-_dim.y));
					break;
				case 2:
					abilityLoc = charLoc.plus(new Vec2d(-_dim.x,0));
					break;
				case 1:
					abilityLoc = charLoc.plus(new Vec2d(0,charDim.y+5));
					break;
				case 3:
					abilityLoc = charLoc.plus(new Vec2d(charDim.x+5,0));
					break;
				default:
					break;
			}
			g.drawImage(_img,_imageLoc.x+(_currFrame%5)*_animationIncrement.x, _imageLoc.y + (_currFrame/5)*_animationIncrement.y, 
					_imageDim.x, _imageDim.y,abilityLoc.x,abilityLoc.y,_dim.x,_dim.y);
		}
	}
		
}
