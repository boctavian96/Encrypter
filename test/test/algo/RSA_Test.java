/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import octavian.algorithms.rsa.RSA;
import octavian.algorithms.rsa.key.KeyGenerator;
import octavian.algorithms.rsa.key.RsaKey;

/**
 *
 * @author octavian
 */
public class RSA_Test {
    public static void main(String[] args){
        KeyGenerator keygen = new KeyGenerator();
        keygen.execute();
        RsaKey sk = keygen.getSk();
        RsaKey pk = keygen.getPk();
        RSA rsaalgo = new RSA(sk, pk, "/home/octavian/NetBeansProjects/Encrypter/rsa_dummy.txt");
        rsaalgo.encrypt();
        rsaalgo.decrypt();
    }
}
