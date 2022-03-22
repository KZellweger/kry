package impl.kevin;

import symmetric.box.SBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static impl.kevin.Utils.dezimalToByte;

public class SboxImpl implements SBox {

    private final Map<Byte, Byte> matrix;

    public SboxImpl(List<Integer> values) {
        this.matrix = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            this.matrix.put(dezimalToByte(i), dezimalToByte(values.get(i)));
        }
    }

    @Override
    public byte[] apply(byte[] b) {
        byte[] bytes = new byte[b.length];

        for (int i = 0; i < b.length; i++) {
            bytes[i] = matrix.get(b[i]);
        }

        return bytes;
    }

    @Override
    public byte[] inverse(byte[] b) {
        byte[] bytes = new byte[b.length];
        for (int i = 0; i < b.length; i++) {
            int finalI = i;
            Map.Entry<Byte, Byte> entry = matrix.entrySet().stream().filter(byteByteEntry -> byteByteEntry.getValue().equals(b[finalI])).findFirst().orElseThrow(() -> new IllegalArgumentException("Unknown Key"));
            bytes[i] = entry.getKey();
        }
        return bytes;
    }
}
