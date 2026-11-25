class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int maxV = 0;
        for (int v : nums) if (v > maxV) maxV = v;

        int[] sa = new int[n], rnk = new int[n], tmp = new int[n];
        int[] cnt = new int[Math.max(n, maxV) + 1];

        for (int i = 0; i < n; i++) cnt[nums[i]]++;
        for (int i = 1; i <= maxV; i++) cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--cnt[nums[i]]] = i;

        int classes = 1;
        rnk[sa[0]] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[sa[i]] != nums[sa[i - 1]]) classes++;
            rnk[sa[i]] = classes - 1;
        }

        for (int k = 1; k < n && classes < n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) tmp[p++] = i;
            for (int j = 0; j < n; j++) if (sa[j] >= k) tmp[p++] = sa[j] - k;

            Arrays.fill(cnt, 0, classes, 0);
            for (int i = 0; i < n; i++) cnt[rnk[i]]++;
            for (int i = 1; i < classes; i++) cnt[i] += cnt[i - 1];
            for (int j = n - 1; j >= 0; j--) sa[--cnt[rnk[tmp[j]]]] = tmp[j];

            tmp[sa[0]] = 0;
            classes = 1;
            for (int j = 1; j < n; j++) {
                int a = sa[j], b = sa[j - 1];
                int a2 = a + k < n ? rnk[a + k] : -1;
                int b2 = b + k < n ? rnk[b + k] : -1;
                if (rnk[a] != rnk[b] || a2 != b2) classes++;
                tmp[a] = classes - 1;
            }
            int[] t = rnk;
            rnk = tmp;
            tmp = t;
        }

        int[] lcp = new int[n + 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            int r = rnk[i];
            if (r > 0) {
                int j = sa[r - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) h++;
                lcp[r] = h;
                if (h > 0) h--;
            } else {
                h = 0;
            }
        }

        int ans = n;
        for (int r = 0; r < n; r++) {
            int need = Math.max(lcp[r], lcp[r + 1]) + 1;
            if (need <= n - sa[r] && need < ans) ans = need;
        }
        return ans;
    }
}