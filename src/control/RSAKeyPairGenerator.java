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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;

public class RSAKeyPairGenerator {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

//    public void writeToFile(String path, byte[] key) throws IOException {
//        File f = new File(path);
//        f.getParentFile().mkdirs();
//
//      try (FileOutputStream fos = new FileOutputStream(f)) {
//        fos.write(key);
//        fos.flush();
//      }
//    }
  public void writeToFile(String path, String key) throws IOException {
    File f = new File(path);
    f.getParentFile().mkdirs();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      writer.write(key);
    }
  }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
