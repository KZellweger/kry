package impl.peter.assignment;

public class SPN {
    private Box sbox, pbox;
    private int r, n, m, s;
    private int key;
    private int[] roundKeys;

    public SPN() {
        this.sbox = new SBox();
        this.pbox = new PBox();
        this.r = 4;
        this.n = 4;
        this.m = 4;
        this.s = 32;
        this.key = 0b0011_1010_1001_0100_1101_0110_0011_1111;
        roundKeys = new int[r + 1];

        int mask = 0b1111_1111_1111_1111;

        for(int i = 0; i < r + 1; i++) {
            int value = mask & (key >> 16 - 4 * i);
            roundKeys[i] = value;
            String bytes = String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
            System.out.println(String.format("Key[%s]: %s", i, bytes));
        }

        sbox.traverse(2);
        sbox.inverse(sbox.tra(2));
    }

    public String encrypt(boolean[] plain) {
        return "";
    }

    public String decrypt(boolean[] cipher) {
        return "";
    }
}
