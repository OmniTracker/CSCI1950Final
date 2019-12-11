package nin.ui;

import main.CSCI1950ProjectScreen;
import nin.level0.NinGameWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.GameMask;
import engine.ui.Button;
import engine.ui.EngineFonts;
import engine.ui.MenuBar;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class NINMenuBar extends MenuBar implements EventHandler {
	private Integer _contextHolder          = -1; 
	private Integer LOAD_GAME_PANEL_VIEW = 1; 
	private Integer END_GAME_PANEL_VIEW     = 2; 
	private Integer SAVE_GAME_PANEL_VIEW    = 3; 
	private NinGameWorld _gameWorld; 
	private final static Integer CSCI1950ProjectScreenIndex = 0;
	private NINXMLGameHandler _xmlGameHandler; 
	public NINMenuBar(AspectRatioHandler aspect) {
		super(aspect,40 ,Color.BLUE);
		this.initializeMenuButtons();
		this.initializePanelViews();
		this.setXMLGameHandler(new NINXMLGameHandler());
		this.setColor = Color.DARKBLUE;
	}
	public void initializeMenuButtons () {
		// Save Game
		Button saveGame = new Button(); 
		saveGame.setText("Save");
		saveGame.setSize( new Vec2d(100,30));
		saveGame.setColor( Color.WHITE);
		saveGame.setFontName(EngineFonts.getAlc());
		this.insertButton(saveGame.getText(),saveGame);	
		// Load Game
		Button loadGame = new Button();
		loadGame.setText("Load");
		loadGame.setSize(new Vec2d(100,30));
		loadGame.setColor( Color.WHITE);
		loadGame.setFontName(EngineFonts.getAlc());
		this.insertButton(loadGame.getText() ,loadGame);
		// End Game
		Button endGame = new Button();
		endGame.setText("End");
		endGame.setSize(new Vec2d(100,30));
		endGame.setColor( Color.WHITE);
		endGame.setFontName(EngineFonts.getAlc());
		this.insertButton(endGame.getText(),endGame);
	}	
	public void initializePanelViews () {
		// Save Game
		SaveGamePanel saveGamePanel = new SaveGamePanel(this.getAspectRatio());
		saveGamePanel.setFontName("Ethnocentric");
		saveGamePanel.setColor(Color.DARKGRAY);
		saveGamePanel.setSecondaryColor(Color.DARKBLUE);
		saveGamePanel.setSize( new Vec2d(600,100));
		saveGamePanel.setOrigin(new Vec2d(0,0));
		saveGamePanel.setBoarderSize(10);
		this.insertPanel(SAVE_GAME_PANEL_VIEW, saveGamePanel);	
		// Load Game Panel
		LoadGamePanel loadGamePanel = new LoadGamePanel( this.getAspectRatio()); 
		loadGamePanel.setColor(Color.DARKGRAY);
		loadGamePanel.setSecondaryColor(Color.DARKBLUE);
		loadGamePanel.setSize( new Vec2d(600,100));	
		loadGamePanel.setOrigin(new Vec2d(0,0));
		loadGamePanel.setBoarderSize(10);
		this.insertPanel((Integer)LOAD_GAME_PANEL_VIEW, loadGamePanel);
		// End Game Panel
		EndGamePanel endGamePanel = new EndGamePanel( this.getAspectRatio()); 
		endGamePanel.setFontName("Ethnocentric");
		endGamePanel.setColor(Color.DARKGRAY);
		endGamePanel.setSecondaryColor(Color.DARKBLUE);
		endGamePanel.setSize( new Vec2d(600,100));	
		endGamePanel.setOrigin(new Vec2d(0,0));
		endGamePanel.setBoarderSize(10);
		this.insertPanel((Integer)END_GAME_PANEL_VIEW, endGamePanel);
	}

	private void drawPanelView(GraphicsContext g) {
		if (this.isMenuActivated() == true)  {
			if (this.getContextHolder() == LOAD_GAME_PANEL_VIEW)  {
				LoadGamePanel panel = (LoadGamePanel) this.getPanelViews().get(LOAD_GAME_PANEL_VIEW);
				panel.onDraw(g);	
			} 
			else if (this.getContextHolder() == END_GAME_PANEL_VIEW) {
				EndGamePanel panel = (EndGamePanel) this.getPanelViews().get(END_GAME_PANEL_VIEW);
				panel.onDraw(g);	
			}
			else if (this.getContextHolder() == SAVE_GAME_PANEL_VIEW) {
				SaveGamePanel panel = (SaveGamePanel) this.getPanelViews().get(SAVE_GAME_PANEL_VIEW);
				panel.onDraw(g);	
			}
		}
	}
	public void onMouseClicked(MouseEvent e) 
	{
		if (this.isMenuActivated() == false) 
		{
			this.checkMenuButtonActivation(e);			
		} 
		else 
		{
			if (this.getContextHolder() == LOAD_GAME_PANEL_VIEW) 
			{
				LoadGamePanel panel = (LoadGamePanel) this.getPanelViews().get(LOAD_GAME_PANEL_VIEW);
				panel.onMouseClicked(e);

				if (panel.getOKButton().clicked(e)) 
				{
					this.getXMLGameHandler().loadGame(_gameWorld);
					return;
				}

				if ( panel.isShowing() == false) 
				{
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}

			} 
			else if (this.getContextHolder() == SAVE_GAME_PANEL_VIEW) 
			{	
				SaveGamePanel panel = (SaveGamePanel) this.getPanelViews().get(SAVE_GAME_PANEL_VIEW);
				panel.onMouseClicked(e);

				if (panel.getOKButton().clicked(e)) 
				{
					this.getXMLGameHandler().saveGame(_gameWorld);					
					return;
				}

				if ( panel.isShowing() == false) 
				{
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}

			} 
			else if (this.getContextHolder() == END_GAME_PANEL_VIEW) 
			{	
				EndGamePanel panel = (EndGamePanel) this.getPanelViews().get(END_GAME_PANEL_VIEW);
				panel.onMouseClicked(e);	
				if (panel.getOKButton().clicked(e)) 
				{
					this.getGameWorld().getApplication().addLevel(GameMask.CSCI1950ProjectScreenIndex, 
							new CSCI1950ProjectScreen(this.getGameWorld().getApplication()));
					this.getGameWorld().getApplication().setLevel(CSCI1950ProjectScreenIndex);
					return;
				}
				if ( panel.isShowing() == false) 
				{
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			} 
		}
	}

	private void checkMenuButtonActivation(MouseEvent e) 
	{
		if (this.checkMenuCollision(e) == true) 
		{	
			String buttonPushed = this.checkButtonCollision(e);
			if (!buttonPushed.isEmpty()) 
			{				
				this.setMenuActivated(true);
				// Give the contexts to right button.
				if (buttonPushed.contains("Save"))
				{
					this.setContextHolder(SAVE_GAME_PANEL_VIEW);
				}
				else if (buttonPushed.contains("Load")) 
				{					
					this.setContextHolder(LOAD_GAME_PANEL_VIEW);
				}
				else if (buttonPushed.contains("End"))
				{					
					this.setContextHolder(END_GAME_PANEL_VIEW);
				}
				else 
				{
					this.setMenuActivated(false);
					return;
				}
			}
		}	

	}
	public void onTick(long nanosSincePreviousTick) {}
	public void onKeyTyped(KeyEvent e) {}
	public void onMousePressed(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}
	public void onKeyReleased(KeyEvent e) {}
	private Integer getContextHolder() {
		return _contextHolder;
	}

	private void setContextHolder(Integer _contextHolder) {
		this._contextHolder = _contextHolder;
	}

	public void setGameWorld(NinGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}

	private NinGameWorld getGameWorld() {
		return _gameWorld;
	}

	public void onDraw(GraphicsContext g) {
		this.drawPanelView(g);
		this.draw(g);
	}

	public void onKeyPressed(KeyEvent e) {

	}

	private NINXMLGameHandler getXMLGameHandler() {
		return _xmlGameHandler;
	}

	private void setXMLGameHandler(NINXMLGameHandler _xmlGameHandler) {
		this._xmlGameHandler = _xmlGameHandler;
	}
}
