package main;

import engine.*;

public class Main 
{
	private final static Integer CSCI1950ProjectScreenIndex = 0;
	public static void main (String[] arg) 
	{
		Application app = new Application("Project Screen");
		app.addLevel(GameMask.CSCI1950ProjectScreenIndex, new CSCI1950ProjectScreen(app));
		app.setLevel(CSCI1950ProjectScreenIndex);
		app.start();
	}
}
