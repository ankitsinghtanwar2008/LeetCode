class Solution {
    public int maxSelectedElements(int[] nums) {
        int lo = Integer.MAX_VALUE, hi = 0;
        for (int v : nums) {
            if (v < lo) lo = v;
            if (v > hi) hi = v;
        }

        byte[] cnt = new byte[hi - lo + 1];
        for (int v : nums) {
            if (cnt[v - lo] < 2) cnt[v - lo]++;
        }

        int ans = 0, prevFinal = 0, partial = 0;
        int range = hi - lo;
        for (int i = 0; i <= range + 1; i++) {
            int c = i <= range ? cnt[i] : 0;
            int cur, next;
            if (c == 0) {
                cur = partial;
                next = 0;
            } else {
                cur = prevFinal + 1;
                next = c == 2 ? cur + 1 : partial + 1;
            }
            if (cur > ans) ans = cur;
            prevFinal = cur;
            partial = next;
        }
        return ans;
    }
}