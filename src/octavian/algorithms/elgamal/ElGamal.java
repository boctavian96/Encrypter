/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.elgamal;

import java.math.BigInteger;
import java.util.Random;
import octavian.filemanager.KeyMaster;

/**
 *
 * @author octavian
 */
public class ElGamal {
    public static KeyMaster km;
//    private final BigInteger bi1;
//    private final BigInteger bi2;
//    private final BigInteger bi3;
    private final Random random;
    
    public ElGamal(){
        random = new Random();
       // bi1 = BigInteger.probablePrime(BIT_LENGTH, random);
        //bi2 = BigInteger.probablePrime(BIT_LENGTH, random);
      //  bi3 = bi1.multiply(bi2);
    }
    
}
