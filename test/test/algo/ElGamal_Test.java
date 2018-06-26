/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import octavian.algorithms.elgamal.ElGamal;
import octavian.algorithms.elgamal.key.ElGamalKeySet;
import octavian.algorithms.elgamal.key.KeyGenerator;

/**
 *
 * @author octavian
 */
public class ElGamal_Test {
    public static void main(String[] args){
        
        String path = "/home/octavian/NetBeansProjects/Encrypter/teste/ElGamal/ElGamalDummy.txt";
        ElGamalKeySet egks = KeyGenerator.generateKeys();
        ElGamal elgamal = new ElGamal(egks, path);
        
        elgamal.encrypt();
        elgamal.decrypt();
    }
}
