package finalgame.engineAdditions;
import engine.ai.GroupInformation;
import finalgame.ai.TestGI;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import support.Vec2d;

public class EnemySystem extends GameSystem {

	private MainGamePlay _world;
	private int level;
	private Vec2d spawnZoneLoc = new Vec2d(800,100);
	private Vec2d spawnZoneSize = new Vec2d(100,400);
	private EnemyFactory factory;
	
	public EnemySystem(MainGamePlay p) {
		super();
		_world = p;
		level = 0;
		factory = new EnemyFactory(_world, this);
	}

	@Override
	public void addObject(GameObject go) {
		if (go.getName().contains("ENEMY")) {
			_objects.add(go);
		}
	}
	
	public void onTick(float nanos) {
		if (_objects.size()==0) {
			TransformComponent p = (TransformComponent) _world.get_player().getComponent("TRANSFORM");
			if (p.getLoc().plus((p.getDim().sdiv(2))).x < 577) {
				spawnZoneLoc = new Vec2d(800,100);
				spawnZoneSize = new Vec2d(100,400);
			} else {
				spawnZoneLoc = new Vec2d(100,100);
				spawnZoneSize = new Vec2d(100,400);
			}
			
			level++;
			if (level%4==0) {
				this.createBoss();
			} else {
				this.createEnemies();
			}
		}
	}
	
	private void createEnemies() {
		
		TestGI gi = new TestGI();
		
		factory.createArcher(spawnZoneLoc, spawnZoneSize, gi);
		factory.createArcher(spawnZoneLoc, spawnZoneSize, gi);
		
		if (level%4>1) {
			factory.createMelee(spawnZoneLoc, spawnZoneSize, gi);
			factory.createHealer(spawnZoneLoc, spawnZoneSize, gi);
		}
		
		if (level%4>2) {
			factory.createMelee(spawnZoneLoc, spawnZoneSize, gi);
			factory.createHealer(spawnZoneLoc, spawnZoneSize, gi);
		}
	}
	
	private void createBoss() {
		factory.createBoss(spawnZoneLoc, spawnZoneSize, new TestGI(), this);
	}
	
	public void createMinions(GroupInformation gi, GameObject boss) {
		factory.createArcher(((TransformComponent) boss.getComponent("TRANSFORM")).getLoc().plus(new Vec2d(0,100)), new Vec2d(0,0), gi);
		factory.createArcher(((TransformComponent) boss.getComponent("TRANSFORM")).getLoc().minus(new Vec2d(0,100)), new Vec2d(0,0), gi);
		factory.createHealer(((TransformComponent) boss.getComponent("TRANSFORM")).getLoc().plus(new Vec2d(100,0)), new Vec2d(0,0), gi);
		factory.createMelee(((TransformComponent) boss.getComponent("TRANSFORM")).getLoc().minus(new Vec2d(100,0)), new Vec2d(0,0), gi);
	}
	
	public int getRound() {
		return level;
	}
}
