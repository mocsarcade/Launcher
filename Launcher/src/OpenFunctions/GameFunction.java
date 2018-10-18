package OpenFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Launcher.Selector;

public class GameFunction extends Function {
	
	protected String gameName;
	
	public GameFunction(String in) {
		gameName = in;
	}
	
	@Override
	public void activate() {
		try {
			System.out.println(new File("games/" + gameName + "/build.bat").getAbsolutePath());
			Runtime.
			   getRuntime().
			   //Make new cmd appear to open game
			   exec("cmd /c " + new File("games/" + gameName + "/build.bat").getAbsolutePath());
			   //exec("cmd /c start " + new File("games/" + gameName + "/build.bat").getPath());
		} catch(IOException e) {
			//TODO: If this error happens, it means game couldn't be run and so remove this game from the library
			e.printStackTrace();
		}
	}
}
