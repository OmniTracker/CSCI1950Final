package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class TickComponent extends Component{

	public TickComponent(GameObject go) {
		super(go);
	}

	@Override
	public void tick(long nanosSinceLastTick) {
		
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		
	}

}
