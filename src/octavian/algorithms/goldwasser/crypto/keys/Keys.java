package octavian.algorithms.goldwasser.crypto.keys;

/**
 * Created by fabio on 11/12/2016.
 */
public class Keys {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private long tries;

    public Keys(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        tries = 1;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public long getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
}
