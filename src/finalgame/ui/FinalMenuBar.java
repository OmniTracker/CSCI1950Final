package finalgame.ui;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

import java.net.MalformedURLException;

import engine.ui.Button;
import engine.ui.EngineFonts;
import engine.ui.MenuBar;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;
import finalgame.engineAdditions.PlayerInputComponent;
import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.FinalGameWorld.VisibleGameWorld;

public class FinalMenuBar extends MenuBar implements EventHandler{
	private Integer _contextHolder          = -1; 
	private Integer INSTRUCTIONS_PANEL_VIEW = 3; 
	private Integer END_GAME_PANEL_VIEW     = 5; 
	private Integer OPTIONS_PANEL_VIEW      = 8; 
	private Integer UPGRADES_PANEL_VIEW     = 9;
	private Integer INPUT_PANEL_VIEW = 10;


	private FinalGameWorld _gameWorld;
	private boolean DEBUG = false;
	private double _transitionAlpha = 0.0; 
	public FinalMenuBar(AspectRatioHandler aspect) 
	{
		super(aspect,40 ,Color.DARKGRAY);
		this.initializeMenuButtons();
		this.initializePanelViews(); 
	}
	public void setMenuHeight (double height) 
	{
		this.setHeight(height);
	}
	public void onDraw(GraphicsContext g) 
	{		
		if (this.getGameWorld().getMainGamePlay() == this.getGameWorld().getCurrentlySelectedScreen()) 
		{
			this.drawPanelView(g);
			this.draw(g);
			this.fillTransition(g);
		}
	}
	private void fillTransition(GraphicsContext g) 
	{
		Vec2d origin = this.getAspectRatio().calculateUpdatedOrigin();
		Vec2d size = this.getAspectRatio().calculateUpdatedScreenSize();
		g.setGlobalAlpha(_transitionAlpha);
		g.setFill(Color.BLACK);
		g.fillRect(origin.x, origin.y, size.x, size.y);
		g.setGlobalAlpha(1);
		if (_transitionAlpha != 0) 
		{
			_transitionAlpha += 0.05;
		}
		if (_transitionAlpha > 1.3) 
		{
			_transitionAlpha = 0;
			this.setContextHolder(-1);
			this.setMenuActivated(false);
			this.getGameWorld().changeCurrentScreen(VisibleGameWorld.INTRODUCTION);
		}
	}
	public void initializePanelViews () 
	{
		// Instructions panel
		InstructionPanel intructionsPanel = new InstructionPanel(this.getAspectRatio());
		intructionsPanel.setColor(Color.DARKGRAY);
		intructionsPanel.setSecondaryColor(Color.DARKGREEN);
		intructionsPanel.setSize( new Vec2d(600,300));
		intructionsPanel.setOrigin(new Vec2d(0,0));
		intructionsPanel.setBoarderSize(10);
		this.insertPanel(INSTRUCTIONS_PANEL_VIEW, intructionsPanel);
		// Options Panel
		OptionsPanel optionsPanel = new OptionsPanel( this.getAspectRatio()); 
		optionsPanel.setColor(Color.DARKGRAY);
		optionsPanel.setSecondaryColor(Color.DARKGREEN);
		optionsPanel.setSize( new Vec2d(1000,600));	
		optionsPanel.setOrigin(new Vec2d(0,0));
		optionsPanel.setBoarderSize(10);
		this.insertPanel((Integer)OPTIONS_PANEL_VIEW, optionsPanel);
		// End Game Panel
		EndGamePanel endGamePanel = new EndGamePanel( this.getAspectRatio()); 
		endGamePanel.setColor(Color.DARKGRAY);
		endGamePanel.setSecondaryColor(Color.DARKGREEN);
		endGamePanel.setSize( new Vec2d(300,200));	
		endGamePanel.setOrigin(new Vec2d(0,0));
		endGamePanel.setBoarderSize(10);
		this.insertPanel((Integer)END_GAME_PANEL_VIEW, endGamePanel);

		// End Game Panel
		UpgradesPanel upgradesPanel = new UpgradesPanel( this.getAspectRatio()); 
		upgradesPanel.setColor(Color.DARKGRAY);
		upgradesPanel.setSecondaryColor(Color.DARKGREEN);
		upgradesPanel.setSize( new Vec2d(500,400));	
		upgradesPanel.setOrigin(new Vec2d(0,0));
		upgradesPanel.setBoarderSize(10);
		this.insertPanel((Integer)UPGRADES_PANEL_VIEW, upgradesPanel);
		
		//Input game panel
		InputPanel inputPanel = new InputPanel( this.getAspectRatio()); 
		inputPanel.setColor(Color.DARKGRAY);
		inputPanel.setSecondaryColor(Color.DARKGREEN);
		inputPanel.setSize( new Vec2d(500,400));	
		inputPanel.setOrigin(new Vec2d(0,0));
		inputPanel.setBoarderSize(10);
		this.insertPanel((Integer)INPUT_PANEL_VIEW, inputPanel);
	}
	public void initializeMenuButtons () 
	{
		// Instructions
		Button instructions = new Button(); 
		instructions.setText("Instructions");
		instructions.setSize( new Vec2d(100,30));
		instructions.setColor( Color.WHITE);
		instructions.setFontName(EngineFonts.getWiz());
		this.insertButton(instructions.getText(),instructions);		
		// Options
		Button options = new Button();
		options.setText("Options");
		options.setSize(new Vec2d(100,30));
		options.setColor( Color.WHITE);
		options.setFontName(EngineFonts.getWiz());
		this.insertButton(options.getText() ,options);
		// End Game
		Button endGame = new Button();
		endGame.setText("End Game");
		endGame.setSize(new Vec2d(100,30));
		endGame.setColor( Color.WHITE);
		endGame.setFontName(EngineFonts.getWiz());
		this.insertButton(endGame.getText(),endGame);

		// Upgrades
		Button upgrades = new Button();
		upgrades.setText("Upgrades");
		upgrades.setSize(new Vec2d(100,30));
		upgrades.setColor( Color.WHITE);
		upgrades.setFontName(EngineFonts.getWiz());
		this.insertButton(upgrades.getText(),upgrades);
	}	
	private void drawPanelView(GraphicsContext g) 
	{
		if (this.isMenuActivated() == true)  
		{
			if (this.getContextHolder() == INSTRUCTIONS_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("INSTRUCTIONS HAS CONTEXT \n");
				}
				InstructionPanel panel = (InstructionPanel) this.getPanelViews().get(INSTRUCTIONS_PANEL_VIEW);
				panel.drawRounded(g);	
			} 
			else if (this.getContextHolder() == OPTIONS_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("CONTROL HAS CONTEXT \n");
				}	
				OptionsPanel panel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
				panel.onDraw(g);
			}
			else if (this.getContextHolder() == END_GAME_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("END GAME HAS CONTEXT \n");
				}	
				EndGamePanel panel = (EndGamePanel) this.getPanelViews().get(END_GAME_PANEL_VIEW);
				panel.onDraw(g);
			}
			else if (this.getContextHolder() == UPGRADES_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("UPGRADES HAS CONTEXT \n");
				}	
				UpgradesPanel panel = (UpgradesPanel) this.getPanelViews().get(UPGRADES_PANEL_VIEW);
				panel.onDraw(g);
			}
			else if (this.getContextHolder() == INPUT_PANEL_VIEW) 
			{
				if (DEBUG == true)
				{
					System.out.println("INPUT HAS CONTEXT \n");
				}
				InputPanel panel = (InputPanel) this.getPanelViews().get(INPUT_PANEL_VIEW);
				try {
					panel.onDraw(g);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				if (buttonPushed.contains("Instructions"))
				{
					this.setContextHolder(INSTRUCTIONS_PANEL_VIEW);
				}
				else if (buttonPushed.contains("Options")) 
				{					
					this.setContextHolder(OPTIONS_PANEL_VIEW);
					OptionsPanel panel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
					panel.initKeyBindingButtons();
				}
				else if (buttonPushed.contains("End Game"))
				{					
					this.setContextHolder(END_GAME_PANEL_VIEW);
				}
				else if (buttonPushed.contains("Upgrades"))
				{					
					this.setContextHolder(UPGRADES_PANEL_VIEW);
				}
				else 
				{
					return;
				}
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
			if (this.getContextHolder() == INSTRUCTIONS_PANEL_VIEW) 
			{
				InstructionPanel panel = (InstructionPanel) this.getPanelViews().get(INSTRUCTIONS_PANEL_VIEW);
				panel.onMouseClicked(e);				
				// Check if the window has been closed
				if ( panel.isShowing() == false) 
				{
					// Reset Context holders
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			} 
			else if (this.getContextHolder() == OPTIONS_PANEL_VIEW) 
			{	
				OptionsPanel panel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
				panel.onMouseClicked(e);	
				// Check if the window has been closeduu
				if ( panel.isShowing() == false)
				{
					System.out.println("option close");
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
					_gameWorld.getMainGamePlay().getKeys();
					_gameWorld.getMainGamePlay().get_gamePlayOverlay().setKeyValues(_gameWorld.getMainGamePlay().getPlaceHolders());
					PlayerInputComponent curr = (PlayerInputComponent)_gameWorld.getMainGamePlay().get_player().getComponent("INPUT");
					curr.setAbilityKeys(_gameWorld.getMainGamePlay().getPlaceHolders());
				}
			} 
			else if (this.getContextHolder() == END_GAME_PANEL_VIEW) 
			{	
				EndGamePanel panel = (EndGamePanel) this.getPanelViews().get(END_GAME_PANEL_VIEW);
				if (panel.getOKButton().clicked(e)) 
				{
					// Start transition to start screen.
					OptionsPanel opanel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
					if (opanel.shouldUpdateHighScores(_gameWorld.getMainGamePlay().get_highScore())) {
						this.setContextHolder(INPUT_PANEL_VIEW);
					}else {
						_transitionAlpha += 0.01;
					}
					return;
				}
				panel.onMouseClicked(e);
				// Check if the window has been closed
				if ( panel.isShowing() == false) 
				{
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			}
			else if (this.getContextHolder() == UPGRADES_PANEL_VIEW) 
			{	
				UpgradesPanel panel = (UpgradesPanel) this.getPanelViews().get(UPGRADES_PANEL_VIEW);
				panel.onMouseClicked(e);	
				// Check if the window has been closed
				if ( panel.isShowing() == false)
				{
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			}
			else if (this.getContextHolder() == INPUT_PANEL_VIEW)
			{
				InputPanel input = (InputPanel) this.getPanelViews().get(INPUT_PANEL_VIEW);
				input.onMouseClicked(e);
				if (input.getOKButton().clicked(e) || input.isShowing()==false) {
					OptionsPanel opanel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
					opanel.UpdateHighScores(_gameWorld.getMainGamePlay().get_highScore(), input.getName());
					_transitionAlpha+=0.01;
					return;
				}
			}
		}
	}
	public void onKeyPressed(KeyEvent e) 
	{	
		
		if (this.isMenuActivated()) 
		{
			if (this.getContextHolder() == OPTIONS_PANEL_VIEW) 
			{
				OptionsPanel panel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
				panel.onKeyPressed(e);
			}
			if (this.getContextHolder() == INPUT_PANEL_VIEW)
			{
				InputPanel panel = (InputPanel) this.getPanelViews().get(INPUT_PANEL_VIEW);
				panel.onKeyPressed(e);
			}
		}
		
		if (e.getCode().toString().equals("U")  && 
				this.isMenuActivated() == false && 
				this.getGameWorld().getMainGamePlay() == 
				this.getGameWorld().getCurrentlySelectedScreen()) 
		{	
			this.setMenuActivated(true);
			this.setContextHolder(UPGRADES_PANEL_VIEW);	
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
	private FinalGameWorld getGameWorld() {
		return _gameWorld;
	}
	public void setGameWorld(FinalGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
