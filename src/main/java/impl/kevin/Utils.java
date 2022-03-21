package impl.kevin;

public class Utils {
    public static byte dezimalToByte(Integer i) {
        if (i > 127 || i < 0) {
            throw new IllegalArgumentException("Integer won't fit in byte");
        }
        return i.byteValue();
    }

    public static byte[] xorByteArrays(byte[] b1, byte[] b2) {
        byte[] res = new byte[b1.length];
        for (int i = 0; i < b1.length; i++) {
            res[i] = (byte) (b1[i] ^ b2[i]);
        }
        return res;
    }

    public static int bytesToInt(byte[] bytes, int blockSize) {
        int value = 0;
        for (byte b : bytes) {
            value = (value << blockSize) + (b & getBitMask(blockSize));
        }
        return value;
    }

    public static byte[] intToBytes(int value, int length, int blockSize) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[length - i - 1] = (byte) (value & getBitMask(blockSize));
            value >>= blockSize;
        }
        return bytes;
    }


    private static int getBitMask(int blockSize) {
        return (1 << blockSize) - 1;
    }

}
