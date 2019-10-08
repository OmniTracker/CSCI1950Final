package main;

import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.ui.*;
import engine.utility.AspectRatioHandler;

public class CSCI1950ProjectScreen extends Screen {
	private ArrayList<Button> _arrayListButton;
	private Button _startButton;
	private Integer _selectedGame; 
	private EngineFonts _fontNames;
	private final Vec2d _buttonSize = new Vec2d(300,50);
	private Color _borderColor;
	private Image _gameEnginePlayableScreen = null;

	private boolean _gameLoading = false;
	private double fadeInY = 0.0;
	private double _fadeout = 0.0;
	
	CSCI1950ProjectScreen(Application app){
		super(app);
		this.initButtons();
	}
	private void initButtons() {
		int index = 0;
		this.setStartButton(new Button("Start",  new Vec2d(0,index),this.getButtonSize(),Color.WHITESMOKE));
		this.setArrayListButton(new ArrayList<Button>());
		this.getArrayListButton().add(new Button("Tic",  new Vec2d(0,index),this.getButtonSize(),Color.BLUE));
		this.getArrayListButton().add(new Button("Alc",  new Vec2d(0,index += this.getButtonSize().y),this.getButtonSize(),Color.PURPLE));
		this.getArrayListButton().add(new Button("Wiz",  new Vec2d(0,index += this.getButtonSize().y),this.getButtonSize(),Color.DARKGREEN));
		this.getArrayListButton().add(new Button("Nin",  new Vec2d(0,index += this.getButtonSize().y),this.getButtonSize(),Color.GOLD));
		this.getArrayListButton().add(new Button("Final",new Vec2d(0,index += this.getButtonSize().y),this.getButtonSize(),Color.PINK));
		this.setBorderColor(Color.WHITE);
	}
	
	
	private void updateScreenButtons() {
		double projectNumber = 5.0;
		Vec2d newScreenSize = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize();
		Vec2d newScreenOrigin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin();

		double x =  newScreenOrigin.x + 30;
		double yFactor = ((newScreenSize.y - 300) / projectNumber);
		double y = newScreenOrigin.y + 130;
		this.getStartButton().setOrigin(new Vec2d(x, y - 20));
		this.getStartButton().setShape(new AABShape(this.getStartButton().getOrigin(),this.getStartButton().getSize()));

		for (Button button : this.getArrayListButton()) {
			Vec2d newOrigin = new Vec2d(x, (y +=  yFactor));
			AABShape shape = new AABShape(newOrigin, button.getSize()); 
			button.setOrigin(newOrigin);			
			button.setShape(shape);
		}
	}
	private void drawBackground(GraphicsContext g) {

		if (this.getGameEnginePlayableScreen() == null) {
			try {
				this.setGameEnginePlayableScreen(this.getApplication().getFactory().generateGameEngineIntroScreen());
			} catch (MalformedURLException e) {

				System.out.print("Failed to  get the image ");	
				e.printStackTrace();
			}
			return;
		}
		Vec2d newOrigin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d newScreenSize = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 				
		g.drawImage(this.getGameEnginePlayableScreen(), newOrigin.x, newOrigin.y, newScreenSize.x, newScreenSize.y);
	}
	@SuppressWarnings("static-access")
	private void courseName(GraphicsContext g) {
		g.setFill(this.getBorderColor());
		Vec2d newScreenOrigin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin();		
		g.setFont(Font.font(this.getFontNames().getAlc() , 40));

		g.fillText("CSCI 1950: 2D Game Engines", newScreenOrigin.x + 600 , newScreenOrigin.y + 50 + fadeInY);
	}
	
	public void onMouseClicked(MouseEvent e) {
		checkButtonClick(e);	
	}
	
