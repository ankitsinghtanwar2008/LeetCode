import java.util.*;

class MajorityChecker {
    int[] arr;
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    Random random = new Random();

    public MajorityChecker(int[] arr) {
        this.arr = arr;

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        int len = right - left + 1;

        for (int t = 0; t < 30; t++) {
            int index = left + random.nextInt(len);
            int value = arr[index];

            ArrayList<Integer> list = map.get(value);

            int l = lowerBound(list, left);
            int r = upperBound(list, right);

            if (r - l >= threshold) {
                return value;
            }
        }

        return -1;
    }

    private int lowerBound(ArrayList<Integer> list, int x) {
        int l = 0, r = list.size();

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (list.get(mid) < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private int upperBound(ArrayList<Integer> list, int x) {
        int l = 0, r = list.size();

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (list.get(mid) <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}