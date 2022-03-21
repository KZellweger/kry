import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class Programmieraufgabe {

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(new String(readChiffre()));


    }

    private static byte[] readChiffre() throws IOException, URISyntaxException {
        URL resource = Programmieraufgabe.class.getClassLoader().getResource("chiffre.txt");
        File chiffre = new File(resource.toURI());
        return Files.readAllBytes(chiffre.toPath());
    }

}
