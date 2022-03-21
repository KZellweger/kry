package impl.kevin;

public class Utils {
    public static byte dezimalToByte(Integer i) {
        if(i > 127 || i < 0){throw new IllegalArgumentException("Integer won't fit in byte");}
        return i.byteValue();
    }
}
