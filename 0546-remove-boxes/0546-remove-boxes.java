class Solution {
    int[][][] dp;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        dp = new int[n][n][n];

        return solve(boxes, 0, n - 1, 0);
    }

    private int solve(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }

        while (l < r && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }

        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }

        int ans = (k + 1) * (k + 1) + solve(boxes, l, r - 1, 0);

        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                ans = Math.max(ans,
                    solve(boxes, l, i, k + 1)
                    + solve(boxes, i + 1, r - 1, 0)
                );
            }
        }

        dp[l][r][k] = ans;
        return ans;
    }
}