/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms;

import java.security.PublicKey;
import java.security.PrivateKey;

/**
 *
 * @author octavian.bodnariu
 */
public interface Algorithm {
    
    public abstract void encrypt(byte[] clearText);
    public abstract void decrypt(byte[] cryptoText);
   
}
