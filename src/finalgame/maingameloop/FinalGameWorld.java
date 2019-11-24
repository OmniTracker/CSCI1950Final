package finalgame.maingameloop;


import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import engine.Application;
import engine.GameWorld;
import finalgame.engineAdditions.SoundSystem;
import finalgame.maingameloop.gameworldmanager.*;
import finalgame.utils.*;

public class FinalGameWorld  extends GameWorld {
	// The game world manage what is currently being shown on the screen
	private boolean _showMenuBar;
	private Introduction    _introduction;
	private PlayerSelection _playerSelection;
	private PlayerDialog    _playerDialog;
	private MainGamePlay    _mainGamePlay;
	private FinalGameObjectHandler _finalGameObjectHandler; 
	private GameWorld _currentlySelectedScreen; 
	private VisibleGameWorld __visibleGameWorldEnum;
	
	private SoundSystem _soundSystem;
	
	public enum VisibleGameWorld {
		INTRODUCTION, 
		PLAYERSELECTION, 
		PLAYERDIALOG,		
		MAINGAMEPLAY
	}
	protected FinalGameWorld(Application app) {
		super(app);
		this.seeShowMenuBar(false);
		this.setIntroduction(new Introduction(app, this));
		this.setPlayerSelection(new PlayerSelection(app, this));
		this.setPlayerDialog(new PlayerDialog(app));
		this.setMainGamePlay(new MainGamePlay(app, this));
		this.setFinalGameObjectHandler(new FinalGameObjectHandler());
		this.changeCurrentScreen(VisibleGameWorld.INTRODUCTION);
		
		//Initialize sound system for playing music which will vary based on screen and play sfx during gameplay
		this.setupSoundSystem();
		
		// Check at what point this is starting
		this.getFinalGameObjectHandler().initGameCharacters();
	}
	public void changeCurrentScreen(VisibleGameWorld screen) 
	{
		if (screen == VisibleGameWorld.INTRODUCTION)
		{
			this.setCurrentlySelectedScreen(this.getIntroduction());
		} 
		else if (screen == VisibleGameWorld.PLAYERSELECTION) 
		{
			this.setCurrentlySelectedScreen(this.getPlayerSelection());
		} 
		else if (screen == VisibleGameWorld.PLAYERDIALOG) 
		{
			this.setCurrentlySelectedScreen(this.getPlayerDialog());
		} 
		else if (screen == VisibleGameWorld.MAINGAMEPLAY) {
			this.setCurrentlySelectedScreen(this.getMainGamePlay());
		}
	}
	public void onTick(long nanosSincePreviousTick) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onTick(nanosSincePreviousTick);
		}	
	}
	public void onDraw(GraphicsContext g) {		
		this.getCurrentlySelectedScreen().onDraw(g);
	}
	public void onMouseClicked(MouseEvent e) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onMouseClicked(e);
		}	
	}	
	public void onKeyPressed(KeyEvent e)  {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onKeyPressed(e);
		}	
	}
	public void onStartup() {
		this.getFinalGameObjectHandler().initGameCharacters();
		this.getPlayerDialog().setCharacterImages(this.getFinalGameObjectHandler().getCharacterImages());
	}
	public void onShutdown() { 
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onShutdown();
		}
	}
	public void onKeyReleased(KeyEvent e) {
		if (this.getCurrentlySelectedScreen() != null) {

			this.getCurrentlySelectedScreen().onKeyPressed(e);
		}
	}
	public void onMousePressed(MouseEvent e) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onMousePressed(e);
		}
	}
	public void onMouseReleased(MouseEvent e) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onMouseReleased(e);
		}
	}
	public void onMouseMoved(MouseEvent e) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onMouseMoved(e);
		}
	}
	public void onMouseWheelMoved(ScrollEvent e) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onMouseWheelMoved(e);
		}
	}
	public void onFocusChanged(boolean newVal) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onFocusChanged(newVal);
		}
	}
	public void onResize(Vec2d newSize) {
		if (this.getCurrentlySelectedScreen() != null) {
			this.getCurrentlySelectedScreen().onResize(newSize);
		}
	}
	private Introduction getIntroduction() {
		return _introduction;
	}
	private void setIntroduction(Introduction _introduction) {
		this._introduction = _introduction;
	}
	public PlayerSelection getPlayerSelection() {
		return _playerSelection;
	}
	private void setPlayerSelection(PlayerSelection _playerSelection) {
		this._playerSelection = _playerSelection;
	}
	private PlayerDialog getPlayerDialog() {
		return _playerDialog;
	}
	private void setPlayerDialog(PlayerDialog _playerDialog) {
		this._playerDialog = _playerDialog;
	}
	private MainGamePlay getMainGamePlay() {
		return _mainGamePlay;
	}
	private void setMainGamePlay(MainGamePlay _mainGamePlay) {
		this._mainGamePlay = _mainGamePlay;
	}
	private GameWorld getCurrentlySelectedScreen() {
		return _currentlySelectedScreen;
	}
	private void setCurrentlySelectedScreen(GameWorld _currentlySelectedScreen) {
		this._currentlySelectedScreen = _currentlySelectedScreen;
	}
	public FinalGameObjectHandler getFinalGameObjectHandler() {
		return _finalGameObjectHandler;
	}
	private void setFinalGameObjectHandler(FinalGameObjectHandler _finalGameObjectHandler) {
		this._finalGameObjectHandler = _finalGameObjectHandler;
	}
	public boolean isShowMenuBar() {
		return _showMenuBar;
	}
	private void seeShowMenuBar(boolean _showMenuBar) {
		this._showMenuBar = _showMenuBar;
	}
	public VisibleGameWorld getVisibleGameWorldEnum() {
		return __visibleGameWorldEnum;
	}
	
	private void setupSoundSystem() {
		_soundSystem = new SoundSystem(this);
	}
}
