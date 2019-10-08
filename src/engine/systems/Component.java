package engine.systems; 

public class Component {
	private String _componentName;
	public Component () {
		// Each component will specify it's own name. This value 
		// doesn't need to be passed into constructor
	}
	public String getComponentName() {
		return _componentName;
	}
	public void setComponentName(String _componentName) {
		this._componentName = _componentName;
	}
}





