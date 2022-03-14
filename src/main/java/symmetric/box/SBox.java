package symmetric.box;

public interface SBox {
    byte[] apply(byte[] b);
    byte[] inverse(byte[] b);
}
