class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = m-1;i>=0;i--){
            while(!s.isEmpty() && s.peek() <= nums2[i]){
                s.pop();
            }

            if(s.isEmpty()){
                map.put(nums2[i],-1);
            }else{
                map.put(nums2[i], s.peek());
            }
            s.push(nums2[i]);
        }
        
        int[] ans = new int[n];
        for(int i=0;i<n;i++){
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}