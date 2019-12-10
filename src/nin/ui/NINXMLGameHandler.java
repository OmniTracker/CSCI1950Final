package nin.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javafx.util.Pair;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
		
	public void loadGame() 
	{
		
		/*

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try 
		{
			docBuilder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();			
		Element root = doc.createElement("KeyBindings");



		doc.appendChild(root);
		int size = _keyBindingMap.size();


		for (int x = 0; x < size; x++) {
			Element action = doc.createElement("action");
			Attr tag = doc.createAttribute("tag");
			tag.setValue(Integer.toString(x));
			action.setAttributeNode(tag);

			Element val = doc.createElement(_keyBindingMap.get(x).getCurrentKeyBindSetting());
			root.appendChild(action);
			action.appendChild(val);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource domSource = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File(xmlPath));

		try {
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	}

	public void saveGame() {
		
		/*
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try 
		{
			docBuilder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();			
		Element root = doc.createElement("KeyBindings");



		doc.appendChild(root);
		int size = _keyBindingMap.size();


		for (int x = 0; x < size; x++) {
			Element action = doc.createElement("action");
			Attr tag = doc.createAttribute("tag");
			tag.setValue(Integer.toString(x));
			action.setAttributeNode(tag);

			Element val = doc.createElement(_keyBindingMap.get(x).getCurrentKeyBindSetting());
			root.appendChild(action);
			action.appendChild(val);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource domSource = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File(xmlPath));

		try {
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/ 
	}


}
