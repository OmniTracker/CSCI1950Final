package finalgame.ui;

import nin.ui.ControlsPanel;
import nin.ui.InstructionPanel;
import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import engine.ui.Button;
import engine.ui.EngineFonts;
import engine.ui.MenuBar;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class FinalMenuBar extends MenuBar implements EventHandler{

	private Integer _contextHolder           = -1; 
	private  Integer RESTART                 = 0; 
	private  Integer PAUSE                   = 1; 
	private  Integer INSTRUCTIONS_PANEL_VIEW = 3; 
	private  Integer CONTROL_PANEL_VIEW      = 4; 
	private  Integer OPTIONS_PANEL_VIEW      = 8; 
	private  Integer KEY_BINDING_PANEL_VIEW  = 12;
	private KeyBindingPanel keyBindingPanel;

	private Button _pauseButton = null;

	private boolean DEBUG = false;

	public FinalMenuBar(AspectRatioHandler aspect) {
		super(aspect,40 ,Color.DARKGRAY);
		this.initializeMenuButtons();
		this.initializePanelViews(); 
	}

	public void initializePanelViews () {
		// Instructions panel
		InstructionPanel intructionsPanel = new InstructionPanel( this.getAspectRatio() );
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
		optionsPanel.setSize( new Vec2d(600,400));	
		optionsPanel.setOrigin(new Vec2d(0,0));
		optionsPanel.setBoarderSize(10);
		this.insertPanel((Integer)OPTIONS_PANEL_VIEW, optionsPanel);
		// Control Panel
		ControlsPanel controlsPanel = new ControlsPanel( this.getAspectRatio()); 
		controlsPanel.setColor(Color.DARKGRAY);
		controlsPanel.setSecondaryColor(Color.DARKGREEN);
		controlsPanel.setSize( new Vec2d(600,400));	
		controlsPanel.setOrigin(new Vec2d(0,0));
		controlsPanel.setBoarderSize(10);
		this.insertPanel((Integer)CONTROL_PANEL_VIEW, controlsPanel);
		// Key Binding Panel
		keyBindingPanel = new KeyBindingPanel( this.getAspectRatio()); 
		keyBindingPanel.setColor(Color.DARKGRAY);
		keyBindingPanel.setSecondaryColor(Color.DARKGREEN);
		keyBindingPanel.setSize( new Vec2d(600,400));	
		keyBindingPanel.setOrigin(new Vec2d(0,0));
		keyBindingPanel.setBoarderSize(10);
		this.insertPanel((Integer)KEY_BINDING_PANEL_VIEW, keyBindingPanel);
	}

	public void initializeMenuButtons () {
		// Controls Panel
		Button controls = new Button(); 
		controls.setText("Controls");
		controls.setSize( new Vec2d(100,30));
		controls.setColor( Color.WHITE);
		controls.setFontName(EngineFonts.getNin());
		this.insertButton(controls.getText(),controls);
		// Instructions
		Button instructions = new Button(); 
		instructions.setText("Instructions");
		instructions.setSize( new Vec2d(100,30));
		instructions.setColor( Color.WHITE);
		instructions.setFontName(EngineFonts.getNin());
		this.insertButton(instructions.getText(),instructions);		
		// Options
		Button options = new Button();
		options.setText("Options");
		options.setSize(new Vec2d(100,30));
		options.setColor( Color.WHITE);
		options.setFontName(EngineFonts.getNin());
		this.setPauseButton(options);
		this.insertButton(options.getText() ,options);
		// Restart
		Button restart = new Button();
		restart.setText("Restart");
		restart.setSize( new Vec2d(100,30));
		restart.setColor( Color.WHITE);
		restart.setFontName(EngineFonts.getNin());
		this.insertButton(restart.getText(),restart);
		// Pause Game
		Button pause = new Button();
		pause.setText("Pause");
		pause.setSize(new Vec2d(100,30));
		pause.setColor( Color.WHITE);
		pause.setFontName(EngineFonts.getNin());
		this.setPauseButton(pause);
		this.insertButton(pause.getText(),pause);
		// Key Binding 
		Button  keyBinding = new Button();
		keyBinding.setText("KeyBinding");
		keyBinding.setSize(new Vec2d(100,30));
		keyBinding.setColor( Color.WHITE);
		keyBinding.setFontName(EngineFonts.getNin());
		this.setPauseButton(keyBinding);
		this.insertButton(keyBinding.getText(),keyBinding);
	}	
	public void setMenuHeight (double height) {
		this.setHeight(height);
	}	

	@Override
	public void onTick(long nanosSincePreviousTick) {

	}

	private void drawPanelView(GraphicsContext g) {

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
			else if (this.getContextHolder() == CONTROL_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("CONTROL HAS CONTEXT \n");
				}	
				ControlsPanel panel = (ControlsPanel) this.getPanelViews().get(CONTROL_PANEL_VIEW);
				panel.drawRounded(g);	
			}
			else if (this.getContextHolder() == OPTIONS_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("CONTROL HAS CONTEXT \n");
				}	
				OptionsPanel panel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
				panel.drawPanelWithSliders(g);
			}
			else if (this.getContextHolder() == KEY_BINDING_PANEL_VIEW) 
			{
				if (DEBUG == true) 
				{
					System.out.print("CONTROL HAS CONTEXT \n");
				}	
				KeyBindingPanel panel = (KeyBindingPanel) this.getPanelViews().get(KEY_BINDING_PANEL_VIEW);
				panel.onDraw(g);
			}
		}
	}
	private void checkMenuButtonActivation(MouseEvent e) {
		if (this.checkMenuCollision(e) == true) 
		{	
			String buttonPushed = this.checkButtonCollision(e);
			if (!buttonPushed.isEmpty()) 
			{				
				this.setMenuActivated(true);
				// Give the contexts to right button.
				if (buttonPushed.contains("Controls"))
				{
					this.setContextHolder(CONTROL_PANEL_VIEW);
				} 
				else if (buttonPushed.contains("Instructions"))
				{
					this.setContextHolder(INSTRUCTIONS_PANEL_VIEW);
				}
				else if (buttonPushed.contains("Options")) 
				{					
					this.setContextHolder(OPTIONS_PANEL_VIEW);
				}
				else if (buttonPushed.contains("Restart")) 
				{
					this.setContextHolder(RESTART);
				} 
				else if (buttonPushed.contains("Pause")) 
				{
					this.setContextHolder(PAUSE);
					this.getPauseButton().setText("Resume");
				} 
				else if (buttonPushed.contains("KeyBinding"))
				{
					this.setContextHolder(KEY_BINDING_PANEL_VIEW);
					keyBindingPanel.initKeyBindingButtons();
				}
				else 
				{
					return;
				}
			}
		}
	}

	public void onKeyTyped(KeyEvent e) {

	}


	public void onDraw(GraphicsContext g) 
	{		
		this.drawPanelView(g);
		this.draw(g);
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
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			} 
			else if (this.getContextHolder() == CONTROL_PANEL_VIEW) 
			{
				ControlsPanel panel = (ControlsPanel) this.getPanelViews().get(CONTROL_PANEL_VIEW);
				panel.onMouseClicked(e);
				// Check if the window has been closed

				if ( panel.isShowing() == false) {
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			} 
			else if (this.getContextHolder() == OPTIONS_PANEL_VIEW) 
			{
				OptionsPanel panel = (OptionsPanel) this.getPanelViews().get(OPTIONS_PANEL_VIEW);
				panel.onMouseClicked(e);
				// Check if the window has been closed
				if ( panel.isShowing() == false) {
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			} 
			else if (this.getContextHolder() == KEY_BINDING_PANEL_VIEW) 
			{
				KeyBindingPanel panel = (KeyBindingPanel) this.getPanelViews().get(KEY_BINDING_PANEL_VIEW);
				panel.onMouseClicked(e);
				// Check if the window has been closed
				if ( panel.isShowing() == false) {
					// Reset Context holder
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}

			}
			else if (this.getContextHolder() == PAUSE) 
			{
				if ( this.getPauseButton().clicked(e) ) 
				{
					this.getPauseButton().setText("Pause");
					this.setContextHolder(-1);
					this.setMenuActivated(false);
				}
			}
		}
	}
	public void onMousePressed(MouseEvent e) {

	}

	public void onKeyPressed(KeyEvent e) 
	{	
		if (this.isMenuActivated()) 
		{
			if (this.getContextHolder() == KEY_BINDING_PANEL_VIEW) 
			{
				KeyBindingPanel panel = (KeyBindingPanel) this.getPanelViews().get(KEY_BINDING_PANEL_VIEW);
				panel.onKeyPressed(e);
			}
		}	
	}
	@Override
	public void onMouseDragged(MouseEvent e) {}
	@Override
	public void onMouseReleased(MouseEvent e) {}
	@Override
	public void onMouseMoved(MouseEvent e) {}
	@Override
	public void onMouseWheelMoved(ScrollEvent e) {}
	@Override
	public void onFocusChanged(boolean newVal) {}
	@Override
	public void onResize(Vec2d newSize) {}
	@Override
	public void onShutdown() {}
	@Override
	public void onStartup() {}
	@Override
	public void onKeyReleased(KeyEvent e) {}
	private Integer getContextHolder() {
		return _contextHolder;
	}
	private void setContextHolder(Integer _contextHolder) {
		this._contextHolder = _contextHolder;
	}
	private Button getPauseButton() {
		return _pauseButton;
	}
	private void setPauseButton(Button _pauseButton) {
		this._pauseButton = _pauseButton;
	}
}
