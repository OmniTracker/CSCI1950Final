package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class AnimateGraphicsComponent extends UnitGraphicsComponent{

	protected int _animateDir;
	protected int _animationLength;
	protected int _recentMove;
	protected int _frameCounter;
	protected Vec2d _animationIncrement;
	protected boolean _decrement;
	
	public AnimateGraphicsComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim, int animation_length, Vec2d animation_increment) {
		super(go, img, imgLoc, imgDim);
		_animateDir = 0;
		_recentMove = 1;
		_frameCounter = 0;
		_decrement = false;
		_animationLength = animation_length;
		_animationIncrement = animation_increment;
	}
	
	@Override
	public void draw(GraphicsContext g, Affine af) {

		TransformComponent temp = ((TransformComponent)_go.getComponent("TRANSFORM"));
		//_imgLoc.x, _imgLoc.y
		if(_animateDir == 0) {
			_decrement = false;
			_frameCounter = 0;
			g.drawImage(_img,_imgLoc.x, _imgLoc.y+_animationIncrement.y*(_recentMove-1), 
					_imgDim.x, _imgDim.y,temp.getLoc().x,temp.getLoc().y,temp.getDim().x,temp.getDim().y);
		}
		else {//directional movement
			if(_frameCounter > _animationLength-1) {
				_decrement = true;
			}
			g.drawImage(_img,_imgLoc.x + (_animationLength-_frameCounter) * _animationIncrement.x, _imgLoc.y+_animationIncrement.y*(_animateDir-1), 
					_imgDim.x, _imgDim.y,temp.getLoc().x,temp.getLoc().y,temp.getDim().x,temp.getDim().y);
			if(_decrement) {
				_frameCounter--;
				if(_frameCounter == 0) {
					_decrement = false;
				}
			}
			else {
				_frameCounter++;
			}
		}
	}
	
	public void setAnimate(int i) {
		if(i>0) {
			_recentMove = i;
		}
		_animateDir = i;
	}

}
