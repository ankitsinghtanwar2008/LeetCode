class Solution {
    public int maxLength(int[] nums) {
        int[] mk = {0, 0, 1, 2, 1, 4, 3, 8, 1, 2, 5};
        int l = 0, used = 0, ans = 2;
        for (int r = 0; r < nums.length; r++) {
            int m = mk[nums[r]];
            while ((used & m) != 0) used ^= mk[nums[l++]];
            used |= m;
            if (r - l + 1 > ans) ans = r - l + 1;
        }
        return ans;
    }
}