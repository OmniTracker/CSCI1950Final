package finalgame.engineAdditions;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class HealthComponent extends Component{

	private double _currentHealth;
	private double _maxHealth;
	private boolean _showHealthBar;
	
	public HealthComponent(GameObject go, double hp) {
		super(go);
		_currentHealth = hp;
		_maxHealth = hp;
		_showHealthBar = true;
	}

	public void takeDamage(double amt) {
		_currentHealth = Math.max(0, _currentHealth-amt);
		if(_currentHealth == 0) {
			this.death();
		}
	}
	
	public void heal(double amt) {
		_currentHealth = Math.min(_currentHealth+amt, _maxHealth);
	}
	
	public void setHealthVisibility(boolean show) {
		_showHealthBar = show;
	}
	
	@Override
	public void tick(long nanosSinceLastTick) {
		
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
		if(_showHealthBar) {
			if(_go.hasComponent("TRANSFORM")) {
				TransformComponent temp = (TransformComponent)_go.getComponent("TRANSFORM");
				Vec2d loc = temp.getLoc();
				double width = temp.getDim().x;
				g.setFill(Color.RED);
				g.fillRect(loc.x-2.5,loc.y - 10, width+5, 4);
				g.setFill(Color.GREEN);
				g.fillRect(loc.x-2.5,loc.y - 10, (width+5)*(_currentHealth/_maxHealth), 4);
			}
		}
	}
	
	public double getHealth() {
		return _currentHealth;
	}
	
	public void death() {
		//_gw.dieObject(_go);
	}

}
