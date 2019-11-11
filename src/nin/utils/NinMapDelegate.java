package nin.utils;

import java.util.ArrayList;

import engine.Application;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectDelegate;

public class NinMapDelegate extends GameObjectDelegate {
	private ArrayList<GameObject> _gameBoardPlatforms = null; 	
	private NINFactory _ninFactory = null; 
	private NINGameObject _mainPlatform;
	private NINGameObject _movingPlatform1;
	private NINGameObject _movingPlatform2;
	private NINGameObject _movingPlatform3;
	
	public NinMapDelegate(Application app) {
		super(app);
		this.setGameBoardPlatforms( new ArrayList<GameObject>() );
	}
	public void initPlatform () {
		
	}
	public ArrayList<GameObject> getGameBoardPlatforms() {
		return _gameBoardPlatforms;
	}
	private void setGameBoardPlatforms(ArrayList<GameObject> _gameBoardPlatforms) {
		this._gameBoardPlatforms = _gameBoardPlatforms;
	}
	private  NINFactory getNINFactory() {
		return _ninFactory;
	}
	public void setNINFactory(NINFactory _ninFactory) {
		this._ninFactory = _ninFactory;
	}
	private NINGameObject getMainPlatform() {
		return _mainPlatform;
	}
	private void setMainPlatform(NINGameObject _mainPlatform) {
		this._mainPlatform = _mainPlatform;
	}
	private NINGameObject getMovingPlatform1() {
		return _movingPlatform1;
	}
	private void setMovingPlatform1(NINGameObject _movingPlatform1) {
		this._movingPlatform1 = _movingPlatform1;
	}
	private NINGameObject getMovingPlatform2() {
		return _movingPlatform2;
	}
	private void setMovingPlatform2(NINGameObject _movingPlatform2) {
		this._movingPlatform2 = _movingPlatform2;
	}
	private NINGameObject getMovingPlatform3() {
		return _movingPlatform3;
	}
	private void setMovingPlatform3(NINGameObject _movingPlatform3) {
		this._movingPlatform3 = _movingPlatform3;
	}
}
