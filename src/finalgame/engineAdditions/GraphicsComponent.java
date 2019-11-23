package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public abstract class GraphicsComponent extends Component{
	
	public GraphicsComponent(GameObject go) {
		super(go);
	}

	public abstract void draw(GraphicsContext g, Affine af);
	
}
