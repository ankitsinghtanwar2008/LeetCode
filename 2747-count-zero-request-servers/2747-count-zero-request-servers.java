class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length, q = queries.length;

        long[] lg = new long[m];
        for (int i = 0; i < m; i++) lg[i] = ((long) logs[i][1] << 17) | logs[i][0];
        Arrays.sort(lg);

        long[] qs = new long[q];
        for (int i = 0; i < q; i++) qs[i] = ((long) queries[i] << 17) | i;
        Arrays.sort(qs);

        int[] cnt = new int[n + 1];
        int[] res = new int[q];
        int active = 0, l = 0, r = 0;

        for (int t = 0; t < q; t++) {
            int qt = (int) (qs[t] >>> 17);
            int idx = (int) (qs[t] & 0x1FFFF);

            while (r < m && (lg[r] >>> 17) <= qt) {
                if (cnt[(int) (lg[r] & 0x1FFFF)]++ == 0) active++;
                r++;
            }
            int lo = qt - x;
            while (l < r && (lg[l] >>> 17) < lo) {
                if (--cnt[(int) (lg[l] & 0x1FFFF)] == 0) active--;
                l++;
            }
            res[idx] = n - active;
        }
        return res;
    }
}