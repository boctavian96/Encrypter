/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rsa.key;

/**
 *
 * @author octavian
 */
public class RsaKey {
    private final String keyValue1;
    private final String keyValue2;

    public RsaKey(String keyValue1, String keyValue2) {
        this.keyValue1 = keyValue1;
        this.keyValue2 = keyValue2;
    }

    public String getKeyValue1() {
        return keyValue1;
    }

    public String getKeyValue2() {
        return keyValue2;
    }

    @Override
    public String toString() {
        return "RsaKey{" + "keyValue1=" + keyValue1 + ", keyValue2=" + keyValue2 + '}';
    }
    
    
}
