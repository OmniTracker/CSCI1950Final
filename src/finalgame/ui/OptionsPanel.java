package finalgame.ui;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.utils.EncryptionConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
	// Key Binding XML
	private HashMap<Integer,KeyBinding > _keyBindingMap = null;
	private Integer _currentlyHoldingContext;
	private Integer _contextFreeNumber = -1;
	private String xmlPath = "./resources/xmlResources/.KeyBinding.xml";
	// High Score XML
	private HashMap < String, Pair <String,String>> _playerRanking =  new HashMap <String,Pair <String,String> > ();
	private String highScoreXMLPath = "./resources/xmlResources/.HighScore.xml";
	private static String secretPath = "./resources/xmlResources/.key";
	
	public OptionsPanel(AspectRatioHandler app) {
		super(app);
		this.setFontName(EngineFonts.getWiz());
		// Sound Slider
		this.setMasterSlider(new Slider("Master" ,-19,123,Color.GREEN,Color.WHITE,400.0,20.0,50.0));
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
	}
	
	public void initKeyBindingButtons () {
		double width = 150; 
		double height = 20;
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
				tname = "Heal";
				break;
			case 1:
				tname = "Ability 1";
				break;
			case 2:
				tname = "Ability 2";
				break;
			case 3:
				tname = "Ability 3";
				break;
			case 4:
				tname = "Move Up";
				break;
			case 5:
				tname = "Move Left";
				break;
			case 6:
				tname = "Move Right";
				break;
			case 7:
				tname = "Move Down";
				break;
			}
			String name = tname; 
			
			
			String val = nList.item(x).getChildNodes().item(0).getNodeName();
			
			this.getKeyBindingMap().put(x,  new KeyBinding (name, val, new Vec2d(50,7), Color.GREEN, Color.WHEAT, width,height));
		}
	}
	
	private void drawKeyBinding (GraphicsContext g) {
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y * 0.2));	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30 ));
		g.fillText("Key Bindings", center.x - (menuSize.x * 0.25),  (center.y + (menuSize.y * 0.2)));	
		g.setTextAlign(TextAlignment.CENTER);
		// Mid point
		center = menuOrigin.plus( (menuSize.x / 4), (menuSize.y  / ( 4 / 3.6)));
		double offsetIncrement = 43;
		// Start of offset
		double offset = -310; 
		for (Entry<Integer, KeyBinding> mapBinding : this.getKeyBindingMap().entrySet()) 
		{ 
			mapBinding.getValue().drawKeyBindingStuff(g, center, offset+= offsetIncrement);	
		}
	}
	public void onMouseClicked(MouseEvent e)
	{
		// Check for collision with one of the buttons.
		for (Entry<Integer, KeyBinding> mapBinding : this.getKeyBindingMap().entrySet()) 
		{ 			
			if (mapBinding.getValue().clicked(e) == true) 
			{
				System.out.print( "following hit: " + mapBinding.getValue().getControlElementName()  + "\n");
				this.setCurrentlyHoldingContext(mapBinding.getKey());
				return;
			}
		}
		this.setCurrentlyHoldingContext(this.getContextFreeNumber());
		
		if (this.checkPanelCollision(e) == true) 
		{
			if ( this.getCloseButton().clicked(e) ) 
			{
				this.setShowing(false);				
			}
			if (this.getApplyButton().clicked(e)) 
			{
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
			
			g.setFill(Color.WHITE);

			g.fillRoundRect( center.x + (menuSize.x * 0.10),  (center.y + (menuSize.y * (0.2 + (offset * loop)) )) - 21, 305, 30, 15, 15);		
			
			
			g.setFill(Color.BLACK);

			g.fillText(entry.getKey()  + ".   Player: " + entry.getValue().getKey() + "   Score: " +  entry.getValue().getValue(), 		
					center.x + (menuSize.x * 0.25),  (center.y + (menuSize.y * (0.2 + (offset * loop)) )   ));
			loop++;
		}	
	}
	public void parseHighScores() 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		try
		{
			builder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		Document endocument = null;
		try 
		{
			endocument = builder.parse(new File(highScoreXMLPath));
		} 
		catch (SAXException | IOException e) 
		{
			e.printStackTrace();
		}
		//get our key from the file
		SecretKey key= null;
		try {
			key = OptionsPanel.loadKeyEncryptionKey(secretPath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document document = endocument;
		try {
			document = OptionsPanel.decryptDocument(endocument, key, XMLCipher.AES_128);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		document.getDocumentElement().normalize();
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
	public boolean shouldUpdateHighScores(int score) {
		int rank = 6;
		for (Entry<String, Pair<String, String>> entry : _playerRanking.entrySet())  
		{
			if (Integer.parseInt(entry.getValue().getValue())<score) {
				rank = Integer.parseInt(entry.getKey());
			}
		}
		if (rank < 6) {
			return true;
		}
		return false;
	}
	public void UpdateHighScores(int score, String name) {
		int rank = 6;
		int maxScoreSeen=0;
		for (Entry<String, Pair<String, String>> entry : _playerRanking.entrySet())  
		{
			if (Integer.parseInt(entry.getValue().getValue())<score && score>maxScoreSeen) {
				rank = Integer.parseInt(entry.getKey());
				maxScoreSeen=score;
			}
		}
		String prevName = "";
		String prevScore= "";
		for (int x = rank; x<6;x++) {
			Pair<String, String> Values = _playerRanking.get(Integer.toString(x));
			if (x==rank) {
				_playerRanking.put(Integer.toString(x), new Pair<String, String>(name,Integer.toString(score)));
			}else {
				_playerRanking.put(Integer.toString(x), new Pair<String, String>(prevName,prevScore));
			}
			prevName = Values.getKey();
			prevScore = Values.getValue();
		}
		saveUpdatedHighScores();
	}
	public void saveUpdatedHighScores() {
		// Get all score			
			File f = new File(highScoreXMLPath);
			f.setExecutable(true);
			f.setWritable(true);
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
				

				
				for (int x = 0; x < size; x++) 
				{
					Pair <String,String> pair = _playerRanking.get(Integer.toString(x+1)); 

					Element highScore = doc.createElement("highScore");
					Attr tag = doc.createAttribute("id");
					tag.setValue(Integer.toString(x+1));
					highScore.setAttributeNode(tag); 
						
					Element firstName = doc.createElement("firstName");
					Element score = doc.createElement("score");
					firstName.setTextContent(pair.getKey());
					score.setTextContent(pair.getValue());

					
					root.appendChild(highScore);
					highScore.appendChild(firstName); 
					highScore.appendChild(score); 
				}
				
				 
				Document encryptedDoc = null;
				SecretKey key = OptionsPanel.getSecretKey("AES");
				OptionsPanel.saveSecretKey(key, secretPath);
				final String algorithmURI = XMLCipher.AES_128;
				try {
					encryptedDoc = encryptDocument(doc, key,algorithmURI);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
				DOMSource domSource = new DOMSource(encryptedDoc);
				StreamResult streamResult = new StreamResult(new File(highScoreXMLPath));
				try 
				{
					transformer.transform(domSource, streamResult);
				} 
				catch (TransformerException e) 
				{
					e.printStackTrace();
				}
				
				f.setExecutable(false);
				f.setWritable(false);
	}
	
	public static SecretKey getSecretKey(String algorithm) {
		 KeyGenerator keyGenerator = null;
		 try {
		  keyGenerator = KeyGenerator.getInstance(algorithm);
		 } catch (NoSuchAlgorithmException e) {
		  e.printStackTrace();
		 }
		 return keyGenerator.generateKey();
		}
	
	public static void saveSecretKey(SecretKey secretKey, String fileName) {
		byte[] bytes = secretKey.getEncoded();
		File file = new File(fileName);
		file.setExecutable(true);
		file.setWritable(true);
		try {
			OutputStream os = new FileOutputStream(file);
			os.write(bytes);
			os.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		file.setExecutable(false);
		file.setWritable(false);
    }
	
	public static Document encryptDocument(Document document, SecretKey secretKey, String algorithm) throws Exception {
		 /* Get Document root element */
		 Element rootElement = document.getDocumentElement();
		 String algorithmURI = algorithm;
		 XMLCipher xmlCipher = XMLCipher.getInstance(algorithmURI);

		 /* Initialize cipher with given secret key and operational mode */
		 xmlCipher.init(XMLCipher.ENCRYPT_MODE, secretKey);

		 /* Process the contents of document */
		 xmlCipher.doFinal(document, rootElement, true);
		 return document;
		}
	
	public static Document decryptDocument(Document document, SecretKey secretKey, String algorithm) throws Exception {
		 Element encryptedDataElement = (Element) document.getElementsByTagNameNS(EncryptionConstants.EncryptionSpecNS, EncryptionConstants._TAG_ENCRYPTEDDATA).item(0);

		 XMLCipher xmlCipher = XMLCipher.getInstance();

		 xmlCipher.init(XMLCipher.DECRYPT_MODE, secretKey);
		 xmlCipher.doFinal(document, encryptedDataElement);
		 return document;
	}
	
	private static SecretKey loadKeyEncryptionKey(final String fileName) throws Exception {
		byte[] key = Files.readAllBytes(Paths.get(fileName));
		SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");
		return originalKey;
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
