package wizard.utils;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;

public final class WIZSpriteFactory {
	/*********************************************** Wiz Char ***********************************************/
	public Image getEzraSprite () {
		Image out = null;
		try{
			out = new Image(new File("resources/characters/ezra/little/ezra.png").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public Image getZelchSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/zelch/zelch.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return out; 
	}
	public  Image getArchySprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/archy/little/archyWalk.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return out; 
	}
	public Image getLylaSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/lyla/little/lyla.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return out; 
	}
	public Image getVultureSprite () {
		try {
			return new Image(new File("resources/characters/vulture/vulture.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public Image getCalorieSprite () {
		try {
			return new Image(new File("resources/characters/calorie/calorie.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public Image getZeroSprite () {
		try {
			return new Image(new File("resources/characters/zero/zero.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	/*********************************************** Wiz Keys ***********************************************/
	public Image getRedKey () {
		try {
			return new Image(new File("resources/.wiz/keys/key0.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public Image getBlueKey () {
		try {
			return new Image(new File("resources/.wiz/keys/key1.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public Image getGreenKey () {
		try {
			return new Image(new File("resources/.wiz/keys/key2.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	/********************************************* Wiz Next Level ********************************************/
	public Image getNextTransferLevel () {
		try {
			return new Image(new File("resources/terrain/source.gif").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
