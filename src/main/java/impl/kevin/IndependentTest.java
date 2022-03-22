package impl.kevin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;

public class IndependentTest {
    byte[] message = {0b0001, 0b0010, 0b1000, 0b1111};
    byte[] chiffre = {0b1010, 0b1110, 0b1011, 0b0100};

    static String text = "000001001101001000001011101110000000001010001" +
            "111100011100111111101100000010100010100001110" +
            "10000000010011011001110010101110110000";

    private static byte[] readChiffre() throws IOException, URISyntaxException {
        URL resource = IndependentTest.class.getClassLoader().getResource("chiffre.txt");
        File chiffre = new File(resource.toURI());
        return Files.readAllBytes(chiffre.toPath());
    }


    public static void main(String[] args) throws IOException, URISyntaxException {
//        verificationSPN();
        verificationSPNWithCTR();
        solution();
    }

    public static void verificationSPN() {
        byte[] message = {0b0001, 0b0010, 0b1000, 0b1111};
        byte[] chiffre = {0b1010, 0b1110, 0b1011, 0b0100};

        SpnImpl kevinsSpn = new SpnImpl(4, 4, 4, 32);
        kevinsSpn.setKey(new byte[]{0b0001, 0b0001, 0b0010, 0b1000, 0b1000, 0b1100, 0b0000, 0b0000}); //Test Key
        byte[] encrypt = kevinsSpn.encrypt(message);
        System.out.println(Arrays.equals(encrypt, chiffre));
        byte[] decrypted = kevinsSpn.decrypt(chiffre);
        System.out.println(Arrays.equals(decrypted, message));

    }

    public static void verificationSPNWithCTR() {
        byte[][] message = {{0b0001, 0b0010, 0b1000, 0b1111}, {0b0001, 0b0010, 0b1000, 0b1111}, {0b0001, 0b0010, 0b1000, 0b1111}, {0b0001, 0b0010, 0b1000, 0b1111}};
        byte[][] chiffre = {{0b1010, 0b1110, 0b1011, 0b0100}, {0b0001, 0b0010, 0b1000, 0b1111}};

        SpnImpl kevinsSpn = new SpnImpl(4, 4, 4, 32);
        kevinsSpn.setKey(new byte[]{0b0001, 0b0001, 0b0010, 0b1000, 0b1000, 0b1100, 0b0000, 0b0000}); //Test Key
        RCTR rctr = new RCTR(kevinsSpn);
        byte[][] encrypt = rctr.encrypt(message);
//        System.out.println(Arrays.equals(encrypt, chiffre));
        byte[][] decrypted = rctr.decrypt(encrypt);
        System.out.println(Arrays.deepEquals(decrypted, message));

    }


    public static void solution() {
        SpnImpl kevinsSpn = new SpnImpl(4, 4, 4, 32);
        kevinsSpn.setKey(new byte[]{0b0011, 0b1010, 0b1001, 0b0100, 0b1101, 0b0110, 0b0011, 0b1111}); //Message Key

        RCTR rctr = new RCTR(kevinsSpn);

        String[] parts = text.split("(?<=\\G.{16})");
        byte[][] encrypted = new byte[parts.length][4];

        for (int i = 0; i < parts.length; i++) {
            encrypted[i] = Utils.intToBytes(Integer.parseInt(parts[i], 2), 4, 4);
        }
        byte[][] decrypted = rctr.decrypt(encrypted);
        for (byte[] bytes : decrypted) {
            int a = (bytes[0] << 4) + bytes[1];
            int b = (bytes[2] << 4) + bytes[3];
            char charA = (char) a;
            char charB = (char) b;
            System.out.println("A: " + a + " Char A: " + charA);
            System.out.println("B: " + b + " Char B: " + charB);
        }
    }

}
