package impl.peter.assignment;

public class KeyGenerator {
    private boolean verbose;
    private int n, m, r;
    private int key;
    private PBox pBox;

    public KeyGenerator(int n, int m, int r, boolean verbose) {
        this.verbose = verbose;
        this.n = n;
        this.m = m;
        this.r = r;
    }

    public int[] getEncryptionKeys() {
        int[] roundKeys = new int[r + 1];
        // Create bit mask of all 1 for size n * m
        int mask = (1 << n * m) - 1;
        if(verbose) System.out.println(String.format("Mask: %16s", Integer.toBinaryString(mask)).replace(' ', '0'));

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

    public int[] getDecryptionKeys() {
        int[] roundKeys = getEncryptionKeys();
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
                // Generate a mask with leading 0 and 1 at the end.
                // TODO: Am I retarded and could I just have used int 1 as mask?
                int mask = (0 << n * m - 1) + 1;

                if(verbose) System.out.println(String.format("Mask: %16s", Integer.toBinaryString(mask)).replace(' ', '0'));

                int key = roundKeys[i];
                int[] keyAsArr = new int[n * m];
                int cnt = 0;
                while(key != 0) {
                    keyAsArr[n * m - cnt - 1] = mask & key;
                    key = key >> 1;
                    cnt++;
                }
                if(verbose) printArr(keyAsArr);
                pBox.permutate(keyAsArr);
                int newKey = 0;
                for(int l = 0; l < keyAsArr.length; l++) {
                    System.out.println(keyAsArr[l]);
                    newKey = newKey << keyAsArr[l];
                }
                result[i] = newKey;
                if(verbose) {
                    String bytes = String.format("%16s", Integer.toBinaryString(newKey).replace(' ', '0'));
                    System.out.println(String.format("Key: %s", bytes));
                }
                if(verbose) printArr(keyAsArr);
            }
        }
        if(verbose) {
            for (int i = 0; i < result.length; i++) {
                String bytes = String.format("%16s", Integer.toBinaryString(result[i])).replace(' ', '0');
                System.out.println(String.format("Key[%s]: %s", i, bytes));
            }
        }
        return result;
    }

    private void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

    public void setPBox(PBox pBox) {
        this.pBox = pBox;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
