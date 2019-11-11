package wizard.level1;

import java.net.MalformedURLException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import wizard.level0.WizLevel0GameWorld;
import wizard.level0.WizLevel0Viewport;
import wizard.utils.WIZDelegateContainer;
import wizard.utils.WIZGameObjectDelegate;
import wizard.utils.WIZMapDelegate;
import engine.Application;
import engine.Screen;

public class WizLevel1 extends Screen {
	
	private WizLevel1Viewport     _viewport; 
	private WIZDelegateContainer _wizDelegateContainer; 
	private boolean _startUp = false;
	public WizLevel1(Application app) {
		super(app);
		this.setWIZDelegateContainer(new WIZDelegateContainer());
	}
	public void onTick(long nanosSincePreviousTick) {
		startup();
		if (_startUp == true) {
			this.getViewport().onTick(nanosSincePreviousTick);
		}		
	}
	public void onDraw(GraphicsContext g)  {
		
		if (_startUp == true) {
			this.getViewport().onDraw(g);
			this.getApplication().borders(g, Color.BLACK);	
		} else {
			g.setFill(Color.BLACK);
			g.fillRect(0, 0, this.getApplication().getAspectRatioHandler().getCurrentScreenSize().x, 
					this.getApplication().getAspectRatioHandler().getCurrentScreenSize().y);			
		}
	}
	public void onMouseClicked(MouseEvent e) {
		if (_startUp == true) {
			this.getViewport().onMouseClicked(e);
		}
	}
	public void onKeyPressed(KeyEvent e)  {
		if (_startUp == true) {
			this.getViewport().onKeyPressed(e);
		}
	}
	private void startup() {
		if (_startUp == false) {			
			WizLevel1GameWorld gameWorld = new WizLevel1GameWorld(this.getApplication()); 
			try {
				this.getWIZDelegateContainer().setWIZMapDelegate(new WIZMapDelegate(this.getApplication()));
				this.getWIZDelegateContainer().setWIZGameObjectDelegate(new WIZGameObjectDelegate(this.getApplication()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.print("Failed to get map information \n");
			}		
			this.getWIZDelegateContainer().setGameWorld(gameWorld);
			this.setViewport(new WizLevel1Viewport(this.getApplication(), gameWorld,new Vec2d(0,0), new Vec2d(0,0)));
			this.getWIZDelegateContainer().getWIZGameObjectDelegate().setup(this.getApplication());
			gameWorld.setWIZDelegateContainer(this.getWIZDelegateContainer());
			_startUp = true;
		}
	}
	private WIZDelegateContainer getWIZDelegateContainer() {
		return _wizDelegateContainer;
	}
	private void setWIZDelegateContainer(WIZDelegateContainer _wizDelegateContainer) {
		this._wizDelegateContainer = _wizDelegateContainer;
	}
	public WizLevel1Viewport getViewport() {
		return _viewport;
	}
	private void setViewport(WizLevel1Viewport _viewport) {
		this._viewport = _viewport;
	}
}
