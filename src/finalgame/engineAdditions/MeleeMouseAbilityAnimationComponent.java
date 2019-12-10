package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import support.Vec2d;

public class MeleeMouseAbilityAnimationComponent extends MouseAbilityAnimationComponent{

	public MeleeMouseAbilityAnimationComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, Vec2d loc,
			Vec2d dim, Vec2d animation_increment, int numFrames, double active_time, double cooldown, double range) {
		super(go, img, imgLoc, imgDim, loc, dim, animation_increment, numFrames, active_time, cooldown, range);
		_numFrames = 36;
		
		
	}
	
	private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        
    }
	
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_active) {
			TransformComponent currTransform = (TransformComponent) _go.getComponent("TRANSFORM");
			Vec2d src = currTransform.getLoc().plus(currTransform.getDim().smult(0.5));
			Vec2d swordLoc = src.plus(_dir.smult(_range));			
			g.drawImage(_img,_imageLoc.x, _imageLoc.y, 
					_imageDim.x, _imageDim.y,swordLoc.x,swordLoc.y,_dim.x,_dim.y);
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
