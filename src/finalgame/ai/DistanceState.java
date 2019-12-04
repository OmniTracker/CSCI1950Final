package finalgame.ai;

import finalgame.engineAdditions.GameObject;
import engine.GameWorld;
import engine.ai.Action;
import engine.ai.CopyWorld;
import engine.ai.GOAPState;
import engine.ai.GameState;
import support.Vec2d;

public class DistanceState extends GOAPState {

	private Vec2d loc;
	private Vec2d size;
	private CopyWorld _cw;
	
	public DistanceState(Action a, Vec2d location, Vec2d size, GameWorld w, GameObject o) {
		super(a);
		loc = location;
		this.size = size;
		_cw = new CopyWorld(w, o);
	}
	
	
	public Vec2d getLoc() {
		return loc;
	}
	
	public Vec2d getSize() {
		return size;
	}
	
	public CopyWorld getWorld() {
		return _cw;
	}

	@Override
	public boolean compare(GameState other) {
		DistanceState o = (DistanceState) other;
		if (this.loc.dist2(o.getLoc())<100) {
			return true;
		} else {
			return false;
		}
	}

}
