/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.elgamal.key;

/**
 *
 * @author octavian
 */
public class ElGamalPrivateKey {
   private final String a;

    public ElGamalPrivateKey(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    @Override
    public String toString() {
        return "ElGamalPrivateKey{" + "a=" + a + '}';
    }
    
    
}
