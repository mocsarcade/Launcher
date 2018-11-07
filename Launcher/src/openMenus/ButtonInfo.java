package openMenus;

import javax.swing.Icon;
import OpenFunctions.Function;

public class ButtonInfo {
	
	public Icon gameImage;
	public Function openCommand;
	public int gameNum;
	
	public ButtonInfo(Icon image, Function command, int gameID) {
		gameImage = image;
		openCommand = command;
		gameNum = gameID;
	}

	public ButtonInfo() {
		gameImage = null;
		openCommand = null;
	}

	public void activate() {
		openCommand.activate();
	}
	
	public ButtonInfo copyInfo() {
		return new ButtonInfo(gameImage, openCommand, gameNum);
	}

}
