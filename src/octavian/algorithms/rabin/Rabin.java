/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rabin;

import octavian.filemanager.FReader;
import octavian.filemanager.FWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;
import octavian.utils.Utils;
import octavian.algorithms.Algorithm;

/**
 * 
 * @author octavian
 */
public class Rabin implements Algorithm{
    private static final Random RANDOM = new SecureRandom();
    private static final BigInteger DOI = BigInteger.valueOf(2);
    private static final BigInteger TREI = BigInteger.valueOf(3);
    private static final BigInteger PATRU = BigInteger.valueOf(4);
    
    private BigInteger N;
    private BigInteger p;
    private BigInteger q;
    private BigInteger result;
    
    private String filePath;
    
    public Rabin(){
        
    }
    
    public Rabin(String filePath){
        this.filePath = filePath;
    }
    
    public Rabin(String filePath, BigInteger[] keys){
        this.filePath = filePath;
        this.N = keys[0];
        this.p = keys[1];
        this.q = keys[2];
    }

    /**
     * Generate a blum public and private key (more efficient decryption) with a specified number of bits
     * @param bitLength Number of bits in public key
     * @return An array of BigIntegers {N,p,q}. N is the public key and p and q are the private keys
     */
    public static BigInteger[] genKey(int bitLength) {
        BigInteger p = blumPrime(bitLength/2);
        BigInteger q = blumPrime(bitLength/2);
        BigInteger N = p.multiply(q);
        return new BigInteger[]{N,p,q};
    }

    /**
     * Encrypt a value with the public key
     * @param m the value to codare
     * @param N the public key, N
     * @return c, the encrypted value
     */
    public static BigInteger codare(BigInteger m, BigInteger N) {
        return m.modPow(DOI, N);
    }

    /**
     * Decrypt a value with the private key (assumes blum key for fast decryption)
     * @param c encrypted number
     * @param p private key, p
     * @param q private key, q
     * @return array of the 4 decryption possibilities
     */
    public static BigInteger[] decodare(BigInteger c, BigInteger p , BigInteger q) {
        BigInteger N = p.multiply(q);
        BigInteger m_p1 = c.modPow(p.add(BigInteger.ONE).divide(PATRU), p);
        BigInteger m_p2 = p.subtract(m_p1);
        BigInteger m_q1 = c.modPow(q.add(BigInteger.ONE).divide(PATRU), q);
        BigInteger m_q2 = q.subtract(m_q1);

        BigInteger[] ext = ext_gcd(p,q);
        BigInteger y_p = ext[1];
        BigInteger y_q = ext[2];

        //y_p*p*m_q + y_q*q*m_p (mod n)
        BigInteger d1 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p1)).mod(N);
        BigInteger d2 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p1)).mod(N);
        BigInteger d3 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p2)).mod(N);
        BigInteger d4 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p2)).mod(N);

        return new BigInteger[]{d1,d2,d3,d4};
    }


    public static BigInteger[] ext_gcd(BigInteger a, BigInteger b) {
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
            p=BigInteger.probablePrime(bitLength,RANDOM);
        }
        while(!p.mod(PATRU).equals(TREI));
        return p;
    }

 
    public void encrypt() {
        FReader fr = new FReader(filePath);
        String mesaj = fr.read();
        FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(filePath) + "_crpt.txt");
        BigInteger m = new BigInteger(mesaj.getBytes(Charset.forName("ascii")));
        
        result = codare(m, N);
        fw.write(result.toString());
    }

    public void decrypt() {
            String dec = null;
            FReader fr = new FReader(filePath);
               FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(filePath) + "_dec.txt");
            result = new BigInteger(fr.read());
            BigInteger[] m2 = Rabin.decodare(result, p, q);
            
            int min = Integer.MAX_VALUE;
            for(BigInteger b:m2) {
                dec = new String(b.toByteArray(), Charset.forName("ascii"));
                if(dec.length() < min){
                    min = dec.length();
                }
            }
            
            for(BigInteger b:m2) {
                dec = new String(b.toByteArray(), Charset.forName("ascii"));
                if(dec.length() == min){
                    System.out.println("Text decodat : " + dec);
                    fw.write(dec);
                }
            }
            
         
            
    }
}