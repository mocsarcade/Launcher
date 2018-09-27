import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

public class MenuButton extends JPanel {


	protected static MenuButton[][] buttons;
    public int curRow;
    public int curCol;
    
	public MenuButton(MenuButton[][] _buttons) {
		//First menuItem initializes buttons
		buttons = _buttons;
	}
    
	public MenuButton(int row, int col, int width, int height, GameInfo info) {
        setPreferredSize(new Dimension(width, height));

        JLabel image = new JLabel(info.gameImage);
		add(image);

		curRow = row;
	    curCol = col;

	    buttons[row][col] = this;
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
	
	public void activate() {
		//Empty for now. Subclasses will open a game or open a new menu
		System.out.println("You clicked me!");
	}
}
