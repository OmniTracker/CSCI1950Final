package alchemy.level0;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import engine.Application;
import engine.Screen;
import engine.ai.astar.AStarSearch;

public class ALCGameScreen extends Screen {

	AStarSearch _aStarSearch; 

	public ALCGameScreen(Application app) {
		super(app);
		this.setViewport(new ALCViewport(app,new AlcGameWorld(app),new Vec2d(0,0),new Vec2d(0,0)));	
	}
	public void onDraw(GraphicsContext g) {
		this.getViewport().onDraw(g);
		this.getApplication().borders(g,Color.BLACK);
	}

	public void onMouseClicked(MouseEvent e) {
		this.getViewport().onMouseClicked(e);
	}
	public void onMousePressed(MouseEvent e) 
	{ 
		this.getViewport().onMousePressed(e);
	}
	public void onMouseDragged(MouseEvent e) {
		this.getViewport().onMouseDragged(e);			
	}
	
	public void onMouseWheelMoved(ScrollEvent e){
		this.getViewport().onMouseWheelMoved(e);
	}
	public void onTick(long nanosSincePreviousTick) {
		this.getViewport().onTick(nanosSincePreviousTick);
	}

}
