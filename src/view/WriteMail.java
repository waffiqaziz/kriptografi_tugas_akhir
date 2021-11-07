/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import control.ControlUser;
import control.RSAUtil;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.JTextArea;
import user.User;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class WriteMail {

  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("MAIL - Write");
  JLabel lEmailPenerima = new JLabel("Subject");
  JLabel lContent = new JLabel("Content");

  JTextField tfEmailPenerima = new JTextField(15);
  JTextArea taContent = new JTextArea();
  
  Border border = BorderFactory.createLineBorder(Color.GRAY);
 
  JButton btnSend = new JButton("Send");
  JButton btnReset = new JButton("Reset");
  JButton btnBack = new JButton("Back");

  ControlUser control = new ControlUser();

  public WriteMail(User n) {
    window.setLayout(null);
    window.setSize(720, 568);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan
    
    taContent.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//ADD COMPONENT
    window.add(lEmailPenerima);
    window.add(lContent);
    window.add(tfEmailPenerima);
    window.add(taContent);
    window.add(btnSend);
    window.add(btnReset);
    window.add(btnBack);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lEmailPenerima.setBounds(80, 35, 150, 30);
    lContent.setBounds(80, 75, 70, 30);

    tfEmailPenerima.setBounds(150, 35, 450, 30);
    taContent.setBounds(150, 75, 450, 300);

    btnSend.setBounds(420, 430, 90, 30);
    btnReset.setBounds(320, 430, 90, 30);
    btnBack.setBounds(220, 430, 90, 30);

    // sett mouse pointer
    lEmailPenerima.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lContent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

// ACTION LISTENER
    btnSend.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        RSAUtil rsa = new RSAUtil();
        ControlUser cu = new ControlUser();
        
        String emailPenerima;
        String plainText;
        
        emailPenerima = tfEmailPenerima.getText();
        plainText = taContent.getText();
        
        String publicKey = null;
        String cipherText = null;
        try {
          publicKey = cu.getPublicKey(emailPenerima);
        } catch (IOException ex) {
          Logger.getLogger(WriteMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          cipherText = rsa.rsaEncryption(plainText, publicKey);
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException ex) {
          Logger.getLogger(WriteMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (control.writeEmail(n, emailPenerima, cipherText)) { // transfer
          window.dispose();
          new MainMenu(n);
          JOptionPane.showMessageDialog(null, "Send Email Success");
        } else {
          JOptionPane.showMessageDialog(null, "Send Email Failed");
        }
      }
    });

  btnReset.addActionListener ( 
    (ActionEvent arg0) -> {
      taContent.setText("");
      tfEmailPenerima.setText("");
    }
  );
  btnBack.addActionListener ( 
    (ActionEvent arg0) -> {
      window.dispose();
      new MainMenu(n);
    }
  );

// MOUSE LISTENER
  lEmailPenerima.addMouseListener ( 
    new MouseAdapter() {
      @Override
      public void mouseClicked
      (MouseEvent e) {
        tfEmailPenerima.requestFocusInWindow();
      }
    }
  );
  lContent.addMouseListener ( 
    new MouseAdapter() {
      @Override
      public void mouseClicked
      (MouseEvent e) {
        taContent.requestFocusInWindow();
      }
    }
  );

  window.addWindowListener ( 
    new WindowAdapter() {
      @Override
      public void windowClosing
      (WindowEvent e) {
       System.out.println("Closed");
      }
    }
  );

//  tfEmailPenerima.addKeyListener ( 
//    new KeyAdapter() {
//      @Override
//      public void keyPressed
//      (KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//          Send.doClick();
//        }
//      }
//    }
//  );
//  taContent.addKeyListener ( 
//    new KeyAdapter() {
//      @Override
//      public void keyPressed
//      (KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//           Send.doClick();
//        }
//      }
//    }
//  );
//  
  }
}
