/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControlUser;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import user.User;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class Login extends JFrame {

  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("MAIL - Login");
  
  JLabel lEmail = new JLabel("Email");
  JTextField tfEmail = new JTextField();

  JLabel lPass = new JLabel("Password");
  JPasswordField pfPass = new JPasswordField();

  JLabel lguide = new JLabel("<HTML><U>Dont Have Account?</U></HTML");

  JButton btnLogin = new JButton("Login");
  JButton btnReset = new JButton("Reset");

  public Login() {
    window.setLayout(null);
    window.setSize(380, 250);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan

//ADD COMPONENT
    window.add(lEmail);
    window.add(tfEmail);
    window.add(pfPass);
    window.add(lPass);
    window.add(lguide);
    window.add(btnLogin);
    window.add(btnReset);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lEmail.setBounds(80, 35, 60, 30);
    lPass.setBounds(80, 75, 58, 30);
    lguide.setBounds(130, 115, 120, 30);

    tfEmail.setBounds(170, 35, 120, 30);
    pfPass.setBounds(170, 75, 120, 30);

    btnLogin.setBounds(200, 155, 90, 30);
    btnReset.setBounds(80, 155, 90, 30);

    // sett mouse pointer
    lEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lguide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

// ACTION LISTENER
    btnLogin.addActionListener((ActionEvent arg0) -> {
      User n = new User();
      ControlUser cn = new ControlUser();
      
      // Tangkap input user
      String email = tfEmail.getText();
      String password = String.valueOf(pfPass.getPassword());
      System.out.println(email);
      System.out.println(password);
      if (email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Fill All Coloumn");
        tfEmail.requestFocusInWindow();
      } else {
        if (cn.login(email, password, n)) {
          window.dispose();
          MainMenu mm = new MainMenu(n);
          mm.pack();
          mm.setLocationRelativeTo(null); // center 
          JOptionPane.showMessageDialog(null, "Login Success");
        } else {
          JOptionPane.showMessageDialog(null, "Incorrect Email or Password" + "\nLogin Failed", "Error Message", JOptionPane.INFORMATION_MESSAGE);
          System.out.println("Login Failed");
          tfEmail.requestFocusInWindow();
        }
      }
    });

    btnReset.addActionListener((ActionEvent arg0) -> {
      pfPass.setText("");
      tfEmail.setText("");
    });

// MOUSE LISTENER
    lguide.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println("Register Form Clicked");
        window.dispose();
        new Register();
      }
    });
    lPass.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        pfPass.requestFocusInWindow();
      }
    });
    lEmail.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfEmail.requestFocusInWindow();
      }
    });

    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("Closed");
      }
    });

    tfEmail.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnLogin.doClick();
        }
      }
    });
    pfPass.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnLogin.doClick();
        }
      }
    });
  }
}
