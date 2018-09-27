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
	public int curRow;
	public int curCol;

	public Selector(MenuButton[][] buttons) {
		//Save menu setup
		setFocusable(true);
		fullMenu = buttons;
		
		//Set look
		Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
     	this.setBorder(redBorder);
     	setOpaque(false);
		
		curRow = 0;
	    curCol = 0;
	    curSelection = buttons[0][0];
	    LoadSelection();

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
	                if (curRow > 0) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow-1][curCol].setActive();
	                	curRow--;
	                	LoadSelection();
	                }
	                break;
	             case KeyEvent.VK_DOWN:
	                if (curRow < buttons.length - 1) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow+1][curCol].setActive();
	                	curRow++;
	                	LoadSelection();
	                }
	                break;
	             case KeyEvent.VK_LEFT:
	                if (curCol > 0) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow][curCol-1].setActive();
	                	curCol--;
	                	LoadSelection();
	                }
	                break;
	             case KeyEvent.VK_RIGHT:
	                if (curCol < buttons[curRow].length - 1) {
	            		//buttons[curRow][curCol].setBorder(defBorder);
	            		//buttons[curRow][curCol+1].setActive();
	                	curCol++;
	                	LoadSelection();
	                }
	                break;
	             default:
	                break;
	             }
	          }
	       });
	}
	
	public void LoadSelection() {
	    curSelection = fullMenu[curRow][curCol];
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
