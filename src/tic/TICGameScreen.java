package tic;

import java.util.Random;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import engine.Application;
import engine.Screen;
import engine.ui.Button;
import engine.utility.AspectRatioHandler;

public class TICGameScreen extends Screen {	
	private TICGameLogic _gameLogic; 
	private Button[][] _buttonGrid;
	private Integer [][] _gameGrid;
	private AspectRatioHandler _aspect;
	private Color _gridColor = Color.CRIMSON;
	private Integer _dimension = 3;
	private Button _focus = null;

	private Button _player1;
	private Button _player2;
	private Button _currentPlayer;
	private Button _increaseDimension;
	private Button _decreaseDimension;
	private Button _currentDimension;

	private double _timeoutFactor = 0.0;

	public TICGameScreen(Application app){
		super(app);
		this.setGameLogic( new TICGameLogic(_dimension));
		this.setAspect(this.getApplication().getAspectRatioHandler());
		this.setButtonGrid(new Button[_dimension][_dimension]);
		this._gameGrid = new Integer[_dimension][_dimension]; 
		for (Integer x = 0; x < this._dimension; x++) {
			for (Integer y = 0; y < this._dimension; y++) {
				this._gameGrid[x][y] = 0;
			}
		}	
		this.getGameLogic().setGameGrid( this._gameGrid);
		this.updateGrid();
		this.setPlayer1( new Button("", new Vec2d(0,0),new Vec2d(0,0), Color.CRIMSON));
		this.setPlayer2( new Button("", new Vec2d(0,0),new Vec2d(0,0), Color.BLUE));
		this.setCurrentPlayer(new Button("", new Vec2d(0,0),new Vec2d(0,0), Color.WHITESMOKE));
		this.setDecreaseDimension( new Button("", new Vec2d(0,0),new Vec2d(0,0), Color.MAGENTA));
		this.setIncreaseDimension(new Button("", new Vec2d(0,0),new Vec2d(0,0), Color.MAROON));
		this.setCurrentDimension(new Button("", new Vec2d(0,0),new Vec2d(0,0), Color.AQUAMARINE));
	}

	private void drawBorder(GraphicsContext g) {
		this.getApplication().borders(g, Color.BLACK);
	}

	private void drawBackGround (GraphicsContext g){
		g.setFill(Color.DARKBLUE);
		g.fillRect(0.0,0.0, 
				this.getAspect().getCurrentScreenSize().x,
				this.getAspect().getCurrentScreenSize().y);
	}

	private void drawGamePanel (GraphicsContext g) {
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		Vec2d screen = this.getAspect().calculateUpdatedScreenSize();
		double boxSize = screen.y - 100;
		double xOrigin = origin.x + ((screen.x / 2) - (boxSize / 2)); 
		double yOrigin = origin.y + 50; 
		g.setFill(Color.FUCHSIA);
		g.fillRect(xOrigin,yOrigin,boxSize, boxSize);
	}

	private void drawGrid (GraphicsContext g) {
		for (int x = 0; x < this.getDimension(); x++) {
			for (int y = 0; y < this.getDimension(); y++) {	
				this.getButtonGrid()[x][y].draw(g);
			}
		}
	}

	private void updateGrid () {
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		Vec2d screen = this.getAspect().calculateUpdatedScreenSize();
		double boxSize = (screen.y - 100) / this.getDimension();
		boxSize -= 10;
		double xOrigin = origin.x + (screen.x / 2) - ((screen.y - 100) / 2); 
		double yOrigin = origin.y + 50; 
		Integer xMulti = 0; 
		Integer yMulti = 0; 

		for (int x = 0; x < this.getDimension(); x++) {
			for (int y = 0; y < this.getDimension(); y++){	
				double ox = ( xOrigin + ((boxSize + 10) * xMulti)) + 3;
				double oy = ( yOrigin + ((boxSize + 10) * yMulti)) + 3; 
				this.getButtonGrid()[x][y] = new Button("", 
						new Vec2d(ox,oy), 
						new Vec2d(boxSize,boxSize), 
						this._gridColor);
				yMulti += 1;
			}
			xMulti += 1; 
			yMulti = 0;
		}
	}

	public void onDraw(GraphicsContext g)  {
		this.drawBackGround(g);
		this.drawBorder(g);
		this.drawGamePanel(g);
		this.drawGrid(g);	
		if (this.getFocus() != null) {
			this.getFocus().setColor(Color.WHEAT);	
			this.getFocus().draw(g);
		}	
		this.markFilled(g);
		this.timerTimeOut (g);
		this.drawPlayerScores(g);
	}	

