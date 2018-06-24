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
public class ElGamalPublicKey {
    private final String l;
    private final String calc;
    private final String p;

    public ElGamalPublicKey(String l, String calc, String p) {
        this.l = l;
        this.calc = calc;
        this.p = p;
    }

    public String getL() {
        return l;
    }

    public String getCalc() {
        return calc;
    }

    public String getP() {
        return p;
    }

    @Override
    public String toString() {
        return "ElGamalPublicKey{" + "l=" + l + ", calc=" + calc + ", p=" + p + '}';
    }
    
    
}
