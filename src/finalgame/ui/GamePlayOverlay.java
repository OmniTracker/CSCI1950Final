package finalgame.ui;

import support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import engine.Application;
import finalgame.engineAdditions.GameObject;
import engine.ui.UIElement;
import engine.utility.AspectRatioHandler;
import finalgame.engineAdditions.AnimateAbilityComponent;
import finalgame.engineAdditions.PlayerHealthComponent;
import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;

public class GamePlayOverlay extends UIElement {
	Application _app;
	FinalGameWorld _parent;
	AspectRatioHandler _aspect;
	String[] placeHolders = new String[8];
	Image[] buttonImages = new Image[4];
	PlayerHealthComponent _health;
	AnimateAbilityComponent[] _abilities = new AnimateAbilityComponent[4];
	private int character;

	public GamePlayOverlay(Application app, FinalGameWorld parent) {
		_app = app;
		_parent = parent;
		_aspect = _app.getAspectRatioHandler();
		_health = null;
	}

	public void drawOverlay(GraphicsContext g) {
		this.drawRound(g);
		this.drawScore(g);
		this.drawHitPoints(g);
		this.drawHitPointValues(g);
		this.drawAbilities(g);
	}

	private void drawHitPointValues(GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(20, (size.y - 40));
		if(_health != null) {
			this.labelHelper(g, roundOrigin, "HP: "+(int)_health.getHealth()+" / "+(int)_health.getTotalHealth());
		}
		else {
			this.labelHelper(g, roundOrigin, "HP: xx / xx");
		}
	}

	private void drawRound(GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d roundOrigin = origin.plus(size.x - 200, 50);
		int round = _parent.getMainGamePlay().getRound();
		this.labelHelper(g, roundOrigin, "Round: " + String.valueOf(round));
	}

