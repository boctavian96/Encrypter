/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import java.math.BigInteger;
import octavian.algorithms.elgamal.ElGamal;
import octavian.algorithms.elgamal.key.ElGamalPrivateKey;
import octavian.algorithms.elgamal.key.ElGamalPublicKey;

/**
 *
 * @author octavian
 */
public class ElGamalDummy {
    public static void main(String[] args){
        ElGamalPublicKey pk = new ElGamalPublicKey("2", "10", "11");
        ElGamalPrivateKey sk = new ElGamalPrivateKey("5");
        BigInteger k = new BigInteger("4");
        
        byte[] valueOne = ElGamal.ValueOne(pk, k);
        
        for(int i = 0; i < valueOne.length; i++){
            System.out.println(valueOne[i]);
        }
        
        byte[] input = "Ana are mere".getBytes();
        
        byte[] valueTwo = ElGamal.ValueTwo(input, pk, k);
        
        for(int i = 0; i < valueTwo.length; i++){
            System.out.println(valueTwo[i]);
        }
        
        byte[] text_decodat = ElGamal.decodare(valueOne, valueTwo, sk, pk);
        
        for(int i = 0; i < text_decodat.length; i++){
            System.out.println(text_decodat[i]);
        }
        System.out.println(new String(text_decodat));
        
        
//        int valueU = 5402;
//        int valueD = 2767;
//        
//        byte[] rezultat_decodare = ElGamal.decode(valueU, valueD, k, sk);
//        
//        for(int i = 0; i < rezultat_decodare.length; i++){
//            System.out.println(rezultat_decodare[i]);
//        }
//        
        
    }
}
