package impl.peter.assignment;

public class PBox extends Box {

    public PBox(boolean b) {
        super();
        box.put(0, 0);
        box.put(1, 4);
        box.put(2, 8);
        box.put(3, 12);
        box.put(4, 1);
        box.put(5, 5);
        box.put(6, 9);
        box.put(7, 13);
        box.put(8, 2);
        box.put(9, 6);
        box.put(10, 10);
        box.put(11, 14);
        box.put(12, 3);
        box.put(13, 7);
        box.put(14, 11);
        box.put(15, 15);
    }

    @Override
    public int traverse(int i) {
        return 0;
    }

    @Override
    public int inverse(int tra) {
        return 0;
    }

    public void permutate(int[] a) {
        // Since the bpox is self-inverse we need to mark swapped entries
        boolean[] swapped = new boolean[a.length];
        for(int i = 0; i < a.length; i++) {
            if(!swapped[i]) {
                swap(a, i, tra(i));
                swapped[i] = true;
                swapped[tra(i)] = true;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
