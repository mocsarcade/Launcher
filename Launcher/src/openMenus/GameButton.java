package openMenus;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import Launcher.MainMenu;
import Launcher.Selector;

public class GameButton extends MenuButton {

	public GameButton(MenuButton[][] _buttons) {
		//First menuItem initializes buttons
		super(_buttons);
	}
    
	public GameButton(int row, int col, int width, int height, ButtonInfo thisInfo) {
		super(row, col, width, height, thisInfo);
	}
	
	@Override
	public MenuButton GetButtonRef() {
		//Move this button to activeX and activeY
		MainMenu.UpdateGames(this);
		return this;
	}

}
