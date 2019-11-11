package wizard.utils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import support.application.Vec2i;
import javafx.scene.image.Image;
import engine.Application;
import engine.ai.astar.AStarSearch;
import engine.utility.Factory;

public class WIZMapDelegate {
	// Map 0 Data
	private ArrayList<String>    _level0Map = new ArrayList<String>(); 
	private ArrayList<String> _level0AIGrid = new ArrayList<String>();
	// Map 1 Data
	private ArrayList<String> _level1Map    = new ArrayList<String>();
	private ArrayList<String> _level1AIGrid = new ArrayList<String>();
	// Images used to create the maps
	private Image                   _levelO = null;
	private Image                     _lava = null;
	private Image                   _level1 = null;
	private Image                     _tron = null;

	// level map setup
	private Vec2i         _level0Dimensions = null;
	private Vec2i         _level1Dimensions = null;

	// AStar Data Grid
	private Integer[][]    _level0AStarGrid = null;  
	private Integer[][]    _level1AStarGrid = null;

	// A Star algorithm for maps.
	private AStarSearch    _level0AStarSearch= null;  
	private AStarSearch    _level1AStarSearch= null;

	private boolean DEBUG = true;

	@SuppressWarnings("static-access")
	public WIZMapDelegate(Application app) throws MalformedURLException {
		Factory factory = app.getFactory(); 
		// This information is only need for actually generating the map itself.
		this.setLevel0Map(Factory.level0Map());
		this.setLevel1Map(factory.level1Map());
		// Setting the AStar algorithm.
		// Level AI Grids
		this.setLevel0AIGrid(factory.level0AIGrid());
		this.setLevel1AIGrid(factory.level1AIGrid());
		// Coordinates

		// Level 0
		Integer x0 = this.getLevel0AIGrid().size(); 
		Integer y0 = this.getLevel0AIGrid().get(0).replace(",", "").length();
		
		// Level 1
		Integer x1 = this.getLevel1AIGrid().size(); 
		Integer y1 = this.getLevel1AIGrid().get(0).replace(",", "").length(); 
		// Set game dimensions
		this.setLevel0Dimensions(new Vec2i(x0,y0));
		this.setLevel1Dimensions(new Vec2i(x1,y1));
		
		// Fill grids
		this.setLevel0AStarGrid(new Integer[x0][y0]);
		this.setLevel1AStarGrid(new Integer[x1][y1]);
		
		// Set AI grid 0		
		for (int row0 = 0; row0 < x0; row0++) 
		{	
			String[] temp0 =   this.getLevel0AIGrid().get(row0).replace(",", "").split(""); 
			for (int col0 = 0; col0 < y0; col0++)  {	
				this.getLevel0AStarGrid()[row0][col0] = Integer.valueOf(temp0[col0]);
				if (DEBUG) {
					System.out.print( this.getLevel0AStarGrid()[row0][col0] ) ;
				}
			}	
			if (DEBUG) {
				System.out.print("\n");
			}
		}
		if (DEBUG) {
			System.out.print("\n\n\n\n");
		}
		// Set AI grid 1 
		for (int row1 = 0; row1 < x1; row1++) {
			String[] temp1 =   this.getLevel1AIGrid().get(row1).replace(",", "").split("");
			for (int col1 = 0; col1 < y1; col1++) {
				this.getLevel1AStarGrid()[row1][col1] = Integer.valueOf(temp1[col1]); 
				if (DEBUG) {
					System.out.print( this.getLevel1AStarGrid()[row1][col1] ) ;
				}
			}
			if (DEBUG) {
				System.out.print("\n");
			}
		}
		this.setLevel0AStarSearch( new AStarSearch (this.getLevel0AStarGrid(),this.getLevel0Dimensions().x,this.getLevel0Dimensions().y));
		this.setLevel1AStarSearch( new AStarSearch (this.getLevel1AStarGrid(),this.getLevel1Dimensions().x,this.getLevel1Dimensions().y));
		//  Images used for game board.
		this.setLevelO(factory.getLevel0Tiles());
		this.setLevel1(factory.getLevel1Tiles());
		this.setTron(factory.getTron());
		this.setLava(factory.getLava());		
	}
	public ArrayList<String> getLevel0Map() {
		return _level0Map;
	}
	private void setLevel0Map(ArrayList<String> _level0Map) {
		this._level0Map = _level0Map;
	}
	private ArrayList<String> getLevel0AIGrid() {
		return _level0AIGrid;
	}
	private void setLevel0AIGrid(ArrayList<String> _level0AIGrid) {
		this._level0AIGrid = _level0AIGrid;
	}
	public ArrayList<String> getLevel1Map() {
		return _level1Map;
	}
	private void setLevel1Map(ArrayList<String> _level1Map) {
		this._level1Map = _level1Map;
	}
	private ArrayList<String> getLevel1AIGrid() {
		return _level1AIGrid;
	}
	private void setLevel1AIGrid(ArrayList<String> _level1AIGrid) {
		this._level1AIGrid = _level1AIGrid;
	}
	public Image getLevelO() {
		return _levelO;
	}
	private void setLevelO(Image _levelO) {
		this._levelO = _levelO;
	}
	public Image getLava() {
		return _lava;
	}
	private void setLava(Image _lava) {
		this._lava = _lava;
	}
	public Image getLevel1() {
		return _level1;
	}
	private void setLevel1(Image _level1) {
		this._level1 = _level1;
	}
	public Image getTron() {
		return _tron;
	}
	private void setTron(Image _tron) {
		this._tron = _tron;
	}
	public AStarSearch getLevel0AStarSearch() {
		return _level0AStarSearch;
	}
	private void setLevel0AStarSearch(AStarSearch _level0AStarSearch) {
		this._level0AStarSearch = _level0AStarSearch;
	}
	public AStarSearch getLevel1AStarSearch() {
		return _level1AStarSearch;
	}
	private void setLevel1AStarSearch(AStarSearch _level1AStarSearch) {
		this._level1AStarSearch = _level1AStarSearch;
	}
	private Vec2i getLevel1Dimensions() {
		return _level1Dimensions;
	}
	private void setLevel1Dimensions(Vec2i _level1Dimensions) {
		this._level1Dimensions = _level1Dimensions;
	}
	private Vec2i getLevel0Dimensions() {
		return _level0Dimensions;
	}
	private void setLevel0Dimensions(Vec2i _level0Dimensions) {
		this._level0Dimensions = _level0Dimensions;
	}
	private Integer[][] getLevel0AStarGrid() {
		return _level0AStarGrid;
	}
	private void setLevel0AStarGrid(Integer[][] _level0AStarGrid) {
		this._level0AStarGrid = _level0AStarGrid;
	}
	private Integer[][] getLevel1AStarGrid() {
		return _level1AStarGrid;
	}
	private void setLevel1AStarGrid(Integer[][] _level1AStarGrid) {
		this._level1AStarGrid = _level1AStarGrid;
	}
}
