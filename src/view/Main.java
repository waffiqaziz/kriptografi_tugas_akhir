/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.MyConnection;

/**
 *
 * @author Waffiq Aziz
 */
public class Main {

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
      MyConnection myConnection = new MyConnection();
      if (myConnection.getCOnnection() != null) {
        Login login = new Login();
        Login login2 = new Login();
        login.pack(); 
        login2.pack(); 
//        Login login2 = new Login();
      }
    });
  }
  
  
}
