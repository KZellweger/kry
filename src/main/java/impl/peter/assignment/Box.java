package impl.peter.assignment;

import java.util.HashMap;
import java.util.Map;

public abstract class Box {
    protected Map<Integer, Integer> box;

    public Box() {
        this.box = new HashMap<>();
    }

    public abstract int traverse(int i);

    protected int tra(int i) {
        return box.get(i);
    }

    public abstract int inverse(int tra);

    public int inv(int i) {
        int j = -1;
        for(Map.Entry<Integer, Integer> entry : box.entrySet()) {
            if(entry.getValue().equals(i)) {
                j = entry.getKey();
            }
        }
        if(j == -1) throw new IllegalArgumentException("Illegal input");
        return j;
    }

    public int size() {
        return box.size();
    }
}
