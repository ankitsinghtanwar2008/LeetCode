class Solution {
    public int splitArray(int[] nums, int k) {
        long low = 0, high = 0;

        for (int x : nums) {
            low = Math.max(low, x);
            high += x;
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return (int) low;
    }

    private boolean canSplit(int[] nums, int k, long limit) {
        int parts = 1;
        long sum = 0;

        for (int x : nums) {
            if (sum + x > limit) {
                parts++;
                sum = x;

                if (parts > k) {
                    return false;
                }
            } else {
                sum += x;
            }
        }

        return true;
    }
}