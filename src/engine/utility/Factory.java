package engine.utility;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
	public Image getKimSprite ( ) throws MalformedURLException{
		Image out = null;
		try{
			out = new Image(new File("resources/characters/celebrity/kimCrying.png").toURI().toURL().toExternalForm()); 
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
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
	public Image getLevel0Tiles () throws MalformedURLException {
		return new Image(new File("resources/.wiz/level0/level0.png").toURI().toURL().toExternalForm()); 
	}
	public Image getFlamingRock () {
		try {
			return new Image(new File("resources/rock.jpg").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public Image getLava () throws MalformedURLException {
		return new Image(new File("resources/.wiz/level0/lava.gif").toURI().toURL().toExternalForm()); 
	}
	public Image getTron () throws MalformedURLException {
		return new Image(new File("resources/.wiz/level1/level1.gif").toURI().toURL().toExternalForm()); 
	}
	public List<List<String>> getFile (String inputFile) {
		File file = new File(inputFile);
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
	public static ArrayList<String> level0Graphical () {
		ArrayList<String> out = new ArrayList<String>(); 
		try{
			FileInputStream fstream = new FileInputStream("resources/.wiz/level0/level0.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				String tokens = strLine.replace("\t", ",");
				out.add(tokens); 
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	
	public Image getLevel1Tiles () throws MalformedURLException {
		return new Image(new File("resources/.wiz/level1/level1.png").toURI().toURL().toExternalForm()); 
	}
	
	public static  ArrayList<String> level0Map ()  {
		ArrayList<String> out = new ArrayList<String>(); 
		try{
			FileInputStream fstream = new FileInputStream("resources/.wiz/level0/level0Grid.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				String tokens = strLine.replace("\t", ",");
				out.add(tokens); 
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public static  ArrayList<String> level0AIGrid ()   {
		ArrayList<String> out = new ArrayList<String>(); 
		try{
			FileInputStream fstream = new FileInputStream("resources/.wiz/level0/level0AIGrid.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				String tokens = strLine.replace("\t", ",");
				out.add(tokens); 
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public static  ArrayList<String> level1AIGrid ()   {
		ArrayList<String> out = new ArrayList<String>(); 
		try{
			FileInputStream fstream = new FileInputStream("resources/.wiz/level1/level1Grid.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				String tokens = strLine.replace("\t", ",");
				out.add(tokens); 
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public static  ArrayList<String> level1Map ()   {
		ArrayList<String> out = new ArrayList<String>(); 
		try{
			FileInputStream fstream = new FileInputStream("resources/.wiz/level1/level1.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				String tokens = strLine.replace("\t", ",");
				out.add(tokens); 
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return out; 
	}
	public static Image getNinBricks () {
		try {
			return new Image(new File("resources/.nin/platform.png").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}	
	
	public void xmlReader (String path) throws ParserConfigurationException, SAXException, IOException {
		// Setup the parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(path);
		doc.getDocumentElement().normalize();
		
	}
	
	public void xmlWriter () throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		

		
	}
}