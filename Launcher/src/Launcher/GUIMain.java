package Launcher;
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

import OpenFunctions.*;
import openMenus.ButtonInfo;
import openMenus.EmptyItem;
import openMenus.MenuButton;
import openMenus.OpenControlMenu;
import openMenus.OpenMainMenu;

public class GUIMain {

	public static void main(String[] args) throws IOException {
	      //Initialize Static Array of buttons on screen:
		  int row=3; //Three rows, including headerMenus
		  int col=9;
		  new MenuButton(new MenuButton[row][col]);
		  
	      
	      //Place both panels together into the main frame: One on top and one on bottom
	      JFrame pane = new MainFrame();
	      pane.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	      //Create Game Menu
	      JPanel contentMenu = new JPanel();
	      MainMenu.createMenu(contentMenu);
	      //Create Header menu
		  JPanel headers = CreateHeader(pane, contentMenu);
	      Selector select = new Selector(MenuButton.GetButtons());
	      
	      //Create Selector
	      GridBagConstraints c = new GridBagConstraints();
	      pane.add(select);
	      //Add header (stays the same the WHOLE TIME)
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
		  headerMenus.setLayout(new GridLayout(1,buttonCol));
		  //Browse
		  ButtonInfo browse = new ButtonInfo(new ImageIcon(ImageIO.read(new File("images/main.jpg"))), new OpenMainMenu(pane, content));
		  headerMenus.add(new MenuButton(0, 0, 250, 100, browse));
		  //Controls
		  ButtonInfo controls = new ButtonInfo(new ImageIcon(ImageIO.read(new File("images/keybinds.jpg"))), new OpenControlMenu(pane, content));
		  headerMenus.add(new MenuButton(0, 1, 250, 100, controls));
		  //Add Game
		  ButtonInfo addGame = new ButtonInfo(new ImageIcon(ImageIO.read(new File("images/addgame.jpg"))), new OpenMainMenu(pane, content));
		  headerMenus.add(new MenuButton(0, 2, 250, 100, addGame));
		  //Create blank spots
	      for (int i = 3; i < buttonCol; i++) {
	    	  if(i < buttonCol) {
	    		  headerMenus.add(new EmptyItem(0, i, 250, 50, browse));
	    	  }
	       }
	      //headerMenus.setBounds(0, 0, screenSize.width, 110);
	      return headerMenus;
	}

}
