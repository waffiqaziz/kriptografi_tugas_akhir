/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.ReadData;
import user.User;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class DetailEmail {

  public DetailEmail(User n,int tampilkan, String cipherText) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    JFrame frame = new JFrame("MAIL - In");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createUI(frame,n,tampilkan, cipherText);
    frame.setSize(720, 568);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
  
  private static void createUI(final JFrame frame, User n, int tampilkan, String cipherText) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    ReadData rd = new ReadData();

    JPanel panel = new JPanel();
    LayoutManager BorderLayout = new BorderLayout();
    JTextArea taContent = new JTextArea();
    panel.setLayout(BorderLayout);

    JButton btnBack = new JButton("Kembali");
    final JLabel label = new JLabel();


  //ADD TO PANEL
    frame.add(panel);
    frame.setSize(550, 400);
    frame.setVisible(true);
    
    panel.add(taContent);
    panel.add(label);
    panel.add(btnBack);
    
   
  //ACTION LISTENER
    btnBack.addActionListener((ActionEvent e) -> {
      frame.dispose();
      try {
        new ShowEmailIn(n, tampilkan);
      } catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
        Logger.getLogger(DetailEmail.class.getName()).log(Level.SEVERE, null, ex);
      }
    });

    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }
}


