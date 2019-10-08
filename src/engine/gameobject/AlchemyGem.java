package engine.gameobject;

import java.util.ArrayList;

import engine.systems.Component;
import support.Vec2d;
import support.collision.CircleShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AlchemyGem {
	private Image _image = null;
	private Color _color = null;
	private Color _backColor = Color.GRAY; 
	private String _tag  = null;
	private Integer _gemIndex; 
	private Vec2d _location;
	private CircleShape _circleShape; 
	private ArrayList<Component> _component;
	private static final double RADIUS1 = 120;
	private static final double RADIUS2 = 100;
	private static final double CIRCLE_RADIUS = RADIUS2 - 30;
	// Initial Gems
	private static final Integer ELEMENT_1 = 0;   
	private static final Integer ELEMENT_2 = 1;  
	private static final Integer ELEMENT_3 = 2;  
	private static final Integer ELEMENT_4 = 3;

	private static final Integer ELEMENT_5 = 4;  
	private static final Integer ELEMENT_6 = 5;  
	private static final Integer ELEMENT_7 = 6;  

	AlchemyGem(Image image,String tag, Vec2d location) {
		this.setTag(tag);
		this.setImage(image);
		this.setLocation(location);
		this.setCircleShape(new CircleShape(location,CIRCLE_RADIUS));
		this.setComponents(new ArrayList<Component>());

		if (tag == "Forest") 
		{
			setGemIndex(ELEMENT_1);
			this.setGemColor(Color.BLUEVIOLET);
		} 
		else if (tag == "Heart") 
		{
			setGemIndex(ELEMENT_2); 
			this.setGemColor(Color.GREEN);
		}
		else if (tag == "Ocean") 
		{
			setGemIndex(ELEMENT_3); 
			this.setGemColor(Color.BLUE);
		}
		else if (tag == "ZeroLight") 
		{
			setGemIndex(ELEMENT_4);
			this.setGemColor(Color.PALEVIOLETRED);
		}
		else if (tag == "Midnight") 
		{
			setGemIndex(ELEMENT_5);
		} 
		else if (tag == "LizardEye") 
		{
			setGemIndex(ELEMENT_6);
			this.setGemColor(Color.PLUM);
		}
		else if (tag == "NightStorm") 
		{
			setGemIndex(ELEMENT_7);
			this.setGemColor(Color.DARKGREEN);
		}
	}

	public void drawGem(GraphicsContext g, Vec2d center)
	{
		this.getCircleShape().setCenter(center); 
		Vec2d imageCenter = new Vec2d (center.x + 60, center.y + 60); 
		if (this.getImage() != null) 
		{
			g.setFill(this.getBackColor());
			g.fillOval( (imageCenter.x - RADIUS1), imageCenter.y - RADIUS1 , RADIUS1 ,  RADIUS1);
			g.setFill(this.getGemColor());
			g.fillOval( (imageCenter.x - RADIUS1) + ((RADIUS1 - RADIUS2)/2), (imageCenter.y - RADIUS1) + ((RADIUS1 - RADIUS2)/2) ,RADIUS2,RADIUS2);
			g.drawImage(this.getImage(), imageCenter.x - RADIUS1 + 4, imageCenter.y - RADIUS2 - 15 , RADIUS1 - 5  , RADIUS1 - 5  );
		}
	}

	public void drawGem(GraphicsContext g) 
	{
		Vec2d center = this.getLocation();
		this.getCircleShape().setCenter(center); 
		Vec2d imageCenter = new Vec2d (center.x + 60, center.y + 60); 
		
		if (this.getImage() != null)
		{
			g.setFill(this.getBackColor());
			g.fillOval( (imageCenter.x - RADIUS1), imageCenter.y - RADIUS1 , RADIUS1 ,  RADIUS1);
			g.setFill(this.getGemColor());
			g.fillOval( (imageCenter.x - RADIUS1) + ((RADIUS1 - RADIUS2)/2), (imageCenter.y - RADIUS1) + ((RADIUS1 - RADIUS2)/2) ,RADIUS2,RADIUS2);
			g.drawImage(this.getImage(), imageCenter.x - RADIUS1 + 4, imageCenter.y - RADIUS2 - 15 , RADIUS1 - 5  , RADIUS1 - 5  );
		}
	}

	public Integer getGemIndex() {
		return this._gemIndex;
	}

	private void setGemIndex(Integer gemIndex) {
		this._gemIndex = gemIndex;
	}

	private Image getImage() {
		return _image;
	}

	private void setImage(Image _image) {
		this._image = _image;
	}

	private Color getGemColor() {
		return _color;
	}

	private void setGemColor(Color _color) {
		this._color = _color;
	}

	public String getTag() {
		return _tag;
	}

	private void setTag(String _tag) {
		this._tag = _tag;
	}

	private Color getBackColor() {
		return _backColor;
	}

	public Vec2d getLocation() {
		return _location;
	}

	public void setLocation(Vec2d _location) {
		this._location = new Vec2d( _location.x, _location.y);
	}

	CircleShape getCircleShape() {
		return _circleShape;
	}

	void setCircleShape(CircleShape _circleShape) {
		this._circleShape = _circleShape;
	}

	public void setBackColor(Color _backColor) {
		this._backColor = _backColor;
	}

	
	public void addComponent(Component c) { 
		this.getComponents().add(c);

	}
	
	public void removeComponent(Component c) {	
		this.getComponents().remove(c);
	}
	
	ArrayList<Component> getComponents() {
		return _component;
	}
	
	private void setComponents(ArrayList<Component> c) {
		this._component = c;
	}
}
