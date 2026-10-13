import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] a = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            a[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(a);
        long ans = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) ans -= a[i];
            else ans += a[i];
        }

        return ans;
    }
}