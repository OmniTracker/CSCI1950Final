package nin.utils;

import engine.gameobject.GameObject;
import engine.utility.Factory;
import javafx.scene.image.Image;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import support.Vec2d;

import java.io.*;
import java.util.HashMap;

public class NINXMLParser extends Factory {

	private  boolean DEBUG = true;

	NINXMLParser() {

	}

	public HashMap <Integer,GameObject> readXMLParserPlatform ( ) {
		// Setup the parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		HashMap <Integer,GameObject> objs = new HashMap <Integer,GameObject>();
		try  {
			builder = factory.newDocumentBuilder();
		}  catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			return null;
		} try  {
			document = builder.parse("resources/xmlResources/gameInput.xml");
		} catch (SAXException | IOException e ) {
			e.printStackTrace();
			return null;
		}
		document.getDocumentElement().normalize();
		Element root = document.getDocumentElement();
		System.out.println("Node Name: " + root.getNodeName() + "\n");	
		NodeList platformNodeList = document.getElementsByTagName("platform");

		if (DEBUG == true) { 
			for (int temp = 0; temp < platformNodeList.getLength(); temp++) {
				Node node = platformNodeList.item(temp);
				System.out.println("");    //Just a separator
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					System.out.println("Platform  id : "    + eElement.getAttribute("id"));
					System.out.println("Stationary : "      + eElement.getElementsByTagName("stationary").item(0).getTextContent());
					System.out.println("File Name : "       + eElement.getElementsByTagName("fileName").item(0).getTextContent());
					System.out.println("Image File Start: " + eElement.getElementsByTagName("imageFileStart").item(0).getTextContent());
					System.out.println("Image File End  : " + eElement.getElementsByTagName("imageFileEnd").item(0).getTextContent());
				}
			}
		}

		for (int temp = 0; temp < platformNodeList.getLength(); temp++)
		{
			Node node = platformNodeList.item(temp);
			GameObject obj = new GameObject(); 

			if (node.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) node;

				// Set the ID first
				obj.setID(Integer.parseInt(eElement.getAttribute("id")));
				obj.getData().setImage(this.getGenericImage(eElement.getElementsByTagName("fileName").item(0).getTextContent()));
				System.out.println("Stationary : "      + eElement.getElementsByTagName("stationary").item(0).getTextContent());
				System.out.println("Image File Start: " + eElement.getElementsByTagName("imageFileStart").item(0).getTextContent());
				System.out.println("Image File End  : " + eElement.getElementsByTagName("imageFileEnd").item(0).getTextContent());
			}
			objs.put(obj.getID(), obj);
		}
		return objs; 
	}

	
	

	public GameObject readXMLParserCharacter ( ) {
		// Setup the parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		GameObject obj = new GameObject();

		try  {
			builder = factory.newDocumentBuilder();
		}  catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			return null;
		} try  {
			document = builder.parse("resources/xmlResources/gameInput.xml");
		} catch (SAXException | IOException e ) {
			e.printStackTrace();
			return null;
		}
		document.getDocumentElement().normalize();
		NodeList characterNodeList = document.getElementsByTagName("character");

		if (DEBUG == true) 
		{
			for (int temp = 0; temp < characterNodeList.getLength(); temp++){
				Node node = characterNodeList.item(temp);
				System.out.println("");    //Just a separator
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					//Print each employee's detail
					Element eElement = (Element) node;
					System.out.println("Character id : "    + eElement.getAttribute("id"));
					System.out.println("File Name : "       + eElement.getElementsByTagName("fileName").item(0).getTextContent());
					System.out.println("Projectile File Name : "       + eElement.getElementsByTagName("projectTileFileName").item(0).getTextContent());
				}
			}			
		}
		for (int temp = 0; temp < characterNodeList.getLength(); temp++){
			Node node = characterNodeList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//Print each employee's detail
				Element eElement = (Element) node;
				obj.setID(Integer.parseInt(eElement.getAttribute("id")));
				obj.getData().setImage(this.getGenericImage(eElement.getElementsByTagName("fileName").item(0).getTextContent()));
				obj.setProjectile(this.getGenericImage(eElement.getElementsByTagName("projectTileFileName").item(0).getTextContent()));	
			}
		}	
		
		obj.getData().setPosition(new Vec2d(200,200));
		return obj; 
	}

	public Image readXMLParserBackgroundImage ( ) {
		// Setup the parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		Image background = null;
		try  {
			builder = factory.newDocumentBuilder();
		}  catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			return null;
		} try  {
			document = builder.parse("resources/xmlResources/gameInput.xml");
		} catch (SAXException | IOException e ) {
			e.printStackTrace();
			return null;
		}
		document.getDocumentElement().normalize();
		NodeList characterNodeList = document.getElementsByTagName("sky");
		Node node = characterNodeList.item(0);
		Element eElement = (Element) node;			
		background = this.getGenericImage(eElement.getElementsByTagName("path").item(0).getTextContent());
		return background; 
	}
	////////////////////////////////////// Test Below ////////////////////////////////////////////////	


	public void writeXMLParserTest ( ) {

		// Setup the parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
		try {
			docBuilder = factory.newDocumentBuilder();
			try {
				doc = docBuilder.parse("/path.xml");
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		// Create elements. and attributes to them. 
		Element player = (Element) doc.createAttribute("Player");
		player.setAttribute("Health","100");
		Element sword = doc.createElement("Sword");
		// Add child elements to other elements, and top element to the doc.
	}
}
