package finalgame.maingameloop.gameworldmanager;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.ui.EngineFonts;
import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.FinalGameWorld.VisibleGameWorld;
import finalgame.ui.HighScorePanel;

public class Introduction extends GameWorld {
	private HighScorePanel _highScorePanel;
	private Button _highScoreButton;
	private Button _selectPlayerButton;
	private FinalGameWorld _finalGameWorld;
	private float alphaIncrement = 0.0f;
	private double _transitionAlpha = 0.0;
	public Introduction(Application app, GameWorld parent) {
		super(app);
		this.setFinalGameWorld((FinalGameWorld) parent);
		this.setupGeneralUI();
	}
	private void setupGeneralUI () {
		// High Score Panel
		HighScorePanel highScorePanel = new HighScorePanel( this.getApplication().getAspectRatioHandler());
		highScorePanel.setColor(Color.DARKGRAY);
		highScorePanel.setSecondaryColor(Color.DARKGREEN);
		highScorePanel.setSize( new Vec2d(1000,500));
		highScorePanel.setOrigin(new Vec2d(0,0));
		highScorePanel.setBoarderSize(10);
		this.setHighScorePanel(highScorePanel);
		// High Score Button
		Button highScoreButton = new Button(); 
		highScoreButton.setText("High Scores");
		highScoreButton.setSize( new Vec2d(250,70));
		highScoreButton.setColor(Color.WHITE);
		highScoreButton.setFontName(EngineFonts.getAlc());
		this.setHighScoreButton(highScoreButton);
		// Select Player Button
		Button selectPlayerButton = new Button(); 
		selectPlayerButton.setText("Select Player");
		selectPlayerButton.setSize( new Vec2d(250,70));
		selectPlayerButton.setColor( Color.WHITE);
		selectPlayerButton.setFontName(EngineFonts.getAlc());
		this.setSelectPlayerButton(selectPlayerButton);
	}
	public void drawButtons  (GraphicsContext g) {
		double yOrigin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().y * 0.8); 
		double xOrigin1 = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().x * 0.1); 
		double xOrigin2 = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().x * 0.7); 
		this.getSelectPlayerButton().setOrigin(new Vec2d(xOrigin2,yOrigin));
		this.getHighScoreButton().setOrigin(new Vec2d(xOrigin1,yOrigin));
		this.getSelectPlayerButton().drawRounded(g);
		this.getHighScoreButton().drawRounded(g);
	}
	public void onTick(long nanosSincePreviousTick) {
		fadeOut();
	}
	private void fadeOut() {
		if (_transitionAlpha != 0.0) 
		{
			_transitionAlpha += .01;
		}
		if (_transitionAlpha > 1.0) 
		{
			this.getFinalGameWorld().getPlayerSelection().initScreen();
			this.getFinalGameWorld().getVisibleGameWorldEnum();
			this.getFinalGameWorld().changeCurrentScreen(VisibleGameWorld.MAINGAMEPLAY);
		}
	}
	public void onDraw(GraphicsContext g) {
		Image intro = this.getFinalGameWorld().getFinalGameObjectHandler().getIntroImage(); 
		Image introBackDrop = this.getFinalGameWorld().getFinalGameObjectHandler().getIntroBackdrop(); 
		Vec2d origin   = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin();
		Vec2d viewSize = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
		if (intro != null) {
			g.drawImage(introBackDrop,origin.x,origin.y,viewSize.x,viewSize.y);
			double xOffset = viewSize.x * 0.2; 
			g.setGlobalAlpha( 0.4);
			g.setFill( Color.WHITE);
			g.fillRoundRect((origin.x + xOffset),origin.y + 10,(viewSize.x - (xOffset * 2)),viewSize.y - xOffset, 60,60);
			g.drawImage(intro,(origin.x + xOffset) + 15,origin.y + 8,(viewSize.x - (xOffset * 2)),viewSize.y - xOffset);			
			g.setGlobalAlpha( 0.8);
			g.drawImage(intro,(origin.x + xOffset),origin.y,(viewSize.x - (xOffset * 2)),viewSize.y - xOffset);
			g.setGlobalAlpha(0.6);
			this.drawButtons(g);
			
			if ( this.getHighScorePanel().isShowing() == true) {
				if (alphaIncrement <= 0.7) {
					alphaIncrement += .01;
				}		
				g.setGlobalAlpha(alphaIncrement);
				this.getHighScorePanel().drawRounded(g);
			} 
			g.setGlobalAlpha(_transitionAlpha);
			g.setFill(Color.BLACK);
			g.fillRect(origin.x,origin.y,viewSize.x,viewSize.y);
			g.setGlobalAlpha(1);
		}
	}
	public void onMouseClicked(MouseEvent e) {
		if ( this.getHighScorePanel().isShowing() == false) 
		{
			alphaIncrement = 0.0f;	
			if (this.getHighScoreButton().clicked(e)) 
			{
				this.getHighScorePanel().setShowing(true);
			} 
			else if (this.getSelectPlayerButton().clicked(e)) 
			{
				_transitionAlpha = 0.01; 
			}
		} else {	
			this.getHighScorePanel().onMouseClicked(e);
		}
	}	
	private FinalGameWorld getFinalGameWorld() {
		return _finalGameWorld;
	}
	private void setFinalGameWorld(FinalGameWorld _finalGameWorld) {
		this._finalGameWorld = _finalGameWorld;
	}
	HighScorePanel getHighScorePanel() {
		return _highScorePanel;
	}
	void setHighScorePanel(HighScorePanel _highScorePanel) {
		this._highScorePanel = _highScorePanel;
	}
	private Button getHighScoreButton() {
		return _highScoreButton;
	}
	private void setHighScoreButton(Button _highScoreButton) {
		this._highScoreButton = _highScoreButton;
	}
	private Button getSelectPlayerButton() {
		return _selectPlayerButton;
	}
	private void setSelectPlayerButton(Button _selectPlayerButton) {
		this._selectPlayerButton = _selectPlayerButton;
	}
}
