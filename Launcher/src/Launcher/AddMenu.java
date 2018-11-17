package Launcher;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import openMenus.GameFinder;

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
      c.gridx = 0;
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
      typec.gridx = 1;
      typec.gridy = 0;
      typec.weightx = 1;
      String[] typeList = {"Ant Buildfile", "Java Jar"};
      JComboBox<String> typeBox = new JComboBox<String>(typeList);
      contentPane.add(typeBox, typec);
      
      //FileExplorer (or url box)
      sourceConstraints = new GridBagConstraints();
      sourceConstraints.gridx = 0;
      sourceConstraints.gridy = 1;
      sourceConstraints.weightx = 1;
      sourceConstraints.gridwidth = 2;
      sourceConstraints.fill = GridBagConstraints.HORIZONTAL;
      sourceFinder = getSourceMaker((String) sourceBox.getSelectedItem());
      contentPane.add(sourceFinder, sourceConstraints);
      
      //Game name
      GridBagConstraints c3 = new GridBagConstraints();
      c3.gridx = 0;
      c3.gridy = 2;
      c3.weightx = 1;
      c3.fill = GridBagConstraints.HORIZONTAL;
      //____ nameBox = new ___();
      //contentPane.add(nameBox, c3);
      
      //Add Game button
      GridBagConstraints c4 = new GridBagConstraints();
      c4.gridx = 1;
      c4.gridy = 2;
      c4.weightx = 1;
      c4.fill = GridBagConstraints.HORIZONTAL;
      //JButton submitButton = new ___();
      //contentPane.add(submitButton, c4);
      
      //Set arrow keys to affecting the menu. You don't get to add a game without a mouse
      Selector.refocus();
   }
   
   private static JComponent getSourceMaker(String sourceType) {
	   if(sourceType.equals("My Computer")) {
		   return new GameFinder();
	   } else {
		   return new JLabel("G-G-G-Github!");
	   }
   }
}
