package finalgame.utils;

import java.util.ArrayList;

import support.Vec2d;
import engine.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class DialogDelegate {
	// Each of the Dialog scene will have 0 - 2 characters
	private Application _app;
	private final int NO_CHARACTERS   = 0;
	private ArrayList<String> _noPlayerDialog;

	private final int ONE_CHARACTERS  = 1;
	private ArrayList<String> _singlePlayerDialog; 
	private Image _player1 = null;

	private final int TWO_CHARACTERS  = 2;
	private ArrayList<DoubleDialog> _doublePlayerDialog; 
	private Image _player2 = null;


	private int _numberOfCharacters;


	public DialogDelegate(Application app, int numberOfCharacters, Image player1, Image player2) {
		if ( numberOfCharacters < 0  || numberOfCharacters > 2) {
			return; 
		}
		this.setNumberOfCharacters(numberOfCharacters);
		this.setNoPlayerDialog(     new ArrayList<String>() );
		this.setSinglePlayerDialog( new ArrayList<String>() );
		this.setDoublePlayerDialog( new ArrayList<DoubleDialog>());
		this.setPlayer1(player1);
		this.setPlayer2(player2);

		if ( (this.getNumberOfCharacters() == ONE_CHARACTERS) &&  (this.getPlayer1() != null)  ) {

		}	
	}


	public void onDraw(GraphicsContext g) {

		Vec2d currentScreenSize = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();
		Vec2d currentScreenOrigin = this.getApp().getAspectRatioHandler().calculateUpdatedScreenSize();

		if (this.getNumberOfCharacters() == NO_CHARACTERS) 
		{
			// Center Dialog box in the middle of the screen.



			// Set the size of the font and just draw the text.


			// Draw the characters for the given scene.

			// Draw the set Dialog boxes.

			// Draw next frame button.
		}

		if ( (this.getNumberOfCharacters() == ONE_CHARACTERS) &&  
				(this.getPlayer1() != null)) 
		{
			// Draw the characters for the given scene in the middle of the screen.


			// Draw the text for the character on the right side of the scene.

			// Draw the set Dialog boxes.

			// Draw next frame button.


		}

		if ((this.getNumberOfCharacters() == TWO_CHARACTERS) &&  
				(this.getPlayer1() != null) && 
				(this.getPlayer2() != null)) 
		{
			// Draw the characters for the given scene.

			// Draw the set Dialog boxes.

			// Draw next frame button.	




			// Draw the set Dialog boxes.

			// Draw next frame button.
		}
	}

	public void onMouseClicked(MouseEvent e) {
		// The next click button should only be activate when there is nothing
		// else to say.

	}
	private Integer getNumberOfCharacters() {
		return _numberOfCharacters;
	}
	private void setNumberOfCharacters(Integer _numberOfCharacters) {
		this._numberOfCharacters = _numberOfCharacters;
	}
	private Application getApp() {
		return _app;
	}
	private void setApp(Application _app) {
		this._app = _app;
	}
	private ArrayList<String> getNoPlayerDialog() {
		return _noPlayerDialog;
	}
	public void setNoPlayerDialog(ArrayList<String> _noPlayerDialog) {
		this._noPlayerDialog = _noPlayerDialog;
	}
	private ArrayList<String> getSinglePlayerDialog() {
		return _singlePlayerDialog;
	}
	public void setSinglePlayerDialog(ArrayList<String> _singlePlayerDialog) {
		this._singlePlayerDialog = _singlePlayerDialog;
	}
	private ArrayList<DoubleDialog> getDoublePlayerDialog() {
		return _doublePlayerDialog;
	}
	public void setDoublePlayerDialog(ArrayList<DoubleDialog> _doublePlayerDialog) {
		this._doublePlayerDialog = _doublePlayerDialog;
	}
	private Image getPlayer1() {
		return _player1;
	}
	private void setPlayer1(Image _player1) {
		this._player1 = _player1;
	}
	private Image getPlayer2() {
		return _player2;
	}
	private void setPlayer2(Image _player2) {
		this._player2 = _player2;
	}

}
