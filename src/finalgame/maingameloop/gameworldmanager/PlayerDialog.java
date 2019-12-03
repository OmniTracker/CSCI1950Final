package finalgame.maingameloop.gameworldmanager;

import java.util.ArrayList;
import java.util.HashMap;

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
import finalgame.utils.DialogDelegate;

public class PlayerDialog extends GameWorld {
	private Button _nextButton;
	private Button _skipButton;
	private FinalGameWorld _gameWorld;
	private HashMap<String,HashMap<String, Image>> _characterImages; 
	private ArrayList<DialogDelegate> _dialogFrame; 
	private Image _selectedPlayerImage = null;
	private String _selectedPlayersName = "";
	private Button _skipDialog; 
	
	private double transitionAlpha = 0.0;

	public PlayerDialog(Application app, FinalGameWorld finalGameWorld) {
		super(app);
		this.setDialogFrame(new ArrayList<DialogDelegate>());
		Button nextButton = new Button(); 
		nextButton.setText("Next => ");
		nextButton.setSize( new Vec2d(200,30));
		nextButton.setColor(Color.WHITE);
		nextButton.setFontName(EngineFonts.getAlc());
		this.setNextButton(nextButton);

		Button skipButton = new Button(); 
		skipButton.setText("Skip Dialog");
		skipButton.setSize( new Vec2d(200,30));
		skipButton.setColor(Color.WHITE);
		skipButton.setFontName(EngineFonts.getAlc());
		this.setSkipButton( skipButton);
		this.setGameWorld(finalGameWorld);
	}
	public void onDraw(GraphicsContext g) { 
		
		if ( transitionAlpha > 0.0) {
			transitionAlpha += 0.007; 
		}		
		if (transitionAlpha >= 1.2) {	
		
			
			this.getGameWorld().changeCurrentScreen(VisibleGameWorld.MAINGAMEPLAY);
		}
		
		
		if (this.getDialogFrame().size() != 0)
		{
			this.getDialogFrame().get(0).onDraw(g);			
		}
		g.setFill(Color.DARKGREEN);
		g.fillRect(this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x,
				this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y, 
				this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().x, 
				this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().y);
		double xOrigin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().x + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().x * 0.8);
		double yOrigin1 = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().y * 0.8); 
		double yOrigin2 = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin().y + 
				(this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize().y * 0.85); 
		this.getNextButton().setOrigin(new Vec2d(xOrigin,yOrigin1));
		this.getNextButton().drawRounded(g);
		this.getSkipButton().setOrigin(new Vec2d(xOrigin,yOrigin2));
		this.getSkipButton().drawRounded(g);
		
		

		
		
		Vec2d origin = this.getApplication().getAspectRatioHandler().calculateUpdatedOrigin(); 
		Vec2d size = this.getApplication().getAspectRatioHandler().calculateUpdatedScreenSize(); 
		g.setGlobalAlpha(transitionAlpha);
		g.setFill(Color.BLACK);
		g.fillRect(origin.x,origin.y,size.x,size.y);
		g.setGlobalAlpha(1);
	}
	
	public void onTick(long nanosSincePreviousTick) {


	}

	public void onMouseClicked(MouseEvent e) {
		if (this.getNextButton().clicked(e)) 
		{
			System.out.print("NEXT \n");
		}
		
		if (this.getSkipButton().clicked(e)) 
		{
			
			transitionAlpha += .001; 
			
			
			System.out.print("SKIP \n");
			
		}
	}

	public void setCurrentPlayer () {


	}

	public void initDialogSequence () {	
		// this.sequence0();
		// This will be the sequence where your selected character will have there 
		// custom dialog. All the dialog up to this point is the same given any selected 
		// player.
		// this.sequence1();
		// this.sequence2();
		// this.sequence3();
		// this.sequence4();
		// This will be the sequence where your selected character will have there 
		// custom dialog. All the dialog up to this point is the same given any selected 
		// player.
		// this.sequence5();
	}

	private void sequence0 (Image player1, Image player2) {
		DialogDelegate sequenceDelegate = new DialogDelegate(this.getApplication(),0,null, null); 

		// Line one

		// Line two

		// Line there


		this.getDialogFrame().add(sequenceDelegate);
	}

	private void sequence1 (Image player1, Image player2) {
		DialogDelegate sequenceDelegate = new DialogDelegate(this.getApplication(),1,null, null); 		

		// Line one

		// Line two

		// Line there

		this.getDialogFrame().add(sequenceDelegate);
	}
	private void sequence2 (Image player1, Image player2) {
		DialogDelegate sequenceDelegate = new DialogDelegate(this.getApplication(),2,null, null); 

		// Player one Line one


		// Player two Line one


		// Player one Line two


		// Player two Line two

		this.getDialogFrame().add(sequenceDelegate);
	}
	private void sequence3 (Image player1, Image player2) {
		DialogDelegate sequenceDelegate = new DialogDelegate(this.getApplication(),2,null, null); 		


		// Player one Line one


		// Player two Line one


		// Player one Line two


		// Player two Line two



		this.getDialogFrame().add(sequenceDelegate);
	}
	private void sequence4 (Image player1, Image player2) {
		DialogDelegate sequenceDelegate = new DialogDelegate(this.getApplication(),2,null, null); 	

		// Player one Line one


		// Player two Line one


		// Player one Line two


		// Player two Line two


		this.getDialogFrame().add(sequenceDelegate);
	}
	private void sequence5 (Image player1, Image player2) {
		DialogDelegate sequenceDelegate = new DialogDelegate(this.getApplication(),1,null, null); 		

		// Line one

		// Line two

		// Line there

		this.getDialogFrame().add(sequenceDelegate);
	}
	private ArrayList<DialogDelegate> getDialogFrame() {
		return _dialogFrame;
	}
	private void setDialogFrame(ArrayList<DialogDelegate> _dialogFrame) {
		this._dialogFrame = _dialogFrame;
	}
	private Image getSelectedPlayerImage() {
		return _selectedPlayerImage;
	}
	private void setSelectedPlayerImage(Image _selectedPlayerImage) {
		this._selectedPlayerImage = _selectedPlayerImage;
	}
	private String getSelectedPlayersName() {
		return _selectedPlayersName;
	}
	private void setSelectedPlayersName(String _selectedPlayersName) {
		this._selectedPlayersName = _selectedPlayersName;
	}
	private Button getSkipDialog() {
		return _skipDialog;
	}
	private void setSkipDialog(Button _skipDialog) {
		this._skipDialog = _skipDialog;
	}
	private  HashMap<String,HashMap<String, Image>> getCharacterImages() {
		return _characterImages;
	}
	public void setCharacterImages(HashMap<String,HashMap<String, Image>> _characterImages) {
		this._characterImages = _characterImages;
	}
	private Button getNextButton() {
		return _nextButton;
	}
	private void setNextButton(Button _highScoreButton) {
		this._nextButton = _highScoreButton;
	}
	private Button getSkipButton() {
		return _skipButton;
	}
	private void setSkipButton(Button _skipButton) {
		this._skipButton = _skipButton;
	}
	private FinalGameWorld getGameWorld() {
		return _gameWorld;
	}
	private void setGameWorld(FinalGameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
}
