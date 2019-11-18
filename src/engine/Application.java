package engine;

import java.net.MalformedURLException;
import java.util.HashMap;

import engine.utility.AspectRatioHandler;
import engine.utility.Factory;
import finalgame.*;
import finalgame.maingameloop.Final;
import support.*;
import wizard.*;
import wizard.level0.WizLevel0;
import tic.*;
import nin.*;
import nin.level0.Nin;
import alchemy.*;
import alchemy.level0.ALCGameScreen;
import engine.ui.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class Application extends FXFrontEnd {	

	public final Integer TIC   = 1; 
	public final Integer ALC   = 2; 
	public final Integer WIZ   = 3; 
	public final Integer NIN   = 4; 
	public final Integer FINAL = 5; 
	private AspectRatioHandler _aspectRatioHandler = null; 
	private HashMap<Integer,Screen> _levelMapping = null;
	private Factory _factory = null;
	private Screen _currentLevel = null;
	private ColorPallet _colorPallet = null;  

	private static final Vec2d DEFAULT_STAGE_SIZE = new Vec2d(1154,700);

	public void loadGame (Integer game) throws MalformedURLException {

		if (game == GameMask.TIC) 
		{
			// All Tic Levels can be added no. Nothing special going on here
			this.addLevel(game, new TICStart(this));
			this.addLevel((game+= 1), new TICGameScreen(this));
		} 
		else if (game == GameMask.ALC) 
		{
			// All Alc Levels can be added no. Nothing special going on here
			this.addLevel(game, new ALCStart(this));
			this.addLevel((game+= 1), new ALCGameScreen(this));
		} 
		else if (game == GameMask.WIZ) 
		{
			this.addLevel(game,new WIZStart(this));
			this.addLevel(game += 1, new WizLevel0(this));
		} 
		else if (game == GameMask.NIN) 
		{
			this.addLevel(game, new NINStart(this));
			this.addLevel(game += 1, new Nin(this));

		} 
		else if (game == GameMask.FINAL) 
		{
			this.addLevel(game, new FinalStart(this));
			this.addLevel(game += 1, new Final(this));
		} 
		else 
		{
			System.out.print("Valid game not loaded\n");
		}
	}

	public Application(String title) {
		super(title);
		this.setLevelMapping(new HashMap<Integer,Screen>()); 
		this.setColorPallet(new ColorPallet());
		this.setAspectRatioHandler( new AspectRatioHandler(DEFAULT_STAGE_SIZE));
		this.setFactory(new Factory());
	}

	public void borders(GraphicsContext g, Color color) {
		g.setFill(color);
		AspectRatioHandler aspect = this.getAspectRatioHandler();
		Vec2d newOrigin = aspect.calculateUpdatedOrigin(); 
		g.fillRect(0.0,0.0, newOrigin.x, aspect.getCurrentScreenSize().y);
		g.fillRect(aspect.getCurrentScreenSize().x - newOrigin.x,0.0, newOrigin.x,  aspect.getCurrentScreenSize().y);
		g.fillRect(0.0,0.0, aspect.getCurrentScreenSize().x, newOrigin.y);
		g.fillRect(0.0, aspect.getCurrentScreenSize().y - newOrigin.y, aspect.getCurrentScreenSize().x, newOrigin.y);
	}

	public void addLevel(Integer i ,Screen s) {
		this.getLevelMapping().put(i, s);
	}

	@Override
	protected void onTick(long nanosSincePreviousTick) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onTick(nanosSincePreviousTick);
		} else {
			this.setCurrentLevel(new Final(this));
		}
	}

	@Override
	protected void onDraw(GraphicsContext g) throws MalformedURLException {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onDraw(g);
		} 
	}

	@Override
	protected void onKeyTyped(KeyEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onKeyTyped(e);
		}
	}

	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onKeyPressed(e);
		}
	}

	@Override
	protected void onKeyReleased(KeyEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onKeyReleased(e);
		}	
	}

	@Override
	protected void onMouseClicked(MouseEvent e) {

		if (this.getCurrentLevel() != null) {

			this.getCurrentLevel().onMouseClicked(e);

		}	

	}

	@Override
	protected void onMousePressed(MouseEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onMousePressed(e);
		}			
	}

	@Override
	protected void onMouseReleased(MouseEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onMouseReleased(e);
		}	
	}

	@Override
	protected void onMouseDragged(MouseEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onMouseDragged(e);
		}				
	}

	@Override
	protected void onMouseMoved(MouseEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onMouseMoved(e);
		}		
	}

	@Override
	protected void onMouseWheelMoved(ScrollEvent e) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onMouseWheelMoved(e);
		}		
	}

	@Override
	protected void onFocusChanged(boolean newVal) {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onFocusChanged(newVal);
		}
	}

	@Override
	protected void onResize(Vec2d newSize) {
		this.getAspectRatioHandler().setCurrentScreenSize(newSize);

		if (this.getCurrentLevel() != null)
		{
			this.getCurrentLevel().onResize(newSize);
		}
	}
	@Override
	protected void onShutdown() {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onShutdown();
		}	}
	@Override
	protected void onStartup() {
		if (this.getCurrentLevel() != null) {
			this.getCurrentLevel().onStartup();
		}	
	}

	public AspectRatioHandler getAspectRatioHandler() {
		return _aspectRatioHandler;
	}

	public void setAspectRatioHandler(AspectRatioHandler _aspectRatioHandler) {
		this._aspectRatioHandler = _aspectRatioHandler;
	}

	public Screen getCurrentLevel() {
		return _currentLevel;
	}

	public void setCurrentLevel(Screen _currentLevel) {
		this._currentLevel = _currentLevel;
	}

	private HashMap<Integer,Screen> getLevelMapping() {
		return _levelMapping;
	}

	public void setLevel(Integer level) {
		this.setCurrentLevel(this.getLevelMapping().get(level));
	}
	private void setLevelMapping(HashMap<Integer,Screen> _levelMapping) {
		this._levelMapping = _levelMapping;
	}

	public ColorPallet getColorPallet() {
		return _colorPallet;
	}

	public void setColorPallet(ColorPallet _colorPallet) {
		this._colorPallet = _colorPallet;
	}

	public Factory getFactory() {
		return _factory;
	}

	public void setFactory(Factory _factory) {
		this._factory = _factory;
	}
}
