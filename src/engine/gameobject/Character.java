package engine.gameobject;

import java.io.File;

import support.Vec2d;
import support.Vec2i;
import support.collision.AABShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Character extends GameObject {
	private Image _characterMini  = null;
	private Image _characterLarge = null;
	private Vec2d _miniImageOffSet = null;
	private Vec2i _miniCharMatrix = null;
	private String _characterLargeImagePath = "";
	private String _characterMiniImagePath = "";
	private AABShape _bigBoundingBox;
	private AABShape _smallBoundingBox;
	public Character() { }
	/**
	 * @brief Draw subsection of image.
	 *  
	 * @param img The image to be drawn or null.
	 * @param sx  The source rectangle's X coordinate position.
	 * @param sy  The source rectangle's Y coordinate position.
	 * @param sw  The source rectangle's width.
	 * @param sh  The source rectangle's height.
	 * @param dx  The destination rectangle's X coordinate position.
	 * @param dy  The destination rectangle's Y coordinate position.
	 * @param dw  The destination rectangle's width.
	 * @param dh  The destination rectangle's height
	 */
	public void drawImageSection (Image img, double sx, double sy, 
								   double sw, double sh, double dx, 
								   double dy, double dw,  double dh) {		
	}	
	public void drawElement() {
		if (getCharacterMini() == null) {
			return;
		} else if (true) {
			return;
		}
	}
	public void smallCharacterDraw (GraphicsContext g, String direction) {
		@SuppressWarnings("unused")
		double yImage = 0;
		double size = 48;	
		if (direction == "UP") {			
			yImage += size * 3;
		} 
		else if (direction == "DOWN") {
			yImage += size * 0;	
		} 
		else if (direction == "LEFT") {
			yImage += size * 1;
		} 
		else if (direction == "RIGHT") {
			yImage += size * 2;
		}
		this.getData().setSize(new Vec2d(50,50));
	}
	public void draw(GraphicsContext g) {}
	private void setCharacterLarge(Image _characterLarge) {
		this._characterLarge = _characterLarge;
	}
	public Image getCharacterLarge() {
		return _characterLarge;
	}
	public Image getCharacterMini() {
		return _characterMini;
	}
	private void importCharacterMini () {
		try {
			this.setCharacterMini(new Image(new File(getCharacterMiniImagePath())
												.toURI().toURL().toExternalForm()));
		} catch ( Exception e ) {
			System.out.println("\nError while importing mini character.\n" + e);
		}
	}
	private void importCharacterLarge () {
		try {
			this.setCharacterLarge(new Image(new File(getCharacterLargeImagePath())
											    .toURI().toURL().toExternalForm()));
		} catch ( Exception e ) {
			System.out.println("\nError while importing large character.\n" + e);
		}
	}
	public String getCharacterLargeImagePath() {
		return _characterLargeImagePath;
	}
	public void setBigSpriteImagePath(String _characterLargeImagePath) {
		File file = new File(_characterLargeImagePath);
		if (file.exists() == false) {			
			return;
		}
		this._characterLargeImagePath = _characterLargeImagePath;
		importCharacterLarge();
	}
	private String getCharacterMiniImagePath() {
		return _characterMiniImagePath;
	}
	public void setLittleImagePath(String _characterMiniImagePath) {
		File file = new File(_characterMiniImagePath);
		if (file.exists() == false) {
			System.out.print( _characterMiniImagePath + "\n");	
			return;
		}
		this._characterMiniImagePath = _characterMiniImagePath;
		importCharacterMini(); 
	}
	public Vec2d getMiniImageOffSet() {
		return _miniImageOffSet;
	}
	public void setMiniImageOffSet(Vec2d _miniImageOffSet) {
		this._miniImageOffSet = _miniImageOffSet;
	}
	public Vec2i getMiniCharMatrix() {
		return _miniCharMatrix;
	}
	public void setMiniCharMatrix(Vec2i _miniCharMatrix) {
		this._miniCharMatrix = _miniCharMatrix;
	}
	private void setCharacterMini(Image _characterMini) {
		this._characterMini = _characterMini;
	}
	public AABShape getBigBoundingBox() {
		return _bigBoundingBox;
	}
	public void setBigBoundingBox(AABShape _bigBoundingBox) {
		this._bigBoundingBox = _bigBoundingBox;
	}
	public AABShape getSmallBoundingBox() {
		return _smallBoundingBox;
	}
	public void setSmallBoundingBox(AABShape _smallBoundingBox) {
		this._smallBoundingBox = _smallBoundingBox;
	}
}