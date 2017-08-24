/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

//<editor-fold desc="Imports">
import filemanager.FileReader;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import config.Config;
import filemanager.KeyMaster;

//</editor-fold>

/**
 * Clasa responsabila pentru Algoritmul RSA
 * @author octavian.bodnariu
 */
public class RSA {
    
    private static KeyMaster km;
    
    /**
     * Path to private key file
     */
    public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
    
    /**
     * Path to public key file
     */
    public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";
    
    public static void generateKey()
    {
        try
        {
            /**
             * Key generator for RSA
             */
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Config.RSA);
            keyGen.initialize(1024);
            
            final KeyPair key = keyGen.genKeyPair();
            km = new KeyMaster(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE);
            km.storeKeys(key.getPublic(), key.getPrivate());
            
            //Create files and store them in prvK and pubK
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
