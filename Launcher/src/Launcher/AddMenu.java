package Launcher;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import openMenus.GameFinder;

/*
 * The addGame menu will depend on mouse input, so no troll using the Mocs Arcade (which only supports keyboard input) can add crazy games.
 * The top part will be two drop-down bars: One is the SOURCE (computer or github) and the other is the TYPE (.jar, .exe, .py, etc).
 * The bottom part will be either a file-explorer for if the source is computer, or a url input for if it's a github.
 * 
 * The file creating will be hardest part
 * 
 * NOTE: The AddGame method is not finished yet! This will have to be finished before including the addGame menu to the program
 */
public class AddMenu {
	
	public static JComponent sourceFinder;
	public static GridBagConstraints sourceConstraints;
	public static JPanel contentPane;
	
   public static void createMenu(JPanel games) throws IOException {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  contentPane = games;
      //Initialize MenuItems
	  contentPane.setLayout(new GridBagLayout());
	  contentPane.setBounds(0, screenSize.height-250*2-100, screenSize.width, 250*2+100);
      
      //SourceBox
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 2;
      c.gridy = 0;
      c.weightx = 1;
      String[] sourceList = {"My Computer", "Github"};
      JComboBox<String> sourceBox = new JComboBox<String>(sourceList);
      sourceBox.addActionListener(new ActionListener() {
    		   public void actionPerformed(ActionEvent e) {
    			   if(e.getSource() instanceof JComboBox<?>) {
    			        JComboBox<String> cb = (JComboBox<String>) e.getSource();
    			        String sourceName = (String)cb.getSelectedItem();
    			        contentPane.remove(sourceFinder);
    			        sourceFinder = getSourceMaker(sourceName);
    			        //GameFinder jc = new GameFinder();
    			        contentPane.add(sourceFinder, sourceConstraints);
    			        contentPane.revalidate();
    			        contentPane.repaint();
    			   }
    			}
      });
      contentPane.add(sourceBox, c);
      
      //TypeBox
      GridBagConstraints typec = new GridBagConstraints();
      typec.gridx = 2;
      typec.gridy = 1;
      typec.weightx = 1;
      String[] typeList = {"Ant Buildfile", "Java Jar"};
      JComboBox<String> typeBox = new JComboBox<String>(typeList);
      contentPane.add(typeBox, typec);

      //Labels
      GridBagConstraints labelC = new GridBagConstraints();
      labelC.gridx = 0;
      labelC.gridy = 0;
      labelC.weightx = 1;
      labelC.insets = new Insets(screenSize.height/20, 0, 0, 0);
      JLabel imageLabel = new JLabel("Choose Game Image");
      contentPane.add(imageLabel, labelC);

      //Labels
      GridBagConstraints labelC2 = new GridBagConstraints();
      labelC2.gridx = 1;
      labelC2.gridy = 0;
      labelC2.weightx = 1;
      labelC2.insets = new Insets(screenSize.height/20, 0, 0, 0);
      JLabel imageLabel2 = new JLabel("Find Game File");
      contentPane.add(imageLabel2, labelC2);
      
      //Game Image
      GridBagConstraints imageCon = new GridBagConstraints();
      imageCon.gridx = 0;
      imageCon.gridy = 1;
      imageCon.weightx = 1;
      imageCon.insets = new Insets(0, screenSize.width/50, screenSize.height/20, screenSize.width/20);
      imageCon.fill = GridBagConstraints.HORIZONTAL;
      JFileChooser imageFinder = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "Images", "jpg", "pdf", "png");
      imageFinder.setFileFilter(filter);
      // Center the text
      contentPane.add(imageFinder, imageCon);
      
      //FileExplorer (or url box)
      sourceConstraints = new GridBagConstraints();
      sourceConstraints.gridx = 1;
      sourceConstraints.gridy = 1;
      sourceConstraints.weightx = 1;
      sourceConstraints.fill = GridBagConstraints.HORIZONTAL;
      sourceConstraints.insets = new Insets(0, 0, screenSize.height/20, 0);
      sourceFinder = getSourceMaker((String) sourceBox.getSelectedItem());
      contentPane.add(sourceFinder, sourceConstraints);
      
      //Game Description
      GridBagConstraints desC = new GridBagConstraints();
      desC.gridx = 0;
      desC.gridy = 2;
      desC.weightx = 1;
      desC.ipadx = screenSize.width/16;
      desC.ipady = screenSize.height/32;
      desC.fill = GridBagConstraints.HORIZONTAL;
      desC.gridheight = 2;
      JTextField description = new JTextField("Enter Game Description");
      // Center the text
      description.setHorizontalAlignment(JTextField.CENTER);
      contentPane.add(description, desC);
      
      
      //Game name
      GridBagConstraints c3 = new GridBagConstraints();
      c3.gridx = 1;
      c3.gridy = 2;
      c3.weightx = 1;
      c3.ipadx = 50; c3.ipady = 10;
      //c3.fill = GridBagConstraints.HORIZONTAL;
      JTextField nameBox = new JTextField("Enter Game Name");
      // Center the text
      nameBox.setHorizontalAlignment(JTextField.CENTER);
      contentPane.add(nameBox, c3);
      
      //Game Title
      GridBagConstraints c5 = new GridBagConstraints();
      c5.gridx = 1;
      c5.gridy = 3;
      c5.weightx = 1;
      c5.ipadx = 50; c3.ipady = 10;
      //c3.fill = GridBagConstraints.HORIZONTAL;
      JTextField titleBox = new JTextField("Enter Game title");
      // Center the text
      titleBox.setHorizontalAlignment(JTextField.CENTER);
      contentPane.add(titleBox, c5);
      
      
      //Add Game button
      GridBagConstraints c4 = new GridBagConstraints();
      c4.gridx = 2;
      c4.gridy = 2;
      c4.weightx = 1;
      //c4.insets = new Insets(0, screenSize.width/8, 0, screenSize.width/8);
      c4.fill = GridBagConstraints.HORIZONTAL;
      c4.gridheight = 2;
      JButton submitButton = new JButton("Add");
      submitButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){  
		          System.out.println("Making new game!");
		          AddGame(sourceFinder, imageFinder, (String) typeBox.getSelectedItem(), nameBox.getText(), description.getText());
		      }
		  });  
      contentPane.add(submitButton, c4);
      
      //Set arrow keys to affecting the menu. You don't get to add a game without a mouse
      Selector.refocus();
   }
   
   private static JComponent getSourceMaker(String sourceType) {
	   if(sourceType.equals("My Computer")) {
		   return new GameFinder();
	   } else {
		   return new JTextField();
	   }
   }
   
   private static void AddGame(JComponent sourceFinder, JFileChooser imageFinder, String type, String gameName, String description) {
	   //Check that no folder of gameName exists yet
	   
	   	   //If one does exist, throw exception
	   
	   //Create new folder in games file
	   
	   //Copy Image file from imageFinder into the file
	   
	   //Check if sourceFinder is a JFileChooser or a JTextField
	   
	   //Create build.bat
	   		//cd to running directory (either sourceFinder's path or .git file)
	   
	   		//Git pull (only if sourceFinder is JTextField)
	   
	   		//Run command (depends on type)
	   
	   //Create info.txt file
	   		//Name and title are both gameName
	   
	   		//Description
	   
	   //Append gameName to games text file
   }
}
