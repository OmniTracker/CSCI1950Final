package finalgame.maingameloop.gameworldmanager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.ui.EngineFonts;
import engine.utility.AspectRatioHandler;
import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.FinalGameWorld.VisibleGameWorld;

public class PlayerSelection extends GameWorld {
	private FinalGameWorld _finalGameWorld;
	private AspectRatioHandler _aspect;
	private HashMap<String,HashMap<String, Image>> _characterImages;
	private HashMap<Integer,String> _characterSelector;
	private Button _rightButton;
	private Button _leftButton;
	private Button _nextLevelButton;
	private Vec2d _centerFrameOrigin = new Vec2d (0,0);
	private Vec2d _rightFrameOrigin  = new Vec2d (0,0);
	private Vec2d _leftFrameOrigin = new Vec2d (0,0);
	private final int NO_DIRECTION    = 0;
	private final int RIGHT_DIRECTION = 1;
	private final int LEFT_DIRECTION  = 2;
	private int direction = NO_DIRECTION;
	private boolean _inTransition = false;
	private Image _brownSpecial = null;
	double transitionX = 0;
	Integer _currentPlayer = 3;
	Integer _nextPlayer = _currentPlayer;
	double _transitionPercent = 0.0; 
	int R = 255;
	int G = 0;
	int B = 0;
	public PlayerSelection(Application app, GameWorld parent) {
		super(app);
		this.setFinalGameWorld((FinalGameWorld) parent);
		this.setAspect(this.getApplication().getAspectRatioHandler());
	}
	public void initScreen () {
		this.setRightButton(new Button());
		this.setLeftButton(new Button());
		this.setNextLevelButton((new Button()));
		this.setCharacterImages(this.getFinalGameWorld().getFinalGameObjectHandler().getCharacterImages());
		this.setCharacterSelector( new HashMap<Integer,String>());
		Integer index = 0;
		for (Entry<String, HashMap<String, Image>> mapElement :
			this.getCharacterImages().entrySet()) {
			String key = (String) mapElement.getKey();
			if (!key.contains("enemy")) {
				this.getCharacterSelector().put(index, key);
				index++;
			}
		}
		_brownSpecial = getBrownLogo (); 
	}
	public void onDraw(GraphicsContext g) {
		// Draw the left and right Arrows
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		Vec2d size = this.getAspect().calculateUpdatedScreenSize();
		double xSize = (size.x / 3.8);
		double ySize = (size.y / 1.3);
		// Draw Image
		g.setFill(Color.BLACK);
		g.fillRect(origin.x, origin.y, size.x, size.y);
		g.setGlobalAlpha(0.65);
		g.drawImage(this.getFinalGameWorld().getFinalGameObjectHandler().getSelectBackground(), (origin.x + xSize), (origin.y + ((size.y / 9) * 2)), (size.x - xSize * 2) , (size.y - ( (size.y / 9) * 4)));
		g.setGlobalAlpha(0.2);
		g.setFill(Color.rgb(R,G,B));
		g.fillRect(origin.x, origin.y, size.x, size.y);
		g.setGlobalAlpha(0.6);
		g.setFill(Color.BLACK);
		this.setLeftFrameOrigin(  new Vec2d( (origin.x - (xSize / 2)) + transitionX               , (origin.y + (size.y / 2)) - (ySize / 2)));
		this.setCenterFrameOrigin(new Vec2d( (origin.x - (xSize / 2)) + transitionX + (size.x / 2), (origin.y + (size.y / 2)) - (ySize / 2)));
		this.setRightFrameOrigin (new Vec2d( (origin.x - (xSize / 2)) + transitionX +  size.x     , (origin.y + (size.y / 2)) - (ySize / 2)));
		g.fillRoundRect( this.getCenterFrameOrigin().x, this.getCenterFrameOrigin().y, xSize, ySize, 40, 40);
		g.fillRoundRect( this.getRightFrameOrigin().x , this.getRightFrameOrigin().y , xSize, ySize, 40, 40);
		g.fillRoundRect( this.getLeftFrameOrigin().x  , this.getLeftFrameOrigin().y  , xSize, ySize, 40, 40);
		g.setGlobalAlpha(1.0);
		g.drawImage(this.getCharacterImages().get(this.getCharacterSelector().get(_currentPlayer)).get("big"), this.getCenterFrameOrigin().x, this.getCenterFrameOrigin().y, xSize, ySize);
		g.drawImage(this.getCharacterImages().get(this.getCharacterSelector().get((( _currentPlayer + 1 == 4)  ? 0 : _currentPlayer + 1))).get("big"), this.getRightFrameOrigin().x, this.getRightFrameOrigin().y, xSize, ySize);
		g.drawImage(this.getCharacterImages().get(this.getCharacterSelector().get((( _currentPlayer - 1 == -1) ? 3 : _currentPlayer - 1))).get("big"), this.getLeftFrameOrigin().x, this.getLeftFrameOrigin().y, xSize, ySize);
		g.setFill(Color.BLACK);
		g.fillRect(origin.x, origin.y, xSize / 2, size.y);
		g.fillRect((origin.x + size.x) - (xSize / 2),origin.y,(xSize / 2), size.y);
		g.setGlobalAlpha(0.6);
		g.setFill(Color.rgb(R,G,B));
		g.fillRect(origin.x, origin.y, xSize / 2, size.y);
		g.fillRect((origin.x + size.x) - (xSize / 2),origin.y,(xSize / 2), size.y);
		this.getLeftButton().setShape( new AABShape( new Vec2d(origin.x, origin.y),new Vec2d(xSize / 2, size.y)));
		this.getRightButton().setShape( new AABShape( new Vec2d((origin.x + size.x) - (xSize / 2), origin.y),new Vec2d(xSize / 2, size.y)));
		g.setGlobalAlpha(1.0);
		g.setFill(Color.BLACK);
		g.fillRect(origin.x, origin.y, size.x, (size.y / 9));
		g.fillRect(origin.x, origin.y +  (size.y - (size.y / 9)), size.x, (size.y / 9));
		this.getNextLevelButton().setShape( new AABShape( new Vec2d(origin.x, origin.y +  (size.y - (size.y / 9))), new Vec2d(size.x, (size.y / 9)) ));
		Vec2d selectionOrigin = origin.plus( size.x - 330,  size.y - 60  );
		this.labelHelper(g,selectionOrigin, "D = RIGHT", "");
		selectionOrigin = origin.plus( 150,  size.y - 60  );
		this.labelHelper(g,selectionOrigin, "A = LEFT", "");
		selectionOrigin = origin.plus( (size.x / 2) - 90,  size.y - 60  );
		this.labelHelper(g,selectionOrigin, "S = SELECT", "");
		g.setGlobalAlpha(_transitionPercent);
		g.setFill(Color.BLACK);
		g.fillRect(origin.x, origin.y, size.x, size.y);
		g.setGlobalAlpha(1.0);
		g.setFill(Color.rgb(R,G,B));
		Vec2d textOrigin = new Vec2d(origin.x + (size.x / 2),  origin.y + 70);
		g.setTextAlign(TextAlignment.CENTER);
		if (_transitionPercent < 0.8) {
			g.setGlobalAlpha(_transitionPercent - 0.4);			
		} else {
			g.setGlobalAlpha(0.4);			
		}
		this.drawBrownSpecial(g);
		g.setGlobalAlpha(1.0);
		
		if (_transitionPercent >= 1.3) {
			g.setFont(Font.font(EngineFonts.getAlc(),80 + (1.3 *  ((size.x / 11)))));
		} else {
			g.setFont(Font.font(EngineFonts.getAlc(),80 + (_transitionPercent * (size.x / 11))));
		}
		
		if (_transitionPercent <= 1.3) 
		{
			g.fillText(this.getCharacterSelector().get(_currentPlayer), textOrigin.x, textOrigin.y + ((size.y / 2) *  (_transitionPercent + 0.01)   ));
		} 
		else
		{
			g.fillText(this.getCharacterSelector().get(_currentPlayer), textOrigin.x, textOrigin.y + (((size.y / 2) * (1.3)) ));				
		}
	}
	private void drawBrownSpecial(GraphicsContext g) {
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
		Vec2d center = origin.plus(size.x/2, size.y/2);
		double scale = 2.5;
		g.drawImage( _brownSpecial, center.x - ( 120 * scale) , center.y - ( 135 * scale ) , 220 * scale , 140 * scale);
	}
	private void labelHelper(GraphicsContext g,Vec2d roundOrigin, String text, String text2) {
		g.setFill(Color.rgb(R,G,B));
		g.fillRoundRect(roundOrigin.x , roundOrigin.y, 180, 50, 50 , 50);
		g.setFill(Color.WHITE);
		g.fillRoundRect(roundOrigin.x + 5, roundOrigin.y + 5, 180 - 10, 50 - 10, 50, 50);
		g.setFill(Color.BLACK);		
		g.setFont(Font.font("Ethnocentric", 15 ));	
		g.fillText(text, roundOrigin.x + 90, roundOrigin.y + 30);
	}
	private void colorRotation ( ) {
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		Vec2d size = this.getAspect().calculateUpdatedScreenSize();
		if ( _currentPlayer == 2 && _nextPlayer == 3) {
			R = 0;
			G = 255 - (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
		}
		if ( _currentPlayer == 1 && _nextPlayer == 0) {
			R = 0;
			G = (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = 255 - (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
		}
		if ( _currentPlayer == 3 && _nextPlayer == 0) {
			R = 255 - (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			G = (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = 0;
		}
		if ( _currentPlayer == 2 && _nextPlayer == 1) {
			R = (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			G = 255 - (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = 0;
		}
		
		if ( _currentPlayer == 0 && _nextPlayer == 1) {
			R = 255;
			G = 20 - (int) (20 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = 147 - (int) (147 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
		}
		if ( _currentPlayer == 0  && _nextPlayer == 3) {
			R = 255 - (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			G = 20 - (int) (20 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = 147;
		}
		if ( _currentPlayer == 3 && _nextPlayer == 2) {
			R = 255;
			G = (int) (20 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = (int) (147 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
		}
		
		if ( _currentPlayer == 1 && _nextPlayer == 2) {
			R = (int) (255 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			G = (int) (20 * (Math.abs(transitionX) /  ((origin.x + size.x) / 2)));
			B = 147;
		}
	}
	public void onTick(long nanosSincePreviousTick) {
		if (_inTransition == true) 
		{
			if (direction == RIGHT_DIRECTION) 
			{
				transitionX += 15.0;
			} 
			else if (direction == LEFT_DIRECTION) 
			{
				transitionX -= 15.0;
			}
			this.colorRotation();
		}
		this.repositionFrame();		
		if (_transitionPercent > 0.0) 
		{
			_transitionPercent += 0.01; 	
			if ( 2.9 < _transitionPercent) 
			{
				this.getFinalGameWorld().changeCurrentScreen(VisibleGameWorld.PLAYERDIALOG);
				_transitionPercent = -0.1; 		
			}
		}
	}
	void repositionFrame() {
		Vec2d origin = this.getAspect().calculateUpdatedOrigin();
		Vec2d size = this.getAspect().calculateUpdatedScreenSize();
		if (Math.abs(transitionX) >= ((origin.x + size.x) / 2)) {
			_inTransition = false;
			if (transitionX < 0) {
				_currentPlayer = (( _currentPlayer + 1 == 4)  ? 0 : _currentPlayer + 1);
			} else {
				_currentPlayer = (( _currentPlayer - 1 == -1) ? 3 : _currentPlayer - 1);
			}
			if (_currentPlayer == 0) {
				R = 255;
				G = 20;
				B = 147;
			}  
			else if (_currentPlayer == 1) {
				R = 0;
				G = 0;
				B = 255;
			} else if (_currentPlayer == 2) {
				R = 0;
				G = 255;
				B = 0;
			} else if (_currentPlayer == 3) {
				R = 255;
				G = 0;
				B = 0;
			}
			transitionX = 0.0;
		}
	}
	public static Image getBrownLogo () {
		Image out = null;
		try{
			out =  new Image(new File("resources/backgrounds/BrownFinal.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	private void rotateCharacters(KeyEvent e) {
		String key = e.getText().toString(); 	
		if (_inTransition == false) {
			if ( key.equals("d") || key.equals("D"))  {
				direction = RIGHT_DIRECTION;
				_nextPlayer = (( _currentPlayer + 1 == 4)  ? 0 : _currentPlayer + 1);
				_inTransition = true;
			}  else if ( key.equals("a") || key.equals("A")) {
				direction = LEFT_DIRECTION;
				_nextPlayer = (( _currentPlayer - 1 == -1) ? 3 : _currentPlayer - 1);
				_inTransition = true;
			} else if (key.equals("s") || key.equals("S")) {
				if (_currentPlayer==2 || _currentPlayer==1) {
					return;
				}
				direction = NO_DIRECTION;
				_transitionPercent += 0.05;
				_inTransition = true;
			}
		}
	}
	public void onKeyPressed(KeyEvent e)  {
		this.rotateCharacters(e);
	}
	private FinalGameWorld getFinalGameWorld() {
		return _finalGameWorld;
	}
	private void setFinalGameWorld(FinalGameWorld _finalGameWorld) {
		this._finalGameWorld = _finalGameWorld;
	}
	private AspectRatioHandler getAspect() {
		return _aspect;
	}
	private void setAspect(AspectRatioHandler _aspect) {
		this._aspect = _aspect;
	}
	private Vec2d getCenterFrameOrigin() {
		return _centerFrameOrigin;
	}
	private void setCenterFrameOrigin(Vec2d _centerFrameOrigin) {
		this._centerFrameOrigin = _centerFrameOrigin;
	}
	Vec2d getRightFrameOrigin() {
		return _rightFrameOrigin;
	}
	private void setRightFrameOrigin(Vec2d _rightFrameOrigin) {
		this._rightFrameOrigin = _rightFrameOrigin;
	}
	private Vec2d getLeftFrameOrigin() {
		return _leftFrameOrigin;
	}
	private void setLeftFrameOrigin(Vec2d _leftFrameOrigin) {
		this._leftFrameOrigin = _leftFrameOrigin;
	}
	private Button getRightButton() {
		return _rightButton;
	}
	private void setRightButton(Button _rightButton) {
		this._rightButton = _rightButton;
	}
	private Button getLeftButton() {
		return _leftButton;
	}
	private void setLeftButton(Button _leftButton) {
		this._leftButton = _leftButton;
	}
	private HashMap<String,HashMap<String, Image>> getCharacterImages() {
		return _characterImages;
	}
	private void setCharacterImages(HashMap<String,HashMap<String, Image>> _characterImages) {
		this._characterImages = _characterImages;
	}
	private HashMap<Integer,String> getCharacterSelector() {
		return _characterSelector;
	}
	private void setCharacterSelector(HashMap<Integer,String> _characterSelector) {
		this._characterSelector = _characterSelector;
	}
	private Button getNextLevelButton() {
		return _nextLevelButton;
	}
	private void setNextLevelButton(Button _nextLevelButton) {
		this._nextLevelButton = _nextLevelButton;
	}
	public int getCharacterSelection() {
		return _currentPlayer;
	}
}
