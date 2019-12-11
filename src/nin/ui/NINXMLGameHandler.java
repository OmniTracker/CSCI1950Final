package nin.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nin.level0.NinGameWorld;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import engine.utility.Factory;

public class NINXMLGameHandler {

	private String xmlPath = "./resources/xmlResources/nin.xml";

	// High Score XML
	private HashMap < String, Pair <String,String>> _playerRanking =  new HashMap <String,Pair <String,String> > ();

	public void openGame () 
	{
		
		// This will just open up the last game. This will remain persistent.
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try
		{
			builder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		Document document = null;

		try 
		{
			document = builder.parse(new File(xmlPath));
		} 
		catch (SAXException | IOException e) 
		{
			e.printStackTrace();
		}

		document.getDocumentElement().normalize();

		Element root = document.getDocumentElement();

		System.out.println(root.getNodeName());

		NodeList nList = document.getElementsByTagName("highScore");

		String id; 
		String name;
		String score;

		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) node;
				id    = eElement.getAttribute("id");
				name  = eElement.getElementsByTagName("firstName").item(0).getTextContent(); 
				score = eElement.getElementsByTagName("score").item(0).getTextContent();

				if (eElement.getAttribute("id") != "") 
				{
					_playerRanking.put(id, new Pair <String,String> (name, score)); 
				}
			}
		}
	}
		
	public void loadGame(NinGameWorld GameWorld) {
		// Write high Score First
		List<String> info = Factory.readNin().get(0);	
		GameWorld.score   = Integer.valueOf(info.get(0));
		GameWorld.lives   = Integer.valueOf(info.get(1));
		String stats = 	String.valueOf(GameWorld.score) + "," + 
		String.valueOf(GameWorld.lives) + "," + String.valueOf(GameWorld.high);  
		Factory.writeNin(stats);
	}

	public void saveGame(NinGameWorld GameWorld) {
		String stats = 	String.valueOf(GameWorld.score) + "," + 
		String.valueOf(GameWorld.lives) + "," + String.valueOf(GameWorld.high);  
		Factory.writeNin(stats);
	}
}
