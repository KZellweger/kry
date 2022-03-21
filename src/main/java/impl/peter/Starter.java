package impl.peter;

import symmetric.spn.SPN;

import java.util.Optional;

public class Starter {
    public static void main(String[] args) {
        SPN spn = new SpnImpl();
        spn.setSBox(Optional.of(new SBoxImpl()));
    }
}
