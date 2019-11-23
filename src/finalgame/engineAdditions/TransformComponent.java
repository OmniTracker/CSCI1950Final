package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.transform.Affine;
import javafx.scene.canvas.GraphicsContext;

public class TransformComponent extends Component{

	private Vec2d _loc;
	private Vec2d _dim;
	private double _scale;
	
	public TransformComponent(GameObject go, Vec2d loc,Vec2d dim, double scale) {
		super(go);
		_loc = loc;
		_scale = scale;
		_dim = dim;
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		// TODO Auto-generated method stub
		
	}
	
	public void move(Vec2d dist) {
		_loc = _loc.plus(dist);
	}
	
	public Vec2d getLoc() {
		return _loc;
	}
	
	public void setLoc(Vec2d loc) {
		_loc = loc;
	}
	
	public Vec2d getDim() {
		return _dim;
	}
	
	public void setDim(Vec2d dim) {
		_dim = dim;
	}
	
	public void setScale(double scale) {
		_scale = scale;
	}
	
	public double getScale() {
		return _scale;
	}
	
}
