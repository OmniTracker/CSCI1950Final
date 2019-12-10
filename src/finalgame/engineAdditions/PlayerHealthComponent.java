package finalgame.engineAdditions;

public class PlayerHealthComponent extends HealthComponent{

	private boolean _coolingDown;
	private double _cooldown;
	private double _cooldownCounter;
	private double _potionStrength;
	
	public PlayerHealthComponent(GameObject go, double hp) {
		super(go, hp);
		// TODO Auto-generated constructor stub
		_cooldown = 30;
		_cooldownCounter = _cooldown;
		_coolingDown = false;
	}
	
	public void usePotion() {
		if(!_coolingDown) {
			this.heal(_potionStrength);
			System.out.println("USED POTION");
			_coolingDown = true;
		}
	}
	
	public void setPotionStrength(double healthAmt) {
		_potionStrength = healthAmt;
	}
	
	@Override
	public void tick(long nanosSinceLastTick) {
		if(_coolingDown) {
			_cooldownCounter -= (double)nanosSinceLastTick/1000000000.0;
			if(_cooldownCounter <= 0) {
				_cooldownCounter = _cooldown;
				_coolingDown = false;
			}
		}
	}
	
	public double getCurrCooldown() {
		return _cooldownCounter;
	}
}
