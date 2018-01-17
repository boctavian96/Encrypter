/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms;

//<editor-fold desc="Imports">
import octavian.filemanager.FileReader;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import octavian.config.Config;
import octavian.filemanager.KeyMaster;
import java.io.File;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import octavian.statistics.LogGenerator;

//</editor-fold>

/**
 * Clasa responsabila pentru Algoritmul RSA
 * @author octavian
 */
public class RSA extends AlgorithmBase {
    
    public static KeyMaster km;
    private BigInteger bi1;
    private BigInteger bi2;
    private BigInteger bi3;
    private BigInteger pi;
    private BigInteger a;
    private BigInteger b;
    private Random random;
    
    private static final int BIT_LENGTH = 1024;
    public static final String PRIVATE_KEY_FILE = "/home/octavian/Program Files/Encrypter/keys/private.txt";
    public static final String PUBLIC_KEY_FILE = "/home/octavian/Program Files/Encrypter/keys/public.txt";
    
    
    public RSA(){
        random = new Random();
        bi1 = BigInteger.probablePrime(BIT_LENGTH, random);
        bi2 = BigInteger.probablePrime(BIT_LENGTH, random);
        bi3 = bi1.multiply(bi2);
        
        pi = bi1.subtract(BigInteger.ONE).multiply(bi2.subtract(BigInteger.ONE));
        a = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        
        while(pi.gcd(a).compareTo(BigInteger.ONE) > 0 && a.compareTo(pi) < 0){
            a.add(BigInteger.ONE);
        }
        b = a.modInverse(pi);
    }
    
    public RSA(BigInteger i, BigInteger j, BigInteger k){
            this.bi1 = i;
            this.bi2 = j;
            this.bi3 = k;
    }
    
    public static void generateKey()
    {
        try
        {
            /**
             * Key generator for RSA
             */
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Config.RSA);
            keyGen.initialize(1024);
            
            //Create files and store them in prvK and pubK
            final KeyPair key = keyGen.genKeyPair();
            km = new KeyMaster(PRIVATE_KEY_FILE, PUBLIC_KEY_FILE);
            km.storeKeys(key.getPublic(), key.getPrivate());
 
        }
        catch(NoSuchAlgorithmException e)
        {
            LogGenerator l = LogGenerator.getInstance();
            l.writeLog('e', e.getMessage());
        }
    }

    public byte[] encrypt(byte[] message){
        return (new BigInteger(message)).modPow(a, bi3).toByteArray();
    }
    
    public byte[] decrypt(byte[] message){
        return (new BigInteger(message)).modPow(b, bi3).toByteArray();
    }
    
    /**
     * Encrypt a text using RSA
     * @param text Text to be encrypted
     * @param key Public key
     * @return Encrypted text
     */
    @Override
    public byte[] encrypt(String text, PublicKey key)
    {
        byte[] enc_text = null;
        try
        {
            Cipher c = Cipher.getInstance(Config.RSA);
            c.init(Cipher.ENCRYPT_MODE, key);
            enc_text = c.doFinal(text.getBytes());
        }
        catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)
        {
            LogGenerator l = LogGenerator.getInstance();
            l.writeLog('e', e.getMessage());
        }
        
        return enc_text;
    }
    
    /**
     * Function to decrypt RSA encrypted text
     * @param text Text to be decrypted
     * @param key Private key
     * @return Decrypted text
     */
    @Override
    public String decrypt(byte[] text, PrivateKey key)
    {
        String result;
        byte[] dec_text = null;
        
        try
        {
            Cipher c = Cipher.getInstance(Config.RSA);
            
            //Decrypt
            c.init(Cipher.DECRYPT_MODE, key);
            dec_text = c.doFinal(text);
        }
        catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)
        {
            LogGenerator l = LogGenerator.getInstance();
            l.writeLog('e', e.getMessage());
        }
        
        result = new String(dec_text);
        return result;
    }

}
