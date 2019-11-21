package finalgame.maingameloop.gameworldmanager;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;
import finalgame.utils.DialogDelegate;

public class PlayerDialog extends GameWorld {


	private HashMap<String,HashMap<String, Image>> _characterImages; 
	private ArrayList<DialogDelegate> _dialogFrame; 
	private Image _selectedPlayerImage = null;
	private String _selectedPlayersName = "";
	private Button _skipDialog; 

	public PlayerDialog(Application app) {
		super(app);
		this.setDialogFrame(new ArrayList<DialogDelegate>());
	}

	public void setCurrentPlayer () {


	}


	public void onDraw(GraphicsContext g) { 
		if (this.getDialogFrame().size() != 0)
		{
			this.getDialogFrame().get(0).onDraw(g);			
		}
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

	public void onTick(long nanosSincePreviousTick) {
		// Transition to the next scene. Even if the dialog doesn't make sense.
		if (this.getDialogFrame().size() == 0) {


		}
	}

	public void onMouseClicked(MouseEvent e) {




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
}
