package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class UnitGraphicsComponent extends GraphicsComponent{

	protected Vec2d _imgLoc;
	protected Vec2d _imgDim;
	protected Image _img;
	
	public UnitGraphicsComponent(GameObject go, Image img, Vec2d imgLoc, Vec2d imgDim) {
		super(go);
		// TODO Auto-generated constructor stub
		_img = img;
		_imgLoc = imgLoc;
		_imgDim = imgDim;
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		TransformComponent temp = ((TransformComponent)_go.getComponent("TRANSFORM"));
		g.drawImage(_img,_imgLoc.x, _imgLoc.y, _imgDim.x, _imgDim.y,temp.getLoc().x,temp.getLoc().y,temp.getDim().x,temp.getDim().y);
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		// TODO Auto-generated method stub
		
	}
}
