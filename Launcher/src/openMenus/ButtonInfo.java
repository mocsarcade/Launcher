package openMenus;

import javax.swing.Icon;
import OpenFunctions.Function;

public class ButtonInfo {
	
	public Icon gameImage;
	public Function openCommand;
	public int gameNum;
	public String gameName;
	public String description;
	
	public ButtonInfo(Icon image, Function command, int gameID) {
		gameImage = image;
		openCommand = command;
		gameNum = gameID;
	}
	
	public ButtonInfo(Icon image, Function command, int gameID, String name, String gameDescription) {
		gameImage = image;
		openCommand = command;
		gameNum = gameID;
		gameName = name;
		description = gameDescription;
	}
	
	public ButtonInfo(Icon image, Function command) {
		gameImage = image;
		openCommand = command;
		gameNum = 0;
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
