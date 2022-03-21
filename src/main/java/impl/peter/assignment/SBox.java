package impl.peter.assignment;

public class SBox extends Box {
    private boolean verbose;

    public SBox(boolean b) {
        super();
        this.verbose = b;
        box.put(0, 14);
        box.put(1, 4);
        box.put(2, 13);
        box.put(3, 1);
        box.put(4, 2);
        box.put(5, 15);
        box.put(6, 11);
        box.put(7, 8);
        box.put(8, 3);
        box.put(9, 10);
        box.put(10, 6);
        box.put(11, 12);
        box.put(12, 5);
        box.put(13, 9);
        box.put(14, 0);
        box.put(15, 7);
    }

    public int traverse(int i) {
        if(verbose) {
            String bytes = String.format("%4s", Integer.toBinaryString(super.tra(i))).replace(' ', '0');
            System.out.println(bytes);
        }
        return super.tra(i);
    }

    public int inverse(int i) {
        if(verbose) {
            String bytes = String.format("%4s", Integer.toBinaryString(super.inv(i))).replace(' ', '0');
            System.out.println(bytes);
        }
        return super.inv(i);
    }
}
