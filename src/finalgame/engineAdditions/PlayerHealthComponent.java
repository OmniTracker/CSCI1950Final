package finalgame.engineAdditions;

import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import finalgame.ui.FinalMenuBar;
import finalgame.ui.OptionsPanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import support.Vec2d;

public class PlayerHealthComponent extends HealthComponent{

	private boolean _coolingDown;
	private double _cooldown;
	private double _cooldownCounter;
	private double _potionStrength;
	private boolean _invulnerable;
	private Image _img;
	
	public PlayerHealthComponent(GameObject go, MainGamePlay gw,  double hp, Image img) {
		super(go,gw, hp);
		_cooldown = 30;
		//default potion strength is 50 hp
		_potionStrength = 50;
		_cooldownCounter = _cooldown;
		_coolingDown = false;
		_img=img;
		_invulnerable = false;
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
	public void takeDamage(double amt) {
		if(!_invulnerable) {
			_currentHealth = Math.max(0, _currentHealth-amt);
			if(_currentHealth == 0) {
				this.death();
			}
		}
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
		if(_coolingDown && _cooldownCounter > _cooldown-2) {
			int frameNumber = (int)((_cooldown - _cooldownCounter)/0.08);
			TransformComponent temp = (TransformComponent)_go.getComponent("TRANSFORM");
			g.drawImage(_img,192*(frameNumber%5), 192*(frameNumber/5), 
					192,192,temp.getLoc().x-temp.getDim().x/2,temp.getLoc().y-temp.getDim().y/2,temp.getDim().x*2,temp.getDim().y*2);
			
		}
	}
	
	public double getCurrCooldown() {
		return _cooldownCounter;
	}
	
	public void setInvulnerable(boolean invuln) {
		_invulnerable = invuln;
	}
	
	@Override
	public void death() {
//		_gw.dieObject(_go);
		FinalGameWorld tw = (FinalGameWorld)_gw.getParent();
		FinalMenuBar menu = tw.get_parent().getFinalViewport().getMenuBar();
		OptionsPanel opanel = (OptionsPanel)menu.getPanelViews().get(8);
		if (opanel.shouldUpdateHighScores(_gw.get_highScore())) {
			menu.setContextHolder(10);
		}
	}
}
