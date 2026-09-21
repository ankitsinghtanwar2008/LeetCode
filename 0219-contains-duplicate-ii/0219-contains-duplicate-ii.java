import java.util.*;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int lastIndex = map.get(nums[i]);

                if (i - lastIndex <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}