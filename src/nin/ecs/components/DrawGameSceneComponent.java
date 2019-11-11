package nin.ecs.components;

import support.Vec2d;
import nin.level0.NinGameWorld;
import nin.utils.NINGameObjectDelagate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import engine.Application;
import engine.systems.Components;

public class DrawGameSceneComponent  extends Components {
	private Application _app;
	private NinGameWorld _gameWorld;
	private NINGameObjectDelagate _ninGameObjectDelagate = null;
	private Image _background = null;


	public DrawGameSceneComponent(Application app, NinGameWorld gameWorld) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
	}
	public void onDraw(GraphicsContext g) {

		this.drawBackground(g);
	}

	private void drawBackground (GraphicsContext g) {		
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d screenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();

		
		
		g.drawImage(this.getBackground(), 0, 0, 2100,1220, origin.x,origin.y,screenSize.x ,screenSize.y);	

	}

	public void onShutdown() {
	}
	public void onStartup() {
		this.setNINGameObjectDelagate(this.getGameWorld().getNINDelegateContainer().getNINGameObjectDelagate());
		this.setBackground(this.getNINGameObjectDelagate().getBackground());
	}	
	private NinGameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	private Application getApp() {
		return _app;
	}
	private void setApp(Application _app) {
		this._app = _app;
	}
	private NINGameObjectDelagate getNINGameObjectDelagate() {
		return _ninGameObjectDelagate;
	}
	private void setNINGameObjectDelagate(NINGameObjectDelagate _ninGameObjectDelagate) {
		this._ninGameObjectDelagate = _ninGameObjectDelagate;
	}
	private Image getBackground() {
		return _background;
	}
	private void setBackground(Image _background) {
		this._background = _background;
	}
}
