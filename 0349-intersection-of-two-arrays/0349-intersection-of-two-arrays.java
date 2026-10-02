import java.util.*;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] seen = new boolean[1001];
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums1) {
            seen[num] = true;
        }

        for (int num : nums2) {
            if (seen[num]) {
                list.add(num);
                seen[num] = false;
            }
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}