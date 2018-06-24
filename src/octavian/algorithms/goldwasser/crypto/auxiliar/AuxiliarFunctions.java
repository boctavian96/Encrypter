package octavian.algorithms.goldwasser.crypto.auxiliar;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by fabio on 11/13/2016.
 */
public class AuxiliarFunctions {


    public static BigInteger randomNumber(int size) throws NoSuchAlgorithmException {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        BigInteger number=null;
        byte bNumber[]=new byte[(int)Math.ceil(size/8.0)];
        do{
            rand.nextBytes(bNumber);
            number=new BigInteger(bNumber);
        }while(number.compareTo(BigInteger.ZERO)<=0);
        return number;
    }

    public static String getBinaryStrung(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            stringBuffer.append(s1);
        }

        return stringBuffer.toString();
    }
}
