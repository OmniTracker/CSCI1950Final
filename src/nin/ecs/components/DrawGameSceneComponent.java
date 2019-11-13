package nin.ecs.components;

import java.util.HashMap;

import support.Vec2d;
import nin.level0.NinGameWorld;
import nin.utils.NINGameObjectDelagate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import engine.Application;
import engine.gameobject.GameObject;
import engine.systems.Components;

public class DrawGameSceneComponent  extends Components {
	private Application _app;
	private NinGameWorld _gameWorld;
	private NINGameObjectDelagate _ninGameObjectDelagate = null;
	private Image _background = null;
	private HashMap<Integer, GameObject> _platforms = null;
	private GameObject _character = null;


	public DrawGameSceneComponent(Application app, NinGameWorld gameWorld) {
		this.setApp(app);
		this.setGameWorld(gameWorld);
	}

	public void onDraw(GraphicsContext g) {
		if (this.getBackground() == null) {
			this.setBackground(this.getGameWorld()
					.getNINDelegateContainer()
					.getNINGameObjectDelagate()
					.getNINXMLParser()
					.readXMLParserBackgroundImage());
		}
		if (this.getPlatforms() == null) {
			this.setPlatforms(this.getGameWorld()
					.getNINDelegateContainer()
					.getNINGameObjectDelagate()
					.getNINXMLParser().readXMLParserPlatform());
		}

		if (this.getCharacter() == null) {
			this.setCharacter(this.getGameWorld()
					.getNINDelegateContainer()
					.getNINGameObjectDelagate()
					.getNINXMLParser().readXMLParserCharacter());
		}

		this.drawBackground(g);
		this.drawStationaryPlatform(g);
		this.drawMovingPlatform(g);
		this.drawCharacter(g);
	}
	private void drawBackground (GraphicsContext g) {		
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d screenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();
		g.setFill(Color.DARKBLUE);
		g.fillRect(origin.x,origin.y,screenSize.x ,screenSize.y);
		g.drawImage(this.getBackground(), 0, 0, 2100,1220, origin.x + 10,origin.y + 50 ,screenSize.x - 20 ,screenSize.y - 60);	
	}

	private void drawStationaryPlatform (GraphicsContext g) {
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d screenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();		
		g.drawImage(this.getPlatforms().get(1).getData().getImage(), 0, 0, 900,100, origin.x + 20,(origin.y + screenSize.y - 120) ,screenSize.x - 40,120);	
	}

	private void drawMovingPlatform (GraphicsContext g) {
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d screenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();	
		g.drawImage(this.getPlatforms().get(2).getData().getImage(), 0, 0, 590,150, origin.x + 200,(origin.y + screenSize.y - 600) ,(590 * .7),(150 * .6 ));
		g.drawImage(this.getPlatforms().get(3).getData().getImage(), 0, 170, 590,110, origin.x + 800,(origin.y + screenSize.y - 400) ,(590 * .7),(110 * .6));
		g.drawImage(this.getPlatforms().get(4).getData().getImage(), 0, 325, 590,150, origin.x + 500,(origin.y + screenSize.y - 200) ,(590 * .7),(150 * .6));
	}

	private void drawCharacter ( GraphicsContext g ) {
		Vec2d origin = this.getApp().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d screenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();	
		g.drawImage(this.getCharacter().getData().getImage(), 0, 0, 600,600, 200, 200, 200,200);
	}

	public void onShutdown() {
	}
	public void onStartup() {
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
	private HashMap<Integer, GameObject> getPlatforms() {
		return _platforms;
	}
	private void setPlatforms(HashMap<Integer, GameObject> _platforms) {
		this._platforms = _platforms;
	}

	private GameObject getCharacter() {
		return _character;
	}

	private void setCharacter(GameObject _character) {
		this._character = _character;
	}
}
