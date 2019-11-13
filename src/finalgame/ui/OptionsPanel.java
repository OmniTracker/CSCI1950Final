package finalgame.ui;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import support.Vec2d;
import engine.ui.Panel;
import engine.ui.Slider;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class OptionsPanel  extends Panel implements EventHandler{

	private Slider _masterSlider  = null; 
	private Slider _soundFXSlider = null;
	private Slider _musicSlider   = null;
	

	protected OptionsPanel(AspectRatioHandler app) {
		super(app);
		this.setMasterSlider(new Slider("Master" ,-19,123,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
		this.setSoundFXSlider(new Slider("Sound" ,3,345,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
		this.setMusicSlider(new Slider("Music"   ,49,92,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
	}

	public void onDraw(GraphicsContext g) {
	}

	public void drawPanelWithSliders (GraphicsContext g) {
		this.drawRounded(g);
		// Need to get the frame of where the slider needs to be drawn.
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		// Mid point
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y / 2));		
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 40 ));
		g.setTextAlign(TextAlignment.CENTER);
		// Main panel Label
		g.fillText("Sound", center.x, menuOrigin.y + 40);
		// Need to figure out the height positioning.
		this.getMasterSlider().draw(g, center, -90);
		this.getSoundFXSlider().draw(g, center, 10);
		this.getMusicSlider().draw(g, center, 110); 
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFocusChanged(boolean newVal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartup() {
		// TODO Auto-generated method stub

	}

	private Slider getMasterSlider() {
		return _masterSlider;
	}

	private void setMasterSlider(Slider _masterSlider) {
		this._masterSlider = _masterSlider;
	}

	private Slider getSoundFXSlider() {
		return _soundFXSlider;
	}

	private void setSoundFXSlider(Slider _soundFXSlider) {
		this._soundFXSlider = _soundFXSlider;
	}

	private Slider getMusicSlider() {
		return _musicSlider;
	}

	private void setMusicSlider(Slider _musicSlider) {
		this._musicSlider = _musicSlider;
	}

}
