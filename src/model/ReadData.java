/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import control.ControlUser;
import control.RSAUtil;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import user.User;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class ReadData {
  public String[][] readEmailIn(User n) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    // untuk menghitung jumlah baris
    CountRow cr = new CountRow();
    RSAUtil rsa = new RSAUtil();
    
    ControlUser cu = new ControlUser();
    
    // untuk menyimpan data
    String data[][] = new String[cr.countRow(n)][2]; // ada 5 kolomwa
    
    try {
      MyConnection myConnection = new MyConnection();
      PreparedStatement ps;
      ResultSet rs;

      String query = "Select * from `log` WHERE `emailPenerima`=?";
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, n.getEmail());
      rs = ps.executeQuery();

      int row = 0;
      while (rs.next()) { //konversi tabel ke string
        data[row][0] = rs.getString(3);
        data[row][1] = rs.getString(4);
        if(rs.getString(2).equals(n.getEmail())){
          data[row][1] = rsa.rsaDecryption(rs.getString(4), cu.getPrivateKey(n.getEmail()));
        }
        row++;
      }
      return data;
    } catch (SQLException ex) {
      System.out.println("Read Data Gagal");
      Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }
  }
  
  public String[][] readEmailOut(User n) {
    // untuk menghitung jumlah baris
    CountRow cr = new CountRow();

    // untuk menyimpan data
    String data[][] = new String[cr.countRow(n)][2]; // ada 5 kolomwa

    try {
      MyConnection myConnection = new MyConnection();
      PreparedStatement ps;
      ResultSet rs;

      String query = "Select * from `log` WHERE `emailPengirim`=?";
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, n.getEmail());
      rs = ps.executeQuery();

      int row = 0;
      while (rs.next()) { //konversi tabel ke string
        data[row][0] = rs.getString(3);
        data[row][1] = rs.getString(5);
        row++;
      }
      return data;
    } catch (SQLException ex) {
      System.out.println("Read Data Gagal");
      Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }
  }
}
