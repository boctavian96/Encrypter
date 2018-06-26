/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import octavian.algorithms.rabin.Rabin;
import octavian.algorithms.rabin.key.RabinKey;
import octavian.algorithms.rabin.key.RabinKeyGenerator;

/**
 *
 * @author octavian
 */
public class Rabin_Test {
    public static void main(String[] args){
        RabinKey rk = RabinKeyGenerator.generateKeys(1024);
        String Path = "/home/octavian/NetBeansProjects/Encrypter/teste/Rabin/RabinDummy.txt";
        Rabin rabin = new Rabin(rk, Path);
        
        rabin.encrypt();
        
        rabin.decrypt();
    }
}