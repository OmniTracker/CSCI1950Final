package engine.finalai;

public abstract class GameState {

	private double _cost;
	
	public GameState() {
	}
	
	// Check if same state.
	public abstract boolean compare(GameState other);
	
	// Get cost so far
	public double getCost() {
		return _cost;
	}
	
	// Set cost so far
	public void setCost(double d) {
		_cost = d;
	}
}
