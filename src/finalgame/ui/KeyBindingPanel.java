package finalgame.ui;

import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.ui.KeyBinding;
import engine.ui.Panel;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class KeyBindingPanel extends Panel implements EventHandler {
	private HashMap<Integer,KeyBinding > _keyBindingMap = null;
	private Integer _currentlyHoldingContext;
	private Integer _contextFreeNumber = -1;
	protected KeyBindingPanel(AspectRatioHandler app) {
		super(app);
		this.setKeyBindingMap(new HashMap <Integer,KeyBinding >());
		this.setCurrentlyHoldingContext(-1);
		this.initKeyBindingButtons();
	}
	public void initKeyBindingButtons () {
		double width = 150; 
		double height = 30;
		this.getKeyBindingMap().put(0,  new KeyBinding ("Test 1", "W", new Vec2d(50,10), Color.GREEN, Color.WHEAT, width,height)); 
		this.getKeyBindingMap().put(1,  new KeyBinding ("Test 2", "X", new Vec2d(50,10), Color.GREEN, Color.WHEAT, width,height)); 
		this.getKeyBindingMap().put(2,  new KeyBinding ("Test 3", "Y", new Vec2d(50,10), Color.GREEN, Color.WHEAT, width,height)); 
		this.getKeyBindingMap().put(3,  new KeyBinding ("Test 4", "U", new Vec2d(50,10), Color.GREEN, Color.WHEAT, width,height)); 
		this.getKeyBindingMap().put(4,  new KeyBinding ("Test 5", "I", new Vec2d(50,10), Color.GREEN, Color.WHEAT, width,height)); 
	}
	public void onDraw(GraphicsContext g) {		
		this.drawRounded(g);
		this.drawKeyBinding(g);
	}
	private void drawKeyBinding (GraphicsContext g) {
		// Need to get the frame of where the slider needs to be drawn.
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		// Mid point
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y / 2));
		double offsetIncrement = 60;
		// Start of offset
		double offset = -180; 
		for (Entry<Integer, KeyBinding> mapBinding : this.getKeyBindingMap().entrySet()) { 
			mapBinding.getValue().drawKeyBindingStuff(g, center, offset+= offsetIncrement);	
		}
	}
	public void onMouseClicked(MouseEvent e) {
		// Check for collision with one of the buttons.
		for (Entry<Integer, KeyBinding> mapBinding : this.getKeyBindingMap().entrySet()) { 			
			if (mapBinding.getValue().clicked(e) == true) {

				System.out.print( "following hit: " + mapBinding.getValue().getControlElementName()  + "\n");
				this.setCurrentlyHoldingContext(mapBinding.getKey());
				return;
			}
		}
		this.setCurrentlyHoldingContext(this.getContextFreeNumber());
	}
	public void onKeyPressed(KeyEvent e) {
		if ( this.getCurrentlyHoldingContext() != this.getContextFreeNumber()) {
			String input = e.getText().toString(); 			
			KeyBinding keyBinding = this.getKeyBindingMap().get(this.getCurrentlyHoldingContext()); 
			keyBinding.alterCurrentKeyBinding(input);			
		} 
	}
	private HashMap<Integer,KeyBinding > getKeyBindingMap() {
		return _keyBindingMap;
	}
	private void setKeyBindingMap(HashMap<Integer,KeyBinding > _keyBindingMap) {
		this._keyBindingMap = _keyBindingMap;
	}
	private Integer getCurrentlyHoldingContext() {
		return _currentlyHoldingContext;
	}
	private void setCurrentlyHoldingContext(Integer _currentlyHoldingContext) {
		this._currentlyHoldingContext = _currentlyHoldingContext;
	}
	private Integer getContextFreeNumber() {
		return _contextFreeNumber;
	}
	// Not using these. Moved out the way to make life a little easier
	public void onTick(long nanosSincePreviousTick) {}
	public void onKeyReleased(KeyEvent e) {}
	public void onKeyTyped(KeyEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}
	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
