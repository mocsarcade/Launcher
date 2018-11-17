package openMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import Launcher.Selector;

public class GameFinder extends JFileChooser {
	
	public GameFinder() {
		super();
		addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				System.out.print("Moving...");
		        Selector.refocus();
		    }
		});
	}
	
}
