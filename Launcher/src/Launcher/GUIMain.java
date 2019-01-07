package Launcher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import openMenus.ButtonInfo;
import openMenus.EmptyItem;
import openMenus.MenuButton;
import openMenus.OpenControlMenu;
import openMenus.OpenMainMenu;

/*
 * Main Method
 * 
 * This project has three things to do:
 *  1. AddGame Menu (uncomment header for addMenu below and turn i=2 to i=3)
 *  2. Adding more possible game types (including steam .exe games) to the addGame menu options
 *  3. Using the controls of this arcade to set default controls of games that are opened (is that even possible?)
 *  		If this isn't possible, change controls menu to be for only 1 player, since the keys will only move the arcade itself
 */
public class GUIMain {
	
	public static JFrame pane;
	public static JPanel contentMenu;
	public static Color backgroundColor = new Color(0,28,53);
	public static Color textColor = new Color(255,127,40);
	//public static JFrame pane;

	public static void main(String[] args) throws IOException {
	      //Initialize Static Array of buttons on screen:
		  int row=3; //Three rows, including headerMenus
		  int col=9;
		  new MenuButton(new MenuButton[row][col]);
		  
	      
	      //Place both panels together into the main frame: One on top and one on bottom
	      pane = new JFrame();
	      pane.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      pane.setUndecorated(true);
	      pane.getContentPane().setBackground(backgroundColor);
	      /*
	      pane.setLocationRelativeTo(null);
	      pane.setLayout(null);
	      //Open MainMenu
	      JPanel contentMenu = new JPanel();
	      MainMenu.createMenu(contentMenu);
	      //Open headerMenu
		  JPanel headers = CreateHeader(pane, contentMenu);
	      //Create selector
	      Selector select = new Selector(MenuButton.GetButtons());
	      pane.add(select);
	      //Add header (stays the same the WHOLE TIME)
	      pane.add(headers);
	      
	      pane.add(contentMenu);
	      */
	      
	      pane.setLayout(new GridBagLayout());
	      pane.setLocationRelativeTo(null);

	      //Create Game Menu
	      contentMenu = new JPanel();
	      MainMenu.createMenu(contentMenu);
	      //Create Header menu
		  JPanel headers = CreateHeader(pane, contentMenu);

	      //Create Selector
	      Selector select = new Selector();
	      pane.add(select);
	      
		  //Add header (stays the same the WHOLE TIME)
	      GridBagConstraints c = new GridBagConstraints();
	      c.gridx = 0;
	      c.gridy = 0;
	      c.weighty = 0.1;
	      c.fill = GridBagConstraints.HORIZONTAL;
	      pane.add(headers, c);

	      GridBagConstraints c2 = new GridBagConstraints();
	      c2.gridx = 0;
	      c2.gridy = 1;
	      c2.weighty = 0.9;
	      c2.fill = GridBagConstraints.BOTH;
	      pane.add(contentMenu, c2);
	      
	      pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      pane.setVisible(true);
	}
	
	public static JPanel CreateHeader(JFrame pane, JPanel content) throws IOException {
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  //Create Header
		  //This menu has 3 buttons 250 wide each with extra components for buffer space
		  int buttonCol = screenSize.width/250;
		  JPanel headerMenus = new JPanel();
		  headerMenus.setBackground(backgroundColor);
		  headerMenus.setLayout(new GridLayout(1,buttonCol));
		  headerMenus.setPreferredSize(new Dimension(screenSize.width, 100));
		  //Browse
		  ButtonInfo browse = new ButtonInfo(new ImageIcon(Utility.Transparent(ImageIO.read(new File("images/main.jpg")))), new OpenMainMenu(pane, content), 0);
		  headerMenus.add(new MenuButton(0, 0, 250, 100, browse));
		  //Controls
		  ButtonInfo controls = new ButtonInfo(new ImageIcon(Utility.Transparent(ImageIO.read(new File("images/keybinds.jpg")))), new OpenControlMenu(pane, content), 0);
		  headerMenus.add(new MenuButton(0, 1, 250, 100, controls));
			  //Add Game
			  //ButtonInfo addGame = new ButtonInfo(new ImageIcon(Utility.Transparent(ImageIO.read(new File("images/addgame.jpg")))), new OpenAddMenu(pane, content), 0);
			  //headerMenus.add(new MenuButton(0, 2, 250, 100, addGame));
		  //Create blank spots
		  //To add another header button, add the button above and then add 1 to the initial 'i'
	      for (int i = 2; i < buttonCol; i++) {
	    	  if(i < buttonCol) {
	    		  headerMenus.add(new EmptyItem(0, i, 250, 50, browse));
	    	  }
	       }
	      //headerMenus.setBounds(0, 0, screenSize.width, 110);
	      return headerMenus;
	}

}
