package alchemy;

import support.Vec2d;
import support.collision.Collision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import engine.Application;
import engine.ui.Screen;
import engine.utility.AspectRatioHandler;
import engine.utility.ZoomHandler;


public class GameWorld  extends Screen {

	private Vec2d _screenSize = new Vec2d (1600, 1200);

	private ZoomHandler _zoomHandler;
	private MouseEvent _mouseDrag;
	Vec2d _origin; 
	private ScrollEvent _lastDrag = null;

	double factor = 1; 

	/*
	public boolean isAbleToZoom (long nanosSincePreviousTick ) { 
		if (nanosSincePreviousTick >= (this.getLastTimeUpdate() + this.getSetTimeout())) {
			this.setLastTimeUpdate(nanosSincePreviousTick);
			return false;
		}
		if (this.getZoomCount() >= this.getZoomMaxScale()) {
			return false;
		}
		if (this.getZoomCount() <= this.getZoomMinScale()) {
			return false;
		}


		return true;
	}

	 */



	protected GameWorld(Application app) {
		super(app);
		this.setZoomHandler(new ZoomHandler(15,0,7,1, 1.04,0));
		this._origin = new Vec2d(0,0);
	}

	private void drawScaledGameWorld (GraphicsContext g) {	

		if (this._lastDrag != null) {
			g.save();


			// Get mouse offset.
			double mousex = this._lastDrag.getSceneX() - 0;
			double mousey = this._lastDrag.getSceneY() - 0;


			double originx = ( mousex/(factor) - mousex/1) * -1;
			double originy = ( mousey/(factor) - mousey/1) * -1;

			// Scale it (centered around the origin due to the trasnslate above).
			g.scale(factor , factor );
			// Offset the visible origin to it's proper position.
			g.translate(- originx , -originy);


			// g.translate(offset.x(), offset.y());

			// g.scale(factor, factor);
			g.setFill(Color.BLUE); 
			g.fillRect(this._origin.x,this._origin.y,100,100);
			g.restore();

		}

	}

	public void onMouseDragged(MouseEvent e)
	{
		if (this.getMouseDrag() != null) {
			double xDiff = e.getSceneX()- this.getMouseDrag().getSceneX();
			double yDiff = e.getSceneY() - this.getMouseDrag().getSceneY();
			double x = xDiff;
			double y = yDiff;
			this.setOrigin(this.getOrigin().plus(x, y));

			this.setMouseDrag(e);
		} else {
			this.setMouseDrag(e);
		}
	}

	public void onDraw(GraphicsContext g) {
		this.drawScaledGameWorld(g);
	}

	public void onMouseWheelMoved(ScrollEvent e) {	

		if (this._lastDrag == null) {
			this._lastDrag = e;
		}

		this._lastDrag = e;
		if (e.getDeltaY() < 10) {
			factor+= .1;
		} else if (e.getDeltaY() > -10) {
			factor-= .1;
		}

	}




	private MouseEvent getMouseDrag() {
		return _mouseDrag;
	}

	private void setMouseDrag(MouseEvent _mouseDrag) {
		this._mouseDrag = _mouseDrag;
	}

	public void onTick(long nanosSincePreviousTick) {
		this.getZoomHandler().isAbleToZoom(nanosSincePreviousTick);
	}
	public ZoomHandler getZoomHandler() {
		return _zoomHandler;
	}

	public void setZoomHandler(ZoomHandler _zoomHandler) {
		this._zoomHandler = _zoomHandler;
	}

	private Vec2d getOrigin() {
		return _origin;
	}

	private void setOrigin(Vec2d _origin) {
		this._origin = _origin;
	}

	private Vec2d getScreenSize() {
		return _screenSize;
	}
}

/* 

	private final Integer ZOOM_MAX_COUNT = 15;
	private final Integer ZOOM_MIN_COUNT = 0;
	private final double ZOOM_SCALE = 1.04;
	private EventHandlerTimeOut _timeOut;
	private Integer _zoomCount = 7;
	private double _zoomScale = 1;
	Application _parent;
	private Vec2d _origin;
	private Vec2d _size;
	private Image _test = null;
	private Vec2d _prevDrag;

	IntroductionGameWorld ( Application parent ) {

		super(parent);
		this._parent = parent; 
		this.setPrevDrag(new Vec2d(0.0,0.0));
		this.setOrigin(new Vec2d(0.0,0.0));
		this.setTimeOut(new EventHandlerTimeOut());
	}

	private void scroll (ScrollEvent e) {
		if (e.getDeltaY() < 0.0)  
		{
			if (this.ZOOM_MAX_COUNT != this._zoomCount)
			{	
				this._zoomCount++;
				this.setZoomScale(this.getZoomScale() * this.ZOOM_SCALE);
			}
		} 
		else
		{
			if (this.ZOOM_MIN_COUNT != this._zoomCount) 
			{
				this._zoomCount--;		
				this.setZoomScale(this.getZoomScale() / this.ZOOM_SCALE);
			}
		}
	}	

	public void onMouseReleased(MouseEvent e) {
		this.setPrevDrag(new Vec2d(0.0,0.0));
	}

	public void onMouseDragged(MouseEvent e) {

		if ( (this.getPrevDrag().x != 0.0) && (this.getPrevDrag().y) != 0.0) 
		{
			double x = (this.getOrigin().x + this.getPrevDrag().x - (e.getScreenX()));
			double y = (this.getOrigin().y + this.getPrevDrag().y - (e.getScreenY()));	
			this.setOrigin(new Vec2d(x, y));
		}
		this.setPrevDrag( new Vec2d(e.getScreenX(),e.getScreenY()));
	}



	private void viewportToGame (GraphicsContext g) {
		Affine affine = new Affine(); 
		affine.appendScale(this.getZoomScale(), this.getZoomScale());
		Vec2d Screen = this.getParent().getAspectRatioHandler().calculateUpdatedScreenSize();
		affine.appendTranslation(Screen.x / 10, Screen.y /10);
		g.transform(affine);
	}

	public void onTick(long nanosSincePreviousTick) {
		this.getTimeOut(); 
	}


	public void onDraw(GraphicsContext g) {
		if (this.getTest() == null) 
		{	
			this.setFactory(new Factory());
			this.setTest(this.getFactory().test()); 
		}
		g.save();
		Affine scale = new Affine();
		scale.appendScale(1.3, 1.3);
		g.transform(scale);
		this.viewportToGame(g);

		g.drawImage(this.getTest(),  ( -1 * this.getOrigin().x) + 90, -1 * this.getOrigin().y ) ;
		g.restore();
	}

	private void setZoomScale(double _zoomScale) 
	{
		this._zoomScale = _zoomScale;
	}
	public double getZoomScale() 
	{
		return this._zoomScale;
	}
	public Application getParent () {
		return _parent; 
	}

	Vec2d getOrigin() {
		return _origin;
	}

	void setOrigin (Vec2d _origin) {
		this._origin = _origin;
	}

	Vec2d getSize() {
		return _size;
	}

	void setSize(Vec2d _size) {
		this._size = _size;
	}


	public Image getTest() {
		return _test;
	}

	public void setTest(Image _test) {
		this._test = _test;
	}

	private Vec2d getPrevDrag() {
		return _prevDrag;
	}

	private void setPrevDrag(Vec2d _prevDrag) {
		this._prevDrag = _prevDrag;
	}

 */
