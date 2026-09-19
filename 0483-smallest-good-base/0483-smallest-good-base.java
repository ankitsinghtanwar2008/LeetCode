class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        int max = 63 - Long.numberOfLeadingZeros(num);
        for (int m = max; m >= 1; m--) {
            long left = 2;
            long right = (long) Math.pow(num, 1.0 / m) + 1;
            while (left <= right) {
                long k = left + (right - left) / 2;
                long sum = 1;
                long power = 1;
                for (int i = 0; i < m; i++) {
                    if (power > (num - 1) / k) {
                        sum = num + 1;
                        break;
                    }
                    power *= k;
                    sum += power;

                    if (sum > num) {
                        break;
                    }
                }
                if (sum == num) {
                    return String.valueOf(k);
                }
                if (sum < num) {
                    left = k + 1;
                } else {
                    right = k - 1;
                }
            }
        }

        return String.valueOf(num - 1);
    }
}