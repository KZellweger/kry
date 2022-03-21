package impl.peter.assignment;

import java.nio.ByteBuffer;

public class SPN {
    private Box sbox, pbox;
    private int r, n, m, s;
    private int key;
    private KeyGenerator generator;

    public SPN() {
        this.sbox = new SBox(true);
        this.pbox = new PBox(true);
        this.r = 4;
        this.n = 4;
        this.m = 4;
        this.key = 0b0011_1010_1001_0100_1101_0110_0011_1111;
        this.s = 32;
        this.generator = new KeyGenerator(n, m, r,true, key, (PBox) pbox);

        Message msg = new Message();
        System.out.println(msg.getMessage());

        CTRMode mode = new CTRMode(true);
        int[] fragments = mode.getFragments(msg.getMessage());

        decrypt(fragments, true);
    }

    public String encrypt(int[] plain) {
        for(int i = 0; i < plain.length; i++) {
            int roundValue = plain[i];
            for(int j = 0; j < r + 1; j++) {
                roundValue = roundValue ^ generator.getEncryptionKeys()[j];
            }
        }
        return "";
    }

    public String decrypt(int[] cipher, boolean verbose) {
        for(int i = 0; i < cipher.length; i++) {

            if(verbose) System.out.println(String.format("Fragment[%s]: %s", i, Helper.getFullByteString(cipher[i])));

            int roundValue = cipher[i];
            for(int j = 0; j < r + 1; j++) {
                System.out.println(String.format("Round Value: %s", Helper.getFullByteString(roundValue)));
                System.out.println(String.format("Current key: %s", Helper.getFullByteString(generator.getDecryptionKeys()[j])));
                roundValue = roundValue ^ generator.getDecryptionKeys()[j];
                System.out.println(String.format("New r Value: %s", Helper.getFullByteString(roundValue)));
                System.out.println("---");
                //roundValue = applySBox(roundValue, true, true);
            }
        }
        return "";
    }

    private int applySBox(int a, boolean inverse, boolean verbose) {
        int result = 0;
        if(verbose) {
            System.out.println(String.format("Before SBox: ", Helper.getFullByteString(a)));
        }

        // split into parts
        int[] parts = new int[4];
        for(int i = 0; i < 4; i++) {
            parts[4 - i - 1] = a & 0b1111;
            a = a >> 4;
        }

        // Sbox
        for(int i = 0; i < 4; i++) {
            if(inverse) {
                parts[i] = sbox.inverse(parts[i]);
            } else {
                parts[i] = sbox.traverse(parts[i]);
            }
        }

        // put back together
        for(int i = 0; i < 4; i++) {
            result = parts[i] << 4;
        }

        if(verbose) {
            System.out.println(String.format("After SBox: ", Helper.getFullByteString(result)));
        }
        return a;
    }

    private void splitToParts(int a) {
        int[] parts = new int[4];
        for(int i = 0; i < 4; i++) {
            parts[4 - i - 1] = a & 0b1111;
        }
    }
}
