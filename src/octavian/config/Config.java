/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.config;

/**
 *
 * @author octavian.bodnariu
 */
public interface Config {
    
    //<editor-fold desc="System paths">
    String WINDOWS_PATH = "C:\\Encry\\";
    String LINUX_PATH = "/home/octavian/Program Files/Encrypter";
    //</editor-fold>
    
    //<editor-fold desc="Algorithms">
    String RSA = "RSA";
    String RABIN = "Rabin";
    String ELGAMAL = "ElGamal";
    String GOLDWASSER = "Goldwasser";
    String DSA = "DSA";
    String DIFFIE_HELLMAN = "Diffie-Hellman";
    //</editor-fold>
}
