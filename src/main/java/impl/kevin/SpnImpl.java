package impl.kevin;

import symmetric.CryptoSystem;
import symmetric.box.SBox;

import java.util.Arrays;
import java.util.List;

import static impl.kevin.Utils.xorByteArrays;

public class SpnImpl implements CryptoSystem {

    private final BitPermutation bitPermutation = new BitPermutation(
            new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            new int[]{0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15});

    private final SBox sbox = new SboxImpl(List.of(0xE, 0x4, 0xD, 0x1, 0x2, 0xF, 0xB, 0x8, 0x3, 0xA, 0x6, 0xC, 0x5, 0x9, 0x0, 0x7));
    private final int n;
    private final int m;
    private final int r;
    private final int s;

    public SpnImpl(int n, int m, int r, int s) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.s = s;
    }

    private byte[] key;

    public void setKey(byte[] key) {
        this.key = key;
    }

    @Override
    public byte[] decrypt(byte[] encrypted) {
        byte[] message = encrypted;

        for (int i = r; i >= 0; i--) {
            byte[] roundKey = Arrays.copyOfRange(key, i, i + 4);
            if (i == 0) {
                // last round
                message = sbox.inverse(message);
                message = xorByteArrays(message, roundKey);
            } else if (i == r) {
                // init round
                message = xorByteArrays(message, roundKey);
            } else {
                message = sbox.inverse(message);
                message = bitPermutation.permute(message);
                roundKey = bitPermutation.permute(roundKey);
                message = xorByteArrays(message, roundKey);
                // regular round
            }
        }

        return message;
    }

    @Override
    public byte[] encrypt(byte[] message) {
        byte[] crypto = message;
        for (int i = 0; i <= r; i++) {
            byte[] roundKey = Arrays.copyOfRange(key, i, i + 4);
            if (i == 0) {
                crypto = xorByteArrays(crypto, roundKey);
                // init round
            } else if (i == r) {
                crypto = sbox.apply(crypto);
                crypto = xorByteArrays(crypto, roundKey);
            } else {
                crypto = sbox.apply(crypto);
                crypto = bitPermutation.permute(crypto);
                crypto = xorByteArrays(crypto, roundKey);
                //regular round
            }
        }
        return crypto;
    }

}
