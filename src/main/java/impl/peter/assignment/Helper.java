package impl.peter.assignment;

public class Helper {
    public static String getFullByteString(int value) {
        return String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }
}
