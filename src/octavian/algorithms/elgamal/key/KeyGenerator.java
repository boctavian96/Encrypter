/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.elgamal.key;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author octavian
 */
public class KeyGenerator {
    

    
    public static ElGamalKeySet generateKeys(){
        Random random;
        int BIT_LENGTH = 1024;
        BigInteger p;
        BigInteger g;
        BigInteger a; // Cheia privata
        BigInteger calc;
        
        ElGamalKeySet elgamalKeys;
        ElGamalPrivateKey sk;
        ElGamalPublicKey pk;
        
        random = new Random();
        
        
        p = BigInteger.probablePrime(BIT_LENGTH, random);
        g = new BigInteger(BIT_LENGTH, random);
        while(g.compareTo(p.add(BigInteger.valueOf(1l))) == 1){
            g = new BigInteger(BIT_LENGTH, random);
        }
        
        a = new BigInteger(BIT_LENGTH, random);
        while(a.compareTo(p.subtract(BigInteger.valueOf(2l))) == 1){
            a = new BigInteger(BIT_LENGTH, random);
        }
        
        p = new BigInteger("11");
        g = new BigInteger("2");
        a = new BigInteger("5");
        
        calc = g.modPow(a, p);
        
        sk = new ElGamalPrivateKey(a.toString());
        pk = new ElGamalPublicKey(g.toString(), calc.toString(), p.toString());
        
        elgamalKeys = new ElGamalKeySet(pk, sk);
        
        return elgamalKeys;
    }
    
    public static void main(String[] args){
        KeyGenerator kg = new KeyGenerator();
        ElGamalKeySet ks = kg.generateKeys();
        
        System.out.println(ks);
    }
    
}
