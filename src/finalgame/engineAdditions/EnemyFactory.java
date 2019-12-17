package finalgame.engineAdditions;

import java.util.Random;

import engine.ai.BehaviorTree;
import engine.ai.GroupInformation;
import engine.ai.Selector;
import engine.ai.Sequencer;
import finalgame.ai.AttackEnemy;
import finalgame.ai.HealTarget;
import finalgame.ai.MeleeAttackEnemy;
import finalgame.ai.MoveTo;
import finalgame.ai.NotInRange;
import finalgame.ai.SpawnMinions;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import support.Vec2d;
import support.debugger.support.shapes.AABShapeDefine;

public class EnemyFactory {

	private MainGamePlay _world;
	private Random rng = new Random();
	private EnemySystem _es;
	
	public EnemyFactory(MainGamePlay w, EnemySystem es) {
		_world = w;
		_es = es;
	}
	
	public GameObject createArcher(Vec2d spawnLoc, Vec2d spawnSize, GroupInformation gi) {
		
		double x = rng.nextDouble()*(spawnSize.x) + spawnLoc.x;
		double y = rng.nextDouble()*(spawnSize.y) + spawnLoc.y;
		Vec2d spawn = new Vec2d(x,y);
		
		GameObject g = new GameObject("ENEMYARCHER");
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(3), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, spawn, new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000 + _es.getRound()*10));
		g.addComponent("ABILITY", new EnemyRangedAbilityComponent(g,_world, MainGamePlay.getShurikenImage(), new Vec2d(9,7),
				new Vec2d(123, 123), new Vec2d(0,0), new Vec2d(15,15), new Vec2d(0, 0),200, 1.0, 0, 300., 10));
		g.addComponent("TICK", new TickComponent(g));
		BehaviorTree bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(_world.get_player(),60000,25000));
		bt.addBehavior(1, new MoveTo(_world.get_player(), 60000,25000, 5));
		bt.addBehavior(0, new AttackEnemy());
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);
		gi.addObject(g);
		return g;
	}
	
	public GameObject createMelee(Vec2d spawnLoc, Vec2d spawnSize, GroupInformation gi) {
		double x = rng.nextDouble()*(spawnSize.x) + spawnLoc.x;
		double y = rng.nextDouble()*(spawnSize.y) + spawnLoc.y;
		Vec2d spawn = new Vec2d(x,y);
		
		GameObject g = new GameObject("ENEMY");
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(2), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, spawn, new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000 + _es.getRound()*10));
		g.addComponent("ABILITY", new EnemyMeleeAbilityComponent(g,_world, MainGamePlay.getWeaponImage(), new Vec2d(108,266),
				new Vec2d(46, 61), new Vec2d(0,0), new Vec2d(60,60), new Vec2d(0, 0),36, 1, 0, 70., 20));
		g.addComponent("TICK", new TickComponent(g));
		BehaviorTree bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(_world.get_player(),5000,0));
		bt.addBehavior(1, new MoveTo(_world.get_player(), 5000,0, 5));
		bt.addBehavior(0, new MeleeAttackEnemy(_world.get_player()));
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		gi.addObject(g);
		_world.getObjects().add(g);
		_world.addToSystems(g);
		
		return g;		
	}
	
	public GameObject createHealer(Vec2d spawnLoc, Vec2d spawnSize, GroupInformation gi) {
		double x = rng.nextDouble()*(spawnSize.x) + spawnLoc.x;
		double y = rng.nextDouble()*(spawnSize.y) + spawnLoc.y;
		Vec2d spawn = new Vec2d(x,y);
		
		GameObject g = new GameObject("ENEMYHEALER");
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(1), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, spawn, new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000 + _es.getRound()*10));
		g.addComponent("TICK", new TickComponent(g));
		BehaviorTree bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(g,100000,-1));
		bt.addBehavior(1, new MoveTo(g, 100000,-1, 6));
		bt.addBehavior(0, new HealTarget());
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(5, new NotInRange(_world.get_player(),Double.MAX_VALUE,50000));
		bt.addBehavior(5, new MoveTo(_world.get_player(), Double.MAX_VALUE,50000, 6));
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);
		gi.addObject(g);
		
		return g;
	}
	
	public GameObject createBoss(Vec2d spawnLoc, Vec2d spawnSize, GroupInformation gi, EnemySystem es) {
		double x = rng.nextDouble()*(spawnSize.x) + spawnLoc.x;
		double y = rng.nextDouble()*(spawnSize.y) + spawnLoc.y;
		Vec2d spawn = new Vec2d(x,y);
		GameObject g = new GameObject("ENEMYBOSS");
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(0), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, spawn, new Vec2d(80,120), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 10000 + _es.getRound()*20));
		g.addComponent("ABILITY", new EnemyMeleeAbilityComponent(g,_world, MainGamePlay.getWeaponImage(), new Vec2d(108,133),
				new Vec2d(46, 61), new Vec2d(0,0), new Vec2d(60,60), new Vec2d(0, 0),36, 1, 0, 100., 25));
		g.addComponent("TICK", new TickComponent(g));
		BehaviorTree bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0, new SpawnMinions(es));
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(2, new NotInRange(_world.get_player(),7500,0));
		bt.addBehavior(2, new MoveTo(_world.get_player(), 7500,0, 8));
		bt.addBehavior(0, new MeleeAttackEnemy(_world.get_player()));
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);
		gi.addObject(g);
		return g;
	}

}
