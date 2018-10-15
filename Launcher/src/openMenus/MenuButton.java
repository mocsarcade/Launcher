package openMenus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

public class MenuButton extends JPanel {

	protected ButtonInfo info;
	protected static MenuButton[][] buttons;
    public int curRow;
    public int curCol;
    protected JLabel label;

    protected int trueWidth;
    protected int trueHeight;
    
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
	    trueWidth = width;
	    trueHeight = height;

	    buttons[row][col] = this;
	    info = thisInfo;
	    
	    //If the openCommand requires taking keyboard input, add it to this button as well
		if(thisInfo.openCommand instanceof KeyListener)
			addKeyListener((KeyListener) thisInfo.openCommand);
	}
	
	public static MenuButton[][] GetButtons() {
		return buttons;
	}
	
	public void refresh() {
        setPreferredSize(new Dimension(trueWidth, trueHeight));
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
	
	public int GetTrueWidth() {
		return trueWidth;
	}
	
	public int GetTrueHeight() {
		return trueHeight;
	}
	
	public void activate() {
		info.activate();
	}
}
