class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] cnt = new int[1501];
        int pmin = Integer.MAX_VALUE;
        for (int[] it : items) {
            cnt[it[0]]++;
            if (it[1] < pmin) pmin = it[1];
        }

        int[] mult = new int[1501];
        for (int f = 1; f <= 1500; f++) {
            int s = 0;
            for (int m = f; m <= 1500; m += f) s += cnt[m];
            mult[f] = s;
        }

        int[] dp = new int[budget + 1];
        for (int[] it : items) {
            int p = it[1];
            if (p > budget) continue;
            int v = mult[it[0]];
            for (int c = budget; c >= p; c--) {
                int t = dp[c - p] + v;
                if (t > dp[c]) dp[c] = t;
            }
        }

        for (int c = pmin; c <= budget; c++) {
            int t = dp[c - pmin] + 1;
            if (t > dp[c]) dp[c] = t;
        }
        return dp[budget];
    }
}