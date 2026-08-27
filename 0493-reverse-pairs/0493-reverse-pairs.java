class Solution {
   public void merge(int[] arr, int low, int mid, int high) {
       int n1 = mid - low + 1;
       int n2 = high - mid;
       int[] left = new int[n1];
       int[] right = new int[n2];
      
       for (int i = 0; i < n1; i++) {
           left[i] = arr[low + i];
       }
       for (int i = 0; i < n2; i++) {
           right[i] = arr[mid + 1 + i];
       }
      
       int k = low, i = 0, j = 0;
       while (i < n1 && j < n2) {
           if (left[i] <= right[j]) {
               arr[k++] = left[i++];
           } else {
               arr[k++] = right[j++];
           }
       }
      
       while (i < n1) {
           arr[k++] = left[i++];
       }
       while (j < n2) {
           arr[k++] = right[j++];
       }
   }
  
   public int countPairs(int[] arr, int low, int mid, int high) {
       int right = mid + 1;
       int count = 0;
       for (int i = low; i <= mid; i++) {
           while (right <= high && (long) arr[i] > 2L * arr[right]) {
               right++;
           }
           count += (right - (mid + 1));
       }
       return count;
   }
  
   public int mergeSort(int[] nums, int low, int high) {
       int count = 0;
       if (low < high) {
           int mid = (low + high) / 2;
           count += mergeSort(nums, low, mid);
           count += mergeSort(nums, mid + 1, high);
           count += countPairs(nums, low, mid, high);
           merge(nums, low, mid, high);
       }
       return count;
   }
  
   public int reversePairs(int[] nums) {
       int low = 0;
       int high = nums.length - 1;
       return mergeSort(nums, low, high);
   }
}


