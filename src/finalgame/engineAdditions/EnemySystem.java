package finalgame.engineAdditions;
import engine.ai.BehaviorTree;
import engine.ai.Selector;
import engine.ai.Sequencer;
import finalgame.ai.AttackEnemy;
import finalgame.ai.MoveTo;
import finalgame.ai.NotInRange;
import finalgame.ai.TestGI;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import support.Vec2d;
import support.debugger.support.shapes.AABShapeDefine;

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
			this.createEnemies();
		}
	}
	
	public void createEnemies() {
		double follow_dist = 5000;
		GameObject g = new GameObject("ENEMY");
		AnimateGraphicsComponent animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(1), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,100), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000));
		g.addComponent("ABILITY", new EnemyMeleeAbilityComponent(g,_world, _world.getWeaponImage(), new Vec2d(108,133),
				new Vec2d(46, 61), new Vec2d(0,0), new Vec2d(60,60), new Vec2d(0, 0),36, 1, 0, 70.));
		g.addComponent("TICK", new TickComponent(g));
		TestGI gi = new TestGI();
		BehaviorTree bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(_world.get_player(),follow_dist,0));
		bt.addBehavior(1, new MoveTo(_world.get_player(), follow_dist,0));
		bt.addBehavior(0, new AttackEnemy(_world.get_player()));
		gi.setLeader(_world.get_player());
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);
/*
		g = new GameObject("ENEMY");
		animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(1), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,200), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000));
		gi = new TestGI();
		bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(_world.get_player(),follow_dist,0));
		bt.addBehavior(1, new MoveTo(_world.get_player(), follow_dist,0));
		bt.addBehavior(0, new AttackEnemy(_world.get_player()));
		gi.setLeader(_world.get_player());
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);*/
		/*
		follow_dist = 50000;
		g = new GameObject("ENEMY");
		animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(1), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,300), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000));
		gi = new TestGI();
		bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(_world.get_player(),follow_dist,30000));
		bt.addBehavior(1, new MoveTo(_world.get_player(), follow_dist,30000));
		bt.addBehavior(0, new AttackEnemy(_world.get_player()));
		gi.setLeader(_world.get_player());
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);*/

		/*g = new GameObject("ENEMYHEALER");
		animate = new AnimateGraphicsComponent(g, _world.getPlayerImage(1), new Vec2d(54,0), new Vec2d(34,48), 2, new Vec2d(48,48));
		g.addComponent("DRAW", animate);
		g.addComponent("ANIMATE", animate);
		g.addComponent("TRANSFORM", new TransformComponent(g, new Vec2d(1000,400), new Vec2d(40,60), 1.0));
		g.addComponent("COLLISION", new AABCollisionComponent(g, new AABShapeDefine(new Vec2d(5.,5.),new Vec2d(10.,10.))));
		g.addComponent("HEALTH", new HealthComponent(g,_world, 1000));
		gi = new TestGI();
		bt = new BehaviorTree(new Selector(),gi,_world, g);
		bt.addBehavior(0,  new Sequencer());
		bt.addBehavior(1, new NotInRange(_world.get_player(),follow_dist,0));
		bt.addBehavior(1, new MoveTo(_world.get_player(), follow_dist,0));
		bt.addBehavior(0, new AttackEnemy(_world.get_player()));
		gi.setLeader(_world.get_player());
		g.addComponent("BEHAVIOR", new AIBehaviorComponent(g,bt));
		_world.getObjects().add(g);
		_world.addToSystems(g);*/
	}
}
