/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Waffiq Aziz
 */
public class MyConnection {

  private String url = "jdbc:mysql://localhost/messages";
  private String user = "root";
  private String pass = "";
  private Connection con = null;

  // menyambungkan database mysql
  public MyConnection(){
    try {
      con = DriverManager.getConnection(url, user, pass);
      System.out.println("Koneksi Berhasil");
    } catch (SQLException ex) {
      Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(null, "Tidak dapat terhubung ke database. Pastikan Xampp sudah aktif!","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
      con = null; // set if koneksi tidak berhasil
    }
  }
  
  public Connection getCOnnection(){
    return con;
  }
}
