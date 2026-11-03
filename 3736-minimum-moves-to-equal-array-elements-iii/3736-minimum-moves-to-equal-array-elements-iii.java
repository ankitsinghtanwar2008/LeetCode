class Solution {
    public int minMoves(int[] nums) {
        int max = 0, sum = 0;
        for (int x : nums) {
            sum += x;
            if (x > max) max = x;
        }
        return max * nums.length - sum;
    }
}