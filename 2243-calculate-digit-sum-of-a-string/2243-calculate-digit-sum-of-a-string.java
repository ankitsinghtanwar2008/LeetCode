class Solution {
    public String digitSum(String s, int k) {
        char[] a = s.toCharArray();
        int len = a.length;

        while (len > k) {
            int w = 0;
            for (int i = 0; i < len; i += k) {
                int end = Math.min(i + k, len), sum = 0;
                for (int j = i; j < end; j++) sum += a[j] - '0';
                if (sum >= 100) a[w++] = (char) ('0' + sum / 100);
                if (sum >= 10) a[w++] = (char) ('0' + sum / 10 % 10);
                a[w++] = (char) ('0' + sum % 10);
            }
            len = w;
        }
        return new String(a, 0, len);
    }
}