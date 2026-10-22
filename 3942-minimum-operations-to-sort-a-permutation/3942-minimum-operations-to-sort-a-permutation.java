class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int k = nums[0];

        boolean rot = true;  
        boolean rev = true;  

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) rot = false;
            if (nums[i] != ((k - i) % n + n) % n) rev = false;
        }

        int ans = Integer.MAX_VALUE;

        if (rot) {
            ans = (k == 0) ? 0 : Math.min(n - k, k + 2);
        }

        if (rev) {
            int kp = (k + 1) % n;
            ans = Math.min(ans, Math.min(kp, n - kp) + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}