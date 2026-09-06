import java.util.*;

class Solution {

    int[] nums;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int pick(int target) {

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                indices.add(i);
            }
        }

        int randomIndex = random.nextInt(indices.size());

        return indices.get(randomIndex);
    }
}