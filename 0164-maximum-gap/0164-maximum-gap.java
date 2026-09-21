class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) {
            return 0;
        }
        int gap = (max - min + n - 2) / (n - 1);
        int[] bucketMin = new int[n - 1];
        int[] bucketMax = new int[n - 1];
        boolean[] used = new boolean[n - 1];
        for (int num : nums) {
            if (num == min || num == max) {
                continue;
            }
            int index = (num - min) / gap;
            if (!used[index]) {
                bucketMin[index] = num;
                bucketMax[index] = num;
                used[index] = true;
            } else {
                bucketMin[index] = Math.min(bucketMin[index], num);
                bucketMax[index] = Math.max(bucketMax[index], num);
            }
        }
        int ans = 0;
        int previous = min;

        for (int i = 0; i < n - 1; i++) {
            if (!used[i]) {
                continue;
            }
            ans = Math.max(ans, bucketMin[i] - previous);
            previous = bucketMax[i];
        }
        ans = Math.max(ans, max - previous);
        return ans;
    }
}