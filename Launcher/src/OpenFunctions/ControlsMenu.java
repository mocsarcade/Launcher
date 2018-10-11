package OpenFunctions;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlsMenu {// extends JPanel {

   //public MainMenu(int row, int col) {
   public static JPanel createMenu() throws IOException {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Initialize MenuItems
	  int rows=2;
	  int cols=5;
	  JPanel games = new JPanel();
	  games.setLayout(new GridLayout(rows,cols));
	  //TODO: CREATE BUTTONS AND STUFF IN THIS MENU

      games.setBounds(0, screenSize.height-250*2-100, cols*250, 250*2);
      return games;
      
   }
}
