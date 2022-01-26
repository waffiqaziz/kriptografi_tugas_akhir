/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import javax.swing.JFileChooser;

public class RSAKeyPairGenerator {

  private final PrivateKey privateKey;
  private final PublicKey publicKey;

  public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);
    KeyPair pair = keyGen.generateKeyPair();
    this.privateKey = pair.getPrivate();
    this.publicKey = pair.getPublic();
  }

  public void savePrivateKey(String privateKey) {
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("/home/me/Documents"));
    int retrival = chooser.showSaveDialog(null);
    if (retrival == JFileChooser.APPROVE_OPTION) {
      try {
        try (
//          FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
          FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
          fw.write(privateKey);
          fw.flush();
        }
      } catch (IOException ex) {
         java.util.logging.Logger.getLogger(RSAKeyPairGenerator.class.getName()).
                 log(java.util.logging.Level.SEVERE, null, ex);
      }
    }
  }

  public PrivateKey getPrivateKey() {
    return privateKey;
  }

  public PublicKey getPublicKey() {
    return publicKey;
  }
}
