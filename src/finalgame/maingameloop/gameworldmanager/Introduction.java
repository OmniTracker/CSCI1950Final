package finalgame.maingameloop.gameworldmanager;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import engine.ui.EngineFonts;
import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.FinalGameWorld.VisibleGameWorld;
import finalgame.ui.OptionsPanel;

public class Introduction extends GameWorld {
	private OptionsPanel _optionsPanel;
	private Button _optionsButton;
	private Button _selectPlayerButton;
	private FinalGameWorld _finalGameWorld;
	private double _transitionAlpha = 0.0;
	private float alphaIncrement = 0.0f;
	public Introduction(Application app, GameWorld parent) {
		super(app);
		this.setFinalGameWorld((FinalGameWorld) parent);
		this.setupGeneralUI();
	}
	private void setupGeneralUI () {
		// Options Panel
		OptionsPanel optionsPanel = new OptionsPanel( this.getApplication().getAspectRatioHandler());
		optionsPanel.setColor(Color.DARKGRAY);
		optionsPanel.setSecondaryColor(Color.DARKGREEN);
		optionsPanel.setSize( new Vec2d(1000,600));
		optionsPanel.setOrigin(new Vec2d(0,0));
		optionsPanel.setBoarderSize(10);
		this.setOptionsPanel(optionsPanel);
		// Options Button
		Button optionsButton = new Button(); 
		optionsButton.setText("Options");
		optionsButton.setSize( new Vec2d(250,70));
		optionsButton.setColor(Color.WHITE);
		optionsButton.setFontName(EngineFonts.getAlc());
		this.setOptionsButton(optionsButton);
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
		this.getOptionsButton().setOrigin(new Vec2d(xOrigin1,yOrigin));

		this.getSelectPlayerButton().drawRounded(g);
		this.getOptionsButton().drawRounded(g);
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
			_transitionAlpha = 0;
			alphaIncrement = 0.0f; 
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
			if ( this.getOptionsPanel().isShowing() == true) 
			{
				if (alphaIncrement <= 0.7) 
				{
					alphaIncrement += .01;
				}		
				g.setGlobalAlpha(alphaIncrement);
				this.getOptionsPanel().onDraw(g);
			} 		
			g.setGlobalAlpha(_transitionAlpha);
			g.setFill(Color.BLACK);
			g.fillRect(origin.x,origin.y,viewSize.x,viewSize.y);
			g.setGlobalAlpha(1);
		}
	}
	public void onKeyPressed(KeyEvent e) 
	{
		if (this.getOptionsPanel().isShowing() == true)
		{
			this.getOptionsPanel().onKeyPressed(e);
		}
	}
	public void onMouseClicked(MouseEvent e) {
		if ( this.getOptionsPanel().isShowing() == false) 
		{
			alphaIncrement = 0.0f;		
			if (this.getOptionsButton().clicked(e)) 
			{				
				this.getOptionsPanel().setShowing(true);
			} 
			else if (this.getSelectPlayerButton().clicked(e)) 
			{
				_transitionAlpha = 0.01; 
			}
		} 
		else 
		{	
			this.getOptionsPanel().onMouseClicked(e);
		}
	}	
	private FinalGameWorld getFinalGameWorld() {
		return _finalGameWorld;
	}
	private void setFinalGameWorld(FinalGameWorld _finalGameWorld) {
		this._finalGameWorld = _finalGameWorld;
	}
	private Button getSelectPlayerButton() {
		return _selectPlayerButton;
	}
	private void setSelectPlayerButton(Button _selectPlayerButton) {
		this._selectPlayerButton = _selectPlayerButton;
	}
	private Button getOptionsButton() {
		return _optionsButton;
	}
	private void setOptionsButton(Button _optionsButton) {
		this._optionsButton = _optionsButton;
	}
	private OptionsPanel getOptionsPanel() {
		return _optionsPanel;
	}
	private void setOptionsPanel(OptionsPanel _optionsPanel) {
		this._optionsPanel = _optionsPanel;
	}
}
