/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Podium
 */
public class JavaApplication2 {
    
    public static void main(String[] args) {

        //Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
                //CreateMenu();
        JPanel f = new JPanel();
        JPanel c = new JPanel();
        GridLayout experimentLayout = new GridLayout(2,0, 10, 10);
        f.setLayout(experimentLayout);
        c.setLayout(new GridLayout(1,3, 10, 10));
        
        for(int i = 0; i < 3; i++)
        {
            JButton newButton = new JButton("Kandumb");
            newButton.setPreferredSize(new Dimension(100, 100));
            c.add(newButton);
        }
        
        for(int i=0; i<15; i++) {
            JButton newButton = new JButton("Hello World");
            newButton.setPreferredSize(new Dimension(100, 250));
            f.add(newButton);
        }
        //JPanel panel = new JPanel();
        //panel.setBounds(10,10,20,20);
        //b.setBorder(redBorder); 
        //f.add(panel);
        //f.add(redBorder);
        JFrame pane = new JFrame();
        pane.add(c, BorderLayout.NORTH);
        //pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(f, BorderLayout.SOUTH);
        
        pane.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pane.setVisible(true);
    }
    
}
