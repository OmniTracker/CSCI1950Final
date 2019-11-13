package main;

import java.net.MalformedURLException;

import nin.level0.Nin;
import wizard.level0.WizLevel0;
import wizard.level1.WizLevel1;
import alchemy.level0.ALCGameScreen;
import engine.*;
import finalgame.level0.Final;

public class Main 
{
	private final static Integer CSCI1950ProjectScreenIndex = 0;

	public static void main (String[] arg) throws MalformedURLException 
	{
		Integer _debugGame = 6; 
		Application app = new Application("Project Screen");
		if (_debugGame == 0) 
		{
			app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new CSCI1950ProjectScreen(app));
		}
		else if (_debugGame == 1) 
		{
			app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new ALCGameScreen(app));
		}	
		else if (_debugGame == 2)
		{
			app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new WizLevel0(app));
		}
		else if (_debugGame == 3 ) 
		{
			app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new WizLevel1(app));
		}
		else if (_debugGame == 5 )
		{
			app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new Nin(app));
		}
		else if (_debugGame == 6 )
		{
			app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new Final(app));
		}
		app.setLevel(CSCI1950ProjectScreenIndex);
		app.start();
	}
}
