/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class ReadData {
  public String[][] readEmailIn(User n) {
    // untuk menghitung jumlah baris
    CountRow cr = new CountRow();

    // untuk menyimpan data
    String data[][] = new String[cr.countRow(n)][5]; // ada 5 kolomwa

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
        data[row][0] = rs.getString(4);
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
  
  public String[][] readEmailOut(User n) {
    // untuk menghitung jumlah baris
    CountRow cr = new CountRow();

    // untuk menyimpan data
    String data[][] = new String[cr.countRow(n)][5]; // ada 5 kolomwa

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
