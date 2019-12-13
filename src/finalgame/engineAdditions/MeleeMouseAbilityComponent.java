package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import support.Vec2d;

public class MeleeMouseAbilityComponent extends MouseAbilityAnimationComponent{

	public MeleeMouseAbilityComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown, range);
	}
    
	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			Vec2d middleSwordLoc = src.plus(_dir.smult(_range));
			Vec2d swordDrawLoc = middleSwordLoc.minus(_dim.sdiv(2));
			double rotationAngle = 360 - _currFrame * 10;
			g.save();
			Rotate r = new Rotate(rotationAngle, middleSwordLoc.x, middleSwordLoc.y);
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
			g.strokeLine(src.x,src.y, sink.x, sink.y);
		}		
	}

}
