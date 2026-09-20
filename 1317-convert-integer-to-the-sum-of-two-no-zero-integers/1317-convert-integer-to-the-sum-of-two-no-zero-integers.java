class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;

            if (isNoZero(a) && isNoZero(b)) {
                return new int[]{a, b};
            }
        }

        return new int[]{-1, -1};
    }

    private boolean isNoZero(int n) {
        while (n > 0) {
            if (n % 10 == 0) {
                return false;
            }
            n /= 10;
        }

        return true;
    }
}