import java.util.*;

class Solution {
    int m, n;
    int total;
    HashMap<Integer, Integer> map;
    Random random;

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        total = m * n;
        map = new HashMap<>();
        random = new Random();
    }

    public int[] flip() {
        int x = random.nextInt(total);
        int index = map.getOrDefault(x, x);

        total--;

        map.put(x, map.getOrDefault(total, total));

        return new int[]{index / n, index % n};
    }

    public void reset() {
        total = m * n;
        map.clear();
    }
}