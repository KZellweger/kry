package impl.peter.assignment;

public class KeyGenerator {
    private boolean verbose;
    private int n, m, r;
    private int key;
    private Box pBox;

    public KeyGenerator(int n, int m, int r, boolean verbose) {
        this.verbose = verbose;
        this.n = n;
        this.m = m;
        this.r = r;
    }

    public int[] getEncryptionKeys() {
        int[] roundKeys = new int[r + 1];
        int mask = (1 << n * m) - 1;
        if(verbose) System.out.println(String.format("Mask: %16s", Integer.toBinaryString(mask)).replace(' ', '0'));

        for(int i = 0; i < r + 1; i++) {
            int value = mask & (key >> 16 - 4 * i);
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
        for(int i = 0; i < roundKeys.length; i++) {
            // i = (0, 4)
            int index = roundKeys.length - 1 - i;
            if(i == 0 || i == roundKeys.length - 1) {
                result[i] = roundKeys[index];
            } else {
                // Permutation of key
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
                permutate(keyAsArr);
                if(verbose) printArr(keyAsArr);
            }
        }
        return result;
    }

    private void permutate(int[] a) {
        // Since the bpox is self-inverse we need to mark swapped entries
        boolean[] swapped = new boolean[a.length];
        for(int i = 0; i < a.length; i++) {
            if(!swapped[i]) {
                swap(a, i, pBox.tra(i));
                swapped[i] = true;
                swapped[pBox.tra(i)] = true;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

    public void setPBox(Box pBox) {
        this.pBox = pBox;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
