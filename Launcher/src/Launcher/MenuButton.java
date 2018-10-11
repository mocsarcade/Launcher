package Launcher;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

import OpenFunctions.ButtonInfo;

public class MenuButton extends JPanel {

	protected ButtonInfo info;
	protected static MenuButton[][] buttons;
    public int curRow;
    public int curCol;
    
	public MenuButton(MenuButton[][] _buttons) {
		//First menuItem initializes buttons
		buttons = _buttons;
	}
    
	public MenuButton(int row, int col, int width, int height, ButtonInfo thisInfo) {
        setPreferredSize(new Dimension(width, height));

        JLabel image = new JLabel(thisInfo.gameImage);
		add(image);

		curRow = row;
	    curCol = col;

	    buttons[row][col] = this;
	    info = thisInfo;
	}
	
	public static MenuButton[][] GetButtons() {
		return buttons;
	}
	
	public int GetXPos() {
		return getX()+getParent().getX();
	}
	
	public int GetYPos() {
		return getY()+getParent().getY();
	}
	
	public MenuButton GetButtonRef() {
		return this;
	}
	
	public void activate() {
		info.activate();
	}
}