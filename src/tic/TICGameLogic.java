package tic;

import java.util.Random;

public class TICGameLogic {
	final Integer PLAYER_1 = 2; 
	final Integer PLAYER_2 = 1; 
	private Integer _player1WinCount = 0;
	private Integer _player2WinCount = 0;
	private Integer _lastPlayer;
	private Integer _currentPlayer;
	private Integer _gameDimension;
	private Integer [][] _gameGrid;

	TICGameLogic(Integer dimension) 
	{
		this.setGameDimension(dimension);		
		resetBoard();
		setRandomPlayer();
	}

	public void resetBoard()
	{
		this.setGameGrid(new Integer[this._gameDimension][this._gameDimension]);
	}

	private void setRandomPlayer() 
	{
		Random r = new Random();
		this.setCurrentPlayer((r.nextInt((PLAYER_1 - PLAYER_2) + 1) + PLAYER_2));
	}

	public boolean checkWin() 
	{
		if (checkForDiagonalWin() || checkVerticalWin () || checkHorizontalWin ()) 
		{	
			for (Integer x = 0; x < this._gameDimension; x++) 
			{
				for (Integer y = 0; y < this._gameDimension; y++) {
					this._gameGrid[x][y] = 0;
				}	
			}	
			this.incrementPlayerScore();
			return true;
		}
		return false;
	}
	public void resetGame( ){
		for (Integer x = 0; x < this._gameDimension; x++) 
		{
			for (Integer y = 0; y < this._gameDimension; y++) {
				this._gameGrid[x][y] = 0;
			}	
		}	
		this.incrementPlayerScore();
	}
	public boolean checkFilled (){
		Integer count =0; 
		for (Integer x = 0; x < this._gameDimension; x++) 
		{
			for (Integer y = 0; y < this._gameDimension; y++) {

				if (this._gameGrid[x][y] != 0) {

					count += 1;
				}
			}	
		}	
		if(count == 9) {
			for (Integer x = 0; x < this._gameDimension; x++) 
			{
				for (Integer y = 0; y < this._gameDimension; y++) 
				{
					this._gameGrid[x][y] = 0;
				}	
			}
			this.resetG(); 
			return true;
		}
		return false; 
	}
	public void resetG( ){
		for (Integer x = 0; x < this._gameDimension; x++) 
		{
			for (Integer y = 0; y < this._gameDimension; y++) {
				this._gameGrid[x][y] = 0;
			}	
		}	
		_player1WinCount = 0;
		_player2WinCount = 0;
	}
	private boolean checkForDiagonalWin() 
	{ 
		Integer indexCount = 0; 
		for (Integer start = 0; start < this.getGameDimension(); start++ ) 
		{
			if (this.getLastPlayer() == this.getGameGrid()[start][start]) 
			{
				indexCount++; 
			}
		}

		if (indexCount == this.getGameDimension()) 
		{
			return true;
		}
		indexCount = 0; 
		for (Integer start = 0; start < this.getGameDimension(); start++ ) 
		{
			if (this.getLastPlayer() == 
					this.getGameGrid()[(this.getGameDimension() - start) - 1][start]) 
			{
				indexCount++; 
			}
		}
		if (indexCount == this.getGameDimension()) 
		{
			return true;
		}
		return false;
	}
	private boolean checkHorizontalWin () 
	{
		Integer indexCount = 0; 
		for (Integer row = 0; row < this.getGameDimension(); row++) 
		{
			for (Integer col = 0; col < this.getGameDimension(); col++)
			{	
				if (this.getLastPlayer() == this.getGameGrid()[row][col]) 
				{
					indexCount++;
				}
			}
			if (indexCount == this.getGameDimension()) 
			{	
				return true;
			}
			indexCount = 0;
		}
		return false;
	}

	private boolean checkVerticalWin () 
	{
		Integer indexCount = 0; 
		for (Integer col = 0; col < this.getGameDimension(); col++)
		{
			for (Integer row = 0; row < this.getGameDimension(); row++) 
			{
				if (this.getLastPlayer() == this.getGameGrid()[row][col]) 
				{
					indexCount++;
				}
			}
			if (indexCount == this.getGameDimension())
			{
				return true;
			}
			indexCount = 0;
		}
		return false;
	}

	private void incrementPlayerScore ( )
	{
		if (this.getLastPlayer() == this.PLAYER_2) 
		{
			this._player1WinCount++;
		} 
		else 
		{
			this._player2WinCount++;			
		}
	}

	public void switchPlayer() 
	{
		this.setLastPlayer(this.getCurrentPlayer());

		if (this.getCurrentPlayer() == PLAYER_1) 
		{
			this.setCurrentPlayer(PLAYER_2);
		} 
		else 
		{
			this.setCurrentPlayer(PLAYER_1);
		}
	}

	public Integer getGameIndexValue(int row, int col) {
		return this.getGameGrid()[row][col]; 
	}

	public Integer [][] getGameGrid() {
		return _gameGrid;
	}

	public void setGameGrid(Integer [][] _gameGrid) {
		this._gameGrid = _gameGrid;
	}

	private Integer getGameDimension() {
		return _gameDimension;
	}
	private void setGameDimension(Integer _gameDimension) {
		this._gameDimension = _gameDimension;
	}

	public Integer getCurrentPlayer() {
		return _currentPlayer;
	}

	public void setCurrentPlayer(Integer _currentPlayer) {
		this._currentPlayer = _currentPlayer;
	}

	private Integer getLastPlayer() {
		return _lastPlayer;
	}

	private void setLastPlayer(Integer _lastPlayer) {
		this._lastPlayer = _lastPlayer;
	}

	public Integer getPlayer1WinCount() {
		return _player1WinCount;
	}

	public void setPlayer1WinCount(Integer _player1WinCount) {
		this._player1WinCount = _player1WinCount;
	}

	public Integer getPlayer2WinCount() {
		return _player2WinCount;
	}

	public void setPlayer2WinCount(Integer _player2WinCount) {
		this._player2WinCount = _player2WinCount;
	}
}