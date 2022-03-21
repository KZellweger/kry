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
        this.generator = new KeyGenerator(n, m, r,true);
        generator.setKey(key);
        generator.setPBox(pbox);

        generator.getDecryptionKeys();

        sbox.traverse(2);
        sbox.inverse(sbox.tra(2));

        Message msg = new Message();
        System.out.println(msg.getMessage());

        int[] arr = new int[msg.getMessage().length()];
        for(int i = 0; i < msg.getMessage().length(); i++) {
            arr[i] = msg.getMessage().toCharArray()[i] == '0' ? 0 : 1;
        }
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

    public String decrypt(int[] cipher) {

        return "";
    }
}
