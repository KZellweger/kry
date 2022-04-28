package rainbow;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    RainbowTable table = new RainbowTable(7, 2000, 2000, false);
	    List<String> result = table.lookupHash(Constants.hash);
        if(result == null) {
            System.out.println(String.format("No password found for hash: %s", Constants.hash));
        } else {
            System.out.println(String.format("Found plain text password: %s leading to reduced rainbow table entry: %s. \n" +
                    "Reduced value: %s leads to hash: %s.", result.get(0), result.get(1), result.get(2), Constants.hash));
        }
    }
}
