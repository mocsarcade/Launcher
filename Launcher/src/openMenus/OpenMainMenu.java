package openMenus;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Launcher.GUIMain;
import Launcher.MainMenu;
import OpenFunctions.Function;

public class OpenMainMenu extends Function {
	
	private JPanel contentPane;
	private JFrame mainFrame;
	
	public OpenMainMenu(JFrame pane, JPanel content) {
		contentPane = content;
		mainFrame = pane;
	}
	
	public void activate() {
		try {
			for(Component c : contentPane.getComponents()) {
				if(c instanceof MenuButton) {
					((MenuButton) c).delete();
				}
			}
			//Get rid of old menu
			contentPane.removeAll();
			//Make and add new menu
			MainMenu.createMenu(contentPane);
			contentPane.setBackground(GUIMain.backgroundColor);
			mainFrame.revalidate();
			mainFrame.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
