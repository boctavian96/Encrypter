package octavian.algorithms.goldwasser.crypto.keys;

import java.math.BigInteger;

/**
 * Created by fabio on 11/12/2016.
 */
public class PrivateKey {
    private BigInteger p;
    private BigInteger q;

    private BigInteger two = BigInteger.valueOf(2);
    private BigInteger one = BigInteger.ONE;

    public PrivateKey(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public byte[] decryptData(byte[] ciphertext, BigInteger y, int k){
        BigInteger twoK;
        twoK = two.pow(k);

        BigInteger c = new BigInteger(ciphertext);
        BigInteger D = calcD(y, twoK);
        BigInteger m = BigInteger.ZERO;
        BigInteger B = BigInteger.ONE;
        BigInteger C = calcC(c, twoK);

        BigInteger z;

        for (int i = 1; i <= k - 1; i++) {
            z = calcZ(k-i, C);
            if(!z.equals(BigInteger.ONE)){
                m = m.add(B);
                C = C.multiply(D);
                C = C.mod(p);
            }

            B = B.multiply(two);
            D = D.pow(2);
            D = D.mod(p);
        }


        /*
        As indicated in the paper but, it introduces noise in the plain text if the message is smaller than the security
        parameter k
        if(!c.equals(BigInteger.ONE)){
        */
        if(m.bitLength() == 128){
            m = m.add(B);
        }

        return m.toByteArray();

    }

    private BigInteger calcD(BigInteger y, BigInteger twoK){
        BigInteger aux = p.subtract(one);
        aux = aux.divide(twoK);
        aux = aux.multiply(BigInteger.valueOf(-1));
        BigInteger D = y.modPow(aux, p);
        return D;
    }

    private BigInteger calcC(BigInteger c, BigInteger twoK){
        BigInteger aux = p.subtract(one);
        aux = aux.divide(twoK);
        BigInteger C = c.modPow(aux, p);
        return C;
    }

    private BigInteger calcZ(int index, BigInteger C){
        BigInteger aux = two.pow(index);
        BigInteger z = C.modPow(aux, p);
        return z;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }
}
