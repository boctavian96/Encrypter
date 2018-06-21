/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.key;

import java.util.logging.Logger;

/**
 *
 * @author octavian
 */
public class KeyAlgorithm {
        private final String secretKey;
        private final String publicKey;

    public KeyAlgorithm(String secretKey, String publicKey) {
        this.secretKey = secretKey;
        this.publicKey = publicKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getPublicKey() {
        return publicKey;
    }   
}
