package impl.kevin;

import symmetric.box.PBox;
import symmetric.box.SBox;
import symmetric.mode.Mode;
import symmetric.spn.SPN;

import java.util.Optional;

public class SpnImpl implements SPN {



    @Override
    public byte[] decrypt(byte[] encrypted) {
        return new byte[0];
    }

    @Override
    public byte[] encrypt(byte[] message) {
        return new byte[0];
    }

    @Override
    public void setN(int n) {

    }

    @Override
    public void setM(int m) {

    }

    @Override
    public void setR(int r) {

    }

    @Override
    public void setS(int s) {

    }

    @Override
    public void useMode(Optional<Mode> mode) {

    }

    @Override
    public void setSBox(Optional<SBox> sBox) {

    }

    @Override
    public void setPBox(Optional<PBox> pBox) {

    }
}
