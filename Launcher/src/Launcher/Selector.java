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

public class Selector extends JPanel {
	
	public MenuButton curSelection;
	public MenuButton[][] fullMenu;

	public Selector(MenuButton[][] buttons) {
		//Save menu setup
		setFocusable(true);
		fullMenu = buttons;
		
		//Set look
		Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
     	this.setBorder(redBorder);
     	setOpaque(false);
     	
	    curSelection = buttons[0][0];
	    LoadSelection(0, 0);

        //Define behavior for when enter is pushed. This will change
	    //In subclasses
        addKeyListener(getEnterListener());
        //Give each button a size and add it to the menu
        
	      //Define movement behavior
	      addKeyListener(new KeyAdapter() {
	          @Override
	          public void keyPressed(KeyEvent e) {
	             switch (e.getKeyCode()) {
	             case KeyEvent.VK_UP:
	                if (curSelection.curRow > 0) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow-1][curCol].setActive();
	                	LoadSelection(-1, 0);
	                }
	                break;
	             case KeyEvent.VK_DOWN:
	                if (curSelection.curRow < buttons.length - 1) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow+1][curCol].setActive();
	                	LoadSelection(1, 0);
	                }
	                break;
	             case KeyEvent.VK_LEFT:
	                if (curSelection.curCol > 0) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow][curCol-1].setActive();
	                	LoadSelection(0, -1);
	                }
	                break;
	             case KeyEvent.VK_RIGHT:
	                if (curSelection.curCol < buttons[curSelection.curRow].length - 1) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow][curCol+1].setActive();
	                	LoadSelection(0, 1);
	                }
	                break;
	             default:
	                break;
	             }
	          }
	       });
	}
	
	public void LoadSelection(int x, int y) {
		//The object we are over may have hidden data or a special position, so set selector to be over its true position
	    curSelection = fullMenu[curSelection.curRow + x][curSelection.curCol + y].GetButtonRef();
	    setBounds(curSelection.GetXPos(),curSelection.GetYPos(),curSelection.getWidth(), curSelection.getHeight());
	}

   protected KeyListener getEnterListener() {
	   return new KeyAdapter() {
	      @Override
	      public void keyTyped(KeyEvent e) {
	         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
	            curSelection.activate();
	         }
	      }
	   };
   }
	
}
