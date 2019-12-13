package finalgame.engineAdditions;

import support.debugger.support.shapes.Shape;

public abstract class AbilityCollisionComponent extends CollisionComponent{

	protected AnimateAbilityComponent _ability;
	protected int _numTargets;
	
	public AbilityCollisionComponent(GameObject go, Shape shape, AnimateAbilityComponent ability, int numTargets) {
		super(go, shape);
		_ability = ability;
		_numTargets = numTargets;
	}
	
	public abstract void hit(GameObject go);

}
