package engine.utility;

import support.Vec2d;
import support.collision.Collision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CircleTraking {
	
	Collision _collision; 
	
	CircleTraking () {}

	public void DrawCircle (Image i, GraphicsContext g, Vec2d center, double radius, double inner, Color color1, Color color2) {
		g.setFill(color1);
		g.fillOval( (center.x - radius), center.y - radius , radius ,  radius);
		g.setFill(color2);
		g.fillOval( (center.x - radius) + ((radius - inner)/2), (center.y - radius) + ((radius - inner)/2) ,inner,inner);
		if (i != null) {
			g.drawImage(i, center.x - radius + 4, center.y - inner - 15 , radius - 5  , radius - 5  );
		}
	}
}
