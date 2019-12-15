package finalgame.engineAdditions;

import engine.ai.BehaviorTree;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class AIBehaviorComponent extends Component{

	private BehaviorTree _bt;
	
	public AIBehaviorComponent(GameObject go, BehaviorTree bt) {
		super(go);
		_bt = bt;
	}
	
	@Override
	public void tick(long nanosSinceLastTick) {
		_bt.tick(nanosSinceLastTick);
	}

	@Override
	public void draw(GraphicsContext g, Affine af) {
	}

	public BehaviorTree getBT() {
		return _bt;
	}
}
