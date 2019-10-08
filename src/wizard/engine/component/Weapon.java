package wizard.engine.component;

import support.Vec2d;
import javafx.scene.image.Image;

public class Weapon {
	
	private String _name; 
	private double _damage;
	private Vec2d _location;
	private Image _image;
	
	Weapon(Image image ,String name, double damage) {
		this.setName(name);
		this.setDamage(damage);
		this.setImage(image);
	}
	public double getDamage() {
		return _damage;
	}
	private void setDamage(double _damage) {
		this._damage = _damage;
	}
	public String getName() {
		return _name;
	}
	private void setName(String _name) {
		this._name = _name;
	}
	public Image getImage() {
		return _image;
	}
	private void setImage(Image _image) {
		this._image = _image;
	}
	public Vec2d getLocation() {
		return _location;
	}
	public void setLocation(Vec2d _location) {
		this._location = _location;
	}
}