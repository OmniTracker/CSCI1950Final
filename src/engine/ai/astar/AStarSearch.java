package engine.ai.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import support.Vec2i;

public class AStarSearch {
	private Integer _maxRow;
	private Integer _maxCol;
	private Integer[][] _grid;
	private boolean _closedList[][]; 
	private List<Map<Integer,Vec2i>> _openList; 
	private AStarCell[][] _cellDetails; 
	private Integer _foundCount = 0;
	private ArrayList<Stack<Vec2i>>_foundPathList; 
	
	Stack<Vec2i> path = null; 
	
	private boolean DEBUG = false;
	
	public AStarSearch(Integer[][] grid ,Integer maxRow, Integer maxCol ) {
		this.setGrid(grid);
		this.setMaxRow(maxRow);
		this.setMaxCol(maxCol);
		this.setFoundPathList(new ArrayList<Stack<Vec2i>>());
	}
	public Stack<Vec2i> starSearch (Vec2i src, Vec2i dest) {
		_foundCount = 0; 
		// Empty last path set
		this.setFoundPathList(new ArrayList<Stack<Vec2i>>());
		
		if (isValid (src.x, src.y) == false) {
			System.out.print("Source is invalid\n");
			return null; 
		} 
		if (isValid (dest.x, dest.y) == false) {
			System.out.print("Source is invalid\n");
			return null; 
		} 
		if (isUnBlocked(this.getGrid(),src.x, src.y) == false || 
				isUnBlocked(this.getGrid(), dest.x, dest.y) == false){
			System.out.print("Source or the destination is blocked" );
			return null; 
		} 
		if (isDestination(src.x, src.y, dest) == true) { 
			System.out.print("We are already at the destination\n"); 
			return null; 
		} 
		this.setClosedList(new boolean[this.getMaxRow()][this.getMaxCol()]);
		for(int i = 0; i < this.getMaxRow(); i++) 	{
			for (int j = 0; j < this.getMaxCol(); j++) {
				this.getClosedList()[i][j] = false;
			}
		}
		AStarCell[][] cellDetails = new AStarCell[this.getMaxRow()][this.getMaxCol()]; 
		for (int i = 0; i < this.getMaxRow() ; i++ ) {
			for (int j = 0; j < this.getMaxCol() ; j++ ) {
				cellDetails[i][j] = new AStarCell();
				cellDetails[i][j].setF(Float.MAX_VALUE); 
				cellDetails[i][j].setG(Float.MAX_VALUE);
				cellDetails[i][j].setH(Float.MAX_VALUE); 
				cellDetails[i][j].setParentI(-1); 
				cellDetails[i][j].setParentJ(-1); 
			}
		}
		this.setCellDetails(cellDetails);
		int i = src.x;
		int j = src.y; 
		cellDetails[i][j].setF(0.0); 
		cellDetails[i][j].setG(0.0); 
		cellDetails[i][j].setH(0.0); 
		cellDetails[i][j].setParentI(i); 
		cellDetails[i][j].setParentJ(j); 
		this.setOpenList(new ArrayList<Map<Integer,Vec2i>>());
		HashMap<Integer, Vec2i> temp1 = new HashMap<Integer,Vec2i>(); 
		temp1.put(0, new Vec2i(i,j));
		this.getOpenList().add(temp1); 
		while (!(this.getOpenList().isEmpty())) 
		{ 
			Map<Integer, Vec2i> p = this.getOpenList().get(0); 	
			Entry<Integer, Vec2i> entry = p.entrySet().iterator().next();
			this.getOpenList().remove(0); 
			int i1 = entry.getValue().x; 
			int j1 = entry.getValue().y;
			this.getClosedList()[i1][j1] = true;
			// North
			nextCell(dest, i1, j1, i1 - 1, j1); 	
			// South
			nextCell(dest, i1, j1,i1 + 1, j1); 	
			// East
			nextCell(dest, i1, j1,i1, j1 + 1); 	
			// West
			nextCell(dest, i1, j1, i1, j1- 1); 	
		}		
		return path;
	}
	private void nextCell(Vec2i dest, int i, int j, int iIncrement, int jIncrement){
		double gNew = 0.0;
		double hNew = 0.0; 
		double fNew = 0.0; 
		// Only process this cell if this is a valid one 
		if (isValid(iIncrement,jIncrement) == true) {
			// If the destination cell is the same as the 
			// current successor 
			if (isDestination(iIncrement, jIncrement, dest) == true) { 
				// Set the Parent of the destination cell 
				this.getCellDetails()[iIncrement][jIncrement].setParentI(i);
				this.getCellDetails()[iIncrement][jIncrement].setParentJ(j); 
				tracePath (this.getCellDetails(), dest);
			} else if (this.getClosedList()[iIncrement][jIncrement] == false && 
					isUnBlocked(this.getGrid(), iIncrement, jIncrement) == true) { 
				gNew = this.getCellDetails()[i][j].getG() + 1.0; 
				hNew = calculateHValue (iIncrement, jIncrement, dest) ; 
				fNew = gNew + hNew; 
				if (this.getCellDetails()[iIncrement][jIncrement].getF() == Float.MAX_VALUE || 
						this.getCellDetails()[iIncrement][jIncrement].getF() > fNew) { 
					Map<Integer, Vec2i> temp2 = new HashMap<Integer, Vec2i>(); 
					temp2.put((int) fNew, new Vec2i(iIncrement,jIncrement)); 
					this.getOpenList().add(temp2); 
				}
				// Update the details of this cell 
				this.getCellDetails()[iIncrement][jIncrement].setF(fNew); 
				this.getCellDetails()[iIncrement][jIncrement].setG(gNew); 
				this.getCellDetails()[iIncrement][jIncrement].setH(hNew); 
				this.getCellDetails()[iIncrement][jIncrement].setParentI(i);
				this.getCellDetails()[iIncrement][jIncrement].setParentJ(j);
			}
		} 
	}
	
