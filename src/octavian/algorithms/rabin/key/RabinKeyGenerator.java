/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rabin.key;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import octavian.algorithms.rabin.config.RabinConfig;

/**
 *
 * @author octavian
 */
public class RabinKeyGenerator {
    private static final Random RANDOM = new SecureRandom();
    
    private BigInteger N;
    private BigInteger p;
    private BigInteger q;
    private BigInteger result;
    
        public static BigInteger[] gcd(BigInteger a, BigInteger b) {
        BigInteger s = BigInteger.ZERO;
        BigInteger old_s = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;
        BigInteger old_t = BigInteger.ZERO;
        BigInteger r = b;
        BigInteger old_r = a;
        while(!r.equals(BigInteger.ZERO)) {
            BigInteger q = old_r.divide(r);
            BigInteger tr = r;
            r = old_r.subtract(q.multiply(r));
            old_r=tr;

            BigInteger ts = s;
            s = old_s.subtract(q.multiply(s));
            old_s=ts;

            BigInteger tt = t;
            t = old_t.subtract(q.multiply(t));
            old_t=tt;
        }
        //gcd, x,y
        //x,y such that ax+by=gcd(a,b)
        return new BigInteger[]{old_r, old_s, old_t};
    }

    /**
     * Generate a random blum prime ( a prime such that p≡3 (mod 4) )
     * @param bitLength number of bits in the prime
     * @return a random blum prime
     */
    public static BigInteger blumPrime(int bitLength) {
        BigInteger p;
        do {
            p=BigInteger.probablePrime(bitLength, RANDOM);
        }
        while(!p.mod(RabinConfig.PATRU).equals(RabinConfig.TREI));
        return p;
    }
    /**
     * Generate a blum public and private key (more efficient decryption) with a specified number of bits
     * @param bitLength Number of bits in public key
     * @return An array of BigIntegers {N,p,q}. N is the public key and p and q are the private keys
     */
    public static RabinKey generateKeys(int bitLength) {
        BigInteger p = blumPrime(bitLength/2);
        BigInteger q = blumPrime(bitLength/2);
        BigInteger N = p.multiply(q);
        
        return new RabinKey(N.toString(), p.toString(), q.toString());
    }
    
}
