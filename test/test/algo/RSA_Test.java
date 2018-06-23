/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import java.security.PublicKey;
import octavian.algorithms.rsa.OLD_RSA;
import octavian.utils.Utils;
import sun.security.rsa.RSAPublicKeyImpl;
/**
 *
 * @author octavian
 */
public class RSA_Test {
    
    public static void main(String[] args){
        OLD_RSA rsa = new OLD_RSA();
        String message = "Ana are mere si pere";
        
        byte[] e = rsa.encrypt(message.getBytes());
        byte[] d = rsa.decrypt(e);
        
        System.out.println("Test string : " + message);
        System.out.println("Byte string : " + message.getBytes());
        System.out.println("Encrypted text : " + Utils.bytesToString(e));
        System.out.println("Decrypted text : " + new String(d));
        
    }

}
