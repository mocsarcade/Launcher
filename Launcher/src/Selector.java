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
	public JFrame holder;

	public Selector(MenuButton[][] buttons, JFrame _parent) {
		//Save menu setup
		setFocusable(true);
		fullMenu = buttons;
		holder = _parent;
		
		//super(new ImageIcon(ImageIO.read(new File("images/pacman_icon_2.jpg"))));
		//Border defBorder = (BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
		//Border defBorder = (new JButton("temp")).getBorder();
		//Border defBorder = new EmptyBorder(10,10,10,10);
		
		//Set look
		Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
     	this.setBorder(redBorder);
		
		

		curRow = 1;
	    curCol = 1;
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

	       //buttons[row][col] = this;
	}
	
	public void LoadSelection() {
		//curSelection.remove(this);
	    curSelection = fullMenu[curRow][curCol];
		//curSelection.add(this);
    	System.out.println("Moving to " + curRow + "," + curCol);
        //setPreferredSize(new Dimension(curSelection.getWidth(), curSelection.getHeight()));
	    setBounds(curSelection.GetXPos(),curSelection.GetYPos(),curSelection.getWidth(), curSelection.getHeight());
    	
	    //holder.revalidate();
	    //holder.repaint();
    	//requestFocus();
    	//curSelection.revalidate();
    	//curSelection.repaint();
	}

   protected KeyListener getEnterListener() {
	   return new KeyAdapter() {
	      @Override
	      public void keyTyped(KeyEvent e) {
	         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
	            ((JButton) e.getComponent()).doClick();
	         }
	      }
	   };
   }
	
}
