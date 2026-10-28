class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] dp = new boolean[n][1024];
        boolean[] tmp = new boolean[1024];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int g = grid[i][j];
                boolean[] cur = dp[j];

                if (i == 0 && j == 0) {
                    cur[g] = true;
                    continue;
                }

                Arrays.fill(tmp, false);
                boolean[] left = j > 0 ? dp[j - 1] : null;
                for (int x = 0; x < 1024; x++) {
                    if ((i > 0 && cur[x]) || (left != null && left[x])) {
                        tmp[x ^ g] = true;
                    }
                }
                System.arraycopy(tmp, 0, cur, 0, 1024);
            }
        }

        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}