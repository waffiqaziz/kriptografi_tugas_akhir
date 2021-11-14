/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import user.User;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class DetailEmail {

  public DetailEmail(User n, String from, String plainText){
    JFrame frame = new JFrame("MAIL - In");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createUI(frame,n,from, plainText);
    frame.setSize(720, 568);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
  
  private static void createUI(final JFrame frame, User n, String from, String plainText) {
    JPanel mainPanel = new JPanel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JLabel label = new JLabel("From : ");
    
    JTextField tf = new JTextField(20);
    JTextArea taContent = new JTextArea(20,45);
    
    JButton btnBack = new JButton("Back to Main Menu");
    Border border = BorderFactory.createLineBorder(Color.GRAY);
    
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(new EmptyBorder(40, 10, 10, 10)); // set border
    frame.add(mainPanel);
    mainPanel.add(panel);
    mainPanel.add(panel2);
    mainPanel.add(panel3);
    
    tf.setText(from);
    taContent.setText(plainText);
//    tf.setBorder(new EmptyBorder(10, 10, 10, 10)); // set border
    
    taContent.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    
  //ADD TO PANEL
    panel.add(label);
    panel.add(tf);
    panel2.add(taContent);
    panel3.add(btnBack);
    
   
  //ACTION LISTENER
    btnBack.addActionListener((ActionEvent e) -> {
       frame.dispose();
       MainMenu mainMenu = new MainMenu(n);
//       mainMenu.pack();
    });
  }
}


