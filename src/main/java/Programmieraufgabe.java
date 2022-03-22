import impl.kevin.RCTR;
import impl.kevin.SpnImpl;
import impl.kevin.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class Programmieraufgabe {

    static String text = "000001001101001000001011101110000000001010001" +
            "111100011100111111101100000010100010100001110" +
            "10000000010011011001110010101110110000";

    public static void main(String[] args) throws IOException, URISyntaxException {
        solutionKevinZellweger();
    }

    private static byte[] readChiffre() throws IOException, URISyntaxException {
        URL resource = Programmieraufgabe.class.getClassLoader().getResource("chiffre.txt");
        File chiffre = new File(resource.toURI());
        return Files.readAllBytes(chiffre.toPath());
    }

    public static void solutionKevinZellweger() {
        SpnImpl kevinsSpn = new SpnImpl(4, 4, 4, 32);
        kevinsSpn.setKey(new byte[]{0b0011, 0b1010, 0b1001, 0b0100, 0b1101, 0b0110, 0b0011, 0b1111}); //Message Key

        RCTR rctr = new RCTR(kevinsSpn);

        String[] parts = text.split("(?<=\\G.{16})");
        byte[][] encrypted = new byte[parts.length][4];

        for (int i = 0; i < parts.length; i++) {
            encrypted[i] = Utils.intToBytes(Integer.parseInt(parts[i], 2), 4, 4);
        }
        StringBuilder builder = new StringBuilder();
        byte[][] decrypted = rctr.decrypt(encrypted);
        for (byte[] bytes : decrypted) {
            int a = (bytes[0] << 4) + bytes[1];
            int b = (bytes[2] << 4) + bytes[3];
            char charA = (char) a;
            char charB = (char) b;
            builder.append(charA).append(charB);
        }

        System.out.println(builder.toString());
    }

}
