package nin.utils;

import engine.utility.Factory;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.*;

public class NINXMLParser extends Factory {
	
	NINXMLParser() {
		
	}

	/*
	 * 	Useful Classes: 
	 * 	
	 *		
	 *		DocumentBuilder
	 *		Document (org.w3c.dom NOT javax/swing)
	 *		Element
	 *		Node & NodeList
	 */

	public void readXMLParser ( ) {
		// Setup the parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try 
		{
			builder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e1) 
		{
			e1.printStackTrace();
			return;
		}
		try 
		{
			document = builder.parse("resources/xmlResources/charles.xml");
		} 
		catch (SAXException | IOException e ) 
		{
			e.printStackTrace();
			return;
		}
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		//Get all employees
		NodeList nList = document.getElementsByTagName("employee");
		System.out.println("============================");
		
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node node = nList.item(temp);
		
			System.out.println("");    //Just a separator
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				//Print each employee's detail
				Element eElement = (Element) node;
				System.out.println("Employee id : "    + eElement.getAttribute("id"));
				System.out.println("First Name : "  + eElement.getElementsByTagName("firstName").item(0).getTextContent());
				System.out.println("Last Name : "   + eElement.getElementsByTagName("lastName").item(0).getTextContent());
				System.out.println("Location : "    + eElement.getElementsByTagName("location").item(0).getTextContent());
			}
		}
	}

	public void writeXMLParser ( ) {

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
