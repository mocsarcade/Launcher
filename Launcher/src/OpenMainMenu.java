import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OpenMainMenu extends Function {
	
	private JPanel contentPane;
	private JFrame mainFrame;
	
	public OpenMainMenu(JFrame pane, JPanel content) {
		contentPane = content;
		mainFrame = pane;
	}
	
	public void activate() {
		try {
			//Get rid of old menu
			contentPane.removeAll();
			mainFrame.remove(contentPane);
			//Make and add new menu
			contentPane = MainMenu.createMenu();
			mainFrame.add(contentPane);
			contentPane.revalidate();
			contentPane.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
