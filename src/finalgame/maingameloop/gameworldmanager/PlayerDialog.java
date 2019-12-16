package finalgame.maingameloop.gameworldmanager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.ui.EngineFonts;
import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.FinalGameWorld.VisibleGameWorld;

public class PlayerDialog extends GameWorld {
	private Button _nextButton;
	private Button _skipButton;
	private FinalGameWorld _gameWorld;
	private HashMap<String,HashMap<String, Image>> _characterImages; 
	private String _selectedPlayersName = "";
	private HashMap<Integer,String> _characterSelector;
	private Image _brownSpecial = null;
	private Image _backRandom = null;
	private Image _mainDude = null;
	private Image _fuckBoy = null;
	private Integer _dialogSequence = 0;
	private int _selectedCharacter = -1;
	private double transitionAlpha = 0.6;
	public PlayerDialog(Application app, FinalGameWorld finalGameWorld) {
		super(app);
		Button nextButton = new Button(); 
		nextButton.setText("Next => ");
		nextButton.setSize( new Vec2d(200,30));
		nextButton.setColor(Color.WHITE);
		nextButton.setFontName(EngineFonts.getAlc());
		this.setNextButton(nextButton);
		Button skipButton = new Button(); 
		skipButton.setText("Skip Dialog");
		skipButton.setSize( new Vec2d(200,30));
		skipButton.setColor(Color.WHITE);
		skipButton.setFontName(EngineFonts.getAlc());
		this.setSkipButton( skipButton);
		this.setGameWorld(finalGameWorld);
		this.setCharacterSelector( new HashMap<Integer,String>());
	}
	public void onDraw(GraphicsContext g) { 
		if (_selectedCharacter == -1) {
			_selectedCharacter = this.getGameWorld().getCharacterSelection();	
			_selectedPlayersName =  this.getCharacterSelector().get(_selectedCharacter); 
			_mainDude = this.getCharacterImages().get(_selectedPlayersName).get("big");
		}
		if (_brownSpecial == null) {
			return;
		}
		if (_characterSelector.size() == 0) {
			return;
		}
		if (_backRandom == null) {
			return;
		}
		this.incrementTransition();
		this.drawBackground(g);
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
		g.setGlobalAlpha(0.2);
		int R = 0, G = 0, B = 0;
		if (_selectedCharacter == 0) {
			R = 255;
			G = 20;
			B = 147;
		}  else if (_selectedCharacter == 1) {
			R = 0;
			G = 0;
			B = 255;
		} else if (_selectedCharacter == 2) {
			R = 0;
			G = 255;
			B = 0;
		} else if (_selectedCharacter == 3) {
			R = 255;
			G = 0;
			B = 0;
		}
		g.setFill(Color.rgb(R,G,B));
		g.fillRect(origin.x,origin.y,size.x,size.y);	
		g.setGlobalAlpha(0.6);
		this.drawBrownSpecial(g);	
		g.setGlobalAlpha(1);
		this.drawMainCharacterAndFuckBoy(g);
		this.drawDialogViews(g);
		this.drawDialogTransitionButtons(g);

		Random r = new Random();
		if (_selectedCharacter == 0) {
			R = r.nextInt((255 - 0) + 1) + 0;
			G = 20;
			B = 147;
		}  
		else if (_selectedCharacter == 1) {
			R = 0;
			G = 0;
			B = r.nextInt((255 - 0) + 1) + 0;
		} else if (_selectedCharacter == 2) {
			R = 0;
			G = r.nextInt((255 - 0) + 1) + 0;
			B = 0;
		} else if (_selectedCharacter == 3) {
			R = r.nextInt((255 - 0) + 1) + 0;
			G = 0;
			B = 0;
		}
		g.setFill(Color.rgb(R,G,B));
		g.fillRect(origin.x, origin.y, size.x, (size.y / 9));
		g.fillRect(origin.x, origin.y +  (size.y - (size.y / 9)), size.x, (size.y / 9));

		if (_selectedCharacter == 0) {
			R = 255;
			G = 20;
			B = 147;
		}  else if (_selectedCharacter == 1) {
			R = 0;
			G = 0;
			B = 255;
		} else if (_selectedCharacter == 2) {
			R = 0;
			G = 255;
			B = 0;
		} else if (_selectedCharacter == 3) {
			R = 255;
			G = 0;
			B = 0;
		}
		g.setGlobalAlpha(transitionAlpha - 0.6);
		g.setFill(Color.rgb(R,G,B));
		g.fillRect(origin.x,origin.y,size.x,size.y);
		g.setGlobalAlpha(1);		
		double _transitionPercent = transitionAlpha - 0.6; 
		Vec2d textOrigin = new Vec2d(origin.x + (size.x / 2),  origin.y + 70);
		g.setTextAlign(TextAlignment.CENTER);
		if (_transitionPercent >= 1.0) {
			g.setFont(Font.font(EngineFonts.getAlc(),80 + (1.0 *  ((size.x / 11)))));
		} else {
			g.setFont(Font.font(EngineFonts.getAlc(),80 + (_transitionPercent * (size.x / 11))));
		}
		if (_transitionPercent <= 1.0) {
			g.setGlobalAlpha(_transitionPercent);			
			g.setFill(Color.WHITE);
			g.fillText("FIGHT !!!", textOrigin.x, textOrigin.y + ((size.y / 2) *  (_transitionPercent)   ));
						
			g.setGlobalAlpha(1.0 - _transitionPercent);
			g.setFill(Color.BLACK);
			g.fillText(_selectedPlayersName, textOrigin.x, textOrigin.y + ((size.y / 2) *  (_transitionPercent)   ));
		} else {
			g.setGlobalAlpha(1.0);			
			g.setFill(Color.WHITE);
			g.fillText("FIGHT !!!", textOrigin.x, textOrigin.y + ((size.y / 2)  ));
			g.setGlobalAlpha(0);			
			g.setFill(Color.BLACK);
			g.fillText(_selectedPlayersName, textOrigin.x, textOrigin.y + (((size.y / 2)) ));				
		}
		g.setGlobalAlpha(1.0);
	}
	private void drawDialogViews(GraphicsContext g) {
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize();
		String output = "";
		String output2 = "";
		Vec2d boxOrigin; 
		Vec2d fuckBoy =  new Vec2d(origin.x + (size.x * 0.25), origin.y + (size.y * 0.4)); 
		Vec2d mainBoy =  new Vec2d(origin.x + (size.x * 0.55), origin.y + (size.y * 0.4)); 
		switch(_dialogSequence) 
		{
		case 0:

			boxOrigin = fuckBoy; 

			output = "And I was like"; 
			output2 = "And I was like"; 


			break;
		case 1:


			boxOrigin = mainBoy; 

			output = "baby, baby, baby oh"; 
			output2 = "And I was like"; 



			break;
		case 2:

			boxOrigin = fuckBoy; 

			output = "Like baby, baby, baby no"; 
			output2 = "And I was like"; 



			break;
		case 3:

			boxOrigin = mainBoy; 

			output = "I thought you'd always";

			output2 = "And I was like"; 




			break;
		case 4:

			boxOrigin = fuckBoy; 

			output = "be mine (mine)"; 

			output2 = "And I was like"; 



			break;
		default:
			return;
		}

		g.setGlobalAlpha(0.8);
		this.labelHelper(g, boxOrigin, output,output2);
		g.setGlobalAlpha(1);
	}
	private void labelHelper(GraphicsContext g,Vec2d roundOrigin, String text, String text2) {
		int R = 0, G = 0, B = 0;
		if (_selectedCharacter == 0) {
			R = 255;
			G = 20;
			B = 147;
		}  else if (_selectedCharacter == 1) {
			R = 0;
			G = 0;
			B = 255;
		} else if (_selectedCharacter == 2) {
			R = 0;
			G = 255;
			B = 0;
		} else if (_selectedCharacter == 3) {
			R = 255;
			G = 0;
			B = 0;
		}
		g.setFill(Color.rgb(R,G,B));
		g.fillRoundRect(roundOrigin.x , roundOrigin.y, 250, 150, 50 , 50);
		g.setFill(Color.WHITE);
		g.fillRoundRect(roundOrigin.x + 5, roundOrigin.y + 5, 250 - 10, 150 - 10, 50, 50);
		g.setFill(Color.BLACK);		
		g.setFont(Font.font("Ethnocentric", 10 ));	
		g.fillText(text, roundOrigin.x + 90, roundOrigin.y + 40);
	}
	private void drawMainCharacterAndFuckBoy(GraphicsContext g) {
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize();
		Vec2d mainCharOrigin = origin.plus((size.x * 1.9 / 3), 0);
		g.drawImage(_mainDude, mainCharOrigin.x, mainCharOrigin.y +  (size.y * 0.05), (size.x * 0.9 / 3), size.y * 0.95);
		g.drawImage(_fuckBoy, origin.x + (size.x * 0.05), origin.y +  (size.y * 0.1), (size.x * 1.1 / 3), size.y * 0.9);
	}
	private void drawBrownSpecial(GraphicsContext g) {
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
		Vec2d center = origin.plus(size.x/2, size.y/2);
		double scale = 2.5;
		g.drawImage( _brownSpecial, center.x - ( 130 * scale) , center.y - ( 100 * scale ) , 260 * scale , 160 * scale);
	}
	private void drawBackground (GraphicsContext g) {
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
		g.drawImage(_backRandom,origin.x, origin.y, size.x, size.y);
	}
	private void drawDialogTransitionButtons(GraphicsContext g) {
		double xOrigin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().x * 0.8);
		double yOrigin1 = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().y * 0.75); 
		double yOrigin2 = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().y * 0.80); 	
		this.getNextButton().setOrigin(new Vec2d(xOrigin,yOrigin1));
		this.getNextButton().drawRounded(g);
		this.getSkipButton().setOrigin(new Vec2d(xOrigin,yOrigin2));
		this.getSkipButton().drawRounded(g);
	}
	private void incrementTransition() {
		if ( transitionAlpha > 0.6) {
			transitionAlpha += 0.01; 
		}		
		if (transitionAlpha >= 3.0) {	
			_selectedCharacter = -1;
			_dialogSequence = 0;
			this.getGameWorld().changeCurrentScreen(VisibleGameWorld.MAINGAMEPLAY);
		}
	}
	public void onMouseClicked(MouseEvent e) {
		if (this.getNextButton().clicked(e)) {
			_dialogSequence++;
			if (_dialogSequence == 5) {
				transitionAlpha += 0.05; 
			}
		}
		if (this.getSkipButton().clicked(e)) {
			transitionAlpha += 0.1; 
		}
	}
	private  HashMap<String,HashMap<String, Image>> getCharacterImages() {
		return _characterImages;
	}
	public void setCharacterImages(HashMap<String,HashMap<String, Image>> _characterImages) {
		this._characterImages = _characterImages;
		Integer index = 0;
		for (Entry<String, HashMap<String, Image>> mapElement : this.getCharacterImages().entrySet()) 
		{
			String key = (String) mapElement.getKey();

			if (!key.contains("enemy")) 
			{
				this.getCharacterSelector().put(index, key);
				index++;
			}
		}
		_brownSpecial = getBrownLogo (); 
		_backRandom = getRand (); 
		_fuckBoy =  getFuckBoy ();
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
	public static Image getRand () {
		Image out = null;
		try{
			out =  new Image(new File("resources/terrain/newBack.jpg").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	public static Image getFuckBoy () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/fuckBoy.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	private Button getNextButton() {
		return _nextButton;
	}
	private void setNextButton(Button _highScoreButton) {
		this._nextButton = _highScoreButton;
	}
	private Button getSkipButton() {
		return _skipButton;
	}
	private void setSkipButton(Button _skipButton) {
		this._skipButton = _skipButton;
	}
	private FinalGameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(FinalGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	private HashMap<Integer,String> getCharacterSelector() {
		return _characterSelector;
	}
	private void setCharacterSelector(HashMap<Integer,String> _characterSelector) {
		this._characterSelector = _characterSelector;
	}
}
