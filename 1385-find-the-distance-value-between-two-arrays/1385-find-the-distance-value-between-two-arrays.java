class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int[] pre = new int[2002];
        for (int y : arr2) pre[y + 1001] = 1;
        for (int i = 1; i < 2002; i++) pre[i] += pre[i - 1];

        int count = 0;
        for (int x : arr1) {
            int lo = Math.max(0, x + 1000 - d);
            int hi = Math.min(2000, x + 1000 + d);
            if (pre[hi + 1] - pre[lo] == 0) count++;
        }
        return count;
    }
}