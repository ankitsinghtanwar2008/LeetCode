class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {

        int MOD = 1000000007;

        for (int[] query : queries) {

            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];

            for (int i = l; i <= r; i += k) {
                nums[i] = (int)(((long) nums[i] * v) % MOD);
            }
        }

        int ans = 0;

        for (int num : nums) {
            ans ^= num;
        }

        return ans;
    }
}