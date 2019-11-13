package engine.gameobject;

import java.util.ArrayList;
import java.util.Stack;

import engine.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import support.Vec2d;
import support.Vec2i;
import support.collision.AABShape;

public class GameObject {
	
	private GameObjectData _data = new GameObjectData();
	private boolean DEBUG = false; 
	private Integer _gameLevel = -1;
	private Integer _AIStepCount = 0;
	private Integer _arrayIndex = 0; 
	private Integer _ID;
	private Image _projectile = null;
	
	public boolean _invisble = false;
	private ArrayList<Stack<Vec2i>> _foundPathList = null; 

	private Stack<Vec2i> _singlePathList; 

	private int _wizSpeed = 2; 
	private Vec2d _collisionOffset = new Vec2d(0,0); 

	public GameObject() {
		this.setFoundPathList( new  ArrayList<Stack<Vec2i>>());
	}
	public GameObject(Application app) {
		this.getData().setApp(app);
		this.setFoundPathList( new  ArrayList<Stack<Vec2i>>());
	}
	public GameObject(String name, 
			Image image, 
			Vec2d imageStart, 
			Vec2d imageSize,
			Vec2d imageGameSize,   
			Vec2d position, 
			Vec2d size) {
		this.getData().setName(name);
		this.getData().setImage(image);
		this.getData().setImageStart(imageStart);
		this.getData().setImageSize(imageSize);
		this.getData().setImageGameSize(imageGameSize);
		this.getData().setPosition(position);
		this.getData().setSize(size);
		this.setFoundPathList( new  ArrayList<Stack<Vec2i>>());
	}
	public GameObject(String name, 
			Image image, 
			Vec2d imageStart, 
			Vec2d imageSize,
			Vec2d imageGameSize,   
			Vec2d position, 
			Vec2d size, 
			Integer[][] gameGrid, 
			Integer maxRow, 
			Integer maxCol) {
		this.getData().setName(name);
		this.getData().setImage(image);
		this.getData().setImageStart(imageStart);
		this.getData().setImageSize(imageSize);
		this.getData().setImageGameSize(imageGameSize);
		this.getData().setPosition(position);
		this.getData().setSize(size);	

		this.setFoundPathList( new  ArrayList<Stack<Vec2i>>());
	}
	public void draw(GraphicsContext g) {
		g.drawImage(this.getData().getImage(),
				this.getData().getImageStart().x + (this.getData().getImageSize().x * this.getData().getStepX()),
				this.getData().getImageStart().y + (this.getData().getImageSize().y * this.getData().getStepY()),
				this.getData().getImageSize().x,
				this.getData().getImageSize().y,
				this.getData().getPosition().x, 
				this.getData().getPosition().y, 
				this.getData().getImageGameSize().x,
				this.getData().getImageGameSize().y);
		if (DEBUG == true) {
			// Used to figure out where the player collision parts are located.
			g.setFill(Color.WHITE);
			g.fillRect(this.getData().getBox().getTopLeft().x,
					this.getData().getBox().getTopLeft().y, 
					this.getData().getBox().size.x,
					this.getData().getBox().size.y);
		}
	}
	public Vec2i AIGrid = null; 
	public AABShape AICollision ( ) {
		return  this.getData().getBox();  
	}
	public void AIDraw (GraphicsContext g, Vec2d worldOrigin) {	
		if ( _invisble == true && ( this.getData().getStepY() == 3 || this.getData().getStepY()== 0)) {

		} else {
			g.drawImage(this.getData().getImage(),
					this.getData().getImageStart().x + (this.getData().getImageSize().x * this.getData().getStepX()),
					this.getData().getImageStart().y + (this.getData().getImageSize().y * this.getData().getStepY()),
					this.getData().getImageSize().x,
					this.getData().getImageSize().y,
					this.getData().getAIposition().x + worldOrigin.y + 25, 
					this.getData().getAIposition().y + worldOrigin.x, 
					this.getData().getImageGameSize().x,
					this.getData().getImageGameSize().y);
		}

		this.getData().getBox().setTopLeft(new Vec2d ( this.getData().getAIposition().x + worldOrigin.y + 25, 
				this.getData().getAIposition().y + worldOrigin.x)); 		
		if (DEBUG == true) {
			g.setFill(Color.WHITE);
			g.fillRect(this.getData().getBox().getTopLeft().x,
					this.getData().getBox().getTopLeft().y, 
					this.getData().getBox().size.x,
					this.getData().getBox().size.y);
		}
		Vec2i out = new Vec2i(((int) Math.floor(this.getData().getAIposition().x / 100)), ((int) Math.floor(this.getData().getAIposition().y / 100)) );
		this.getData().setAIGridLocation(out);
	}
	public void characterMove(String key) {
		Integer stepX = this.getData().getStepX();
		if (key.contains("UP") ){
			this.getData().setStepY(3);
		} else if (key.contains("DOWN")) {
			this.getData().setStepY(0);
		} else if (key.contains("LEFT")) {
			this.getData().setStepY(1);
		} else if (key.contains("RIGHT")) {
			this.getData().setStepY(2);			
		}
		this.getData().setStepX(stepX += 1);
		if (stepX == 3)  {
			this.getData().setStepX(0);
		}
	}
	public void characterAIMove(String key) {
		Integer stepX = this.getData().getStepX();
		if (key.contains("UP") ){
			this.getData().setStepY(3);
		} else if (key.contains("DOWN")) {
			this.getData().setStepY(0);
		} else if (key.contains("LEFT")) {
			this.getData().setStepY(1);
		} else if (key.contains("RIGHT")) {
			this.getData().setStepY(2);			
		}
		this.getData().setStepX(stepX += 1);
		if (stepX == 3)  {
			this.getData().setStepX(0);
		}
	}
	public void onKeyPressed(KeyEvent e) {
		this.characterMove(e.getCode().toString());
	}
	public GameObjectData getData() {
		return _data;
	}
	public Integer getGameLevel() {
		return _gameLevel;
	}
	public void setGameLevel(Integer _gameLevel) {
		this._gameLevel = _gameLevel;
	}
	public ArrayList<Stack<Vec2i>> getFoundPathList() {
		return _foundPathList;
	}
	public void setFoundPathList(ArrayList<Stack<Vec2i>> _foundPathList) {
		this._foundPathList = _foundPathList;
	}
	public Integer getAIStepCount() {
		return _AIStepCount;
	}
	public void setAIStepCount(Integer _AIStepCount) {
		this._AIStepCount = _AIStepCount;
	}
	public Integer getArrayIndex() {
		return _arrayIndex;
	}
	public void setArrayIndex(Integer _arrayIndex) {
		this._arrayIndex = _arrayIndex;
	}
	public Stack<Vec2i> getSinglePathList() {
		return _singlePathList;
	}
	public void setSinglePathList(Stack<Vec2i> _singlePathList) {
		this._singlePathList = _singlePathList;
	}
	public int getWizSpeed() {
		return _wizSpeed;
	}
	public void setWizSpeed(int _wizSpeed) {
		this._wizSpeed = _wizSpeed;
	}
	public Vec2d getCollisionOffset() {
		return _collisionOffset;
	}
	public void setCollisionOffset(Vec2d _collisionOffset) {
		this._collisionOffset = _collisionOffset;
	}
	public Integer getID() {
		return _ID;
	}
	public void setID(Integer _ID) {
		this._ID = _ID;
	}
	public Image getProjectile() {
		return _projectile;
	}
	public void setProjectile(Image _projectile) {
		this._projectile = _projectile;
	}
}