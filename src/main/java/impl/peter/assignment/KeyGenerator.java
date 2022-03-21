package impl.peter.assignment;

public class KeyGenerator {
    private boolean verbose;
    private int n, m, r;
    private int key;
    private PBox pBox;
    private int[] encryptionKeys, decryptionKeys;

    public KeyGenerator(int n, int m, int r, boolean verbose, int key, PBox pBox) {
        this.verbose = verbose;
        this.n = n;
        this.m = m;
        this.r = r;
        this.key = key;
        this.pBox = pBox;
        this.encryptionKeys = generateEncryptionKeys();
        this.decryptionKeys = generateDecryptionKeys();
    }

    private int[] generateDecryptionKeys() {
        int[] roundKeys = encryptionKeys;
        int[] result = new int[roundKeys.length];
        // For each encryption key
        for(int i = 0; i < roundKeys.length; i++) {
            // Swap the index
            int index = roundKeys.length - 1 - i;
            // If i = 0 or i = keys.size - 1 only swap
            if(i == 0 || i == roundKeys.length - 1) {
                result[i] = roundKeys[index];
            } else {
                // Permutation of key if not first or last key
                // Mask with leading 0 and 1 at the end.
                int mask = 1;

                int key = roundKeys[i];
                int[] keyAsArr = new int[n * m];
                int cnt = 0;
                while(key != 0) {
                    keyAsArr[n * m - cnt - 1] = mask & key;
                    key = key >> 1;
                    cnt++;
                }

                pBox.permutate(keyAsArr);

                int n = 0, l = keyAsArr.length;
                for (int m = 0; m < l; ++m) {
                    n = (n << 1) + (keyAsArr[m] == 1 ? 1 : 0);
                }

                result[index] = n;
            }
        }
        if(verbose) {
            System.out.println("Inverese Keys:");
            for (int i = 0; i < result.length; i++) {
                String bytes = String.format("%16s", Integer.toBinaryString(result[i])).replace(' ', '0');
                System.out.println(String.format("Key[%s]: %s", i, bytes));
            }
        }
        return result;
    }

    private int[] generateEncryptionKeys() {
        int[] roundKeys = new int[r + 1];
        // Create bit mask of all 1 for size n * m
        int mask = (1 << n * m) - 1;

        // For each round create a key
        for(int i = 0; i < r + 1; i++) {
            // The round key will be calculated by shifting for n bits each time
            int value = mask & (key >> n * m - n * i);
            roundKeys[i] = value;

            if(verbose) {
                String bytes = String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
                System.out.println(String.format("Key[%s]: %s", i, bytes));
            }
        }
        return roundKeys;
    }

    public int[] getEncryptionKeys() {
        return encryptionKeys;
    }

    public int[] getDecryptionKeys() {
        return decryptionKeys;
    }
}
