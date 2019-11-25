package finalgame.engineAdditions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class GraphicsSystem extends GameSystem{
	
	public GraphicsSystem(){
		super();
	}
	public void onDraw(GraphicsContext g, Affine af) {
		for(int i = 0; i<_objects.size();i++) {
			_objects.get(i).draw(g, af);
		}
	}
	
	public void addObject(GameObject go) {
		if(go.hasComponent("DRAW")) {
			_objects.add(go);
		}
	}
}
