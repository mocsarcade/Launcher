import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class EmptyItem extends MenuButton {
	
	public EmptyItem(int row, int col, int width, int height, ButtonInfo info) {
		super(row, col, width, height, info);
		//Empty item holds a place and everything, except that it is invisible
		setVisible(false);
	}
	
	@Override
	public MenuButton GetButtonRef() {
		//Empty items cannot be selected, so they move the selector to a different button
		return buttons[curRow][curCol-1].GetButtonRef();
	}
}
