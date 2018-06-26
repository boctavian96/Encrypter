/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rsa.key;

import java.math.BigInteger;
import java.util.Random;

/**
 * Key Generator for RSA algorithm
 * @author octavian
 */
public class KeyGenerator {
    
    private static final int BIT_LENGTH = 4096;
    private final BigInteger n; 
    private final BigInteger q;
    
    private RsaKey pk;
    private RsaKey sk;
    
    public KeyGenerator(){
        Random random = new Random();
        this.n = BigInteger.probablePrime(BIT_LENGTH, random);
        this.q = BigInteger.probablePrime(BIT_LENGTH, random);
        
    }
    
    private BigInteger rezultatEuler(){
        BigInteger result;
        result = n.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        return result;
    }
    
    private BigInteger generateEpsilon(BigInteger euler){
        // Cel mai mare divizor comun(e, pie) = 1
        Random random = new Random();
        BigInteger epsilon = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        while(euler.gcd(epsilon).compareTo(BigInteger.ONE) > 0 && epsilon.compareTo(euler) < 0){
            epsilon.add(BigInteger.ONE);
        }
        
        return epsilon;
    }
    
    private BigInteger generateAlt(BigInteger eps, BigInteger pie){        
        return eps.modInverse(pie);
    }
    
    private RsaKey generatePublicKey(BigInteger produs, BigInteger epsilon){
        return new RsaKey(produs.toString(), epsilon.toString());
    }
    
    private RsaKey generatePrivateKey(BigInteger produs, BigInteger alt){
        return new RsaKey(produs.toString(), alt.toString());
    }
    
    
    public void execute(){
        BigInteger produs = this.n.multiply(this.q);
        BigInteger euler = rezultatEuler();
        BigInteger epsilon = generateEpsilon(euler);
        BigInteger alt = generateAlt(epsilon, euler);
        
        pk = generatePublicKey(produs, epsilon);
        sk = generatePrivateKey(produs, alt);   
        
        System.out.println("Cheia publica :" + pk.toString());
        System.out.println("Cheia privata :" + sk.toString());
        
    }

    public RsaKey getPk() {
        return pk;
    }

    public RsaKey getSk() {
        return sk;
    }
    
}
