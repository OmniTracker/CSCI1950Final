package finalgame.utils;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import engine.utility.Factory;

public class FinalFactory extends Factory {
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * General Logos
	 */
	public Image getMainLogo () {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/finalLogo.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	
	public Image getMainLogoBackDrop () {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/street.gif").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	
	
	public Image getMainLogoBackDrop2 () {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/forest.gif").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	
	public Image getMainLogoSelete () {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/finalLogoSelect.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public Image getLeftArrow () {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/left.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public Image getRightArrow () {
		Image out = null;
		try{
			out =  new Image(new File("resources/randomFinalImages/right.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Select-able Characters
	 * 
	 */
	/*
	 * Ezra
	 */
	public Image getEzraSmallSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/ezra/little/ezra.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public Image getEzraBigSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/ezra/big/ezraSmile.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	/*
	 * Lyla
	 */
	public  Image getLylaSmallSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/lyla/little/lyla.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public  Image getLylaBigSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/lyla/big/Lyla.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	/*
	 * Sam
	 */
	public  Image getSamSmallSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/sam/little/sam.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public  Image getSamBigSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/sam/big/samHappy.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	/*
	 * Archy
	 */
	public  Image getArchySmallSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/archy/little/archyWalk.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	public  Image getArchyBigSprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/archy/big/archyShock.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Enemy Characters
	 * 
	 */
	public  Image getEnemySprite () {
		Image out = null;
		try{
			out =  new Image(new File("resources/characters/townsPeople/townsPeople.png").toURI().toURL().toExternalForm()); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out; 
	}
}
