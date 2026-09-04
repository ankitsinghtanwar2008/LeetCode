import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;
        for (ArrayList<Integer> list : map.values()) {

            for (int i = 0; i + 2 < list.size(); i++) {

                int first = list.get(i);
                int third = list.get(i + 2);

                int distance = 2 * (third - first);

                ans = Math.min(ans, distance);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}