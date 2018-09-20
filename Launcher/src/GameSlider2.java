import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameSlider2 extends JPanel {

   private JButton[][] buttons;

   public GameSlider2(int row, int col) {
      super(new GridLayout(row, col));
      Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
      Border defBorder = (new JButton("temp")).getBorder();
      buttons = new JButton[row][col];
      for (int i = 0; i < buttons.length; i++) {
         for (int j = 0; j < buttons[0].length; j++) {
        	//Each button has its own row
            final int curRow = i;
            final int curCol = j;
            buttons[i][j] = new JButton(i + ", " + j);
            //Define key-based behavior for each button
            buttons[i][j].addKeyListener(enter);
            buttons[i][j].addKeyListener(new KeyAdapter() {
               @Override
               public void keyPressed(KeyEvent e) {
                  switch (e.getKeyCode()) {
                  case KeyEvent.VK_UP:
                     if (curRow > 0) {
                    	buttons[curRow][curCol].setBorder(defBorder);
                        buttons[curRow - 1][curCol].requestFocus();
                  		buttons[curRow - 1][curCol].setBorder(redBorder);
                     }
                     break;
                  case KeyEvent.VK_DOWN:
                     if (curRow < buttons.length - 1) {
                     	buttons[curRow][curCol].setBorder(defBorder);
                        buttons[curRow + 1][curCol].requestFocus();
                  		buttons[curRow + 1][curCol].setBorder(redBorder);
                     }
                     break;
                  case KeyEvent.VK_LEFT:
                     if (curCol > 0) {
                     	buttons[curRow][curCol].setBorder(defBorder);
                        buttons[curRow][curCol - 1].requestFocus();
                  		buttons[curRow][curCol - 1].setBorder(redBorder);
                     }
                     break;
                  case KeyEvent.VK_RIGHT:
                     if (curCol < buttons[curRow].length - 1) {
                     	buttons[curRow][curCol].setBorder(defBorder);
                        buttons[curRow][curCol + 1].requestFocus();
                     	buttons[curRow][curCol + 1].setBorder(redBorder);
                     }
                     break;
                  default:
                     break;
                  }
               }
            });
            //Give each button a size and add it to the menu
            buttons[i][j].setPreferredSize(new Dimension(100, 250));
            add(buttons[i][j]);
         }
      }
   }

   private KeyListener enter = new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            ((JButton) e.getComponent()).doClick();
         }
      }
   };
}
