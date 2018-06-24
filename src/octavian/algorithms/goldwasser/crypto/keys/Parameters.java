package octavian.algorithms.goldwasser.crypto.keys;

import octavian.algorithms.goldwasser.crypto.exceptions.KeySizeException;

import java.math.BigInteger;

/**
 * Created by fabio on 11/26/2016.
 */
public class Parameters {

    //Precalculated multiplication of small odd primes
    public static BigInteger getMultValue(int keysize) throws KeySizeException {
        switch (keysize) {
            case 512:
                return new BigInteger("2305567963945518424753102147331756070");
            case 1024:
                return new BigInteger("87714969705038411076272137418539099801877190558970371113762453702525982911939243939521562715111692818014473106390");
            case 2048:
                return new BigInteger("701764568397177639429934196433089102743025746236414229080225837074272045291632043086949070392760174425577006174281717269518386990825977829627851538394490999398853685660995118108109194230249333697862263973575143401427595863627712293058638082185214171207189543108565790");
            case 3072:
                return new BigInteger("20023620209044528159511741092026111575721096287952033280890216911029466309299244261280131263405759302102973368274392117584712154554147852782521923413761337061812444922609817603902014814625260877983870114563454641565468417567572976451068314416743541124863024839827028842502970154324854269188122507157260575794215703271997986950447857877037520410269496235087062782560401130103628214344829307099367702771821422466053941751470");
            default:
                throw new KeySizeException("Key size not supported");
        }
    }

    //Precalculated carmichael function values
    public static BigInteger getLambda(int keysize) throws KeySizeException {
        switch (keysize) {
            case 512:
                return new BigInteger("39419059680");
            case 1024:
                return new BigInteger("99110123605167229553191056864000");
            case 2048:
                return new BigInteger("140379462794950205602335179623117256704314480728044684312450881611123616000");
            case 3072:
                return new BigInteger("2163007819928319045144077616071473234472244592535698454967677333267913784508384522471179127444684832000");
            default:
                throw new KeySizeException("Key size not supported");
        }
    }

    public static long getMaxExecTime(int keysize) throws KeySizeException {
        switch (keysize) {
            case 512:
                return 500;
            case 1024:
                return 1000;
            case 2048:
                return 6000;
            case 3072:
                return 40000;
            default:
                throw new KeySizeException("Key size not supported");
        }
    }
}
