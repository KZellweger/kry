package symmetric.spn;

import symmetric.CryptoSystem;
import symmetric.box.PBox;
import symmetric.box.SBox;
import symmetric.mode.Mode;

import java.util.Optional;

public interface SPN extends CryptoSystem {

    /**
     * @param n Number of Blocks
     */
    void setN(int n);

    /**
     * @param m Block size
     */
    void setM(int m);

    /**
     * @param r number of Rounds
     */
    void setR(int r);

    /**
     * @param s Key must have size of 2 * n * m
     */
    void setS(int s);

    /**
     * @param mode Optional EncryptionMode
     */
    void useMode(Optional<Mode> mode);

    /**
     * @param sBox Optional concrete SBox
     */
    void setSBox(Optional<SBox> sBox);

    /**
     * @param pBox Optional concrete PBox
     */
    void setPBox(Optional<PBox> pBox);

}
