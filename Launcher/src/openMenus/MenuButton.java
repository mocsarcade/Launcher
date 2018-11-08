package openMenus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

import Launcher.Selector;

public class MenuButton extends JPanel {

	protected ButtonInfo info;
	protected static MenuButton[][] buttons;
    public int curRow;
    public int curCol;
    protected JLabel label;
    
	public MenuButton(MenuButton[][] _buttons) {
		//First menuItem initializes buttons
		buttons = _buttons;
	}
	
	public void Initialize() {
		info.openCommand.Initialize(this);
	}
    
	public MenuButton(int row, int col, int width, int height, ButtonInfo thisInfo) {
		//Set size and position
        setPreferredSize(new Dimension(width, height));

        label = new JLabel(thisInfo.gameImage);
		add(label);

		curRow = row;
	    curCol = col;

	    buttons[row][col] = this;
	    info = thisInfo;
	    
	    //If the openCommand requires taking keyboard input, add it to this button as well
		if(thisInfo.openCommand instanceof KeyListener)
			addKeyListener((KeyListener) thisInfo.openCommand);
	}
    
	/**
	 * Copy Constructor
	 * @param row  the row on the button array this copied button will be on
	 * @param col  the column on the button array this copied button will be on
	 * @param oldButton  the button being copied
	 */
	public MenuButton(int row, int col, MenuButton oldButton) {
		//Set size and position
        setPreferredSize(new Dimension(oldButton.getWidth(), oldButton.getHeight()));

	    info = oldButton.copyInfo();
        label = new JLabel(info.gameImage);
		add(label);

		curRow = row;
	    curCol = col;

	    buttons[row][col] = this;
	    
	    //If the openCommand requires taking keyboard input, add it to this button as well
		if(info.openCommand instanceof KeyListener)
			addKeyListener((KeyListener) info.openCommand);
	}
	
	public int getID() {
		return info.gameNum;
	}
	
	public void setPosition(int row, int col) {
	    buttons[row][col] = this;
		curRow = row;
		curCol = col;
	}
	
	public ButtonInfo copyInfo() {
		return info.copyInfo();
	}

	public static MenuButton[][] GetButtons() {
		return buttons;
	}
	
	public void refresh() {}
	
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
