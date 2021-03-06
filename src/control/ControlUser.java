/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;
import view.Login;
import view.Register;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class ControlUser {

  public boolean checkEmail(String email) { // cek apakah acc sudah ada/belum
    PreparedStatement ps;
    ResultSet rs;

    String query = "SELECT * FROM `user` WHERE `email` =?";
    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, email);
      rs = ps.executeQuery();

      return rs.next(); // true jika ada username yang sama
    } catch (SQLException ex) {
      Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public boolean writeEmail(User n, String emailPenerima, String cipherText) {
    PreparedStatement ps;

    String query = "INSERT INTO `log`(`emailPenerima`, `emailPengirim`, `content`) VALUES (?,?,?)";

    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, emailPenerima);
      ps.setString(2, n.getEmail());
      ps.setString(3, cipherText);

      // jika berhasil
      if (ps.executeUpdate() > 0) {
        System.out.println("pengriman pesan berhasil");
        return true;
      }
    } catch (SQLException ex) {
      System.out.println("pengiriman pesan gagal");
      Logger.getLogger(ControlUser.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return false;
  }

  public boolean register(User n) {
    PreparedStatement ps;

    String query = "INSERT INTO user (`email`, `pass`, `full_name`, `telp`, `dateOfBirth`, `publicKey`) VALUES (?,?,?,?,?,?)";

    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, n.getEmail());
      ps.setString(2, n.getPass());
      ps.setString(3, n.getNama());
      ps.setString(4, n.getTelp());
      ps.setString(6, n.getPublicKey());

      if (n.getDateOfBirth() != null) { // jika date kosong maka set null
        ps.setString(5, n.getDateOfBirth());
      } else {
        ps.setNull(5, 0);
      }

      // jika berhasil
      if (ps.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return false;
  }

  public String encryptSHA(String password) {
    try {
      //gunakan SHA-512
      MessageDigest md = MessageDigest.getInstance("SHA-512");

      // digest() menghitung password 
      // dilakukan proses enkripsi, simpan dalam bentuk byte array
      byte[] messageDigest = md.digest(password.getBytes());

      // ubah byte array ke bentuk fungsi signum 
      BigInteger no = new BigInteger(1, messageDigest);

      // ubah passwor ke nilai hex
      String hashtext = no.toString(16);

      // ubah ke agar menjadi 32 bit (minimal)
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }

      // return the HashText
      return hashtext;
    } // For specifying wrong message digest algorithms
    catch (NoSuchAlgorithmException e) {
      java.util.logging.Logger.getLogger(ControlUser.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }
    return null;
  }

  public boolean login(String email, String pass, User n) {
    PreparedStatement ps;
    ResultSet rs;
    
    pass = encryptSHA(pass); // encrypt SHA-512
    
    String query = "SELECT * FROM `user` WHERE `email` =? AND `pass` =?";

    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, email);
      ps.setString(2, pass);
      rs = ps.executeQuery();

      if (rs.next()) {
        //`user_id`, `email`, `pass`, `full_name`, `telp`, `dateOfBirth`
        // simpan data dalam variabel 
        String dtUserId = rs.getString(1);
        String dtEmail = rs.getString(2);
        String dtPass = rs.getString(3);
        String dtName = rs.getString(4);
        String dtTelp = rs.getString(5);
        String dtDate = rs.getString(6);
        
        System.out.println("dtpass : " + dtPass);
        // simpan dalam atribut
        n.setUser(dtEmail, dtPass, dtName, dtTelp);
        n.setUser_id(dtUserId);
        n.setDate(dtDate);

        return true;
      } else {
        return false;
      }
    } catch (SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  public boolean changePass(String newPass, String id) {
    PreparedStatement ps;
    String query = "UPDATE `user` SET `pass`=? WHERE `user_id`=?";

    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, newPass);
      ps.setString(2, id);
      int i = ps.executeUpdate();

      return i == 1; // jika change pin success

    } catch (SQLException ex) {
      Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  public String getPublicKey(String email) throws IOException {
    PreparedStatement ps;
    ResultSet rs;
    String query = "SELECT * FROM `user` WHERE `email` =?";

    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getCOnnection().prepareStatement(query);
      ps.setString(1, email);

      rs = ps.executeQuery();

      if (rs.next()) {
        String publicKeyRecipient = rs.getString(7);
        System.out.println(publicKeyRecipient);
        System.out.println("PublicKey berhasil");
        return publicKeyRecipient;
      } else {
        System.out.println("PublicKey penerima gagal");
      }
    } catch (SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("PublicKey penerima gagal");
    return null;
  }
}
