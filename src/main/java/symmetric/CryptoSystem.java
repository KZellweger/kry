package symmetric;

public interface CryptoSystem {

    byte[] decrypt(byte[] encrypted);
    byte[] encrypt(byte[] message);

}
