import java.util.*; 
class Solution {
   public double helper(double x, int n) {
       if (n == 0) return 1; 
       double temp = helper(x, n / 2); 
       temp *= temp; 
       if (n % 2 != 0) temp *= x; 
       return temp;
   }

   public double myPow(double x, int n) {
       if (n < 0) {
           long val = (long) n; 
           double ans = helper(x, (int) -val); 
           return 1.0 / ans; 
       }
       return helper(x, n);
   }

   public static void main(String[] args) {
       Solution sol = new Solution();
       double x = 2.0; 
       int n = 10;     
       System.out.println(sol.myPow(x, n)); 
   }
}
