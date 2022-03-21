package impl.peter;

import symmetric.box.SBox;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class SBoxImpl implements SBox {
    private Map<byte[], byte[]> sbox;

    public SBoxImpl() {
        this.sbox = new HashMap<>();
        putHexValue(0x0, 0xE);
        putHexValue(0x1, 0x4);
        putHexValue(0x2, 0xD);
        putHexValue(0x3, 0x1);
        putHexValue(0x4, 0x2);
        putHexValue(0x5, 0xF);
        putHexValue(0x6, 0xB);
        putHexValue(0x7, 0x8);
        putHexValue(0x8, 0x3);
        putHexValue(0x9, 0xA);
        putHexValue(0xA, 0x6);
        putHexValue(0xB, 0xC);
        putHexValue(0xC, 0x5);
        putHexValue(0xD, 0x9);
        putHexValue(0xE, 0x0);
        putHexValue(0xF, 0x7);
        System.out.println(this);
    }

    private void putHexValue(int key, int value) {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(key);
        byte[] keyArr = b.array();
        b.clear();
        b.putInt(value);
        byte[] valueArr = b.array();
        sbox.put(keyArr, valueArr);
    }

    @Override
    public byte[] apply(byte[] b) {
        return new byte[0];
    }

    @Override
    public byte[] inverse(byte[] b) {
        return new byte[0];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry entry : sbox.entrySet()) {
            sb.append(String.format("%s: %s", ((byte[])entry.getKey()), ((byte[])entry.getValue())));
        }
        return sb.toString();
    }
}
