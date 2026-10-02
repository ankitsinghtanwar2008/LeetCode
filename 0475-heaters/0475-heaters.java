import java.util.*;

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int ans = 0;

        for (int house : houses) {
            int i = Arrays.binarySearch(heaters, house);

            if (i >= 0) {
                continue;
            }

            i = -i - 1;

            int left = i - 1 >= 0 ? house - heaters[i - 1] : Integer.MAX_VALUE;
            int right = i < heaters.length ? heaters[i] - house : Integer.MAX_VALUE;

            ans = Math.max(ans, Math.min(left, right));
        }

        return ans;
    }
}