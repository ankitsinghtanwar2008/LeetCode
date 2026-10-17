import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> {
            int x = digitSum(nums[a]);
            int y = digitSum(nums[b]);

            if (x != y) return x - y;
            return nums[a] - nums[b];
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || idx[i] == i) continue;

            int j = i;
            int cycle = 0;

            while (!visited[j]) {
                visited[j] = true;
                j = idx[j];
                cycle++;
            }

            swaps += cycle - 1;
        }

        return swaps;
    }

    private int digitSum(int n) {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
}