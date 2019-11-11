package nin.level0;

import support.Vec2d;
import nin.utils.NINDelegateContainer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.Screen;

public class Nin extends Screen {
	private NinViewport _ninViewport 		= null;
	private NinGameWorld _ninGameWorld 		= null;
	public Nin(Application app) {
		super(app);
		this.setNINGameWorld(new NinGameWorld(this.getApplication()));
		this.getNINGameWorld().setNINDelegateContainer(new NINDelegateContainer(this.getApplication()));
		this.setNINViewport(new NinViewport(this.getApplication(), 
				this.getNINGameWorld(),
				new Vec2d(0,0), 
				new Vec2d(0,0)));	
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getNINViewport().onTick(nanosSincePreviousTick);
	}

	public void onMouseDragged(MouseEvent e) {
		this.getNINViewport().onMouseDragged(e);		
	}

	public void onDraw(GraphicsContext g) {
		this.getNINViewport().onDraw(g);
		this.getApplication().borders(g, Color.BLACK);
	}
	public void onKeyPressed(KeyEvent e) {
		this.getNINViewport().onKeyPressed(e);
	}
	public void onMouseClicked(MouseEvent e) {
		this.getNINViewport().onMouseClicked(e);
	}

	public void onShutdown() {
		this.getNINViewport().onShutdown();
	}

	public void onStartup() {
		// Initialize these upon start-up
		this.getNINViewport().onStartup();
	}


	private NinViewport getNINViewport() {
		return _ninViewport;
	}
	private void setNINViewport(NinViewport _ninViewport) {
		this._ninViewport = _ninViewport;
	}
	private NinGameWorld getNINGameWorld() {
		return _ninGameWorld;
	}
	private void setNINGameWorld(NinGameWorld _ninGameWorld) {
		this._ninGameWorld = _ninGameWorld;
	}
}
