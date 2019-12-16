package finalgame.utils;

import java.util.HashMap;

import javafx.scene.image.Image;

public class FinalGameObjectHandler {
	private HashMap<String,HashMap<String, Image>> _characterImages; 
	private FinalFactory _finalFactory;
	private Image _introImage; 
	private Image _introBackdrop;
	private Image _selectBackground;
	private Image _leftArrow; 
	private Image _rightArrow;
	public FinalGameObjectHandler () {
		this.setCharacterImages( new HashMap<String,HashMap<String, Image>>());		
		this.setFinalFactory( new FinalFactory());
	}
	public void initGameCharacters () {
		/****** Main Logo ******/
		this.setIntroImage( this.getFinalFactory().getMainLogo());
		
		
		this.setIntroBackdrop( this.getFinalFactory().getMainLogoBackDrop() );
		
		
		
		this.setSelectBackground( this.getFinalFactory().getMainLogoSelete());
		this.setRightArrow( this.getFinalFactory().getRightArrow());
		this.setLeftArrow(this.getFinalFactory().getLeftArrow());
		/****** Ezra ******/
		HashMap<String, Image> ezra = new HashMap<String, Image>(); 
		ezra.put("little", this.getFinalFactory().getEzraSmallSprite()); 
		ezra.put("big", this.getFinalFactory().getEzraBigSprite());
		this.getCharacterImages().put("ezra", ezra);
		/****** Lyla ******/
		HashMap<String, Image> lyla = new HashMap<String, Image>(); 
		lyla.put("little", this.getFinalFactory().getLylaSmallSprite()); 
		lyla.put("big", this.getFinalFactory().getLylaBigSprite());
		this.getCharacterImages().put("lyla", lyla);
		/****** Sam ******/
		HashMap<String, Image> sam = new HashMap<String, Image>(); 
		sam.put("little", this.getFinalFactory().getSamSmallSprite()); 
		sam.put("big", this.getFinalFactory().getSamBigSprite());
		this.getCharacterImages().put("sam", sam);
		/****** Archy ******/
		HashMap<String, Image> archy_final = new HashMap<String, Image>(); 
		archy_final.put("little", this.getFinalFactory().getArchySmallSprite()); 
		archy_final.put("big", this.getFinalFactory().getArchyBigSprite());
		this.getCharacterImages().put("archy", archy_final);
		/****** Enemy ******/
		HashMap<String, Image> enemy = new HashMap<String, Image>(); 
		enemy.put("enemy", this.getFinalFactory().getEnemySprite()); 
		this.getCharacterImages().put("enemy", enemy);
	}
	public HashMap<String,HashMap<String, Image>> getCharacterImages() {
		return _characterImages;
	}
	void setCharacterImages(HashMap<String,HashMap<String, Image>> _characterImages) {
		this._characterImages = _characterImages;
	}
	public FinalFactory getFinalFactory() {
		return _finalFactory;
	}
	private void setFinalFactory(FinalFactory _finalFactory) {
		this._finalFactory = _finalFactory;
	}
	public Image getIntroImage() {
		return _introImage;
	}
	private void setIntroImage(Image _introImage) {
		this._introImage = _introImage;
	}
	public Image getIntroBackdrop() {
		return _introBackdrop;
	}
	private void setIntroBackdrop(Image _introBackdrop) {
		this._introBackdrop = _introBackdrop;
	}
	public Image getSelectBackground() {
		return _selectBackground;
	}
	private void setSelectBackground(Image _selectBackground) {
		this._selectBackground = _selectBackground;
	}
	public Image getLeftArrow() {
		return _leftArrow;
	}
	private void setLeftArrow(Image _leftArrow) {
		this._leftArrow = _leftArrow;
	}
	public Image getRightArrow() {
		return _rightArrow;
	}
	private void setRightArrow(Image _rightArrow) {
		this._rightArrow = _rightArrow;
	}
}
