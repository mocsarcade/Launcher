package OpenFunctions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Launcher.InputManager;
import Launcher.Selector;

public class ChangeKey extends Function implements KeyListener {
	
	private char functionKey; private int player;
	private boolean acceptingInput;
	
	public ChangeKey(char key, int _player) {
		functionKey = Character.toUpperCase(key);
		player = _player;
	}

	public void activate() {
		//Set focus to this key so it will accept input
		acceptingInput = true;
		holder.requestFocus();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(acceptingInput) {
			InputManager.getManager().setKey(functionKey, e.getKeyCode(), player);
			//Change the label
			holder.refresh();
			//Give mobility back to the selector
			Selector.refocus();
			Selector.reloadKeys();
			//No longer accept input
			acceptingInput=false;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

}
