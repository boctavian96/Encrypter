/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.algorithms.rabin.key;

/**
 *
 * @author octavian
 */
public class RabinKey {
    //public Key
    private final String n;
    
    //private Key
    private final String p;
    private final String q;

    public RabinKey(String n, String p, String q) {
        this.n = n;
        this.p = p;
        this.q = q;
    }

    public String getN() {
        return n;
    }

    public String getP() {
        return p;
    }

    public String getQ() {
        return q;
    }

}
