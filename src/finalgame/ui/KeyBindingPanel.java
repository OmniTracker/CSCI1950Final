package finalgame.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import engine.ui.Button;
import engine.ui.EngineFonts;
import engine.ui.KeyBinding;
import engine.ui.Panel;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class KeyBindingPanel extends Panel implements EventHandler {
	private HashMap<Integer,KeyBinding > _keyBindingMap = null;
	private Integer _currentlyHoldingContext;
	private Integer _contextFreeNumber = -1;
	private String xmlPath = "./resources/xmlResources/KeyBinding.xml";
	protected KeyBindingPanel(AspectRatioHandler app) {
		super(app);
		this.setKeyBindingMap(new HashMap <Integer,KeyBinding >());
		this.setCurrentlyHoldingContext(-1);
		this.initKeyBindingButtons();
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
			String name = "Action" + nList.item(x).getAttributes().item(0).getNodeValue();
			String val = nList.item(x).getChildNodes().item(0).getNodeName();
			this.getKeyBindingMap().put(x,  new KeyBinding (name, val, new Vec2d(50,10), Color.GREEN, Color.WHEAT, width,height));
		}
	}
	public void onDraw(GraphicsContext g) {		
		this.drawRounded(g);
		this.drawKeyBinding(g);
		this.drawApply(g);
	}
	private void drawKeyBinding (GraphicsContext g) {
		// Need to get the frame of where the slider needs to be drawn.
		Vec2d menuOrigin = this.getOrigin();
		Vec2d menuSize  = this.getSize(); 
		// Mid point
		Vec2d center = menuOrigin.plus( (menuSize.x / 2), (menuSize.y / 2));
		double offsetIncrement = 60;
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

				//System.out.print( "following hit: " + mapBinding.getValue().getControlElementName()  + "\n");
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
	public void onKeyPressed(KeyEvent e) {
		if ( this.getCurrentlyHoldingContext() != this.getContextFreeNumber()) {
			String input = e.getText().toString(); 			
			KeyBinding keyBinding = this.getKeyBindingMap().get(this.getCurrentlyHoldingContext()); 
			keyBinding.alterCurrentKeyBinding(input);			
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
	// Not using these. Moved out the way to make life a little easier
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
	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
