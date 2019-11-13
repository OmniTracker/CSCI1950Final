package wizard.ui;

import nin.ui.InstructionPanel;
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
	private Button _restartButton = null;
	private boolean DEBUG = false;
	private WIZRestart _restartGame = null; 
	
	public WIZMenuBar(AspectRatioHandler aspect) {
		super(aspect,40 ,Color.WHITE);	
		this.initializeMenuButtons();
		this.initializePanelViews(); 
	}	

	public void initializePanelViews ()  {
		// Instructions panel
		InstructionPanel intructionsPanel = new InstructionPanel( this.getAspectRatio() );
		intructionsPanel.setColor(Color.DARKGRAY);
		intructionsPanel.setSecondaryColor(Color.GREEN);
		intructionsPanel.setSize( new Vec2d(600,300));
		intructionsPanel.setOrigin(new Vec2d(0,0));
		intructionsPanel.setBoarderSize(10);
		this.insertPanel(INSTRUCTIONS_PANEL_VIEW, intructionsPanel);
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
			}
		}
	}
	@Override
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

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onFocusChanged(boolean newVal) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onShutdown() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onStartup() {
		// TODO Auto-generated method stub
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

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	private Button getRestartButton() {
		return _restartButton;
	}

	private void setRestartButton(Button _restartButton) {
		this._restartButton = _restartButton;
	}

	public WIZRestart getRestartGame() {
		return _restartGame;
	}

	public void setRestartGame(WIZRestart _restartGame) {
		this._restartGame = _restartGame;
	}

}
