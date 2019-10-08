package wizard.engine.component;

import engine.systems.Component;

public class DamageComponent extends Component {
	private double _maxHealth;
	private double _currentHealth;
	private boolean _isAlive = true;
	DamageComponent () {
		this.setComponentName("Damage");
	}
	public double getMaxHealth() {
		return _maxHealth;
	}
	public void setMaxHealth(double _maxHealth) {
		this._maxHealth = _maxHealth;
	}
	public double getCurrentHealth() {
		return _currentHealth;
	}
	public void setCurrentHealth(double _currentHealth) {
		this._currentHealth = _currentHealth;
	}
	public double getHealthPercent() {
		return(this.getCurrentHealth() / this.getMaxHealth()) * 10;
	}
	public boolean isAlive() {
		return _isAlive;
	}
	public void setAlive(boolean _isAlive) {
		this._isAlive = _isAlive;
	}	
	public void killNow() {
		this.setAlive(false);
	}
}