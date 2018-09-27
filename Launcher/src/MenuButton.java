import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

public class MenuButton extends JPanel {


	protected static MenuButton[][] buttons;
    public int curRow;
    public int curCol;
    
	public MenuButton(MenuButton[][] _buttons) {
		//First menuItem initializes buttons
		buttons = _buttons;
	}
    
	public MenuButton(int row, int col, int width, int height, GameInfo info) {
        setPreferredSize(new Dimension(width, height));

        JLabel image = new JLabel(info.gameImage);
		//image.setPreferredSize(new Dimension(width+20, height+20));
		add(image);
		//Border defBorder = (BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
		//Border defBorder = (new JButton("temp")).getBorder();
		//Border defBorder = new EmptyBorder(10,10,10,10);
		
		

		curRow = row;
	    curCol = col;

        //Define behavior for when enter is pushed. This will change
	    //In subclasses
        //addKeyListener(getEnterListener());
        //Give each button a size and add it to the menu
        /*
	      //Define movement behavior
	      addKeyListener(new KeyAdapter() {
	          @Override
	          public void keyPressed(KeyEvent e) {
	             switch (e.getKeyCode()) {
	             case KeyEvent.VK_UP:
	                if (curRow > 0) {
	            		buttons[curRow][curCol].setBorder(defBorder);
	            		buttons[curRow-1][curCol].setActive();
	                }
	                break;
	             case KeyEvent.VK_DOWN:
	                if (curRow < buttons.length - 1) {
	            		buttons[curRow][curCol].setBorder(defBorder);
	            		buttons[curRow+1][curCol].setActive();
	                }
	                break;
	             case KeyEvent.VK_LEFT:
	                if (curCol > 0) {
	            		buttons[curRow][curCol].setBorder(defBorder);
	            		buttons[curRow][curCol-1].setActive();
	                }
	                break;
	             case KeyEvent.VK_RIGHT:
	                if (curCol < buttons[curRow].length - 1) {
	            		buttons[curRow][curCol].setBorder(defBorder);
	            		buttons[curRow][curCol+1].setActive();
	                }
	                break;
	             default:
	                break;
	             }
	          }
	       });*/

	       buttons[row][col] = this;
	}
	
	public static MenuButton[][] GetButtons() {
		return buttons;
	}
	
	public int GetXPos() {
		return getX()+getParent().getX();
	}
	
	public int GetYPos() {
		return getY()+getParent().getY();
	}
	
	   protected void setActive() {
		Border redBorder = BorderFactory.createLineBorder(Color.RED,5);

        buttons[curRow][curCol].requestFocus();
     	buttons[curRow][curCol].setBorder(redBorder);
	   }
	   /*
	   protected KeyListener getEnterListener() {
		   return new KeyAdapter() {
			      @Override
			      public void keyTyped(KeyEvent e) {
			         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			            ((JButton) e.getComponent()).doClick();
			         }
			      }
			   };
	   }*/
}
