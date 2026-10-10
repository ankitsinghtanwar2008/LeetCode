class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];
        long total = (long) k1 + k2;
        long sum = 0, max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i] * diff[i];
            max = Math.max(max, diff[i]);
        }

        if (sum == 0 || total >= diffSum(diff)) return 0;

        long left = 0, right = max;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long need = 0;

            for (long d : diff) {
                if (d > mid) need += d - mid;
            }

            if (need <= total) right = mid;
            else left = mid + 1;
        }

        long ans = 0;
        long need = 0;

        for (long d : diff) {
            if (d > left) {
                need += d - left;
                d = left;
            }
            ans += d * d;
        }

        long extra = total - need;
        return ans - extra * (2 * left - 1);
    }

    private long diffSum(long[] diff) {
        long sum = 0;
        for (long d : diff) sum += d;
        return sum;
    }
}