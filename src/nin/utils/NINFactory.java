package nin.utils;

import java.io.File;

import javafx.scene.image.Image;

public final class NINFactory {
	public Image getMainGround () {
		Image out = null;
		try{
			out = new Image(new File("resources/terrain/ninMainGround.png").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public Image getMovingPlatforms () {
		Image out = null;
		try{
			out = new Image(new File("resources/terrain/ninMovingPlatforms.png").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public Image getLucas () {
		Image out = null;
		try{
			out = new Image(new File("resources/characters/lucas/ninLucasSprite.jpg").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public Image getLucasProjectile () {
		Image out = null;
		try{
			out = new Image(new File("resources/characters/lucas/lucasProjectile.jpg").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
}
