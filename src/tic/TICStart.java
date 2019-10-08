package tic;

import support.Vec2d;
import support.collision.AABShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import engine.Application;
import engine.ui.Button;
import engine.ui.Screen;
import engine.utility.AspectRatioHandler;

public class TICStart extends Screen {
	private Button _startButton;
	private AspectRatioHandler _aspect; 
	private double _fadeOutLimit = 0.0;
	private double _fadeOut = 0.0;
	private boolean _gameLoading = false;

	public TICStart(Application app) {
		super(app);
		app.setTitle("TIC");
		this.setAspect(this.getApplication().getAspectRatioHandler());
		this.setStartButton(new Button("Start", new Vec2d(0,0),new Vec2d(300,50),Color.GREY));
	}

	private void drawBorder(GraphicsContext g) {
		Vec2d screenSize = this.getAspect().getCurrentScreenSize();
		g.setFill(Color.BLUE);
		g.fillRect(0,0, screenSize.x, screenSize.y);
		this.getApplication().borders(g, Color.BLACK);
	}

	private void drawLogo(GraphicsContext g) {
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString("Tic"), 400 ));
		g.setTextAlign(TextAlignment.CENTER);
		double x = (this.getAspect().getCurrentScreenSize().x / 2);
		double y = (this.getAspect().getCurrentScreenSize().y / 2);
		g.fillText("Tic", x, y);
	}	

	private void updateButtonsPosition() {
		
		Vec2d newSize   = this.getAspect().calculateUpdatedScreenSize();
		Vec2d newOrigin = this.getAspect().calculateUpdatedOrigin(); 
		double x = newOrigin.x + (newSize.x / 2) - (this.getStartButton().getSize().x / 2);
		double y = newOrigin.y +  (newSize.y / 2) + 200;
		this.getStartButton().setOrigin(new Vec2d(x, y));
		AABShape newShape = new AABShape(this.getStartButton().getOrigin(),this.getStartButton().getSize());
		this.getStartButton().setShape(newShape);
	}

	private void transitionOut() {
		_fadeOut += 0.009;
		if (_fadeOutLimit < 300) {
			_fadeOutLimit += 6; 				
		}
		if (_fadeOut > 1.2)  {
			this.getApplication().setLevel((this.getApplication().TIC + 1));
		}
	}

	public void onMouseClicked(MouseEvent e) {
		if (this.getStartButton().clicked(e)) {
			if (this._gameLoading  == false ) {
				this._gameLoading = true;
			}
		}
	}

	public void drawFadeOut (GraphicsContext g) {
		g.setGlobalAlpha(_fadeOut);
		g.setFill(Color.BLACK);
		g.fillRect(0,0, 
				this.getAspect().getCurrentScreenSize().x, 
				this.getAspect().getCurrentScreenSize().y);
		g.setGlobalAlpha(1.0);
	}

	public void onDraw(GraphicsContext g) {
		this.drawBorder(g);
		this.drawLogo(g);
		this.updateButtonsPosition();
		this.getStartButton().draw(g);
		this.drawFadeOut(g);
	}

	public void onTick(long nanosSincePreviousTick) {
		this.setAspect(this.getApplication().getAspectRatioHandler());
		if (this._gameLoading == true) {
			this.transitionOut();			
		}
	}

	private Button getStartButton() {
		return _startButton;
	}

	private void setStartButton(Button _startButton) {
		this._startButton = _startButton;
	}

	public AspectRatioHandler getAspect() {
		return _aspect;
	}

	public void setAspect(AspectRatioHandler _aspect) {
		this._aspect = _aspect;
	}
}
