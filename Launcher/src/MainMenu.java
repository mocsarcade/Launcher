import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {// extends JPanel {

   //public MainMenu(int row, int col) {
   public static void main(String[] args) throws IOException {
      //super(new GridLayout(row, col));
      //Initialize MenuItems
	  int row=3; //Three rows, including headerMenus
	  int col=8;
	  new MenuButton(new MenuButton[row][col]);
	  
	  //Make a GameInfo
	  GameInfo pacMan = new GameInfo(new ImageIcon(ImageIO.read(new File("images/pacman_icon_2.jpg"))));

	  
	  //Make topButtons for other menus
	  //This menu is divided into six parts: 3 extra menus and 3 filled spots
	  int buttonCol = 9;
	  JPanel headerMenus = new JPanel();
	  headerMenus.setLayout(new GridLayout(1,col));
	  headerMenus.add(new MenuButton(0, 0, 100, 50, pacMan));
	  headerMenus.add(new MenuButton(0, 1, 100, 50, pacMan));
	  headerMenus.add(new MenuButton(0, 2, 100, 50, pacMan));
	  //Create blank spots
      for (int i = 3; i < buttonCol; i++) {
    	  if(i < col) {
    		  headerMenus.add(new EmptyItem(0, i, 100, 50, pacMan));
    	  } else {
    		  headerMenus.add(new EmptyItem(0, col-1, 100, 50, pacMan));
    	  }
          //add(new JPanel());
       }
      
      //Games Panel
	  JPanel b = new JPanel();
	  b.setLayout(new GridLayout(2,col));
      for (int i = 1; i <row; i++) { //Start at 1 because row 0 is headerMenus
         for (int j = 0; j < col; j++) {
            b.add(new MenuButton(i, j, 100, 200, pacMan));
         }
      }
      
      //Place both panels together into the main frame: One on top and one on bottom
      JFrame pane = new JFrame();
      Insets insets = pane.getInsets();
      pane.setExtendedState(JFrame.MAXIMIZED_BOTH);
      pane.setLocationRelativeTo(null);
      pane.setLayout(null);
      //pane.add(headerMenus, BorderLayout.NORTH);
      //pane.add(new JSeparator(), BorderLayout.CENTER);
      //pane.add(b, BorderLayout.SOUTH);
      pane.add(headerMenus);
      pane.add(b);
      Dimension size = headerMenus.getPreferredSize();
      headerMenus.setBounds(25 + insets.left, 5 + insets.top,
              size.width, size.height);
      b.setBounds(insets.left, 205 + insets.top,
              250*col, 250*2);
      
      pane.setVisible(true);
      
      Selector select = new Selector(MenuButton.GetButtons(), pane);
      pane.add(select);
      select.requestFocus();
   }
}
