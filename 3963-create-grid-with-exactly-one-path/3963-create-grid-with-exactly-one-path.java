class Solution {
    public String[] createGrid(int m, int n) {
        char[] top = new char[n];
        Arrays.fill(top, '.');
        String first = new String(top);

        String[] grid = new String[m];
        grid[0] = first;
        if (m > 1) {
            Arrays.fill(top, '#');
            top[n - 1] = '.';
            String rest = new String(top);
            for (int i = 1; i < m; i++) grid[i] = rest;
        }
        return grid;
    }
}