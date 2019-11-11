package engine.ai.astar;

public class AStarCell {

    private int _parentI; 
    private int _parentJ; 
    // f = g + h 
    private double _f;
    private double _g; 
    private double _h; 	
	AStarCell () {

	}
	int getParentI() {
		return _parentI;
	}
	void setParentI(int _parentI) {
		this._parentI = _parentI;
	}
	int getParentJ() {
		return _parentJ;
	}
	void setParentJ(int _parentJ) {
		this._parentJ = _parentJ;
	}
	double getF() {
		return _f;
	}
	void setF(double _f) {
		this._f = _f;
	}
	double getG() {
		return _g;
	}
	void setG(double _g) {
		this._g = _g;
	}
	double getH() {
		return _h;
	}
	void setH(double _h) {
		this._h = _h;
	}
}
