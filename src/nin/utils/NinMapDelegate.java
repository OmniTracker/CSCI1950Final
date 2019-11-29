package nin.utils;

import java.util.ArrayList;

import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectDelegate;
import engine.utility.Factory;

public class NinMapDelegate extends GameObjectDelegate {

	private ArrayList<GameObject> _gameBoardPlatforms = null; 

	public NinMapDelegate(Application app) {
		super(app);
		this.setGameBoardPlatforms( new ArrayList<GameObject>() );
	}

	public void initPlatform () {
		// Make platform
		GameObject platform = new GameObject();
		this.getFactory();
		platform.getData().setImage( Factory.getNinBricks());
		platform.getData().setPosition(new Vec2d(0,0));
		platform.getData().setImageSize(new Vec2d(48,48));
		platform.getData().setImageStart(new Vec2d(0,0));
		platform.getData().setImageGameSize(new Vec2d(60,60));
		platform.getData().setBox(new AABShape(platform.getData().getPosition(), new Vec2d(60,60)));
		this.getGameBoardPlatforms().add(platform);
		
		
		
		
	}

	public ArrayList<GameObject> getGameBoardPlatforms() {
		return _gameBoardPlatforms;
	}


	private void setGameBoardPlatforms(ArrayList<GameObject> _gameBoardPlatforms) {
		this._gameBoardPlatforms = _gameBoardPlatforms;
	}

}
