package symmetric.box;

public interface PBox {
    byte[] apply(byte[] b);
    byte[] inverse(byte[] b);
}
