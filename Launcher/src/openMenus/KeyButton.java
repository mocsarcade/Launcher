package openMenus;
import javax.swing.JLabel;

import Launcher.InputManager;

public class KeyButton extends MenuButton {
	
	private char key;
	private int player;
	
	public KeyButton(int row, int col, int width, int height, ButtonInfo thisInfo) {
		super(row, col, width, height, thisInfo);
		label = new JLabel("NULL");
		add(label);
	}
	
	@Override
	public void refresh() {
		super.refresh();
		setLetter(InputManager.getManager().getKey(key, player));
		InputManager.getManager().UpdateFile();
	}
	
	public void setLetter(String letter) {
		remove(label);
		label = new JLabel(letter);
		add(label);
		revalidate();
		repaint();
	}

	public void setLetter(char _key, String letter, int _player) {
		key = _key; player = _player;
		remove(label);
		label = new JLabel(letter);
		add(label);
		revalidate();
		repaint();
	}
}
