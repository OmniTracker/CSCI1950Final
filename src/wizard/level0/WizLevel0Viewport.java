package wizard.level0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import wizard.ui.WIZMenuBar;
import wizard.utils.WIZRestart;
import engine.Application;
import engine.ui.ViewportHandler;

public class WizLevel0Viewport extends ViewportHandler {
	private WIZMenuBar _menuBar = null;

	WizLevel0Viewport(Application parent, WizLevel0GameWorld gameWorld, Vec2d origin, Vec2d size) {
		super(parent, gameWorld, origin, size);	
		this.setMenuBar( new WIZMenuBar( this.getAspect()));
	}

	public void onDraw(GraphicsContext g) {
		this.drawBackgroundColor(g);
		this.getGameWorld().miniMap = this.getMenuBar().isMiniMap();
		this.getGameWorld().onDraw(g);
		this.getMenuBar().onDraw(g);
	}

	public void onKeyPressed(KeyEvent e)  {		
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onKeyPressed(e);
		}
	}

	public void onTick(long nanosSincePreviousTick) {
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onTick(nanosSincePreviousTick);
		}
	}

	public void onMouseClicked(MouseEvent e) {
		if (this.getMenuBar().getRestartGame() == null)  {
			this.getMenuBar().setRestartGame(new WIZRestart(this.getGameWorld(),
					this.getGameWorld().getWIZDelegateContainer().getWIZGameObjectDelegate()));		
		}
		if ( this.getMenuBar().isMenuActivated() == false ) {
			this.getGameWorld().onMouseClicked(e);
		}
		this.getMenuBar().onMouseClicked(e);
	}

	private void drawBackgroundColor(GraphicsContext g) 
	{
		Vec2d size = this.getAspect().getCurrentScreenSize();
		g.setFill(Color.DARKGREEN);
		g.fillRect(0,0, size.x, size.y );
		g.setGlobalAlpha(0.75);
		g.setFill(Color.BLACK);
		g.fillRect(0,0, size.x, size.y );
		g.setGlobalAlpha(1.0);
	}
	private WIZMenuBar getMenuBar() {
		return _menuBar;
	}
	private void setMenuBar(WIZMenuBar _menuBar) {
		this._menuBar = _menuBar;
	}
}
