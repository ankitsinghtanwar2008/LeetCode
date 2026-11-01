class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;
        for (int x : nums) {
            sum += x;
            if (x < min) min = x;
        }
        return (int) (sum - (long) nums.length * min);
    }
}