package Launcher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RepaintManager;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import OpenFunctions.GameFunction;
import openMenus.ButtonInfo;
import openMenus.MenuButton;
import openMenus.GameButton;

public class MainMenu {// extends JPanel {
	
	//public static int activeX;
	//public static int activeY;
	private static JTextPane description;
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
	  games.setLayout(null);
      games.setBounds(0, screenSize.height-250*2-100, screenSize.width, 250*2+100);
      
      //Initialize MenuItems
	  
	  //Make GameInfo. Right now it holds a useless function object. Later we will create OpenGame objects
	  //for what type of game this object is
      gameInfo = new ArrayList<ButtonInfo>();
	  //Load all games and place in a LIST of GameFunction objects
	  Scanner in = new Scanner(new File("games/allGames.txt"));
	  int gameNum = 0;
	  while(in.hasNext()) {
		  String gameName = in.next().trim();
		  //Load game description for this game and make a gameInfo object for it
		  Scanner infoReader = new Scanner(new File("games/" + gameName + "/info.txt"));
		  try {
			  String _name = "";
			  String _title = "";
			  String _description = "";
			  while(infoReader.hasNextLine()) {
				  String tag = infoReader.nextLine().trim();
				  if(tag.equals("TITLE (game window title while playing):") || tag.equals("TITLE:")) {
					  _title = infoReader.nextLine();
				  }
				  if(tag.equals("NAME:")) {
					  _name = infoReader.nextLine();
				  }
				  if(tag.equals("DESCRIPTION:")) {
					  _description = infoReader.nextLine();
				  }
			  }
			  gameInfo.add(new ButtonInfo(
					  new ImageIcon(ImageIO.read(new File("games/" + gameName + "/image.jpg"))),
					  new GameFunction(gameName, _title),
					  gameNum,
					  _name,
					  _description
					  ));
		  } catch(Exception e) {
			  //If the description text file is empty, just make the gameName the name in the inFile and leave the description blank
			  gameInfo.add(new ButtonInfo(
					  new ImageIcon(ImageIO.read(new File("games/" + gameName + "/image.jpg"))),
					  new GameFunction(gameName),
					  gameNum,
					  gameName,
					  ""
					  ));
		  }
		  gameNum++;
		  infoReader.close();
	  }
	  in.close();
	  //Iterator<ButtonInfo> infoIter = gameInfo.iterator();

	  leftButton = new GameButton(ROW, LEFT_COL, screenSize.width/(ROW+1) - 10, 250, GetLeft(active));
	  centerButton = new GameButton(ROW, MIDDLE_COL, screenSize.width/(ROW+1) - 10, 250, GetCenter(active));
	  rightButton = new GameButton(ROW, RIGHT_COL, screenSize.width/(ROW+1) - 10, 250, GetRight(active));
	  
	  //Create description box
	      description = new JTextPane();
	
		  //Set Look
		  description.setEditable(false); description.setFocusable(false); //descriptionBox.setLineWrap(true);
		  description.setBackground(new Color(0,56,107)); description.setForeground(new Color(224,170,15));
		  
		  //Set description's document to MAIN
		  description.setDocument(getDocument());
	      
	      //Finally, add to panel
		  games.add(description);
		  description.setBounds(screenSize.width-(int) games.getSize().getHeight(), 0, (int) games.getSize().getHeight(), (int) games.getSize().getHeight());
	  
      
	  //Add Games
		  games.add(leftButton);
		  leftButton.setBounds(250+125-(IMAGE_SIZE/2) - (IMAGE_SIZE+100), (games.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE);

		  games.add(centerButton);
		  centerButton.setBounds(250+125-(IMAGE_SIZE/2), (games.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE); //250 is the width of the header buttons
		  
		  games.add(rightButton);
		  rightButton.setBounds(250+125-(IMAGE_SIZE/2) + (IMAGE_SIZE+100), (games.getHeight()/8), IMAGE_SIZE, IMAGE_SIZE);
	  
   }
   
   public static void RefreshDescriptionBox(MenuButton selection) {
	   StyledDocument doc = selection.getDocument();
	   if(description != null && doc != null) {
		   description.setStyledDocument(doc);
		   //RepaintManager.currentManager(GUIMain.contentMenu).markCompletelyClean(GUIMain.contentMenu);
	   }
	   //Selector.update();
   }
   
   /*
    * Gets the stylized document for the description box. The default box that welcomes users is created here
    */
   public static StyledDocument getDocument() {
	      //Define format
		  SimpleAttributeSet globalAttr = new SimpleAttributeSet(); 
		  StyleConstants.setAlignment(globalAttr,StyleConstants.ALIGN_CENTER);

		  SimpleAttributeSet headerAttr = new SimpleAttributeSet();
		  StyleConstants.setFontSize(headerAttr, 20);
		  StyleConstants.setBold(headerAttr, true);
		  
		  //Create Description's Doc
		  StyledDocument doc = new DefaultStyledDocument();
		  try {
			doc.insertString(doc.getLength(), "Welcome to the Mocs Virtual Arcade!\n\n", headerAttr);
			doc.insertString(doc.getLength(), "Press " + InputManager.getManager().getKey('L', 0) + " or " + InputManager.getManager().getKey('R', 0) + " to move and "  + InputManager.getManager().getKey('A', 0) +  " to open a game!\n", null);
		  } catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  doc.setParagraphAttributes(0, 200, globalAttr, false);
		  return doc;
   }
   
   public static void UpdateGames(MenuButton newCenter) {
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	   final int MOVE_RATE = 10;
	   //Create timer to move games over time
	   final Timer moveGames = new Timer(1, new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   int activeX = 250+125-(IMAGE_SIZE/2);
			   for(int i=0; i<MOVE_RATE; i++) {
				   if(newCenter.getX() > activeX) {
					   leftButton.setBounds(leftButton.getX()-1, leftButton.getY(), leftButton.getWidth(), leftButton.getHeight());
					   centerButton.setBounds(centerButton.getX()-1, centerButton.getY(), centerButton.getWidth(), centerButton.getHeight());
					   rightButton.setBounds(rightButton.getX()-1, rightButton.getY(), rightButton.getWidth(), rightButton.getHeight());
				   }
				   else if(newCenter.getX() < activeX) {
					   leftButton.setBounds(leftButton.getX()+1, leftButton.getY(), leftButton.getWidth(), leftButton.getHeight());
					   centerButton.setBounds(centerButton.getX()+1, centerButton.getY(), centerButton.getWidth(), centerButton.getHeight());
					   rightButton.setBounds(rightButton.getX()+1, rightButton.getY(), rightButton.getWidth(), rightButton.getHeight());
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
				   }
			   }
			   GUIMain.pane.revalidate();
		   }
	   });
	   moveGames.start();
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
