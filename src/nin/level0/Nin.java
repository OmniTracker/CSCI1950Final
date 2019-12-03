package nin.level0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.Application;
import engine.Screen;

public class Nin extends Screen {
	private NinViewport _ninViewport = null;
	public Nin(Application app) {
		super(app);
		
		this.setNinViewport( new NinViewport(app, 
				new NinGameWorld(app), 
				new Vec2d(0,0), 
				new Vec2d(0,0)));
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getNinViewport().onTick(nanosSincePreviousTick);
	}

	
	public void onDraw(GraphicsContext g) {
		this.getNinViewport().onDraw(g);	
		this.getApplication().borders(g, Color.BLACK);
	}
	public void onKeyPressed(KeyEvent e) {
		this.getNinViewport().onKeyPressed(e);
	}
	public void onMouseClicked(MouseEvent e) {
		this.getNinViewport().onMouseClicked(e);
		
		System.out.print("nin \n");
	}
	private void setNinViewport(NinViewport _ninViewport) {
		this._ninViewport = _ninViewport;
	}
	private NinViewport getNinViewport() {
		return _ninViewport;
	}
}
