package finalgame.maingameloop.gameworldmanager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.ui.EngineFonts;
import finalgame.ui.HighScorePanel;

public class MainGamePlay extends GameWorld {

	private boolean _highScoreTest = true;
	private HighScorePanel _highScorePanel = null;
	private Button _highScoreTestButton = null;

	public MainGamePlay(Application app, GameWorld parent) {
		super(app);
		this.setupGeneralUI();
	}

	private void setupGeneralUI () 
	{
		// Options Panel
		HighScorePanel highScorePanel = new HighScorePanel( this.getApplication().getAspectRatioHandler());
		highScorePanel.setColor(Color.DARKGRAY);
		highScorePanel.setSecondaryColor(Color.DARKGREEN);
		highScorePanel.setSize( new Vec2d(500,300));
		highScorePanel.setOrigin(new Vec2d(0,0));
		highScorePanel.setBoarderSize(10);
		this.setHighScorePanel(highScorePanel);
		if (_highScoreTest == true)
		{
			// Options Button
			Button highScoreTestButton = new Button(); 
			highScoreTestButton.setText("Test High Score");
			highScoreTestButton.setSize( new Vec2d(250,70));
			highScoreTestButton.setColor(Color.BLUE);
			highScoreTestButton.setFontName(EngineFonts.getAlc());
			this.setHighScoreTestButton(highScoreTestButton);
		}
	}
	public void onKeyPressed(KeyEvent e) 
	{	
		if ( this.getHighScorePanel().isShowing() == true) 
		{
			this.getHighScorePanel().onKeyPressed(e);
		} 		
	}

	public void onDraw(GraphicsContext g) 
	{
		if (this.getHighScoreTestButton() != null) 
		{	
			Vec2d origin   = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin();
			Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
			Vec2d buttonOrigin = origin.plus( (size.x / 2), (size.y / 2));
			this.getHighScoreTestButton().setOrigin(buttonOrigin);
			this.getHighScoreTestButton().drawRounded(g);
		}

		if (this.getHighScorePanel().isShowing()) 
		{
			this.getHighScorePanel().onDraw(g);
		}
	}
	public void onTick(long nanosSincePreviousTick) {

	}
	public void onMouseClicked(MouseEvent e) 
	{
		if ( this.getHighScorePanel().isShowing() == false) 
		{
			if (this.getHighScoreTestButton() != null) 
			{
				// Prompt fake high score button.
				if ( this.getHighScoreTestButton().clicked(e) ) 
				{
					this.getHighScorePanel().setShowing(true);
				}
			}
		} 
		else 
		{	
			this.getHighScorePanel().onMouseClicked(e);
		}
	}
	private Button getHighScoreTestButton() {
		return _highScoreTestButton;
	}
	private void setHighScoreTestButton(Button _highScoreTestButton) {
		this._highScoreTestButton = _highScoreTestButton;
	}
	private HighScorePanel getHighScorePanel() {
		return _highScorePanel;
	}
	private void setHighScorePanel(HighScorePanel _highScorePanel) {
		this._highScorePanel = _highScorePanel;
	}
	public void onKeyTyped(KeyEvent e) {}
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
}
