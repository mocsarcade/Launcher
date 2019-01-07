package Launcher;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import OpenFunctions.ChangeKey;
import openMenus.ButtonInfo;
import openMenus.KeyButton;

public class ControlsMenu {// extends JPanel {
	
	private static final int KEY_WIDTH = 150;
	private static final int KEY_HEIGHT = 155;

   public static void createMenu(JPanel games) throws IOException {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Initialize MenuItems
	  int rows=2;
	  int cols=9;
	  games.setLayout(new GridLayout(rows,cols,(screenSize.width-cols*KEY_WIDTH)/(cols-1), (int) (screenSize.height*0.9-(KEY_HEIGHT*2))/6 ));
      games.setBounds(0, screenSize.height-250*2-100, screenSize.width, 250*2+100);
	  //Up
      for (int i = 1; i <rows+1; i++) { //Start at 1 because row 0 is headerMenus
    	KeyButton thisButton =
    	new KeyButton(i, 0, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - Up.png"),
        		new ChangeKey('U', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('U', InputManager.getManager().getKey('U', i-1), i-1);
    	games.add(thisButton);

    	//Left
    	thisButton =
    	new KeyButton(i, 1, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - Left.png"),
        		new ChangeKey('L', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('L', InputManager.getManager().getKey('L', i-1), i-1);
    	games.add(thisButton);

    	//Down
    	thisButton =
    	new KeyButton(i, 2, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - Down.png"),
        		new ChangeKey('D', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('D', InputManager.getManager().getKey('D', i-1), i-1);
    	games.add(thisButton);

    	//Right
    	thisButton =
    	new KeyButton(i, 3, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - Right.png"),
        		new ChangeKey('R', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('R', InputManager.getManager().getKey('R', i-1), i-1);
    	games.add(thisButton);

    	//A
    	thisButton =
    	new KeyButton(i, 4, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - A.png"),
        		new ChangeKey('A', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('A', InputManager.getManager().getKey('A', i-1), i-1);
    	games.add(thisButton);

    	//B
    	thisButton =
    	new KeyButton(i, 5, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - B.png"),
        		new ChangeKey('B', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('B', InputManager.getManager().getKey('B', i-1), i-1);
    	games.add(thisButton);

    	//X
    	thisButton =
    	new KeyButton(i, 6, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - X.png"),
        		new ChangeKey('X', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('X', InputManager.getManager().getKey('X', i-1), i-1);
    	games.add(thisButton);

    	//Y
    	thisButton =
    	new KeyButton(i, 7, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - Y.png"),
        		new ChangeKey('Y', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('Y', InputManager.getManager().getKey('Y', i-1), i-1);
    	games.add(thisButton);

    	//Z
    	thisButton =
    	new KeyButton(i, 8, KEY_WIDTH, KEY_HEIGHT,
        	new ButtonInfo(
        		Transparent("images/key - Z.png"),
        		new ChangeKey('Z', i-1)
        			));
    	thisButton.Initialize();
    	thisButton.setLetter('Z', InputManager.getManager().getKey('Z', i-1), i-1);
    	games.add(thisButton);
       }
      
      //return games;
   }
   
   public static Icon Transparent(String fileURL) throws IOException {
	   return new ImageIcon(Utility.Transparent(ImageIO.read(new File(fileURL))));
   }
}
