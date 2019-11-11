package engine.systems; 

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class Components {
	private String _componentName;
	public Components () {
		// Each component will specify it's own name. This value 
		// doesn't need to be passed into constructor
	}
	public void onTick(long nanosSincePreviousTick) {
	}
	public String getComponentName() {
		return _componentName;
	}
	public void setComponentName(String _componentName) {
		this._componentName = _componentName;
	}
	public void onDraw(GraphicsContext g)  {
	}
	public void onKeyPressed(KeyEvent e) {
	}
}





