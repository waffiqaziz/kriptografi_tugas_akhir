/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class RSAUtil {


  public void keyUtilGenerator(User n, String email) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IOException, NoSuchAlgorithmException {
    ControlUser cu = new ControlUser();
    
    RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
    
    //generate publickey
      System.out.println("cek public key");
      String pub = Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded());
      cu.setKey(n, pub);
    
    //generate privatekey
      String priv = Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded());
      keyPairGenerator.savePrivateKey(priv);
      System.out.println("Public : "+Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
      System.out.println("Privat : "+Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));

  //PENGECEKAN RSA WORK/NO
    //plaintext
      System.out.println("CHECKING RSA WORK");
      String secretMessage = "Baeldung secret message";
      System.out.println("Plainttext : " + secretMessage);
    
    //private dan public key
      PrivateKey privateKey = keyPairGenerator.getPrivateKey();
      PublicKey publicKey = keyPairGenerator.getPublicKey();
      
    try {
      //enkripsi dengan publickey metode RSA
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
      
      //mengenkripsi pesan(hanya bisa array byte),
        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
      
      // di deskripsi Base64 Alphabet, shg pesan akan lebih mudah dibaca
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        System.out.println("Ciphertext : " + encodedMessage);
        
      //deskripsi dengan privatekey (RSA)
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        
      //proses deskripsi
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
      
      //deskripsi diubah ke format UTF-8
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        System.out.println(decryptedMessage);
              
    } catch (NoSuchAlgorithmException ex) {
      Logger.getLogger(RSAUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public String rsaEncryption(String data, String base64PublicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

    PublicKey publicKey = null;
    try {
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      publicKey = keyFactory.generatePublic(keySpec);

    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      Logger.getLogger(RSAUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    
    byte[] secretMessageBytes = data.getBytes(StandardCharsets.UTF_8);
    byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
    String encodedMessage;
    encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
    
    return encodedMessage;
  }
  
  public String rsaDecryption(String data, String base64PrivateKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

    PrivateKey privateKey = null;
    try {
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      privateKey = keyFactory.generatePrivate(keySpec);

    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      Logger.getLogger(RSAUtil.class.getName()).log(Level.SEVERE, null, ex);
    }

    Cipher decryptCipher = Cipher.getInstance("RSA");
    decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] reCipherBytes = Base64.getDecoder().decode(data);
    
    byte[] decryptedMessageBytes = decryptCipher.doFinal(reCipherBytes);
    String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    System.out.println(decryptedMessage);
    
    return decryptedMessage;

  }
  
}


