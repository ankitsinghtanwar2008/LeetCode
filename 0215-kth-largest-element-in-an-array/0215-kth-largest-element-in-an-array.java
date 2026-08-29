class Solution {
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int pivot = nums[left + (int)(Math.random() * (right - left + 1))];

            int low = left;
            int mid = left;
            int high = right;

            while (mid <= high) {
                if (nums[mid] < pivot) {
                    swap(nums, low++, mid++);
                } else if (nums[mid] > pivot) {
                    swap(nums, mid, high--);
                } else {
                    mid++;
                }
            }

            if (target < low) {
                right = low - 1;
            } else if (target > high) {
                left = high + 1;
            } else {
                return nums[target];
            }
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}