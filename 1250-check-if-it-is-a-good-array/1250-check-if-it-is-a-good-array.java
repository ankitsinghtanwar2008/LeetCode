class Solution {
    public boolean isGoodArray(int[] nums) {
        int gcd = 0;

        for (int num : nums) {
            gcd = findGcd(gcd, num);

            if (gcd == 1) {
                return true;
            }
        }

        return false;
    }

    private int findGcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}