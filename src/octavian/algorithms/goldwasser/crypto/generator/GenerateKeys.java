package octavian.algorithms.goldwasser.crypto.generator;

import octavian.algorithms.goldwasser.crypto.exceptions.KeySizeException;
import octavian.algorithms.goldwasser.crypto.keys.Keys;
import octavian.algorithms.goldwasser.crypto.keys.Parameters;
import octavian.algorithms.goldwasser.crypto.keys.PrivateKey;
import octavian.algorithms.goldwasser.crypto.keys.PublicKey;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by fabio on 11/12/2016.
 */
public class GenerateKeys {
    private int k;
    private SecureRandom rand;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);
    private BigInteger twoK;
    private Keys keys;
    private final int n;
    private final int halfn;
    private final BigInteger lambda;
    private final BigInteger mult;
    private final int MAX_TRIES = 500;

    public GenerateKeys(int k, int n) throws KeySizeException, NoSuchAlgorithmException {
        this.k = k;
        this.rand = SecureRandom.getInstance("SHA1PRNG");
        this.twoK = two.pow(k);
        this.n = n;
        this.halfn = n/2;
        /*
        lambda and mult are previously calculated values, this are always the same for each key size
         */
        this.lambda = Parameters.getLambda(n);
        this.mult = Parameters.getMultValue(n);
    }


    public Keys generateKeys() throws NoSuchAlgorithmException, KeySizeException {

        //q parameter generation
        BigInteger q = BigInteger.probablePrime(halfn, rand);
        int tries = 1;

        while(true) {
            BigInteger pmin = BigInteger.probablePrime(halfn - 1, rand);
            BigInteger pmax = BigInteger.probablePrime(halfn, rand);

            BigInteger rmin = calcR(pmin);
            BigInteger rmax = calcR(pmax);

            BigInteger interval = rmax.subtract(rmin);
            interval = interval.add(one);

            BigInteger V0 = calcV0(rmin, mult);
            BigInteger v = calcv(mult);
            BigInteger V = calcV(v, V0, mult, interval.bitLength());


            BigInteger p;
            boolean isPrime;
            boolean isCongruent;
            long count = 0;
            do {
                p = calcP(rmin, V);
                V = V.multiply(two);
                V = V.mod(mult);
                isPrime = p.isProbablePrime(99);
                isCongruent = verifyPcongruence(p);

                count = count + 1;
                //Usually if the algorithm does not provide a valid p in 500 tries it wount be able to do it, so
                //The parameters are recalculated
            } while ((!isPrime || !isCongruent) && count <= MAX_TRIES);
            //only proceed if p is prime and congruent with 1 mod 2^k
            if(isPrime && isCongruent) {
                BigInteger N = p.multiply(q);

                BigInteger y = calcY(N, p, q);


                PublicKey publicKey = new PublicKey(N, y, k);
                PrivateKey privateKey = new PrivateKey(p, q);

                keys = new Keys(privateKey, publicKey);
                keys.setTries(tries);
                break;
            }else{
                tries ++;
            }

        }

        return keys;
    }

    /*
        Calculates the value v
     */
    private BigInteger calcv(BigInteger mul) throws NoSuchAlgorithmException {
        BigInteger k = new BigInteger(mul.bitLength(), rand);

        BigInteger U,r;
        BigInteger aux;
        while(true) {
            U = k.modPow(lambda, mul);

            if (U.compareTo(BigInteger.ZERO) != 0) {
                r =new BigInteger(mul.bitLength(), rand);
                aux = r.multiply(U);
                aux = aux.mod(mul);
                k = aux.add(k);


            } else {
                break;
            }
        }
        return k;
    }

    /*
        y calculation
     */
    private BigInteger calcY(BigInteger n, BigInteger p, BigInteger q) throws NoSuchAlgorithmException {
        BigInteger y;
        do{
            y= new BigInteger(n.bitLength(), rand);
        }while(y.compareTo(n)>=0 || y.modPow(p.subtract(one).divide(two), p).equals(one) || y.modPow(q.subtract(one).divide(two), q).equals(one));

        return y;
    }

    /*
        p calculation
     */
    private BigInteger calcP(BigInteger rmin, BigInteger V){
        BigInteger aux = rmin.add(V);
        aux = aux.multiply(twoK);
        aux = aux.add(one);
        return aux;
    }
    /*
        rmin and rmax calculation
     */
    private BigInteger calcR(BigInteger p){
        BigInteger aux = p.subtract(one);
        aux = aux.divide(twoK);
        return aux;
    }

    /*
        V0 calculation
     */
    private BigInteger calcV0(BigInteger rmin, BigInteger mult){
        BigInteger aux = one.divide(twoK);
        aux = aux.add(rmin);
        aux = aux.multiply(BigInteger.valueOf(-1));
        aux = aux.mod(mult);
        return aux;
    }
    /*
        V calculation, this implies the verification if V is congruent with V0 + v mod mult
     */
    private BigInteger calcV(BigInteger v, BigInteger V0, BigInteger mult, int len){
        BigInteger aux;
        do {
            aux =  new BigInteger(len-1, rand);
            aux = aux.mod(mult);

        }while (verifyV(aux, V0, mult, v));
        return aux;
    }

    /*
        Verification if V is congruent with V0 + v mod mult
     */
    private boolean verifyV(BigInteger V, BigInteger V0, BigInteger mul, BigInteger v){
        BigInteger aux = V.subtract(V0.add(v));
        aux = aux.remainder(mul);
        return aux.compareTo(BigInteger.ZERO) == 0;
    }

    /*
        Verification if P is congruent with 1 mod 2^k
     */
    private boolean verifyPcongruence(BigInteger p){
        BigInteger one = BigInteger.valueOf(1);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger twoK = two.pow(k);
        BigInteger x = (p.subtract(one)).remainder(twoK);
        if (x.equals(BigInteger.ZERO)) return true;
        return false;
    }


}
