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
	
	public EmptyItem(int row, int col, int width, int height) {
		super(row, col, width, height);
		setVisible(false);
	}
	
	@Override
	protected void setActive() {
		buttons[curRow][curCol-1].setActive();
	}
}
