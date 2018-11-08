package Launcher;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

import OpenFunctions.GameFunction;
import openMenus.ButtonInfo;
import openMenus.EmptyItem;
import openMenus.MenuButton;
import openMenus.GameButton;

public class MainMenu {// extends JPanel {
	
	//public static int activeX;
	//public static int activeY;
	public static List<ButtonInfo> gameInfo;
	public static MenuButton leftButton;
	public static MenuButton centerButton;
	public static MenuButton rightButton;
	public static final int LEFT_COL = 0;
	public static final int MIDDLE_COL = 1;
	public static final int RIGHT_COL = 2;
	public static final int ROW = 1;
	
	public static final int IMAGE_SIZE = 500;
	
	public static int active = 1;

   //public MainMenu(int row, int col) {
   public static void createMenu(JPanel games) throws IOException {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Initialize MenuItems
	  
	  //Make GameInfo. Right now it holds a useless function object. Later we will create OpenGame objects
	  //for what type of game this object is
      gameInfo = new ArrayList<ButtonInfo>();
	  //Load all games and place in a LIST of GameFunction objects
	  Scanner in = new Scanner(new File("games/allGames.txt"));
	  int gameNum = 0;
	  while(in.hasNext()) {
		  String gameName = in.next().trim();
		  gameInfo.add(new ButtonInfo(
				  new ImageIcon(ImageIO.read(new File("games/" + gameName + "/image.jpg"))),
				  new GameFunction(gameName),
				  gameNum));
		  gameNum++;
	  }
	  in.close();
	  //Iterator<ButtonInfo> infoIter = gameInfo.iterator();
	  
	  leftButton = new GameButton(ROW, LEFT_COL, screenSize.width/(ROW+1) - 10, 250, GetLeft(active));
	  centerButton = new GameButton(ROW, MIDDLE_COL, screenSize.width/(ROW+1) - 10, 250, GetCenter(active));
	  rightButton = new GameButton(ROW, RIGHT_COL, screenSize.width/(ROW+1) - 10, 250, GetRight(active));
	  
	  games.setLayout(null);
      games.setBounds(0, 100, screenSize.width, screenSize.height-100);
	  games.add(leftButton);
	  leftButton.setBounds(250+125-(IMAGE_SIZE/2) - (IMAGE_SIZE+100), (games.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE);
	  games.add(centerButton);
	  centerButton.setBounds(250+125-(IMAGE_SIZE/2), (games.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE); //250 is the width of the header buttons
	  games.add(rightButton);
	  rightButton.setBounds(250+125-(IMAGE_SIZE/2) + (IMAGE_SIZE+100), (games.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE);
	  
      //Games Panel
	  /*
	  int rows=2;
	  int cols=(int) screenSize.width/255;
	  //games.setLayout(new GridLayout(rows,cols,(screenSize.width-cols*250)/(cols-1), (int) (screenSize.getSize().getHeight()-250*rows)/(rows-1) ));
	  games.setLayout(new GridLayout(rows,cols,5, (int) (screenSize.height*0.9-500)/6 ));
	  //games.setBorder(new EmptyBorder( (int) (screenSize.height*0.9-500)/6, 0, (int) (screenSize.height*0.9-500)/6, 0));
      for (int i = 1; i <rows+1; i++) { //Start at 1 because row 0 is headerMenus
         for (int j = 0; j < cols; j++) {
        	 if(infoIter.hasNext()) {
            	 games.add(new MenuButton(i, j, screenSize.width/cols - 10, 250, infoIter.next()));
        	 } else {
	    		  //games.add(new MenuButton(i, j, screenSize.width/cols - 10, 250, new ButtonInfo(new ImageIcon(ImageIO.read(new File("images/nothing.jpg"))), new Function())));
	    		  games.add(new EmptyItem(i, j, screenSize.width/cols - 10, 250, new ButtonInfo()));
        	 }
         }
      }*/

      //games.setBounds(0, screenSize.height-250*2-100, screenSize.width, 250*2+100);
      //games.setBounds(0, screenSize.height-250*2-100, 250*Math.min(cols, counter), 250*2+100);
      //return games;
      
   }
   
   public static void UpdateGames(MenuButton newCenter) {
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	   final int MOVE_RATE = 5;
	   //Create timer to move games over time
	   final Timer moveGames = new Timer(1, new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   int activeX = 250+125-(IMAGE_SIZE/2);
			   if(newCenter.getX() > activeX) {
				   leftButton.setBounds(leftButton.getX()-MOVE_RATE, leftButton.getY(), leftButton.getWidth(), leftButton.getHeight());
				   centerButton.setBounds(centerButton.getX()-MOVE_RATE, centerButton.getY(), centerButton.getWidth(), centerButton.getHeight());
				   rightButton.setBounds(rightButton.getX()-MOVE_RATE, rightButton.getY(), rightButton.getWidth(), rightButton.getHeight());
			   }
			   else if(newCenter.getX() < activeX) {
				   leftButton.setBounds(leftButton.getX()+MOVE_RATE, leftButton.getY(), leftButton.getWidth(), leftButton.getHeight());
				   centerButton.setBounds(centerButton.getX()+MOVE_RATE, centerButton.getY(), centerButton.getWidth(), centerButton.getHeight());
				   rightButton.setBounds(rightButton.getX()+MOVE_RATE, rightButton.getY(), rightButton.getWidth(), rightButton.getHeight());
			   } else {
				   if(newCenter.equals(leftButton)) {
					   //Once movement is complete, reset each button to its position
					   leftButton.setPosition(ROW, MIDDLE_COL);
					   centerButton.setPosition(ROW, RIGHT_COL);
					   //Update active
					   active = newCenter.getID();
					   //Destroy offscreen Game
					   GUIMain.contentMenu.remove(rightButton);
					   rightButton = centerButton;
					   centerButton = leftButton;
					   //Create replacement game
					   leftButton = new GameButton(ROW, LEFT_COL, screenSize.width/(ROW+1) - 10, 250, GetLeft(active));
					   GUIMain.contentMenu.add(leftButton);
					   leftButton.setBounds(250+125-(IMAGE_SIZE/2) - (IMAGE_SIZE+100), (GUIMain.contentMenu.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE);
					   
				   } else if(newCenter.equals(rightButton)) {
					   //Once movement is complete, reset each button to its position
					   rightButton.setPosition(ROW, MIDDLE_COL);
					   centerButton.setPosition(ROW, LEFT_COL);
					   //Update active
					   active = newCenter.getID();
					   //Destroy offscreen Game
					   GUIMain.contentMenu.remove(leftButton);
					   leftButton = centerButton;
					   centerButton = rightButton;
					   //Create replacement game
					   rightButton = new GameButton(ROW, RIGHT_COL, screenSize.width/(ROW+1) - 10, 250, GetRight(active));
					   GUIMain.contentMenu.add(rightButton);
					   rightButton.setBounds(250+125-(IMAGE_SIZE/2) + (IMAGE_SIZE+100), (GUIMain.contentMenu.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE);
					   
				   }
				   ((Timer)evt.getSource()).stop();
				   GUIMain.contentMenu.revalidate();
				   Selector.refocus();
				   Selector.revalidateSelector();
			   }
		   }
	   });
	   moveGames.start();
	   /*
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   int activeX = 250+125-(IMAGE_SIZE/2);
	   while(newCenter.getX() != activeX) {
		   if(newCenter.getX() > activeX)
			   newCenter.setBounds(newCenter.getX()-1, newCenter.getY(), newCenter.getWidth(), newCenter.getHeight());
		   if(newCenter.getX() < activeX)
			   newCenter.setBounds(newCenter.getX()+1, newCenter.getY(), newCenter.getWidth(), newCenter.getHeight());
		   try {
			   java.util.concurrent.TimeUnit.MICROSECONDS.sleep(1);
		   }
		   catch(InterruptedException ex)
		   {
		   }
		   //Repainting Not Working. Perhaps make this background process?
		   GUIMain.contentMenu.revalidate();
	   }*/
   }
   
   public static ButtonInfo GetLeft(int active) {
	   //If Left button is out of bounds, loop around list
	   if(active-1 < 0) {
		   return gameInfo.get(active - 1 + gameInfo.size());
	   }
	   //If Left button is out of bounds, loop around list
	   else if(active-1 >= gameInfo.size()) {
		   return gameInfo.get(active - 1 - gameInfo.size());
	   }
	   else {
		   return gameInfo.get(active - 1);
	   }
   }
   
   public static ButtonInfo GetCenter(int active) {
	   //If Center button is out of bounds, loop around list
	   if(active < 0) {
		   return gameInfo.get(active + gameInfo.size());
	   }
	   //If Left button is out of bounds, loop around list
	   else if(active >= gameInfo.size()) {
		   return gameInfo.get(active - gameInfo.size());
	   }
	   else {
		   return gameInfo.get(active);
	   }
   }
   
   public static ButtonInfo GetRight(int active) {
	   //If Center button is out of bounds, loop around list
	   if(active + 1 < 0) {
		   return gameInfo.get(active + 1 + gameInfo.size());
	   }
	   //If Left button is out of bounds, loop around list
	   else if(active + 1 >= gameInfo.size()) {
		   return gameInfo.get(active + 1 - gameInfo.size());
	   }
	   else {
		   return gameInfo.get(active + 1);
	   }
   }
}
