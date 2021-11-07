/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class Cek {

  public static void main(String[] args) {
    RSAUtil rsa = new RSAUtil();
    ControlUser cu = new ControlUser();
    String plaintText = "Baeldung secret message";
    System.out.println("Plaintext: " + plaintText);
    String cipherText = null;
    String cipherDecrypted = null;
    String publicKey = null;
    String privateKey = null;
    
    // public dan private key
    try {
      privateKey = cu.getPrivateKey("2");
      publicKey = cu.getPublicKey("2");
    } catch (IOException ex) {
      Logger.getLogger(Cek.class.getName()).log(Level.SEVERE, null, ex);
    }

    System.out.println("PublicKey : " + publicKey);
    System.out.println("PrivateKey : " + privateKey);
     

    try {
      // encrypt
      cipherText = rsa.rsaEncryption(plaintText, publicKey);
      System.out.println("CipherText : " + cipherText);
      
      //decrypt
      cipherDecrypted = rsa.rsaDecryption(cipherText, privateKey);
    } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException ex) {
      Logger.getLogger(Cek.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    System.out.println("Plaintext: " + cipherDecrypted);
  }

}
