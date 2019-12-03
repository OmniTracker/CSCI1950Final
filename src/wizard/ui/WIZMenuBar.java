package wizard.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import wizard.utils.WIZRestart;
import engine.ui.Button;
import engine.ui.EngineFonts;
import engine.ui.MenuBar;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class WIZMenuBar extends MenuBar implements EventHandler {
	// This is the button that took control of the game. Set to Zero for now.
	private Integer _contextHolder           = -1; 
	private  Integer PAUSE                   = 1; 
	private  Integer INSTRUCTIONS_PANEL_VIEW = 3; 
	private Button _pauseButton = null;
	private boolean DEBUG = false;
	private WIZRestart _restartGame = null; 
	private boolean miniMap = true;
	public WIZMenuBar(AspectRatioHandler aspect) {
		super(aspect,40 ,Color.WHITE);	
		this.initializeMenuButtons();
		this.initializePanelViews(); 
	}	
	public void initializePanelViews ()  {
		// Instructions panel
		WIZInstructionPanel intructionsPanel = new WIZInstructionPanel( this.getAspectRatio() );
		intructionsPanel.setColor(Color.DARKGRAY);
		intructionsPanel.setSecondaryColor(Color.GREEN);
		intructionsPanel.setSize( new Vec2d(600,300));
		intructionsPanel.setOrigin(new Vec2d(0,0));
		intructionsPanel.setBoarderSize(10);
		this.insertPanel(INSTRUCTIONS_PANEL_VIEW, intructionsPanel);
		this.setMenuActivated(true);
		this.setContextHolder(INSTRUCTIONS_PANEL_VIEW);
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
				WIZInstructionPanel panel = (WIZInstructionPanel) this.getPanelViews().get(INSTRUCTIONS_PANEL_VIEW);
				panel.onDraw(g);
			} 
		}
	}
	public void initializeMenuButtons () {
		// Instructions
		Button instructions = new Button(); 
		instructions.setText("Instructions");
		instructions.setSize( new Vec2d(100,30));
		instructions.setColor( Color.WHITE);
		instructions.setFontName(EngineFonts.getWiz());
		this.insertButton(instructions.getText(),instructions);		
		// Restart
		Button restart = new Button();
		restart.setText("Restart");
		restart.setSize( new Vec2d(100,30));
		restart.setColor( Color.WHITE);
		restart.setFontName(EngineFonts.getWiz());
		this.insertButton(restart.getText(),restart);

		// Pause Game
		Button pause = new Button();
		pause.setText("Pause");
		pause.setSize(new Vec2d(100,30));
		pause.setColor( Color.WHITE);
		pause.setFontName(EngineFonts.getWiz());
		this.setPauseButton(pause);
		this.insertButton(pause.getText() ,pause);
		
		// Mini Map
		Button minimap = new Button();
		minimap.setText("Mini_Map");
		minimap.setSize(new Vec2d(100,30));
		minimap.setColor( Color.WHITE);
		minimap.setFontName(EngineFonts.getWiz());
		this.setPauseButton(minimap);
		this.insertButton(minimap.getText() ,minimap);
	}	
	public void setMenuHeight (double height) {
		this.setHeight(height);
	}	
	public void onDraw(GraphicsContext g) {
		this.drawPanelView(g);
		this.draw(g);
	}
	private void checkMenuButtonActivation(MouseEvent e) 
	{	
		if (this.checkMenuCollision(e) == true) 
		{	
			String buttonPushed = this.checkButtonCollision(e);

			if (!buttonPushed.isEmpty()) 
			{			
				// Give the contexts to right button.
				if (buttonPushed.contains("Instructions"))
				{
					this.setMenuActivated(true);
					this.setContextHolder(INSTRUCTIONS_PANEL_VIEW);
				} 
				else if (buttonPushed.contains("Pause")) 
				{
					this.setMenuActivated(true);
					this.setContextHolder(PAUSE);
					this.getPauseButton().setText("Resume");
				}  
				else if (buttonPushed.contains("Restart"))
				{
					this.getRestartGame().restartLevel();
				} 
				else if  (buttonPushed.contains("Mini_Map")) 
				{
					if (this.isMiniMap() == true) 
					{
						this.setMiniMap(false);
					}
					else 
					{
						this.setMiniMap(true);
					}
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
				WIZInstructionPanel panel = (WIZInstructionPanel) this.getPanelViews().get(INSTRUCTIONS_PANEL_VIEW);
				panel.onMouseClicked(e);				
				// Check if the window has been closed
				if ( panel.isShowing() == false) 
				{
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
	private Button getPauseButton() {
		return _pauseButton;
	}

	private void setPauseButton(Button _pauseButton) {
		this._pauseButton = _pauseButton;
	}

	private Integer getContextHolder() {
		return _contextHolder;
	}

	private void setContextHolder(Integer _contextHolder) {
		this._contextHolder = _contextHolder;
	}
	public WIZRestart getRestartGame() {
		return _restartGame;
	}
	public void setRestartGame(WIZRestart _restartGame) {
		this._restartGame = _restartGame;
	}
	public void onKeyTyped(KeyEvent e) {}
	public void onKeyPressed(KeyEvent e) {}
	public void onKeyReleased(KeyEvent e) {}
	public void onMousePressed(MouseEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}
	public void onTick(long nanosSincePreviousTick) {}
	public boolean isMiniMap() {
		return miniMap;
	}
	private void setMiniMap(boolean miniMap) {
		this.miniMap = miniMap;
	}
}
