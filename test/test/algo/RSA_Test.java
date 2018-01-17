/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.algo;

import octavian.algorithms.RSA;
/**
 *
 * @author octavian
 */
public class RSA_Test {
    
    
    public static void main(String[] args){
        RSA rsa = new RSA();
        rsa.generateKey();
    
        System.out.println(rsa.encrypt("Ana are mere si pere", rsa.km.));
    }

}
