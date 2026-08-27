class Solution {
   public boolean isPalindrome(String s) {
       int n = s.length();
       int low = 0;
       int high = n - 1;

       while (low < high) {
           if (!Character.isLetterOrDigit(s.charAt(low))) {
               low++;
           } else if (!Character.isLetterOrDigit(s.charAt(high))) {
               high--;
           } else if (Character.toLowerCase(s.charAt(low)) == Character.toLowerCase(s.charAt(high))) {
               low++;
               high--;
           } else {
               return false;
           }
       }
       return true;
   }
}

