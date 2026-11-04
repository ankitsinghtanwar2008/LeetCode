class Solution {
    private int[] cnt, vals;
    private long[] sm;
    private int sz, top;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long[] enc = new long[n];
        for (int i = 0; i < n; i++) enc[i] = ((long) (nums[i] + 1_000_000) << 17) | i;
        Arrays.sort(enc);

        int[] rank = new int[n];
        vals = new int[n];
        sz = 0;
        long last = -1;
        for (int t = 0; t < n; t++) {
            long v = enc[t] >>> 17;
            int idx = (int) (enc[t] & ((1 << 17) - 1));
            if (v != last) {
                vals[sz++] = (int) (v - 1_000_000);
                last = v;
            }
            rank[idx] = sz;
        }

        cnt = new int[sz + 1];
        sm = new long[sz + 1];
        top = Integer.highestOneBit(sz);
        int m = (x + 1) >> 1;

        int w = n - x + 1;
        long[] cost = new long[w];
        long ws = 0;
        for (int i = 0; i < x; i++) {
            update(rank[i], 1, nums[i]);
            ws += nums[i];
        }
        cost[0] = calc(ws, x, m);
        for (int i = 1; i < w; i++) {
            update(rank[i - 1], -1, -nums[i - 1]);
            update(rank[i + x - 1], 1, nums[i + x - 1]);
            ws += nums[i + x - 1] - nums[i - 1];
            cost[i] = calc(ws, x, m);
        }

        final long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1], cur = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            cur[0] = INF;
            for (int i = 1; i <= n; i++) {
                long best = cur[i - 1];
                if (i >= x) {
                    long cand = prev[i - x] + cost[i - x];
                    if (cand < best) best = cand;
                }
                cur[i] = best;
            }
            long[] t = prev;
            prev = cur;
            cur = t;
        }
        return prev[n];
    }

    private void update(int p, int dc, long dv) {
        for (; p <= sz; p += p & -p) {
            cnt[p] += dc;
            sm[p] += dv;
        }
    }

    private long calc(long ws, int x, int m) {
        int pos = 0, rem = m, c = 0;
        long s = 0;
        for (int pw = top; pw > 0; pw >>= 1) {
            int np = pos + pw;
            if (np <= sz && cnt[np] < rem) {
                pos = np;
                rem -= cnt[np];
                c += cnt[np];
                s += sm[np];
            }
        }
        long med = vals[pos];
        return med * c - s + (ws - s) - med * (x - c);
    }
}