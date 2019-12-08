package nin.level0;

import nin.systems.*;
import nin.utils.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import engine.Application;
import engine.GameWorld;
import engine.ui.Button;

public class NinGameWorld  extends GameWorld {

	private NinGameObjectDelegate _ninGameObjectDelegate = null;
	private NinMapDelegate _ninMapDelegate = null;
	private GraphicsSystem _graphicsSystem = null;
	private MovementSystem _movementSystem = null;
	
	private Button _button = null; 

	protected NinGameWorld(Application app) {
		super(app);
		this.setGraphicsSystem(new GraphicsSystem(app,this));
		this.setMovementSystem(new MovementSystem(app,this));
		this.setNinGameObjectDelegate( new NinGameObjectDelegate(app) );
		this.setNinMapDelegate( new NinMapDelegate(app));
	}
	public void onTick(long nanosSincePreviousTick) {
		if ( this.getNinGameObjectDelegate().getGameCharacters().size() != 0) {
			// Run the Decision Tree. This will run on every Tick.
			this.getNinGameObjectDelegate().getGameCharacters().get(0).getData().getNinBehaviorTree().runTree();
			this.getNinGameObjectDelegate().getGameCharacters().get(1).getData().getNinBehaviorTree().runTree();
			this.getNinGameObjectDelegate().getGameCharacters().get(2).getData().getNinBehaviorTree().runTree();
			this.getNinGameObjectDelegate().getGameCharacters().get(3).getData().getNinBehaviorTree().runTree();
		}
	}
	
	public void onDraw(GraphicsContext g) {
		if ( this.getNinGameObjectDelegate().getGameCharacters().size() == 0) {
			this.getNinGameObjectDelegate().initCharacter();
		}
		if (this.getNinMapDelegate().getGameBoardPlatforms().size() == 0 ) {
			this.getNinMapDelegate().initPlatform();
		}
		this.getGraphicsSystem().onDraw(g);
	}
	
	
	public void onMouseClicked(MouseEvent e) {
		

		
		if (_button != null) {
			

			if ( _button.clicked(e) == true) {
				
				this.getNinGameObjectDelegate().resetStatic();
			}
		}
	}
	
	public void onKeyPressed(KeyEvent e)  {
		
		this.getMovementSystem().onKeyPressed(e);
	}
	public NinMapDelegate getNinMapDelegate() {
		return _ninMapDelegate;
	}
	private void setNinMapDelegate(NinMapDelegate _ninMapDelegate) {
		this._ninMapDelegate = _ninMapDelegate;
	}
	public NinGameObjectDelegate getNinGameObjectDelegate() {
		return _ninGameObjectDelegate;
	}
	private void setNinGameObjectDelegate(NinGameObjectDelegate _ninGameObjectDelegate) {
		this._ninGameObjectDelegate = _ninGameObjectDelegate;
	}
	private GraphicsSystem getGraphicsSystem() {
		return _graphicsSystem;
	}
	private void setGraphicsSystem(GraphicsSystem _graphicsSystem) {
		this._graphicsSystem = _graphicsSystem;
	}
	private MovementSystem getMovementSystem() {
		return _movementSystem;
	}
	private void setMovementSystem(MovementSystem _movementSystem) {
		this._movementSystem = _movementSystem;
	}
	public Button getButton() {
		return _button;
	}
	public void setButton(Button _button) {
		this._button = _button;
	}
}
