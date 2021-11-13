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
  
  public String[][] readEmail(User n, int tampilkan) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    // untuk menghitung jumlah baris
    CountRow cr = new CountRow();
    RSAUtil rsa = new RSAUtil();

    ControlUser cu = new ControlUser();

    // untuk menyimpan data
    String data[][] = new String[cr.countRow(n, tampilkan)][3]; // ada 3 kolom

    try {
      MyConnection myConnection = new MyConnection();
      PreparedStatement ps;
      ResultSet rs;

      //query tergantung yang akan ditampilkan
      String query = null;
      // 1 = tampilkan semua, 2 = tampilkan email masuk, 3 = tampilkan email keluar
      switch (tampilkan) {
        case 1 ->
          query = "Select * from `log` WHERE `emailPenerima`=? OR `emailPengirim`=?";
        case 2 ->
          query = "Select * from `log` WHERE `emailPenerima`=?";
        case 3 ->
          query = "Select * from `log` WHERE `emailPengirim`=?";
        default -> {
          System.out.println("query read table salah");
        }
      }

      ps = myConnection.getCOnnection().prepareStatement(query);
      if (tampilkan == 1) {
        ps.setString(1, n.getEmail());
        ps.setString(2, n.getEmail());
      } else if (tampilkan == 2 || tampilkan == 3) {
        ps.setString(1, n.getEmail());
      }

      rs = ps.executeQuery();
      int row = 0;

      switch (tampilkan) {
        case 1 -> {
          // show all emai.l
          while (rs.next()) { //konversi tabel ke string
//            data[row][0] = rs.getString(2);
            data[row][0] = rs.getString(3);
            data[row][1] = rs.getString(2);
            data[row][2] = rs.getString(4);
//            if (rs.getString(2).equals(n.getEmail())) {
//              data[row][2] = rsa.rsaDecryption(rs.getString(4), cu.getPrivateKey(n.getEmail()));
//            }
            row++;
          }
        }
        case 2 -> {
          // show email in
          while (rs.next()) { //konversi tabel ke string
            data[row][0] = rs.getString(3);
            data[row][1] = rs.getString(4);
//            if (rs.getString(2).equals(n.getEmail())) {
//              //rsa.rsaDecryption(ciphertext, privatekey utf-8)
//              data[row][1] = rsa.rsaDecryption(rs.getString(4), cu.getPrivateKey(n.getEmail()));
//            }
            row++;
          }
        }
        case 3 -> {
          // show email out
          while (rs.next()) { //konversi tabel ke string
            data[row][0] = rs.getString(2);
            data[row][1] = rs.getString(4);
//            if (rs.getString(2).equals(n.getEmail())) {
//              //rsa.rsaDecryption(ciphertext, privatekey utf-8)
//              data[row][1] = rsa.rsaDecryption(rs.getString(4), cu.getPrivateKey(n.getEmail()));
//            }
            row++;
          }
        }
        default -> {
        }
      }

      return data;
    } catch (SQLException ex) {
      System.out.println("Read Data Gagal");
      Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }
  }

}
