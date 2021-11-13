/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import model.MyConnection;
import user.User;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class MainMenu extends JFrame {

  MyConnection myConnection = new MyConnection();

  String user;
  String pin;

  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("MAIL - Main Menu");
  JLabel lGuide = new JLabel();

  JButton btnChangePass = new JButton("Change Password");
  JButton btnTranfer = new JButton("Write Email");
  JButton btnEmailIn = new JButton("Email In");
  JButton btnEmailAll = new JButton("All Email");
  JButton btnEmailOut = new JButton("Email Out");
  JButton btnLogout = new JButton("Logout");
  
  public MainMenu(User n) {
    lGuide.setText("Welcome " + n.getNama());
    lGuide.setVerticalTextPosition(0);
    window.setLayout(null);
    window.setSize(380, 390);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan
    
//ADD COMPONENT
    window.add(lGuide);
    window.add(btnChangePass);
    window.add(btnEmailIn);
    window.add(btnEmailAll);
    window.add(btnEmailOut);
    window.add(btnTranfer);
    window.add(btnLogout);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lGuide.setBounds(0, 25, 370, 30);
    btnTranfer.setBounds(120, 75, 130, 30);
    btnEmailAll.setBounds(95, 115, 180, 30);
    btnEmailIn.setBounds(95, 155, 180, 30);
    btnEmailOut.setBounds(95, 195, 180, 30);
    btnChangePass.setBounds(110, 235, 155, 30);
    btnLogout.setBounds(120, 275, 130, 30);

    lGuide.setHorizontalAlignment(0);
// ACTION LISTENER
    btnChangePass.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new ChangePassword(n);
    });
    btnTranfer.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new WriteMail(n);
    });
    btnEmailIn.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      try {
        new ShowEmailIn(n,2);
      } catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    btnEmailOut.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      try {
        new ShowEmailOut(n,3);
      } catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    btnEmailAll.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      try {
        new ShowEmail(n,1);
      } catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    btnLogout.addActionListener((ActionEvent arg0) -> {
      int yes = JOptionPane.showConfirmDialog(
              null,
              "Are you sure want to Logout",
              "Confirm Logout",
              JOptionPane.YES_NO_OPTION);
      if (yes == JOptionPane.YES_OPTION) {
        window.dispose();
        new Login();
      }
    });

    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, e);
      }
    });
  }
}
