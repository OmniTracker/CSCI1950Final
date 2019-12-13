package finalgame.engineAdditions;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;

public class EnemySystem extends GameSystem {

	MainGamePlay _world;
	
	public EnemySystem(MainGamePlay p) {
		super();
		_world = p;
	}

	@Override
	public void addObject(GameObject go) {
		if (go.getName().contains("ENEMY")) {
			_objects.add(go);
		}
	}
	
	public void onTick(float nanos) {
		if (_objects.size()==0) {
			_world.loadEnemies();
		}
	}
}
