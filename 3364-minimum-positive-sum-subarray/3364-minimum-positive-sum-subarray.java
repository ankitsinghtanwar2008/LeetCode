class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums.get(i);

        int best = Integer.MAX_VALUE;
        for (int i = l; i <= n; i++) {
            int lo = Math.max(0, i - r);
            for (int j = lo; j <= i - l; j++) {
                int s = pre[i] - pre[j];
                if (s > 0 && s < best) best = s;
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}