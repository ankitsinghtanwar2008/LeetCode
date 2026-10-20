class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        // pre[i] = number of descents at positions j < i (nums[j] > nums[j+1])
        int[] pre = new int[n];
        for (int i = 0; i < n - 1; i++) {
            pre[i + 1] = pre[i] + (nums[i] > nums[i + 1] ? 1 : 0);
        }

        int total = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;
            int prevMax = 0;

            for (int s = 0; s < n; s += k) {
                int e = s + k - 1;

                // descents inside block + wrap-around (last -> first)
                int d = pre[e] - pre[s] + (nums[e] > nums[s] ? 1 : 0);
                if (d > 1) {
                    ok = false;
                    break;
                }

                int mn = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
                for (int i = s; i <= e; i++) {
                    mn = Math.min(mn, nums[i]);
                    mx = Math.max(mx, nums[i]);
                }

                if (mn < prevMax) {
                    ok = false;
                    break;
                }
                prevMax = mx;
            }

            if (ok) total += k;
        }

        return total;
    }
}