	private void drawScore(GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d scoreOrigin = origin.plus(size.x - 200, 90);
		MainGamePlay main = _parent.getMainGamePlay();
		String score = Integer.toString(main.get_highScore());
		this.labelHelper(g, scoreOrigin, "Score: "+ score);
	}
	private void labelHelper(GraphicsContext g, Vec2d roundOrigin, String text) {
		g.setFill(Color.GRAY);
		g.fillRoundRect(roundOrigin.x, roundOrigin.y, 180, 35, 5, 5);
		g.setFill(Color.WHITE);
		g.fillRoundRect(roundOrigin.x + 5, roundOrigin.y + 5, 180 - 10, 35 - 10, 5, 5);
		g.setFill(Color.BLACK);
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 20));
		g.fillText(text, roundOrigin.x + 90, roundOrigin.y + 25);
	}

	public void drawHitPoints(GraphicsContext g) {
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		double heathBarMaxDraw = size.x - 600;
		Vec2d hitpointOrigin = origin.plus(0, size.y - 40);

		// Draw Health bar max.
		g.setFill(Color.BLACK);
		g.fillRoundRect(origin.x + 220, hitpointOrigin.y, heathBarMaxDraw, 30, 20, 20);
		
		double currHealth = 0;
		if(_health != null) {
			currHealth = (_health.getHealth()/_health.getTotalHealth()) * (heathBarMaxDraw-8);
		}
		// This is just here as a placeholder
		g.setFill(Color.AQUA);
		g.fillRoundRect(origin.x + 225, hitpointOrigin.y + 4, currHealth, 22, 20, 20);
	}

	public void drawAbilities(GraphicsContext g) {
		// We just need to draw 4 boxes.
		double boxSize = 75;
		Vec2d size = _aspect.calculateUpdatedScreenSize();
		Vec2d origin = _aspect.calculateUpdatedOrigin();
		Vec2d attributesOrigin = origin.plus(size.x - 340, size.y - boxSize);
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 30));
		int increment = 0;
		int incrementSize = 85;
		int smallBaxFactor = 2;
		// This is literally a black box of code.
		g.setFill(Color.RED);
		g.strokeRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		switch(character) {
		case 0:
			g.drawImage(buttonImages[0],0,0,32,32,attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);
			break;
		case 1:
			g.drawImage(buttonImages[0],0,0,32,32,attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);
			break;
		case 2:
			g.drawImage(buttonImages[0],0,0,32,32,attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);
			break;
		case 3:
			g.drawImage(buttonImages[0],0,0,32,32,attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);
			break;
		}
		if(_health != null && _health.isCoolingDown()) {
			g.setFill(Color.rgb(121, 115, 115, 0.4));
			g.fillRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, (_health.getCurrCooldown()/_health.getCooldown())*boxSize, boxSize);
			g.setFill(Color.LIGHTCORAL);
		}
		else {
			g.setFill(Color.WHITESMOKE);
		}
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2,
				attributesOrigin.y + (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2,
				(boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders[0], attributesOrigin.x + (increment++ * incrementSize) + (boxSize / 4),
				attributesOrigin.y + (boxSize / smallBaxFactor) + ((boxSize / 3) + 4));
		g.setFill(Color.PURPLE);
		g.strokeRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		switch(character) {
		case 0:
			g.drawImage(buttonImages[1],768,512,128,128, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 1:
			g.drawImage(buttonImages[1], attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 2:
			g.drawImage(buttonImages[1], attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 3:
			g.drawImage(buttonImages[1], attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		}		
		if(_abilities[1] != null && _abilities[1].isCoolingDown()) {
			g.setFill(Color.rgb(121, 115, 115, 0.4));
			g.fillRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, (_abilities[1].getCurrCooldown()/_abilities[1].getCooldown())*boxSize, boxSize);
			g.setFill(Color.LIGHTCORAL);
		}
		else {
			g.setFill(Color.WHITESMOKE);
		}
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2,
				attributesOrigin.y + (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2,
				(boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders[1], attributesOrigin.x + (increment++ * incrementSize) + (boxSize / 4),
				attributesOrigin.y + (boxSize / smallBaxFactor) + ((boxSize / 3) + 4));
		g.setFill(Color.GREEN);
		g.strokeRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		switch(character) {
		case 0:
			g.drawImage(buttonImages[2],576,0,192,192, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 1:
			g.drawImage(buttonImages[2],576,0,192,192, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 2:
			g.drawImage(buttonImages[2],576,0,192,192, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 3:
			g.drawImage(buttonImages[2],576,0,192,192, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		}		
		if(_abilities[2] != null && _abilities[2].isCoolingDown()) {
			g.setFill(Color.rgb(121, 115, 115, 0.4));
			g.fillRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, (_abilities[2].getCurrCooldown()/_abilities[2].getCooldown())*boxSize, boxSize);
			g.setFill(Color.LIGHTCORAL);
		}
		else {
			g.setFill(Color.WHITESMOKE);
		}
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2,
				attributesOrigin.y + (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2,
				(boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders[2], attributesOrigin.x + (increment++ * incrementSize) + (boxSize / 4),
				attributesOrigin.y + (boxSize / smallBaxFactor) + ((boxSize / 3) + 4));
		g.setFill(Color.PINK);
		g.strokeRoundRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize, 10, 10);
		switch(character) {
		case 0:
			g.drawImage(buttonImages[3],0,0,728,892, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 1:
			g.drawImage(buttonImages[3],0,0,2100,2000, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 2:
			g.drawImage(buttonImages[3],0,0,2100,2000, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		case 3:
			g.drawImage(buttonImages[3],0,0,2100,2000, attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, boxSize, boxSize);			break;
		}		
		if(_abilities[3] != null && _abilities[3].isCoolingDown()) {
			g.setFill(Color.rgb(121, 115, 115, 0.4));
			g.fillRect(attributesOrigin.x + (increment * incrementSize), attributesOrigin.y, (_abilities[3].getCurrCooldown()/_abilities[3].getCooldown())*boxSize, boxSize);
			g.setFill(Color.LIGHTCORAL);
		}
		else {
			g.setFill(Color.WHITESMOKE);
		}
		g.fillRoundRect(attributesOrigin.x + (increment * incrementSize) + 2,
				attributesOrigin.y + (boxSize / smallBaxFactor) - 2, (boxSize / smallBaxFactor) - 2,
				(boxSize / smallBaxFactor) - 2, 10, 10);
		g.setFill(Color.BLACK);
		g.fillText(placeHolders[3], attributesOrigin.x + (increment++ * incrementSize) + (boxSize / 4),
				attributesOrigin.y + (boxSize / smallBaxFactor) + ((boxSize / 3) + 4));
	}

	public void setKeyValues(String[] p) {
		placeHolders = p;
	}

	public void loadNeededImages(int character) {
		this.character=character;
		buttonImages[0]= MainGamePlay.getPotionImage();
		switch (character) {
		case 0:
			// LYLA
			buttonImages[1] = MainGamePlay.getTeleportImage();
			buttonImages[3] = MainGamePlay.getFireWaveImage();
			buttonImages[2] = MainGamePlay.getIceBlockImage();
			
			break;
		case 1:
			// EZRA
//		_player.addComponent("HEALTH", new PlayerHealthComponent(_player, 150, getHealImage()));
			break;
		case 2:
			// SAM
//		_player.addComponent("HEALTH", new PlayerHealthComponent(_player, 125, getHealImage()));
			break;
		case 3:
			buttonImages[1] = MainGamePlay.getPortalImage();
			buttonImages[2] = MainGamePlay.getElectricScratchImage();
			buttonImages[3] = MainGamePlay.getAOELightningImage();
			break;
		default:
			break;
		}
	}
	
	public void getPlayerInfo(GameObject player) {
		_health = (PlayerHealthComponent)player.getComponent("HEALTH");
		_abilities[1] = (AnimateAbilityComponent)player.getComponent("ABILITY_F");
		_abilities[2] = (AnimateAbilityComponent)player.getComponent("ABILITY_E");
		_abilities[3] = (AnimateAbilityComponent)player.getComponent("ABILITY_Q");
	}
}
