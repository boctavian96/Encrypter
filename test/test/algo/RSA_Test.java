/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import java.security.PublicKey;
import octavian.algorithms.RSA;
import sun.security.rsa.RSAPublicKeyImpl;
/**
 *
 * @author octavian
 */
public class RSA_Test {
    
    
    
    public static void main(String[] args){
        PublicKey pk = null;
        byte[] key = {1, 2, 3, 4, 5, 6, 7};
        
        try{
            pk = new RSAPublicKeyImpl(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        RSA rsa = new RSA();
        
        RSA.generateKey();
    
        System.out.println(rsa.encrypt("Ana are mere si pere", pk));
    }

}
