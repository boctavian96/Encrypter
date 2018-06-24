package octavian.algorithms.goldwasser.crypto.keys;

import octavian.algorithms.goldwasser.crypto.auxiliar.AuxiliarFunctions;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fabio on 11/12/2016.
 */
public class PublicKey {
    private BigInteger N;
    private BigInteger y;
    private int k;
    private BigInteger twoK;
    private BigInteger two = BigInteger.valueOf(2);

    public PublicKey(BigInteger n, BigInteger y, int k) {
        N = n;
        this.y = y;
        this.k = k;
        this.twoK = two.pow(k);
    }

    public BigInteger getN() {
        return N;
    }

    public void setN(BigInteger n) {
        N = n;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public byte[] encryptMessage(byte[] message) throws NoSuchAlgorithmException {
        BigInteger m = new BigInteger(message);
        BigInteger aux1 = this.getY().modPow(m, this.getN());
        BigInteger aux2 = AuxiliarFunctions.randomNumber(k).modPow(twoK, this.getN());
        BigInteger c = aux1.multiply(aux2);
        c = c.mod(this.getN());
        return c.toByteArray();

    }
}
