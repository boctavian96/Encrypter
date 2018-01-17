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
public abstract class AlgorithmBase {
    
    public abstract byte[] encrypt(String text, PublicKey key);
    public abstract String decrypt(byte[] text, PrivateKey key);
}
