package finalgame.ui;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;
import support.Vec2d;
import engine.ui.KeyBinding;
import engine.ui.Panel;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class HighScorePanel extends Panel implements EventHandler {
	private KeyBinding _name; 
	private KeyBinding _score;
	private int _nameInt  = 0;
	private int _scoreInt = 1;

	private HashMap < String, Pair <String,String>> _playerRanking =  new HashMap <String,Pair <String,String> > ();
	private String highScoreXMLPath = "./resources/xmlResources/HighScore.xml";	
	private String highScoreXMLPathOut = "./resources/xmlResources/HighScore1.xml";	

	private Integer _contextFreeNumber = -1;
	private Integer _currentlyHoldingContext;

	public HighScorePanel(AspectRatioHandler app) {
		super(app);
		this.setName(new KeyBinding  ("Name",  "Jamison", new Vec2d(50,10), Color.GREEN, Color.WHEAT,  70, 50));
		this.setScore(new KeyBinding ("Score", "350",      new Vec2d(50,10), Color.GREEN, Color.WHEAT, 70, 50));
		
		
	 	saveHighScore(); 
	}

	public void onTick(long nanosSincePreviousTick) {}

	public void onDraw(GraphicsContext g)  {
		this.drawRounded(g);
		this.drawSubmit(g);
		Vec2d size   =  this.getSize();
		Vec2d origin = this.getOrigin(); 
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30 ));
		g.setTextAlign(TextAlignment.CENTER);

		this.getName().drawKeyBindingStuff(g, new Vec2d(origin.x + ( size.x / 2 ), (origin.y + size.y / 2)), -30);
		this.getScore().drawKeyBindingStuff(g, new Vec2d(origin.x + ( size.x / 2 ), (origin.y + size.y / 2)), 30);
	}
	
	public void onKeyPressed(KeyEvent e) 
	{
		String input = e.getText().toString(); 
		if  (_nameInt == this.getContextFreeNumber()) 
		{					

		} 
		else if (_scoreInt == this.getContextFreeNumber()) 
		{

		} 		
	}
	
	private void saveHighScore () 
	{
		// Get all score
		this.parseHighScores();
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try 
		{
			docBuilder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		Document doc = docBuilder.newDocument();
		
		//General make our root a descriptive name
		Element root = doc.createElement("HighScore");
		doc.appendChild(root);
		
		int size = _playerRanking.size();
		
		System.out.print( size + " Size :  \n");

		
		for (int x = 0; x < size; x++) 
		{
			Pair <String,String> pair = _playerRanking.get(x); 

			Element highScore = doc.createElement("highScore");
			Attr tag = doc.createAttribute("id");
			tag.setValue(Integer.toString(x));
			highScore.setAttributeNode(tag); 
				
			Element firstName = doc.createElement(pair.getValue());
			Element score = doc.createElement(pair.getKey());

			System.out.print(pair.getValue() + "  :  " + pair.getKey() + "\n");
			
			root.appendChild(highScore);
			highScore.appendChild(firstName); 
			highScore.appendChild(score); 
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try 
		{
			transformer = transformerFactory.newTransformer();
		} 
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		DOMSource domSource = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File(highScoreXMLPathOut));
		try 
		{
			transformer.transform(domSource, streamResult);
		} 
		catch (TransformerException e) 
		{
			e.printStackTrace();
		}
	}

	private void parseHighScores() 
	{		
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
			document = builder.parse(new File(highScoreXMLPath));
		} 
		catch (SAXException | IOException e) 
		{
			e.printStackTrace();
		}
		document.getDocumentElement().normalize();
		Element root = document.getDocumentElement();
				
		NodeList nList = document.getElementsByTagName("HighScore");
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
	
	public void onMouseClicked(MouseEvent e) 
	{		
		if  (this.getName().clicked(e)) 
		{
			this.setCurrentlyHoldingContext(_nameInt);
		} 
		else if (this.getScore().clicked(e)) 
		{
			this.setCurrentlyHoldingContext(_scoreInt);			
		} 
		else 
		{
			this.setCurrentlyHoldingContext(this.getContextFreeNumber());
		}
	}
	private Integer getCurrentlyHoldingContext() {
		return _currentlyHoldingContext;
	}
	private void setCurrentlyHoldingContext(Integer _currentlyHoldingContext) {
		this._currentlyHoldingContext = _currentlyHoldingContext;
	}
	private Integer getContextFreeNumber() {
		return _contextFreeNumber;
	}
	private void setContextFreeNumber(Integer _contextFreeNumber) {
		this._contextFreeNumber = _contextFreeNumber;
	}
	private KeyBinding getName() {
		return _name;
	}
	private void setName(KeyBinding _name) {
		this._name = _name;
	}
	private KeyBinding getScore() {
		return _score;
	}
	private void setScore(KeyBinding _score) {
		this._score = _score;
	}
	public void onKeyTyped(KeyEvent e) {}
	public void onKeyReleased(KeyEvent e) {}
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