	public void drawPlayerScores (GraphicsContext g) {		
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		Vec2d screen = this.getAspect().calculateUpdatedScreenSize();
		double boxSize = screen.x / 18;
		this.getPlayer1().setSize(new Vec2d(boxSize* 3.7,boxSize));
		this.getPlayer2().setSize(new Vec2d(boxSize * 3.7,boxSize));
		this.getCurrentPlayer().setSize(new Vec2d(boxSize * 3.7,boxSize));
		this.getDecreaseDimension().setSize(new Vec2d(boxSize* 3.7,boxSize)); 
		this.getIncreaseDimension().setSize(new Vec2d(boxSize* 3.7,boxSize));
		this.getCurrentDimension().setSize(new Vec2d(boxSize* 3.7,boxSize));
		this.getPlayer2().setOrigin(new Vec2d(origin.x,origin.y + 50));
		this.getPlayer1().setOrigin(new Vec2d(origin.x,origin.y + boxSize + 50));
		this.getCurrentPlayer().setOrigin(new Vec2d(origin.x,origin.y + (boxSize * 2) + 50));
		this.getDecreaseDimension().setOrigin(new Vec2d(origin.x,origin.y + (boxSize * 3) + 50)); 
		this.getIncreaseDimension().setOrigin(new Vec2d(origin.x,origin.y + (boxSize * 4) + 50));
		this.getCurrentDimension().setOrigin(new Vec2d(origin.x,origin.y + (boxSize * 5) + 50));
		this.getPlayer1().draw(g); 
		this.getPlayer2().draw(g);
		this.getCurrentPlayer().draw(g);
		this.getDecreaseDimension().draw(g); 
		this.getIncreaseDimension().draw(g);
		this.getCurrentDimension().draw(g);
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString("Alc"),boxSize / 6));
		g.setTextAlign(TextAlignment.CENTER); 
		String more = ""; 
		String more1 = ""; 
		if (this.getGameLogic().getCurrentPlayer() == this.getGameLogic().PLAYER_1) {
			more += "GO"; 
		} else if (this.getGameLogic().getCurrentPlayer() == this.getGameLogic().PLAYER_2) {
			more1 += "GO"; 
		}
		// Still need to populate the data
		g.fillText(more1 + " Player 1 Score: " + this.getGameLogic().getPlayer1WinCount(), origin.x + boxSize * 2,origin.y + (boxSize + 20));
		g.fillText( more + " Player 2 Score: " + + this.getGameLogic().getPlayer2WinCount(), origin.x + boxSize * 2,origin.y + (boxSize * 2) + 20);
		g.fillText("Press ENTER to  " , origin.x + boxSize * 2,origin.y + (boxSize * 3) + 20);
		g.fillText("change grid color", origin.x + boxSize * 2,origin.y + (boxSize * 4) + 20);
		g.fillText("Press SPACE to  ", origin.x + boxSize * 2,origin.y + (boxSize * 5) + 20);
		g.fillText("restart game    ", origin.x + boxSize * 2,origin.y + (boxSize * 6) + 20);
	}

	public void timerTimeOut (GraphicsContext g){
		Vec2d screen = this.getAspect().calculateUpdatedScreenSize();
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		double size = screen.x * this._timeoutFactor;
		if(this._timeoutFactor <= 0.20) {
			g.setFill(Color.WHITE);
		} else if ((0.20 <  this._timeoutFactor) && (this._timeoutFactor <= 0.40)) {
			g.setFill(Color.GREEN);
		} else if ((0.40 <  this._timeoutFactor) && (this._timeoutFactor <= 0.60)){
			g.setFill(Color.GOLD);
		} else if ((0.60 <  this._timeoutFactor) && (this._timeoutFactor <= 0.80)) {
			g.setFill(Color.RED);
		} else {
			g.setFill(Color.DARKRED);
		}
		g.fillRect(origin.x, + origin.y + (screen.y * 0.95), size , (screen.y * 0.05));
	}

	public void markFilled (GraphicsContext g) {
		for (Integer x = 0; x < this._dimension; x++) {
			for (Integer y = 0; y < this._dimension; y++) {
				if (this.getGameLogic().getGameGrid()[x][y] != 0) {
					g.setFill(Color.BLACK);	
					Vec2d origin = this.getButtonGrid()[x][y].getOrigin(); 
					Vec2d size   = this.getButtonGrid()[x][y].getSize();
					g.setFill(Color.BLACK);	
					g.setFont(Font.font(this.getEngineFont().getFontString("Tic"), size.x ));
					g.setTextAlign(TextAlignment.CENTER);
					double xO = origin.x + (size.x / 2);
					double yO = origin.y + size.y * 0.85;
					if (this.getGameLogic().getGameGrid()[x][y] == 1) {
						g.fillText("X", xO, yO);
					} else if (this.getGameLogic().getGameGrid()[x][y] == 2) {
						g.fillText("O", xO, yO);
					}					
				}
			}
		}
	}

	public void onTick(long nanosSincePreviousTick) {
		this.updateGrid();	
		this._timeoutFactor += 0.003;
		if(this._timeoutFactor > 1.0) {
			this._timeoutFactor = 0.0;
			this.getGameLogic().switchPlayer();
		}
		this.getGameLogic().checkWin(); 
		this.getGameLogic().checkFilled();
	}	

	private void tryGridSelect (MouseEvent e) {
		for (Integer x = 0; x < this._dimension; x++) {
			for (Integer y = 0; y < this._dimension; y++) {
				if (this.getGameLogic().getGameGrid()[x][y] == 0) {
					if ( this.getButtonGrid()[x][y].clicked(e)) {	
						this.getGameLogic().getGameGrid()[x][y] = 
								this.getGameLogic().getCurrentPlayer();
						this._timeoutFactor = 0.0;
						this.getGameLogic().switchPlayer();
					}
				}
			}	
		}
	}

	private Button checkButton (MouseEvent e){
		Button focus = null;
		for (Integer x = 0; x < this._dimension; x++) {
			for (Integer y = 0; y < this._dimension; y++) {
				if ( this.getButtonGrid()[x][y].clicked(e) ){
					focus = this.getButtonGrid()[x][y]; 
				}
			}
		}
		return focus;
	}

	public void onMouseClicked(MouseEvent e) {
		this.tryGridSelect(e);
	}

	public void onMouseMoved(MouseEvent e) {
		this.setFocus(checkButton(e)); 
	}	

	@Override
	public void onKeyPressed(KeyEvent e) {
		System.out.print(e.getCode().toString());
		if (e.getCode().toString() == "SPACE" ) {
			_timeoutFactor = 0; 
			this.getGameLogic().resetG();
		}
		if (e.getCode().toString() == "ENTER" ){
			Random r = new Random();
			Integer pick =  r.nextInt(4 + 1);
			System.out.print(e.getCode().toString() + pick);
			if (pick == 0) {
				this._gridColor = Color.WHEAT;
			} 
			if  (pick == 1) {
				this._gridColor = Color.AQUA;
			}
			if (pick == 2) {
				this._gridColor = Color.GOLD;
			} 
			if (pick == 3){
				this._gridColor = Color.CYAN;
			}
		}		
	}

	public TICGameLogic getGameLogic() {
		return _gameLogic;
	}

	public void setGameLogic(TICGameLogic _gameLogic) {
		this._gameLogic = _gameLogic;
	}

	private Button[][] getButtonGrid() {
		return _buttonGrid;
	}

	private void setButtonGrid(Button[][] _buttonGrid) {
		this._buttonGrid = _buttonGrid;
	}

	Integer getDimension() {
		return _dimension;
	}

	void setDimension(Integer _dimension) {
		this._dimension = _dimension;
	}

	private AspectRatioHandler getAspect() {
		return _aspect;
	}

	private void setAspect(AspectRatioHandler _aspect) {
		this._aspect = _aspect;
	}

	private Button getFocus() {
		return _focus;
	}

	private void setFocus(Button _focus) {
		this._focus = _focus;
	}

	private Button getPlayer2() {
		return _player1;
	}

	private void setPlayer2(Button _modeChangeUp) {
		this._player1 = _modeChangeUp;
	}

	private Button getPlayer1() {
		return _player2;
	}

	private void setPlayer1(Button _modeChangeDown) {
		this._player2 = _modeChangeDown;
	}

	private Button getCurrentPlayer() {
		return _currentPlayer;
	}

	private void setCurrentPlayer(Button _currentPlayer) {
		this._currentPlayer = _currentPlayer;
	}

	private Button getIncreaseDimension() {
		return _increaseDimension;
	}

	private void setIncreaseDimension(Button _increaseDimension) {
		this._increaseDimension = _increaseDimension;
	}

	private Button getDecreaseDimension() {
		return _decreaseDimension;
	}

	private void setDecreaseDimension(Button _decreaseDimension) {
		this._decreaseDimension = _decreaseDimension;
	}

	private Button getCurrentDimension() {
		return _currentDimension;
	}

	private void setCurrentDimension(Button _currentDimension) {
		this._currentDimension = _currentDimension;
	}
}
