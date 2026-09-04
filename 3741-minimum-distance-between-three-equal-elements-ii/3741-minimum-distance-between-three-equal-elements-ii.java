import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            
            int value = nums[i];

            if (!map.containsKey(value)) {
                map.put(value, new ArrayList<>());
            }

            ArrayList<Integer> list = map.get(value);
            list.add(i);

            if (list.size() >= 3) {
                int a = list.get(list.size() - 3);
                int b = list.get(list.size() - 2);
                int c = list.get(list.size() - 1);

                int distance = Math.abs(a - b)
                             + Math.abs(b - c)
                             + Math.abs(c - a);

                ans = Math.min(ans, distance);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}