	void tracePath(AStarCell cellDetails[][], Vec2i dest) { 
		
		if (DEBUG == true) {
			System.out.print("Destination found\n");
			System.out.print("\nThe Path is "); 	
		}
		int row = dest.x; 
		int col = dest.y; 
		
		path = new Stack<Vec2i>();
		
		while (!(cellDetails[row][col].getParentI() == row  && 
				cellDetails[row][col].getParentJ() == col ))  { 
			
			path.push( new Vec2i(row, col));   

			int temp_row = cellDetails[row][col].getParentI(); 
			int temp_col = cellDetails[row][col].getParentJ(); 
			row = temp_row; 
			col = temp_col; 
			if (DEBUG) {
				System.out.print( "<--" + row + " , "+ col + "-->" + "\n");				
			}
		}
		path.push(new Vec2i(row, col));
		
		this.getFoundPathList().add( new Stack<Vec2i>() );
		
		System.out.print( "Pushed size: " + path.size() + "\n");	
		
		this.setFoundCount(_foundCount + 1);
		
		return; 
	} 
	
	public Integer[][] getGrid() {
		return _grid;
	}
	private void setGrid(Integer[][] _grid) {
		this._grid = _grid;
	}
	boolean isValid(Integer row, Integer col) { 
		boolean valid1 = (row >= 0) && (row < this.getMaxRow()); 
		boolean valid2 =  (col >= 0) && (col < this.getMaxCol());
		return (valid1 && valid2);  
	} 
	boolean isUnBlocked(Integer[][] cell, int row, int col)  { 
		if (this.getGrid()[row][col] == 1) {
			return true;
		} else {
			return false;
		}
	}
	boolean isDestination(int row, int col, Vec2i dest) { 
		if (row == dest.x && col == dest.y) {	
			return true;
		} else {
			return false;
		}
	} 
	double calculateHValue(int row, int col, Vec2i dest)  { 
		double dist = (row- dest.x ) * (row-dest.x) + (col-dest.y) * (col-dest.y); 
		return Math.pow(dist, 2); 
	} 
	public Integer getMaxRow() {
		return _maxRow;
	}
	private void setMaxRow(Integer _maxRow) {
		this._maxRow = _maxRow;
	}
	public Integer getMaxCol() {
		return _maxCol;
	}
	private void setMaxCol(Integer _maxCol) {
		this._maxCol = _maxCol;
	}
	AStarCell[][] getCellDetails() {
		return _cellDetails;
	}
	void setCellDetails(AStarCell[][] _cellDetails) {
		this._cellDetails = _cellDetails;
	}
	private boolean[][] getClosedList() {
		return _closedList;
	}
	private void setClosedList(boolean _closedList[][]) {
		this._closedList = _closedList;
	}
	private List<Map<Integer,Vec2i>> getOpenList() {
		return _openList;
	}
	private void setOpenList(List<Map<Integer,Vec2i>> _openList) {
		this._openList = _openList;
	}
	private void setFoundCount(Integer _foundCount) {
		this._foundCount = _foundCount;
	}
	public Integer getFoundCount() {
		return this._foundCount; 
	}
	public ArrayList<Stack<Vec2i>> getFoundPathList() {
		return _foundPathList;
	}
	private void setFoundPathList(ArrayList<Stack<Vec2i>> _foundPathList) {
		this._foundPathList = _foundPathList;
	}
}