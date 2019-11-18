package engine.ui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import support.Vec2d;
import support.collision.AABShape;

public class Button extends  UIElement {
	private String _fontName = ""; 
	private Image _buttonImage = null; 
	private AABShape _shape;
	Font _font; 
	public Button() {  }
	public Button(String text, Vec2d origin, Vec2d size,  Color color) {
		this.setText(text);
		this.setOrigin(origin);
		this.setSize(size);
		this.setColor(color);
		this.setShape(new AABShape(this.getOrigin(), this.getSize()));
	}
	public void draw(GraphicsContext g) {
		g.setFill(this.getColor());
		g.fillRect(this.getOrigin().x, this.getOrigin().y, this.getSize().x , this.getSize().y);
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30 ) );
		this.setShape(new AABShape(this.getOrigin(), this.getSize()));
		g.setTextAlign(TextAlignment.CENTER);
		Vec2d textOrigin = new Vec2d( (this.getOrigin().x + (this.getSize().x / 2)), ( this.getOrigin().y + ( this.getSize().y / 2 ) ) + 10 );
		g.fillText(this.getText(), textOrigin.x, textOrigin.y);
	}
	public void drawRounded(GraphicsContext g) {
		// Fill the button
		g.setFill(this.getColor());
		g.fillRoundRect(this.getOrigin().x, this.getOrigin().y, this.getSize().x , this.getSize().y,10,10);
		g.setFill(Color.BLACK);	
		// Reset the collision boxes
		this.setShape(new AABShape(this.getOrigin(), this.getSize()));
		if (!this.getFontName().isEmpty()) {
			// Set the 
			g.setFont(Font.font(this.getFontName()));
			g.setTextAlign(TextAlignment.CENTER);
			Vec2d textOrigin = new Vec2d( (this.getOrigin().x + (this.getSize().x / 2)), ( this.getOrigin().y + ( this.getSize().y / 2 ) ) + 5 );
			g.fillText(this.getText(), textOrigin.x, textOrigin.y);
		}
	}	
	public boolean clicked (MouseEvent e) {
		Vec2d mouse = new Vec2d (e.getSceneX(), e.getSceneY());  
		return this.isColliding(this.getShape(), mouse); 
	}
	public boolean isColliding(AABShape s1, Vec2d s2) {
		return pointRect(s2.x,s2.y, s1.getTopLeft().x,  s1.getTopLeft().y, s1.getSize().x, s1.getSize().y); 
	}
	boolean pointRect(double px, double py, double rx, double ry, double rw, double rh) {
		if (px >= rx &&        
				px <= rx + rw &&   
				py >= ry &&        
				py <= ry + rh) {  
			return true;
		}
		return false;
	}
	public AABShape getShape() {
		return _shape;
	}
	public void setShape(AABShape _shape) {
		this._shape = _shape;
	}
	public void setOrigin(Vec2d _origin) {
		this._origin = _origin;
		this.setShape(new AABShape(this.getOrigin(), this.getSize())); 
	}
	private String getFontName() {
		return _fontName;
	}
	public void setFontName(String _fontName) {
		this._fontName = _fontName;
	}
	public Image getButtonImage() {
		return _buttonImage;
	}
	public void setButtonImage(Image _buttonImage) {
		this._buttonImage = _buttonImage;
	}
}
