package finalgame.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

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
import engine.ui.EngineFonts;
import engine.ui.KeyBinding;
import engine.ui.Panel;
import engine.ui.Slider;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class OptionsPanel  extends Panel implements EventHandler{
	// Sound
	private Slider _masterSlider  = null; 
	private Slider _soundFXSlider = null;
	private Slider _musicSlider   = null;
	// Key Binding XML
	private HashMap<Integer,KeyBinding > _keyBindingMap = null;
	private Integer _currentlyHoldingContext;
	private Integer _contextFreeNumber = -1;
	private String xmlPath = "./resources/xmlResources/.KeyBinding.xml";
	// High Score XML
	private HashMap < String, Pair <String,String>> _playerRanking =  new HashMap <String,Pair <String,String> > ();
	private String highScoreXMLPath = "./resources/xmlResources/HighScore.xml";
	
	public OptionsPanel(AspectRatioHandler app) {
		super(app);
		this.setFontName(EngineFonts.getWiz());
		// Sound Slider
		this.setMasterSlider(new Slider("Master" ,-19,123,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
		this.setSoundFXSlider(new Slider("Sound" ,3,345,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
		this.setMusicSlider(new Slider("Music"   ,49,92,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
		// Key Binding
		this.setKeyBindingMap(new HashMap <Integer,KeyBinding >());
		this.setCurrentlyHoldingContext(-1);
		this.initKeyBindingButtons();
		// High Scores
		this.parseHighScores();
	}
	public void onKeyPressed(KeyEvent e) {
		if ( this.getCurrentlyHoldingContext() != this.getContextFreeNumber()) {
			String input = e.getCode().toString(); 			
			KeyBinding keyBinding = this.getKeyBindingMap().get(this.getCurrentlyHoldingContext()); 
			keyBinding.alterCurrentKeyBinding(input);			
		} 
	}
	public void onDraw(GraphicsContext g) {
		this.drawRounded(g);
		// Main Divider
		this.drawDivider(g);
		this.drawSounds(g);		
		this.drawHighScores(g);
		// Find a way to 
		this.drawKeyBinding(g);
		this.drawApply(g);
		this.drawHighScores(g);
	}
	
	public void initKeyBindingButtons () {
		double width = 150; 
		double height = 30;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(xmlPath);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("action");
		int size = nList.getLength();
		for (int x = 0; x<size;x++) {
			String tname = null;
			switch(x) {
			case 0:
				tname = "Move Up";
				break;
			case 1:
				tname = "Move Left";
				break;
			case 2:
				tname = "Move Right";
				break;
			case 3:
				tname = "Move Down";
				break;
			case 4:
				tname = "Heal";
				break;
			case 5:
				tname = "Ability 1";
				break;
			case 6:
				tname = "Ability 2";
				break;
			case 7:
				tname = "Ability 3";
				break;
			}
			String name = tname;// + nList.item(x).getAttributes().item(0).getNodeValue();
			String val = nList.item(x).getChildNodes().item(0).getNodeName();
			this.getKeyBindingMap().put(x,  new KeyBinding (name, val, new Vec2d(50,7), Color.GREEN, Color.WHEAT, width,height));
		}
	}
	
	private void drawKeyBinding (GraphicsContext g) {
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y * 0.1));	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30 ));
		g.fillText("Key Bindings", center.x - (menuSize.x * 0.25),  (center.y + (menuSize.y * 0.48)));	
		g.setTextAlign(TextAlignment.CENTER);
		// Mid point
		center = menuOrigin.plus( (menuSize.x / 4), (menuSize.y  / ( 4 / 3.6)));
		double offsetIncrement = 35;
		// Start of offset
		double offset = -180; 
		for (Entry<Integer, KeyBinding> mapBinding : this.getKeyBindingMap().entrySet()) { 
			mapBinding.getValue().drawKeyBindingStuff(g, center, offset+= offsetIncrement);	
		}
	}
	public void onMouseClicked(MouseEvent e) {
		// Check for collision with one of the buttons.
		for (Entry<Integer, KeyBinding> mapBinding : this.getKeyBindingMap().entrySet()) { 			
			if (mapBinding.getValue().clicked(e) == true) {
				System.out.print( "following hit: " + mapBinding.getValue().getControlElementName()  + "\n");
				this.setCurrentlyHoldingContext(mapBinding.getKey());
				return;
			}
		}
		this.setCurrentlyHoldingContext(this.getContextFreeNumber());
		if (this.checkPanelCollision(e) == true) {
			if ( this.getCloseButton().clicked(e) ) {
				this.setShowing(false);				
			}
			if (this.getApplyButton().clicked(e)) {
				this.saveBindings();
				
			}
		}
	}
	private void saveBindings() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();
		//General make our root a descriptive name
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
	}
	@SuppressWarnings("static-access")
	private void drawSounds(GraphicsContext g) {
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y * 0.1));	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getWiz()), 30 ));
		g.fillText("Sound Control", center.x - (menuSize.x * 0.25),  (center.y + (menuSize.y * 0.08)));	
		g.setTextAlign(TextAlignment.CENTER);
		this.getMasterSlider().draw(g, center.plus(  -1 * (menuSize.x * 0.25), (menuSize.y / 2) ),  - 190);
		this.getSoundFXSlider().draw(g, center.plus( -1 * (menuSize.x * 0.25), (menuSize.y / 2) ),  - 130);
		this.getMusicSlider().draw(g, center.plus(   -1 * (menuSize.x * 0.25), (menuSize.y / 2) ),  -70);
	}
	@SuppressWarnings("static-access")
	private void drawDivider(GraphicsContext g) {
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y * 0.1));	
		g.setFill(Color.BLACK);	
		g.fillRoundRect(center.x + (menuSize.x * 0.05), center.y + (menuSize.y * 0.01) ,  5 ,menuSize.y * 0.8, 10, 10);
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getWiz()), 40 ));
		g.fillText("Options", center.x, center.y - 20);
	}
	private void drawHighScores(GraphicsContext g) {
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y * 0.1));	
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30 ));
		g.setTextAlign(TextAlignment.CENTER);
		g.fillText("High Scores ", center.x + (menuSize.x * 0.25),  (center.y + (menuSize.y * 0.08)));
		double offset = 0.14;
		int loop = 0;
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 20 ));
		
		for (Entry<String, Pair<String, String>> entry : _playerRanking.entrySet())  
		{
			g.fillText(entry.getKey()  + ".   Player: " + entry.getValue().getKey() + "   Score: " +  entry.getValue().getValue(), 		
					center.x + (menuSize.x * 0.25),  (center.y + (menuSize.y * (0.2 + (offset * loop)) )));
			loop++;
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
	private HashMap<Integer,KeyBinding > getKeyBindingMap() {
		return _keyBindingMap;
	}
	private void setKeyBindingMap(HashMap<Integer,KeyBinding > _keyBindingMap) {
		this._keyBindingMap = _keyBindingMap;
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
	private Slider getMasterSlider() {
		return _masterSlider;
	}
	private void setMasterSlider(Slider _masterSlider) {
		this._masterSlider = _masterSlider;
	}
	private Slider getSoundFXSlider() {
		return _soundFXSlider;
	}
	private void setSoundFXSlider(Slider _soundFXSlider) {
		this._soundFXSlider = _soundFXSlider;
	}
	private Slider getMusicSlider() {
		return _musicSlider;
	}
	private void setMusicSlider(Slider _musicSlider) {
		this._musicSlider = _musicSlider;
	}	
	public void onTick(long nanosSincePreviousTick) {}
	public void onKeyReleased(KeyEvent e) {}
	public void onKeyTyped(KeyEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}
	public void onMousePressed(MouseEvent e) {}
}
