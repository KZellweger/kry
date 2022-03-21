package impl.kevin;

import symmetric.box.PBox;
import symmetric.box.SBox;

import java.util.Arrays;
import java.util.List;

public class SpnImpl {

    private final PBox bitPermutation = new BitPermutation(
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

    public byte[] decrypt(byte[] encrypted) {
        return new byte[0];
    }

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
                crypto = bitPermutation.apply(crypto);
                crypto = xorByteArrays(crypto, roundKey);
                //regular round
            }
        }
        return crypto;
    }

    private byte[] xorByteArrays(byte[] b1, byte[] b2) {
        byte[] res = new byte[b1.length];
        for (int i = 0; i < b1.length; i++) {
            res[i] = (byte) (b1[i] ^ b2[i]);
        }
        return res;
    }


}
