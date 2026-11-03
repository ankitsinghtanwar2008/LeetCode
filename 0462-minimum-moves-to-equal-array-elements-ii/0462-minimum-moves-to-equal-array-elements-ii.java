class Solution {
    public int minMoves2(int[] nums) {
        int med = select(nums, nums.length / 2);
        long sum = 0;
        for (int x : nums) sum += Math.abs((long) x - med);
        return (int) sum;
    }

    private int select(int[] a, int k) {
        int lo = 0, hi = a.length - 1;
        int seed = 0x9E3779B9;
        while (lo < hi) {
            seed ^= seed << 13;
            seed ^= seed >>> 17;
            seed ^= seed << 5;
            int pivot = a[lo + (seed & 0x7fffffff) % (hi - lo + 1)];

            int lt = lo, i = lo, gt = hi;
            while (i <= gt) {
                int v = a[i];
                if (v < pivot) {
                    a[i++] = a[lt];
                    a[lt++] = v;
                } else if (v > pivot) {
                    a[i] = a[gt];
                    a[gt--] = v;
                } else {
                    i++;
                }
            }

            if (k < lt) hi = lt - 1;
            else if (k > gt) lo = gt + 1;
            else return pivot;
        }
        return a[k];
    }
}