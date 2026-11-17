class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] c = s.toCharArray();
        int maxLen = n - 2 * (k - 1);
        final int INF = 1_000_000;

        int[][] divs = new int[maxLen + 1][];
        for (int L = 2; L <= maxLen; L++) {
            int cnt = 0;
            for (int d = 1; d < L; d++) if (L % d == 0) cnt++;
            divs[L] = new int[cnt];
            int idx = 0;
            for (int d = 1; d < L; d++) if (L % d == 0) divs[L][idx++] = d;
        }

        int[][] cost = new int[n][maxLen + 1];
        for (int L = 2; L <= maxLen; L++) {
            int[] ds = divs[L];
            for (int i = 0; i + L <= n; i++) {
                int best = INF;
                for (int d : ds) {
                    int sum = 0;
                    for (int g = 0; g < d; g++) {
                        int a = i + g, b = i + g + L - d;
                        while (a < b) {
                            if (c[a] != c[b]) sum++;
                            a += d;
                            b -= d;
                        }
                        if (sum >= best) break;
                    }
                    if (sum < best) best = sum;
                    if (best == 0) break;
                }
                cost[i][L] = best;
            }
        }

        int[] prev = new int[n + 1], cur = new int[n + 1];
        Arrays.fill(prev, INF);
        prev[0] = 0;
        for (int p = 1; p <= k; p++) {
            Arrays.fill(cur, INF);
            int hi = n - 2 * (k - p);
            for (int i = 2 * p; i <= hi; i++) {
                int best = INF;
                int maxL = Math.min(maxLen, i - 2 * (p - 1));
                for (int L = 2; L <= maxL; L++) {
                    int v = prev[i - L] + cost[i - L][L];
                    if (v < best) best = v;
                }
                cur[i] = best;
            }
            int[] t = prev;
            prev = cur;
            cur = t;
        }
        return prev[n];
    }
}