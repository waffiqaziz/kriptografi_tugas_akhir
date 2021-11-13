/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class User{
  private String user_id, pass, full_name, email, telp, dateOfBirth, publicKey;

// SETTER
    public void setUser(String email, String pass, String full_name, String telp){
      this.pass = pass;
      this.full_name = full_name;
      this.email = email;
      this.telp = telp;
    }
    public void setUser_id(String user_id){
      this.user_id = user_id;
    }
    public void setDate(String date){
      this.dateOfBirth = date;
    }
    public void setPass(String pass){
      this.pass = pass;
    }
    public boolean setPublicKey(String publicKey){
      this.publicKey = publicKey;
      return !publicKey.isBlank(); // return true jika public key tidak kosong
    }
    
// GETTER  
    public String getUserID(){
      return user_id;
    }
    public String getNama(){
      return full_name;
    }
    public String getEmail(){
      return email;
    }
    public String getTelp(){
      return telp;
    }
    public String getDateOfBirth(){
      return dateOfBirth;
    }
    public String getPass(){
      return pass;
    }
    public String getPublicKey(){
      return publicKey;
    }
}
