import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIMain {

	public static void main(String[] args) throws IOException {
	      //Initialize Static Array of buttons on screen:
		  int row=3; //Three rows, including headerMenus
		  int col=8;
		  new MenuButton(new MenuButton[row][col]);
		  
		  
		  JPanel headers = CreateHeader();

	      
	      
	      //Place both panels together into the main frame: One on top and one on bottom
	      JFrame pane = new JFrame();
	      pane.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      pane.setLocationRelativeTo(null);
	      pane.setLayout(null);
	      //Create selector
	      Selector select = new Selector(MenuButton.GetButtons());
	      pane.add(select);
	      //Add header (stays the same the WHOLE TIME)
	      pane.add(headers);
	      //Open MainMenu
	      JPanel gamesMenu = MainMenu.createGameMenu();
	      
	      pane.add(gamesMenu);

	      pane.setVisible(true);
	}
	
	public static JPanel CreateHeader() throws IOException {
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  //Create Header
		  //This menu has 3 buttons 250 wide each with extra components for buffer space
		  int buttonCol = screenSize.width/250;
		  JPanel headerMenus = new JPanel();
		  headerMenus.setLayout(new GridLayout(1,buttonCol));
		  //Browse
		  GameInfo browse = new GameInfo(new ImageIcon(ImageIO.read(new File("images/main.jpg"))));
		  headerMenus.add(new MenuButton(0, 0, 250, 100, browse));
		  //Controls
		  GameInfo controls = new GameInfo(new ImageIcon(ImageIO.read(new File("images/keybinds.jpg"))));
		  headerMenus.add(new MenuButton(0, 1, 250, 100, controls));
		  //Add Game
		  GameInfo addGame = new GameInfo(new ImageIcon(ImageIO.read(new File("images/addgame.jpg"))));
		  headerMenus.add(new MenuButton(0, 2, 250, 100, addGame));
		  //Create blank spots
	      for (int i = 3; i < buttonCol; i++) {
	    	  if(i < buttonCol) {
	    		  headerMenus.add(new EmptyItem(0, i, 250, 50, browse));
	    	  } else {
	    		  headerMenus.add(new EmptyItem(0, buttonCol-1, 250, 50, browse));
	    	  }
	       }
	      headerMenus.setBounds(0, 0, screenSize.width, 110);
	      return headerMenus;
	}

}
