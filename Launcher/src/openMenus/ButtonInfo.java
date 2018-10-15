package openMenus;
import javax.swing.Icon;

import OpenFunctions.Function;

public class ButtonInfo {
	
	public Icon gameImage;
	public Function openCommand;
	
	public ButtonInfo(Icon image, Function command) {
		gameImage = image;
		openCommand = command;
	}
	
	public void activate() {
		openCommand.activate();
	}

}
