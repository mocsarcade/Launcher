package openMenus;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Launcher.ControlsMenu;
import OpenFunctions.Function;

public class OpenControlMenu extends Function {
	
	private JPanel contentPane;
	private JFrame mainFrame;
	
	public OpenControlMenu(JFrame pane, JPanel content) {
		contentPane = content;
		mainFrame = pane;
	}
	
	public void activate() {
		try {
			//Get rid of old menu
			contentPane.removeAll();
			//Make and add new menu
			ControlsMenu.createMenu(contentPane);
			mainFrame.revalidate();
			mainFrame.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
