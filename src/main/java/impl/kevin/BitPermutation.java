package impl.kevin;

import static impl.kevin.Utils.bytesToInt;
import static impl.kevin.Utils.intToBytes;

public class BitPermutation {
    private final int[] indexFrom;
    private final int[] indexTo;

    private final int[] permTarget = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    public BitPermutation(int[] indexFrom, int[] indexTo) {
        this.indexFrom = indexFrom;
        this.indexTo = indexTo;
    }


    public byte[] permute(byte[] bytes) {
        int value = bytesToInt(bytes, 4);
        int newValue = convert(value, indexFrom, indexTo);
        return intToBytes(newValue, 4, 4);
    }

    private int convert(int value, int[] preIdx, int[] newIdx) {
        int newValue = 0;
        for (int i = 0; i < newIdx.length; i++) {

            int bit = (value >> preIdx[i]) & 1;
            newValue |= bit << newIdx[i];
        }
        return newValue;
    }

}
