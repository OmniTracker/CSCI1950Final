package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public abstract class Component {
	protected GameObject _go;
	
	public Component(GameObject go) {
		_go = go;
	}
		
	public abstract void tick(long nanosSinceLastTick);
	public abstract void draw(GraphicsContext g, Affine af);
	public void remove() {
		
	}
}
