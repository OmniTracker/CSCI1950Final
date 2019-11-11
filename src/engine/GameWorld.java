package engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;
import wizard.utils.WIZDelegateContainer;

public class GameWorld {
	private WIZDelegateContainer _wizDelegateContainer;
	private Application _application;
	private Integer _level;
	private Vec2d _gameSize;
	private Vec2d _origin;
	protected GameWorld(Application app) {
		this.setApplication(app);
	}
	public void onTick(long nanosSincePreviousTick) {
	}
	public void onDraw(GraphicsContext g) {
	}
	public void onKeyTyped(KeyEvent e) {
	}
	public void onKeyPressed(KeyEvent e)  {
	}
	public void onKeyReleased(KeyEvent e) {
	}
	public void onMouseClicked(MouseEvent e) {
	}
	public void onMousePressed(MouseEvent e) {
	}
	public void onMouseReleased(MouseEvent e) {
	}
	public void onMouseDragged(MouseEvent e) {
	}
	public void onMouseMoved(MouseEvent e) {
	}
	public void onMouseWheelMoved(ScrollEvent e) {
	}
	public void onFocusChanged(boolean newVal) {
	}
	public void onResize(Vec2d newSize) {
	}
	public void onShutdown() {
	}
	public void onStartup() {
	}
	public Application getApplication() {
		return _application;
	}
	public void setApplication(Application _application) {
		this._application = _application;
	}
	public Vec2d getGameSize() {
		return _gameSize;
	}
	public void setGameSize(Vec2d _gameSize) {
		this._gameSize = _gameSize;
	}
	public Vec2d getOrigin() {
		return _origin;
	}
	public void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}
	public Integer getLevel() {
		return _level;
	}
	public void setLevel(Integer _level) {
		this._level = _level;
	}
	public WIZDelegateContainer getWIZDelegateContainer() { 		
		return _wizDelegateContainer;
	}
	public void setWIZDelegateContainer(WIZDelegateContainer _wizDelegateContainer) {
		this._wizDelegateContainer = _wizDelegateContainer;
	}
}
