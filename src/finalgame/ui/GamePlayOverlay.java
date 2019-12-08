package finalgame.ui;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import engine.Application;
import engine.ui.UIElement;
import engine.utility.AspectRatioHandler;
import finalgame.maingameloop.FinalGameWorld;

public class GamePlayOverlay extends UIElement{

	Application _app;
	FinalGameWorld _parent;
	AspectRatioHandler _aspect; 

	public GamePlayOverlay(Application app, FinalGameWorld parent) {
		_app = app;
		_parent = parent;
		_aspect = _app.getAspectRatioHandler();
	}

	public void drawOverlay (GraphicsContext g)  {
		this.drawRound(g);
		this.drawScore(g); 
		this.drawHitPoints (g);
		this.drawHitPointValues(g);
		this.drawCoinCount (g);
		this.drawAbilities (g);	
	}
	
	private void drawHitPointValues (GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(20, (size.y - 40)); 
		this.labelHelper(g,roundOrigin, "HP: xx / xx"); 
	}

	private void drawRound (GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(size.x - 200, 50);
		this.labelHelper(g,roundOrigin, "Round: ###"); 
	}

	private void drawScore(GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d scoreOrigin = origin.plus(size.x - 200, 90);
		this.labelHelper(g,scoreOrigin, "Score: ###"); 
	}

	private void drawCoinCount (GraphicsContext g) {	
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(20, (size.y - 80));
		this.labelHelper(g,roundOrigin, "Coins: xxxx"); 
	}
	
	
	private void labelHelper(GraphicsContext g,Vec2d roundOrigin, String text) {
		g.setFill(Color.GRAY);
		g.fillRoundRect(roundOrigin.x , roundOrigin.y, 180, 35, 5, 5);
		g.setFill(Color.WHITE);
		g.fillRoundRect(roundOrigin.x + 5, roundOrigin.y + 5, 180 - 10, 35 - 10, 5, 5);
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 20 ));
		g.fillText(text, roundOrigin.x + 90, roundOrigin.y + 25);
	}
	
	
	public void drawHitPoints (GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		double heathBarMaxDraw = size.x - 600;
		Vec2d hitpointOrigin = origin.plus(0,size.y - 40);
		
		// Draw Health bar max.
		g.setFill(Color.BLACK);
		g.fillRoundRect(origin.x + 220, hitpointOrigin.y, heathBarMaxDraw, 30, 20, 20);
		
		// This is just here as a placeholder 
		g.setFill(Color.AQUA);
		g.fillRoundRect(origin.x + 225, hitpointOrigin.y + 4, 200, 22, 20, 20); 
	}

	public void drawAbilities (GraphicsContext g) {
		// We just need to draw 4 boxes.
		double boxSize = 75;
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d attributesOrigin = origin.plus(size.x - 340 ,size.y - boxSize);
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30 ));
		int increment = 0;
		int incrementSize = 85;
		int smallBaxFactor = 2;
		String placeHolders = "X"; 
		// This is literally a black box of code.
		g.setFill(Color.RED);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		g.setFill(Color.WHITESMOKE);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2, attributesOrigin.y + (boxSize / smallBaxFactor) - 2 , (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders, attributesOrigin.x + (increment++ * incrementSize) + (boxSize/ 4), attributesOrigin.y + (boxSize / smallBaxFactor) +  ( (boxSize/ 3) + 4));
		g.setFill(Color.PURPLE);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		g.setFill(Color.WHITESMOKE);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2, attributesOrigin.y + (boxSize / smallBaxFactor) - 2 , (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders, attributesOrigin.x + (increment++ * incrementSize) + (boxSize/ 4), attributesOrigin.y + (boxSize / smallBaxFactor) +  ( (boxSize/ 3) + 4));		
		g.setFill(Color.GREEN);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		g.setFill(Color.WHITESMOKE);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2, attributesOrigin.y + (boxSize / smallBaxFactor) - 2 , (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders, attributesOrigin.x + (increment++ * incrementSize) + (boxSize/ 4), attributesOrigin.y + (boxSize / smallBaxFactor) +  ( (boxSize/ 3) + 4));
		g.setFill(Color.PINK);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		g.setFill(Color.WHITESMOKE);
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2, attributesOrigin.y + (boxSize / smallBaxFactor) - 2 , (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders, attributesOrigin.x + (increment++ * incrementSize) + (boxSize/ 4), attributesOrigin.y + (boxSize / smallBaxFactor) +  ( (boxSize/ 3) + 4));
	}

}
