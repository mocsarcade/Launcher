package Launcher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import openMenus.MenuButton;

public class Selector extends JPanel {
	
	public MenuButton curSelection;
	private KeyListener InputListener;
	
	private static Selector singleton;

	public Selector(MenuButton[][] buttons) {
		singleton = this;
		//Save menu setup
		setFocusable(true);
		
		//Set look
		Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
     	this.setBorder(redBorder);
     	setOpaque(false);
     	
     	//Start selector at 0,0
	    curSelection = buttons[0][0];
	    LoadSelection(0, 0);

        //Define behavior for when and key is pushed. This will change when the controlSet is changed
		InputListener = getListener();
        addKeyListener(InputListener);
	}
	
	public void LoadSelection(int x, int y) {
		//fullMenu = MenuButton.GetButtons();
		//System.out.println(fullMenu[curSelection.curRow + x][curSelection.curCol + y].GetButtonRef());
		//The object we are over may have hidden data or a special position, so set selector to be over its true position
	    curSelection = MenuButton.GetButtons()[curSelection.curRow + x][curSelection.curCol + y].GetButtonRef();
	    setBounds(curSelection.GetXPos(),curSelection.GetYPos(),(int) curSelection.getSize().getWidth(), (int) curSelection.getSize().getHeight());
	}
	
	public void LoadSelection(MenuButton select) {
		//fullMenu = MenuButton.GetButtons();
		//System.out.println(fullMenu[curSelection.curRow + x][curSelection.curCol + y].GetButtonRef());
		//The object we are over may have hidden data or a special position, so set selector to be over its true position
	    curSelection = select;
	    setBounds(curSelection.GetXPos(),curSelection.GetYPos(),(int) curSelection.getSize().getWidth(), (int) curSelection.getSize().getHeight());
	}
	
	public static void refocus() {
		singleton.requestFocus();
	}
	public static void revalidateSelector() {
		singleton.LoadSelection(singleton.curSelection);
	}
	
	public static void reloadKeys() {
		singleton.RELOAD_KEY_LISTENER();
	}
	public void RELOAD_KEY_LISTENER() {
		removeKeyListener(InputListener);
		InputListener = getListener();
        addKeyListener(InputListener);
	}

   protected KeyListener getListener() {
      //Define key behavior
      return new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
             if(InputManager.getManager().getKeyNum('U',0) == e.getKeyCode()) {
                 if (curSelection.curRow > 0) {
                 	LoadSelection(-1, 0);
                 }
             }
             else if(InputManager.getManager().getKeyNum('D',0) == e.getKeyCode()) {
                 if (curSelection.curRow < MenuButton.GetButtons().length - 1) {
                 	LoadSelection(1, 0);
                 }
             }
             else if(InputManager.getManager().getKeyNum('L',0) == e.getKeyCode()) {
                 if (curSelection.curCol > 0) {
                 	LoadSelection(0, -1);
                 }
             }
             else if(InputManager.getManager().getKeyNum('R',0) == e.getKeyCode()) {
                 if (curSelection.curCol < MenuButton.GetButtons()[curSelection.curRow].length - 1) {
                 	LoadSelection(0, 1);
                 }
             } else if(InputManager.getManager().getKeyNum('A',0) == e.getKeyCode()) {
             	 curSelection.activate();
             }
          }
       };
   }
	
}
