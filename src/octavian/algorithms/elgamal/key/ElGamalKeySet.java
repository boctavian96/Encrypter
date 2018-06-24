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
public class ElGamalKeySet {
    private final ElGamalPublicKey pk;
    private final ElGamalPrivateKey sk;

    public ElGamalKeySet(ElGamalPublicKey pk, ElGamalPrivateKey sk) {
        this.pk = pk;
        this.sk = sk;
    }

    public ElGamalPublicKey getPk() {
        return pk;
    }

    public ElGamalPrivateKey getSk() {
        return sk;
    }

    @Override
    public String toString() {
        return "ElGamalKeySet{" + "pk=" + pk + ", sk=" + sk + '}';
    }
}
