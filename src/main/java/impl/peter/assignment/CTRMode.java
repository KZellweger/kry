package impl.peter.assignment;

public class CTRMode {
    private boolean verbose;

    public CTRMode(boolean verbose) {
        this.verbose = verbose;
    }

    public int[] getFragments(String text) {
        int[] result = new int[text.length() / 16 + 1];

        // pseudo random
        result[0] = 0b0001_1000_1111_0000;

        int cnt = 1;
        for(int i = 0; i < text.length(); i += 16) {
            String substr = text.substring(i, i + 16);
            result[cnt] = Integer.parseInt(substr, 2);
            cnt++;
        }

        if(verbose) {
            for(int i = 0; i < result.length; i++) {
                System.out.println(String.format("Block[%s]: %s", i, String.format(Helper.getFullByteString(result[i]))));
            }
        }
        return result;
    }
}
