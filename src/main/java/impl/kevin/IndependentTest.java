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

    static String text = "000001001101001000001011101110000000001010001\n" +
            "111100011100111111101100000010100010100001110\n" +
            "10000000010011011001110010101110110000";

    private static byte[] readChiffre() throws IOException, URISyntaxException {
        URL resource = IndependentTest.class.getClassLoader().getResource("chiffre.txt");
        File chiffre = new File(resource.toURI());
        return Files.readAllBytes(chiffre.toPath());
    }


    public static void main(String[] args) throws IOException, URISyntaxException {
        byte[] message = {0b0001, 0b0010, 0b1000, 0b1111};
        byte[] chiffre = {0b1010, 0b1110, 0b1011, 0b0100};

        SpnImpl kevinsSpn = new SpnImpl(4, 4, 4, 32);
        kevinsSpn.setKey(new byte[]{0b0001, 0b0001, 0b0010, 0b1000, 0b1000, 0b1100, 0b0000, 0b0000});
        byte[] encrypt = kevinsSpn.encrypt(message);
        System.out.println(Arrays.equals(encrypt, chiffre));
        byte[] decrypted = kevinsSpn.decrypt(chiffre);
        System.out.println(Arrays.equals(decrypted, message));
    }
}
