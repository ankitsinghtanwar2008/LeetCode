import java.util.*;

class Solution {
    public String nearestPalindromic(String n) {
        int len = n.length();
        long num = Long.parseLong(n);
        HashSet<Long> set = new HashSet<>();
        set.add((long) Math.pow(10, len - 1) - 1);
        set.add((long) Math.pow(10, len) + 1);
        int half = (len + 1) / 2;
        long prefix = Long.parseLong(n.substring(0, half));
        for (long x = prefix - 1; x <= prefix + 1; x++) {
            String left = String.valueOf(x);
            StringBuilder sb = new StringBuilder(left);

            int start = len % 2 == 0
                    ? left.length() - 1
                    : left.length() - 2;

            for (int i = start; i >= 0; i--) {
                sb.append(left.charAt(i));
            }

            set.add(Long.parseLong(sb.toString()));
        }

        set.remove(num);

        long ans = -1;
        long minDiff = Long.MAX_VALUE;

        for (long x : set) {
            long diff = Math.abs(x - num);

            if (diff < minDiff || (diff == minDiff && x < ans)) {
                minDiff = diff;
                ans = x;
            }
        }

        return String.valueOf(ans);
    }
}