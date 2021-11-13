/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JDateChooser;
import control.ControlUser;
import control.RSAUtil;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.MyConnection;
import user.User;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class Register {

  MyConnection con = new MyConnection();

  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("MAIL - Register");
  JLabel lEmail = new JLabel("Email");
  JLabel lPass = new JLabel("Password");
  JLabel lName = new JLabel("Full Name");
  JLabel lTelp = new JLabel("No. Telephone");
  JLabel lDate = new JLabel("Date of Birth");

  JLabel lguide = new JLabel("<HTML><U>Have Account?</U></HTML");

  JTextField tfEmail = new JTextField();
  JPasswordField pfPass = new JPasswordField(16);
  JTextField tfName = new JTextField(20);
  JTextField tfTelp = new JTextField(15);
  JDateChooser dcDate = new JDateChooser();

  JButton btnRegis = new JButton("Regis");
  JButton btnReset = new JButton("Reset");

  public Register() {
    window.setLayout(null);
    window.setSize(450, 380);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan

//ADD COMPONENT
    window.add(lPass);
    window.add(lName);
    window.add(lEmail);
    window.add(lTelp);
    window.add(lDate);
    window.add(lguide);
    window.add(pfPass);
    window.add(tfEmail);
    window.add(dcDate);
    window.add(tfName);
    window.add(tfTelp);
    window.add(btnRegis);
    window.add(btnReset);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lName.setBounds(90, 35, 70, 30);
    lEmail.setBounds(90, 75, 35, 30);
    lPass.setBounds(90, 115, 70, 30);
    lTelp.setBounds(90, 155, 90, 30);
    lDate.setBounds(90, 195, 80, 30);

    tfName.setBounds(200, 35, 150, 30);
    tfEmail.setBounds(200, 75, 150, 30);
    pfPass.setBounds(200, 115, 150, 30);
    tfTelp.setBounds(200, 155, 150, 30);
    dcDate.setBounds(200, 195, 150, 30);
    lguide.setBounds(180, 235, 150, 30);

    btnRegis.setBounds(240, 275, 90, 30);
    btnReset.setBounds(110, 275, 90, 30);

// SETT MOUSE POINTER
    lName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lTelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lDate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lguide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lguide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

// ACTION LISTENER
    btnRegis.addActionListener((var arg0) -> {
      User n = new User();
      ControlUser cu = new ControlUser();
      RSAUtil rsa = new RSAUtil();
      String date = null;

      String name = tfName.getText();
      String pass = String.valueOf(pfPass.getPassword());
      String email = tfEmail.getText();
      String telp = tfTelp.getText();

      // cek jika kolom ada yang kosong
      if (name.equals("") || email.equals("") || telp.equals("") || dcDate.getDate() == null) {
        JOptionPane.showMessageDialog(null, "All Form Must Filled");
      } else {

        // cek jika ada kolom yang belum di isi
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); // format tahun-bulan-hari
        date = dateformat.format(dcDate.getDate());
        System.out.println("Cek Date " + date);

        if (!cu.checkEmail(email)) { // jika tidak ada email yang sama, maka akan di masukkan kedalam database
          n.setUser(email, pass, name, telp);
          n.setDate(date);
          try {
            if (n.setPublicKey(rsa.keyUtilGenerator()) && cu.register(n)) {
              window.dispose();
              Login login = new Login();
              login.pack();
              JOptionPane.showMessageDialog(null, "New User Add");
            }
          } catch (IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IOException | NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "Register Failed", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
          }

        } else {
          JOptionPane.showMessageDialog(null, "Email has been Registered as Account");
        }
      }
    });

    btnReset.addActionListener((ActionEvent arg0) -> {
      pfPass.setText("");
      tfEmail.setText("");
      tfName.setText("");
      tfTelp.setText("");
      dcDate.setDate(null);
    });

// MOUSE LISTENER
    lguide.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println("Login Form Clicked");
        window.dispose();
        Login login = new Login();
        login.pack();
      }
    });
    lEmail.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfEmail.requestFocusInWindow();
      }
    });
    lName.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfName.requestFocusInWindow();
      }
    });
    lDate.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        dcDate.requestFocusInWindow();
      }
    });
    lPass.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        pfPass.requestFocusInWindow();
      }
    });
    lTelp.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfTelp.requestFocusInWindow();
      }
    });

// WINDOWS LISTENER   
    window.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.out.println("Closed");
      }
    });
  }
}
