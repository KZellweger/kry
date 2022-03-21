package impl.kevin;

import symmetric.box.PBox;

import java.nio.ByteBuffer;

public class BitPermutation implements PBox {
    private final int[] indexFrom;
    private final int[] indexTo;

    private final int[] permTarget = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    public BitPermutation(int[] indexFrom, int[] indexTo) {
        this.indexFrom = indexFrom;
        this.indexTo = indexTo;
    }


    @Override
    public byte[] apply(byte[] bytes) {

        int value = 0;
        int newValue = 0;
        for (byte b : bytes) {
            value = (value << 4) + (b & 0xF);
        }

        newValue = convert(value, indexFrom, indexTo);

        byte[] res = new byte[Integer.BYTES];
        int length = res.length;
        for (int i = 0; i < length; i++) {
            res[length - i - 1] = (byte) (newValue & 0xF);
            newValue >>= 4;
        }

        return res;


//        byte[] res = new byte[b.length];
//        for (int i = 0; i < b.length; i++) {
//            res[i] = (byte) convert(b[i], indexFrom, indexTo);
//        }
//        return res;
    }

    @Override
    public byte[] inverse(byte[] b) {
        int newValue = convert(ByteBuffer.wrap(b).getInt(), indexFrom, indexTo);
        return ByteBuffer.allocate(b.length).putInt(newValue).array();
    }

    private char[] permutation(char[] orig) {
        char[] res = new char[orig.length];

        for (int i = 0; i < orig.length; i++) {
            res[permTarget[i]] = orig[i];
        }

        return res;
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
