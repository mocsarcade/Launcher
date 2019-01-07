package OpenFunctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Timer;
import Launcher.InputManager;
import Launcher.MainMenu;
import Launcher.Selector;

public class GameFunction extends Function {
	
	protected String gameName;
	protected String gameTitle;
	
	public GameFunction(String in) {
		gameName = in;
		gameTitle = null;
	}

	public GameFunction(String in, String title) {
		gameName = in;
		gameTitle = title;
	}

	@Override
	public void activate() {
		InputManager.getManager().allowInput(false);
		MainMenu.RunningGame();
		try {
			final Process runningGame = Runtime.getRuntime().
				//Make new cmd appear to open game
				exec("cmd /c " + new File("games/" + gameName + "/build.bat").getAbsolutePath());
			//Focus on window every few seconds
			//While loop runs as long as game is running
		   final Timer moveGames = new Timer(3000, new ActionListener() {
			   public void actionPerformed(ActionEvent evt) {
				   try {
						//Check if game has terminated
						runningGame.exitValue();
						//If you make it past exitValue without an exception, we're good!
						InputManager.getManager().allowInput(true);
						Selector.revalidateSelector();
						((Timer)evt.getSource()).stop();
					} catch(Exception e) {
						//If an error occurs, then focus on the window and continue
						try {
							if(gameTitle != null) {
								Runtime.getRuntime().exec("cmd /c call sendKeys.bat \"" + gameTitle + "\" \"\"");
							} else {
								Runtime.getRuntime().exec("cmd /c call sendKeys.bat \"" + gameName + "\" \"\"");
							}
						} catch (IOException e1) {} 
					}
				   
			   }
		   });
		   moveGames.start();
			   
		} catch(IOException e) {}
		//Allow input to come in once the game ends
	}
}
