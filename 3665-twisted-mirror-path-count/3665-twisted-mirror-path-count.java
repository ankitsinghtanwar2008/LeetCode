class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[] cur = new int[n], nxt = new int[n];

        for (int i = 0; i < m; i++) {
            int[] row = grid[i];
            int r = (i == 0) ? 1 : 0;
            for (int j = 0; j < n; j++) {
                int d = cur[j];
                if (row[j] == 0) {
                    int land = r + d;
                    if (land >= MOD) land -= MOD;
                    if (i == m - 1 && j == n - 1) return land;
                    nxt[j] = land;
                    r = land;
                } else {
                    nxt[j] = r;
                    r = d;
                }
            }
            int[] t = cur;
            cur = nxt;
            nxt = t;
        }
        return 0;
    }
}