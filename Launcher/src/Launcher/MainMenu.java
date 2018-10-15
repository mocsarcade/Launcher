package Launcher;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import OpenFunctions.Function;
import OpenFunctions.GameFunction;
import openMenus.ButtonInfo;
import openMenus.MenuButton;

public class MainMenu {// extends JPanel {

   //public MainMenu(int row, int col) {
   public static void createMenu(JPanel games) throws IOException {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Initialize MenuItems
	  
	  //Make GameInfo. Right now it holds a useless function object. Later we will create OpenGame objects
	  //for what type of game this object is
      List<ButtonInfo> gameInfo = new ArrayList<ButtonInfo>();
	  //Load all games and place in a LIST of GameFunction objects
	  Scanner in = new Scanner(new File("games/allGames.txt"));
	  while(in.hasNext()) {
		  String gameName = in.next().trim();
		  gameInfo.add(new ButtonInfo(
				  new ImageIcon(ImageIO.read(new File("games/" + gameName + "/image.jpg"))),
				  new GameFunction(new Scanner(new File("games/" + gameName + "/runCommand.txt"))) ));
	  }
	  Iterator<ButtonInfo> infoIter = gameInfo.iterator();
	  
	  
      //Games Panel
	  int rows=2;
	  int cols=7;
	  int counter=0;
	  games.setLayout(new GridLayout(rows,cols));
      for (int i = 1; i <rows+1; i++) { //Start at 1 because row 0 is headerMenus
         for (int j = 0; j < cols; j++) {
        	 if(infoIter.hasNext()) {
            	 games.add(new MenuButton(i, j, 250, 250, infoIter.next()));
            	 counter++;
        	 }
         }
      }

      //games.setBounds(0, screenSize.height-250*2-100, screenSize.width, 250*2+100);
      games.setBounds(0, screenSize.height-250*2-100, 250*Math.min(cols, counter), 250*2+100);
      //return games;
      
   }
}
