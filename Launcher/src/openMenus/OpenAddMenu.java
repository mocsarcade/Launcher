package openMenus;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Launcher.AddMenu;
import Launcher.GUIMain;
import OpenFunctions.Function;

public class OpenAddMenu extends Function {
	
	private JPanel contentPane;
	private JFrame mainFrame;
	
	public OpenAddMenu(JFrame pane, JPanel content) {
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
			AddMenu.createMenu(contentPane);
			contentPane.setBackground(GUIMain.backgroundColor);
			mainFrame.revalidate();
			mainFrame.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
