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
public class CountRow {

  public int countRow(User n, int tampilkan) {
    int row = 0;
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
      switch (tampilkan) {
        case 1:
          ps.setString(1, n.getEmail());
          ps.setString(2, n.getEmail());
          break;
        case 2:
          ps.setString(1, n.getEmail());
          break;
        case 3:
          ps.setString(1, n.getEmail());
          break;
        default:
      }
      rs = ps.executeQuery();

      while (rs.next()) {
        row++;
      }
      return row;
    } catch (SQLException ex) {
      Logger.getLogger(CountRow.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -4;
  }
}
