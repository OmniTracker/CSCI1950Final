package engine.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import engine.gameobject.Character;
import engine.gameobject.GameObject;
import javafx.scene.image.Image;

public class Factory {
	public Factory() {	}
	public HashMap<Integer,GameObject>alchemyObjs () {
		HashMap <Integer,GameObject> alchemyGemObjects = new HashMap<>(); 	
		String gemDirectory = "resources/gems/"; 
		File dir = new File(gemDirectory);
		File[] directoryListing = dir.listFiles();		
		int index = 0;
		if (directoryListing != null) {
			for (File child : directoryListing) {
				System.out.print(child + "\n");

				Character gem = new Character();
				gem.setBigSpriteImagePath(child.toString());
				alchemyGemObjects.put(index, gem); 
				index++;
			}
		}
		return alchemyGemObjects;
	}
	private static Character generateCharacter (String bigSprite, String littleSprite ) {
		Character character = new Character();
		character.setBigSpriteImagePath(bigSprite);
		character.setLittleImagePath(littleSprite);
		return character;
	}
	public HashMap <String,GameObject> alchemyCharacterFactory() {
		System.out.print("Running  Characters Factory.\n");	
		HashMap <String,GameObject> alchemyGameObjects = new HashMap<>(); 	
		String charactersPath = "resources/characters/";
		alchemyGameObjects.put("Archy", generateCharacter( charactersPath + "archy/big/archyHappy.png",charactersPath + "archy/little/archyWalk.png"));
		alchemyGameObjects.put("Ezra", generateCharacter(charactersPath + "ezra/big/ezraSmile.png", charactersPath + "ezra/little/ezra.png"));
		alchemyGameObjects.put("Lyla", generateCharacter(charactersPath + "lyla/big/Lyla.png", charactersPath + "lyla/little/lyla.png"));
		alchemyGameObjects.put("Sam", generateCharacter(charactersPath + "sam/big/samHappy.png",  charactersPath + "sam/little/sam.png"));
		return alchemyGameObjects; 
	}
	public Image test () {
		Character sam = new Character();
		String tester = "resources/characters/lyla/big/Lyla.png"; 
		sam.setBigSpriteImagePath(tester);
		return sam.getCharacterLarge();  
	}
	public Image generateBrickWall ( ) throws MalformedURLException{
		return new Image(new File("resources/terrain/brick.jpg").toURI().toURL().toExternalForm()); 
	}
	public Image generateFloor ( ) throws MalformedURLException{
		return new Image(new File("resources/terrain/floor.jpg").toURI().toURL().toExternalForm()); 
	}
	public Image generateGameEngineIntroScreen ( ) throws MalformedURLException{
		String gif = "resources/backgrounds/";
		Random r = new Random();
		Integer pick =  r.nextInt(7 + 1);
		if (pick == 0) {
			gif += "pika.gif"; 
		} else if  (pick == 1) {
			gif += "gen.gif"; 
		} else if (pick == 2) {
			gif += "drake.gif"; 
		}  else if (pick == 3) {
			gif += "arcade.gif"; 
		} else if(pick == 4)  {
			gif += "source.gif"; 
		} else if (pick == 5) {
			gif += "gaga.gif";
		} else if (pick == 6) {
			gif += "jackson.gif";
		} else if (pick == 7) {
			gif += "tompkin.jpg";
		}
		return new Image(new File(gif).toURI().toURL().toExternalForm()); 
	}
	public Image generateGameEngineTransitionScreen ( ) throws MalformedURLException{
		return new Image(new File("resources/backgrounds/transition.gif").toURI().toURL().toExternalForm()); 
	}
	public Image generateALCScreen ( ) throws MalformedURLException{
		return new Image(new File("resources/backgrounds/Alchemist.jpg").toURI().toURL().toExternalForm()); 
	}
	public String getWizGameStats () {
		String home = System.getProperty("user.home") + "/.CSCI1950Wiz";
		System.out.print( home + "\n");		
		File file = new File(home);
		BufferedReader br = null;
		String st; 
		String out = "";
		try {
			br = new BufferedReader(new FileReader(file));

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} 
		try {
			while ((st = br.readLine()) != null) {
				out += st;
			}
		} catch (IOException e) {

			e.printStackTrace();
			return "";
		} 
		try {
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return out;
	}


	public List<List<String>> getWizLevel0 () {
		String level = "resources/.wizLevels/level0.txt";
		File file = new File(level);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		List<List<String>> out = new ArrayList<List<String>>();
		List<String> wall = null;
		String st;
		try {
			while ((st = br.readLine()) != null) {
				wall = new ArrayList<String>(Arrays.asList(st.split(",")));
				out.add(wall);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

}