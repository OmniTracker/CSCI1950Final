package finalgame.maingameloop.gameworldmanager;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import support.Vec2d;
import support.collision.AABShape;
import engine.Application;
import engine.GameWorld;
import engine.utility.Factory;

public class MainGamePlay extends GameWorld {

	public MainGamePlay(Application app, GameWorld parent) {
		super(app);
	}
	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * 		enemy.getData().setImageSize(new Vec2d(48,48));
		enemy.getData().setImageStart(new Vec2d(0,0));
		enemy.getData().setImageGameSize(new Vec2d(60,60));
		enemy.getData().setBox(new AABShape(main.getData().getAIposition(), new Vec2d(60,60)));
	 * 
	 * 
	 * 

	 */
	
	
	/*
	 * 
	public static Image getEzraSprite (){

		Image out = null;
		try{
			out = new Image(new File("resources/characters/ezra/little/ezra.png").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public static Image getZelchSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/zelch/zelch.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public static Image getArchySprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/archy/little/archyWalk.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public static Image getLylaSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/lyla/little/lyla.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public static Image getVultureSprite () {
		try {
			return new Image(new File("resources/characters/vulture/vulture.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.print("Dafuq \n");
		return null;
	}


	 */
	
	public void onTick(long nanosSincePreviousTick) {
		System.out.print("Main Game Play \n");
	}
	public void onDraw(GraphicsContext g)  {}
	public void onKeyTyped(KeyEvent e) {}
	public void onKeyPressed(KeyEvent e) {}
	public void onKeyReleased(KeyEvent e) {}
	public void onMouseClicked(MouseEvent e) {}
	public void onMousePressed(MouseEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}
}
