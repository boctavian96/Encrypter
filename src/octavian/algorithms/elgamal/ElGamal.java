/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.elgamal;

import java.math.BigInteger;
import java.util.Random;
import octavian.algorithms.Algorithm;
import octavian.algorithms.elgamal.key.ElGamalKeySet;
import octavian.algorithms.elgamal.key.ElGamalPrivateKey;
import octavian.algorithms.elgamal.key.ElGamalPublicKey;
import octavian.filemanager.FReader;
import octavian.filemanager.FWriter;
import octavian.utils.Utils;

/**
 *
 * @author octavian
 */
public class ElGamal implements Algorithm{
    
    private static final int BIT_LENGTH = 1024;
    private final ElGamalKeySet keys;
    private final String path;
    private BigInteger k;
    
    public ElGamal(ElGamalKeySet egks, String path){
        this.keys = egks;
        this.path = path;
    }
    
    private void calculateK(){
//        BigInteger randomNumber = new BigInteger(BIT_LENGTH, new Random());
//        BigInteger p = new BigInteger(keys.getPk().getP());
//        
//        while(randomNumber.compareTo(p.subtract(new BigInteger("2"))) == 1){
//            randomNumber = new BigInteger(BIT_LENGTH, new Random());
//        }
//        
        this.k = new BigInteger("4");
    }
    
    private byte[] ValueOne(){
        BigInteger p = new BigInteger(keys.getPk().getP());
        BigInteger g = new BigInteger(keys.getPk().getL());
        BigInteger result1 = g.modPow(k, p);
        
        return result1.toByteArray();
    }
    
    public static byte[] ValueOne(ElGamalPublicKey pk, BigInteger k){
        BigInteger p = new BigInteger(pk.getP());
        BigInteger g = new BigInteger(pk.getL());
        BigInteger result1 = g.modPow(k, p);
        
        return result1.toByteArray();
    }
    
    public static byte[] ValueTwo(byte[] clearText, ElGamalPublicKey pk, BigInteger k){
        BigInteger p = new BigInteger(pk.getP());
        BigInteger calc = new BigInteger(pk.getCalc());
        BigInteger message = new BigInteger(clearText);
        
        BigInteger result2 = message.multiply(calc.modPow(k, p));
        
        return result2.toByteArray();
    }
    
    private byte[] ValueTwo(byte[] clearText){
        BigInteger p = new BigInteger(keys.getPk().getP());
        BigInteger calc = new BigInteger(keys.getPk().getCalc());
        BigInteger message = new BigInteger(clearText);
        BigInteger result2 = message.multiply(calc.modPow(k, p));
        
        return result2.toByteArray();
    }
    
    private byte[] decodare(byte[] cryptoText1, byte[] cryptoText2){
        BigInteger sk = new BigInteger(keys.getSk().getA());
        BigInteger p = new BigInteger(keys.getPk().getP());
        
        BigInteger valueOne = new BigInteger(cryptoText1);
        BigInteger valueTwo = new BigInteger(cryptoText2);
        
        
        //BigInteger result = valueTwo.multiply(valueOne.modInverse(p)).mod(p);
        
        BigInteger result = valueTwo.multiply(valueOne.modPow(sk.negate(), p));
        //BigInteger result = valueTwo.multiply(valueOne.modPow(sk.negate(), BigInteger.)).mod(p);
        
                
        return result.toByteArray();
        
    }
    
        public static byte[] decodare(byte[] cryptoText1, byte[] cryptoText2, ElGamalPrivateKey sk, ElGamalPublicKey pk){
        BigInteger secret = new BigInteger(sk.getA());
        BigInteger p = new BigInteger(pk.getP());
         
        BigInteger valueOne = new BigInteger(cryptoText1);
        BigInteger valueTwo = new BigInteger(cryptoText2);
        
        
        //BigInteger result = valueTwo.multiply(valueOne.modInverse(p)).mod(p);
        BigInteger result = valueTwo.multiply(valueOne.modPow(secret.negate(), p));
        
                
        return result.toByteArray();
        
    }

    @Override
    public void encrypt() {
        calculateK();
        
        FReader fr = new FReader(path);
        String clearText = fr.read();
        
        FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(path) + "_enc1.txt");
        fw.writeNumbers(ValueOne());
        
        FWriter fw2 = new FWriter(Utils.getFilenameWithoutExtension(path) + "_enc2.txt");
        fw2.writeNumbers(ValueTwo(clearText.getBytes()));
    }

    @Override
    public void decrypt() {
        FReader fr1 = new FReader(Utils.getFilenameWithoutExtension(path) + "_enc1.txt");
        FReader fr2 = new FReader(Utils.getFilenameWithoutExtension(path) + "_enc2.txt");
        
        byte[] valueOne = fr1.readNumbers();
        byte[] valueTwo = fr2.readNumbers();
        
        byte[] textClar = decodare(valueOne, valueTwo);
        
        System.out.println("Text decriptat : " + new String(textClar));
        
        FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(path) + "_dec.txt");
        fw.write(new String(textClar));
    }
    
}