	private void checkButtonClick(MouseEvent e) {
		for (Button button : this.getArrayListButton()) {	
			if ( button.clicked(e) ) {
				selectGame(button); 
			}
		}
		
		if (this.getStartButton().clicked(e)) {
			loadGame ();
		}
	}
	private void loadGame () {		
		System.out.print( this.getStartButton().getText() + " \n");
		System.out.print(this.getApplication().getFactory().getWizGameStats());
		_gameLoading = true;
		this.getApplication().loadGame(this.getSelectedGame());
	}
	private void borders(GraphicsContext g) {
		g.setFill(this.getBorderColor());
		AspectRatioHandler aspect = this.getApplication().getAspectRatioHandler();
		Vec2d newOrigin = aspect.calculateUpdatedOrigin(); 
		g.fillRect(0.0,0.0, newOrigin.x, aspect.getCurrentScreenSize().y);
		g.fillRect(aspect.getCurrentScreenSize().x - newOrigin.x,0.0, newOrigin.x,  aspect.getCurrentScreenSize().y);
		g.fillRect(0.0,0.0, aspect.getCurrentScreenSize().x, newOrigin.y);
		g.fillRect(0.0, aspect.getCurrentScreenSize().y - newOrigin.y, aspect.getCurrentScreenSize().x, newOrigin.y);
		g.setGlobalAlpha(0.5);
		g.fillRect(newOrigin.x + 20, (newOrigin.y + 80), 320, (aspect.calculateUpdatedScreenSize().y - 160));
		g.setGlobalAlpha(1.0);
	}
	
	private void selectGame(Button button) {
		this.setBorderColor(button.getColor());
		if (button.getText() == "Tic") {
			this.setSelectedGame(this.getApplication().TIC);
		} else if (button.getText() == "Alc") {
			this.setSelectedGame(this.getApplication().ALC);
		}  else if (button.getText() == "Wiz") {
			this.setSelectedGame(this.getApplication().WIZ);
		}  else if (button.getText() == "Nin") {
			this.setSelectedGame(this.getApplication().NIN);
		}  else if (button.getText() == "Final") {
			this.setSelectedGame(this.getApplication().FINAL);	
		} 
	}
	
	private void transitionOut(GraphicsContext g) {
		Vec2d screenSize = this.getApplication().getAspectRatioHandler().getCurrentScreenSize(); 
		if (_gameLoading == true) {
			_fadeout += 0.006;
			if (fadeInY < 300) {
				fadeInY += 4.3; 				
			}
			if (_fadeout > 1.9) {
				this.getApplication().setLevel(this.getSelectedGame());
			}
			g.setGlobalAlpha(_fadeout);
			g.setFill(Color.BLACK);
			g.fillRect(0,0, screenSize.x, screenSize.y);
			g.setGlobalAlpha(1.0);
		}
	}
	
	public void onTick(long nanosSincePreviousTick) {
		updateScreenButtons();
	}
	
	public void onDraw(GraphicsContext g)  {
		this.drawBackground(g);
		this.borders(g);
		this.getStartButton().draw(g);
		for (Button button : this.getArrayListButton()) {
			button.draw(g);
		}	
		this.transitionOut(g);
		this.courseName(g);

	}
	ArrayList<Button> getArrayListButton() {
		return _arrayListButton;
	}
	void setArrayListButton(ArrayList<Button> _arrayListButton) {
		this._arrayListButton = _arrayListButton;
	}
	private Vec2d getButtonSize() {
		return _buttonSize;
	}
	private Button getStartButton() {
		return _startButton;
	}
	private void setStartButton(Button _startButton) {
		this._startButton = _startButton;
	}
	public Integer getSelectedGame() {
		return _selectedGame;
	}
	public void setSelectedGame(Integer _selectedGame) {
		this._selectedGame = _selectedGame;
	}
	public Color getBorderColor() {
		return _borderColor;
	}
	public void setBorderColor(Color _borderColor) {
		this._borderColor = _borderColor;
	}
	public EngineFonts getFontNames() {
		return _fontNames;
	}
	public void setFontNames(EngineFonts _fontNames) {
		this._fontNames = _fontNames;
	}
	private Image getGameEnginePlayableScreen() {
		return _gameEnginePlayableScreen;
	}
	private void setGameEnginePlayableScreen(Image _gameEnginePlayableScreen) {
		this._gameEnginePlayableScreen = _gameEnginePlayableScreen;
	}
}
