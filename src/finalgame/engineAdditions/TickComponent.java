package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public abstract class TickComponent extends Component{

	public TickComponent(GameObject go) {
		super(go);
	}

	@Override
	public abstract void tick(long nanosSinceLastTick);

	@Override
	public abstract void draw(GraphicsContext g, Affine af);

}
