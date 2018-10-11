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

import Launcher.MenuButton;

public class MainMenu {// extends JPanel {

   //public MainMenu(int row, int col) {
   public static JPanel createMenu() throws IOException {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Initialize MenuItems
	  
	  //Make GameInfo. Right now it holds a useless function object. Later we will create OpenGame objects
	  //for what type of game this object is
	  ButtonInfo pacMan = new ButtonInfo(new ImageIcon(ImageIO.read(new File("images/pacman_icon_2.jpg"))), new Function());

	  
      //Games Panel
	  int rows=2;
	  int cols=5;
	  JPanel games = new JPanel();
	  games.setLayout(new GridLayout(rows,cols));
      for (int i = 1; i <rows+1; i++) { //Start at 1 because row 0 is headerMenus
         for (int j = 0; j < cols; j++) {
        	 games.add(new MenuButton(i, j, 100, 200, pacMan));
         }
      }

      games.setBounds(0, screenSize.height-250*2-100, cols*250, 250*2);
      return games;
      
   }
}
