class Solution {
    public int numOfSubarrays(int[] arr) {
        long ans = 0;
        int even = 1, odd = 0, parity = 0;
        for (int x : arr) {
            parity ^= x & 1;
            if (parity == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }
        }
        return (int) (ans % 1_000_000_007);
    }
}