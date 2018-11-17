package Launcher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.RepaintManager;
import javax.swing.Timer;
import javax.swing.border.Border;

import openMenus.GameButton;
import openMenus.MenuButton;

public class Selector extends JPanel {
	
	public MenuButton curSelection;
	private KeyListener InputListener;
	
	private static Selector singleton;

	public Selector() {
		singleton = this;
		//Save menu setup
		setFocusable(true);
		
		//Set look
		//Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
     	//this.setBorder(redBorder);
     	setOpaque(false);
     	
     	//Start selector at 0,0
	    curSelection = MenuButton.GetButtons()[0][0];
	    LoadSelection(0, 0);

        //Define behavior for when and key is pushed. This will change when the controlSet is changed
		InputListener = getListener();
        addKeyListener(InputListener);
	}
	
	public static void refocus() {
		singleton.requestFocus();
	}
	
	private void LoadSelection(int x, int y) {
		try {
		    curSelection = MenuButton.GetButtons()[curSelection.curRow + x][curSelection.curCol + y].GetButtonRef();
			MainMenu.RefreshDescriptionBox(curSelection);
		    revalidate();
		} catch(NullPointerException e) {
			//If there is no button at that area, it's a-okay! Just don't go there!
		}
	}

	private void LoadSelection(MenuButton newSelector) {
	    curSelection = MenuButton.GetButtons()[newSelector.curRow][newSelector.curCol].GetButtonRef();
	}

	public static void revalidateSelector() {
		singleton.LoadSelection(singleton.curSelection);
	}
	
	public void paintComponent(Graphics g) {
		setBounds(curSelection.GetXPos(),curSelection.GetYPos(),(int) curSelection.getSize().getWidth(), (int) curSelection.getSize().getHeight());
		g.setColor(new Color(250,0,0));
	    g.drawRect(0, 0, (int) curSelection.getSize().getWidth()-1, (int) curSelection.getSize().getHeight()-1);
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
        	  if(InputManager.getManager().isActive()) {
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
          }
       };
   }
	
}
