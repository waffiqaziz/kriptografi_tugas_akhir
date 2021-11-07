/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.MyConnection;
import javax.swing.JOptionPane;
import user.User;
import control.ControlUser;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Waffiq Aziz
 */
public class Main {

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        MyConnection myConnection = new MyConnection();
        if (myConnection.getCOnnection() == null) {
          JOptionPane.showMessageDialog(null, "Tidak dapat terhubung ke database. Pastikan Xampp sudah aktif!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        } else {
          User n = new User();
//          ControlUser cu = new ControlUser();
          new Login();
//            new WriteMail(n);
//           new AllEmail(n);
        }
      }

    });
  }
}
