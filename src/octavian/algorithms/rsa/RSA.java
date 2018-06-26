/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rsa;

import java.math.BigInteger;
import octavian.algorithms.Algorithm;
import octavian.algorithms.rsa.key.RsaKey;
import octavian.filemanager.FReader;
import octavian.filemanager.FWriter;
import octavian.utils.Utils;

/**
 *
 * @author octavian
 */
public class RSA implements Algorithm{
    
    private final String filePath;
    private final RsaKey sk;
    private final RsaKey pk;
    
    /**
     * Constructor
     * @param sk 
     */
    public RSA(RsaKey sk, RsaKey pk, String filePath){
        this.sk = sk;
        this.pk = pk;
        this.filePath = filePath;
    }
    
    private byte[] codare(byte[] text){
        byte[] cryptotext;
        BigInteger value1 = new BigInteger(pk.getKeyValue1());
        BigInteger value2 = new BigInteger(pk.getKeyValue2());
        cryptotext = (new BigInteger(text)).modPow(value2, value1).toByteArray();
        
        return cryptotext;
    }
    
    private byte[] decodare(byte[] cryptotext){
        byte[] cleartext;
        BigInteger value1 = new BigInteger(sk.getKeyValue1());
        BigInteger value2 = new BigInteger(sk.getKeyValue2());
        cleartext = (new BigInteger(cryptotext)).modPow(value2, value1).toByteArray();
        
        return cleartext;
    }
    
    
    public void encrypt(byte[] clearText){
        byte[] cryptoText = codare(clearText);
        System.out.println("Text criptat : " + new String(cryptoText));
        
        FWriter fw = new FWriter(filePath);
        fw.write(cryptoText);
        
        //Scrie textul criptat in fisier
    }
    
    @Override
    public void encrypt(){
        FReader fr = new FReader(filePath);
        byte[] input = fr.readBytes();
        byte[] cryptoText = codare(input);     
        FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(filePath) + "_enc.txt");
        fw.writeNumbers(cryptoText);

    }
    
    
    public void decrypt(byte[] cryptoText){
        byte[] textClar = decodare(cryptoText);
        System.out.println("Text decriptat : " + new String(textClar));
        
        FWriter fw = new FWriter(filePath);
        fw.write(new String(textClar));
        
        //Scrie textul criptat in fisier
    }
    
    @Override 
    public void decrypt(){
        byte[] cryptoText;
        byte[] textClar;
        FReader fr = new FReader(Utils.getFilenameWithoutExtension(filePath) + "_enc.txt");
        cryptoText = fr.readNumbers();
        textClar = decodare(cryptoText);
        FWriter fw = new FWriter(Utils.getFilenameWithoutExtension(filePath) + "_dec.txt");
        fw.write(new String(textClar));        
        //Scrie textul criptat in fisier
    }
    
}
