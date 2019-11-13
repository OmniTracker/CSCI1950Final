package wizard.dataui;

import java.util.HashMap;

import engine.GameWorld;
import engine.ui.Label;
import engine.ui.UIElement;
import engine.utility.AspectRatioHandler;

public class AchievementPanel extends UIElement {
	private HashMap <String,WIZKeyButton> _keyButtons;
	private HashMap <String,Label> _gamePlayDataLabels; 
	@SuppressWarnings("unused")
	private GameWorld _gameWorld;
	private AspectRatioHandler _aspectRatio; 

	AchievementPanel(GameWorld gameWorld) {
		super();
		this.setGameWorld(gameWorld);
		this.setAspectRatio(gameWorld.getApplication().getAspectRatioHandler());
		this.setKeyButtons(new HashMap <String,WIZKeyButton>());
		this.setGamePlayDataLabels(new HashMap <String,Label>());
		this.generateLayoutComponents();
		this.setPanelUILayout();
	}
	
	public void onTick(long nanosSincePreviousTick) {
		this.updateUIData();
	}
	
	private void updateUIData() {
		// This will be used to maintain the aspect ratio of all the elements in
		// the main UI Panel.
			
	}
	
	private void setPanelUILayout () {
		// You will need the aspect ratio to do this properly.
		
	}

	private void generateLayoutComponents () {
		// Need to have three buttons for the key elements
		this.getKeyButtons().put("Red", new WIZKeyButton()); 
		this.getKeyButtons().put("Blue", new WIZKeyButton()); 
		this.getKeyButtons().put("Green", new WIZKeyButton()); 
		// Need to have a single button to restart the game.
		this.setRestartButton( new RestartButton());
		// Need labels for the following.
		// enemy currently on the board
		this.getGamePlayDataLabels().put("Enemy Name", new Label() );
		// Is the gate open
		this.getGamePlayDataLabels().put("Can You Escape", new Label());
		// how much money do you have left
		this.getGamePlayDataLabels().put("Remaining Lives", new Label() ); 
	}
	
	public HashMap <String,WIZKeyButton> getKeyButtons() {
		return _keyButtons;
	}
	private void setKeyButtons(HashMap <String,WIZKeyButton> _keyButtons) {
		this._keyButtons = _keyButtons;
	}
	public  RestartButton getRestartButton() {
		return _restartButton;
	}
	private void setRestartButton(RestartButton _restartButton) {
		this._restartButton = _restartButton;
	}
	public HashMap <String,Label> getGamePlayDataLabels() {
		return _gamePlayDataLabels;
	}
	public void setGamePlayDataLabels(HashMap <String,Label> _gamePlayDataLabels) {
		this._gamePlayDataLabels = _gamePlayDataLabels;
	}
	private void setGameWorld(GameWorld _gameWorld) {
		this._gameWorld = _gameWorld;
	}
	public  AspectRatioHandler getAspectRatio() {
		return _aspectRatio;
	}
	public void setAspectRatio(AspectRatioHandler _aspectRatio) {
		this._aspectRatio = _aspectRatio;
	}
}