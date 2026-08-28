import java.util.*;
class Solution {
    public List<List<Integer>> findDisappearedNumbers(
            int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        long start = lower;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < lower) {
                continue;
            }
            if (nums[i] > upper) {
                break;
            }
            if (start < nums[i]) {
                result.add(Arrays.asList(
                    (int) start,
                    nums[i] - 1
                ));
            }
            start = (long) nums[i] + 1;
        }
        if (start <= upper) {
            result.add(Arrays.asList(
                (int) start,
                upper
            ));
        }
        return result;
    }
}