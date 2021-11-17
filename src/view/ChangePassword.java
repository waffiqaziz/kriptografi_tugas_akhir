/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControlUser;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import user.User;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class ChangePassword {

  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("Change Password");
  JLabel lOldPass = new JLabel("Old Password");
  JLabel lRePass = new JLabel("Retype Password");

  JPasswordField pfOldPass = new JPasswordField();
  JPasswordField pfRePass = new JPasswordField();
  JLabel lNewPass = new JLabel("New Password");

  JPasswordField pfNewPass = new JPasswordField();
  JLabel lguide = new JLabel("<HTML><U>Forget Old Password?</U></HTML");

  JButton btnChange = new JButton("Change");
  JButton btnReset = new JButton("Reset");
  JButton btnBack = new JButton("Back");
  
  ControlUser cn = new ControlUser();

  public ChangePassword(User n) {
    window.setLayout(null);
    window.setSize(380, 290);
    //  window.setDefaultCloseOperation(3);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan

//ADD COMPONENT
    window.add(lOldPass);
    window.add(pfOldPass);
    window.add(pfNewPass);
    window.add(pfRePass);
    window.add(lNewPass);
    window.add(lRePass);
    window.add(lguide);
    window.add(btnChange);
    window.add(btnReset);
    window.add(btnBack);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lOldPass.setBounds(80, 35, 65, 30);
    lNewPass.setBounds(80, 75, 70, 30);
    lRePass.setBounds(80, 115, 70, 30);
    lguide.setBounds(140, 155, 160, 30);

    pfOldPass.setBounds(170, 35, 120, 30);
    pfNewPass.setBounds(170, 75, 120, 30);
    pfRePass.setBounds(170, 115, 120, 30);

    btnChange.setBounds(230, 195, 90, 30);
    btnReset.setBounds(140, 195, 90, 30);
    btnBack.setBounds(50, 195, 90, 30);

    // sett mouse pointer
    lOldPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lNewPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lRePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lguide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    System.out.println("pass : "+ n.getPass());
// ACTION LISTENER
    btnChange.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        String newPass, oldPass, rePass;
        boolean cek = true;
        try{
          rePass = String.valueOf(pfRePass.getPassword());
          newPass = String.valueOf(pfNewPass.getPassword());
          oldPass = cn.encryptSHA(String.valueOf(pfOldPass.getPassword()));
          
          System.out.println(n.getPass() + " " + rePass + " " + newPass);
          if(rePass.equals(newPass)) {
          } else {
            JOptionPane.showMessageDialog(null, "Retype and New Password not Same");
            pfRePass.requestFocusInWindow();
            cek = false;
          }
          
          rePass = cn.encryptSHA(rePass);
          newPass = cn.encryptSHA(rePass);
          
          if (oldPass.equals(n.getPass()) && cek) { // cek Password lama dengan Password input user sama/tidak
            if (cn.changePass(newPass, n.getUserID())) { // update Password
              n.setPass(newPass);
              JOptionPane.showMessageDialog(null, "Change Password Success");
              window.dispose();
              new MainMenu(n);
            }
          } else {
            JOptionPane.showMessageDialog(null, "Password not Same" + "\nChange Password Failed", "Error Message", JOptionPane.INFORMATION_MESSAGE);
            pfOldPass.requestFocusInWindow();
          }
          
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Fill in all the columns" + "\nPassword Must be Numeric", "Error Message", JOptionPane.INFORMATION_MESSAGE);
          Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });

    btnReset.addActionListener((ActionEvent arg0) -> {
      pfNewPass.setText("");
      pfOldPass.setText("");
      pfRePass.setText("");
    });
    btnBack.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new MainMenu(n);
    });

// MOUSE LISTENER
    lguide.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println("CS Clicked");
        JOptionPane.showMessageDialog(null, "Please Contact Customer Service");
      }
    });
    lNewPass.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        pfNewPass.requestFocusInWindow();
      }
    });
    lOldPass.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        pfOldPass.requestFocusInWindow();
      }
    });
    lRePass.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        pfRePass.requestFocusInWindow();
      }
    });

    window.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.out.println("Closed");
      }
    });
  }
}
