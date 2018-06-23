/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rabin;

import java.math.BigInteger;
import java.nio.charset.Charset;

import octavian.algorithms.Algorithm;
import octavian.algorithms.rabin.config.RabinConfig;
import octavian.algorithms.rabin.key.RabinKey;
import octavian.algorithms.rabin.key.RabinKeyGenerator;
import octavian.filemanager.FReader;
import octavian.filemanager.FWriter;
import octavian.utils.Utils;

/**
 *
 * @author octavian
 */
public class Rabin implements Algorithm{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    private final String filePath;
    private final RabinKey rk;
    
    public Rabin(RabinKey rk, String filePath){
        this.rk = rk;
        this.filePath = filePath;
    }
    
    /**
     * Criptare
     * @param cryptoText
     * @return 
     */
    public BigInteger codare(BigInteger cryptoText) {
        return cryptoText.modPow(RabinConfig.DOI, new BigInteger(rk.getN()));
    }

    /**
     * Decrypt a value with the private key (assumes blum key for fast decryption)
     * @param cryptoText
     * @return array of the 4 decryption possibilities
     */
    public BigInteger[] decodare(byte[] cryptoText) {
        BigInteger p = new BigInteger(rk.getP());
        BigInteger q = new BigInteger(rk.getQ());
        BigInteger c = new BigInteger(cryptoText);
        
        
        BigInteger N = p.multiply(q);
        BigInteger m_p1 = c.modPow(p.add(BigInteger.ONE).divide(RabinConfig.PATRU), p);
        BigInteger m_p2 = p.subtract(m_p1);
        BigInteger m_q1 = c.modPow(q.add(BigInteger.ONE).divide(RabinConfig.PATRU), q);
        BigInteger m_q2 = q.subtract(m_q1);

        BigInteger[] ext = RabinKeyGenerator.gcd(p, q);
        BigInteger y_p = ext[1];
        BigInteger y_q = ext[2];

        //y_p*p*m_q + y_q*q*m_p (mod n)
        BigInteger d1 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p1)).mod(N);
        BigInteger d2 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p1)).mod(N);
        BigInteger d3 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p2)).mod(N);
        BigInteger d4 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p2)).mod(N);

        return new BigInteger[]{d1,d2,d3,d4};
                
}
    @Override
    public void encrypt() {
        BigInteger result;
        FReader fr = new FReader(filePath);
        String mesaj = fr.read();
        FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(filePath) + "_enc.txt");
        BigInteger m = new BigInteger(mesaj.getBytes(Charset.forName("ascii")));
        
        result = codare(m);
        fw.write(result.toString());
    }

    @Override
    public void decrypt() {
            String dec = null;
            FReader fr = new FReader(Utils.getFilenameWithoutExtension(filePath) + "_enc.txt");
            FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(filePath) + "_dec.txt");
            BigInteger result = new BigInteger(fr.read());
            BigInteger[] m2 = RabinOld.decodare(result, new BigInteger(rk.getP()), new BigInteger(rk.getQ()));
            
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
