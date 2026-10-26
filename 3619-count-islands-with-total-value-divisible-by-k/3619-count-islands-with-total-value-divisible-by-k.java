class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] dirs = {1, 0, -1, 0, 1};
        int[] stack = new int[m * n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = grid[i][j];
                    grid[i][j] = 0;
                    int top = 0;
                    stack[top++] = i * n + j;

                    while (top > 0) {
                        int cur = stack[--top];
                        int r = cur / n, c = cur % n;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dirs[d], nc = c + dirs[d + 1];
                            if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] > 0) {
                                sum += grid[nr][nc];
                                grid[nr][nc] = 0;
                                stack[top++] = nr * n + nc;
                            }
                        }
                    }

                    if (sum % k == 0) count++;
                }
            }
        }
        return count;
    }
}