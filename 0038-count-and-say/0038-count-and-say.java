class Solution {
    public String countAndSay(int n) {
        String s = "1";

        for (int i = 1; i < n; i++) {
            StringBuilder next = new StringBuilder();

            int j = 0;

            while (j < s.length()) {
                int count = 1;

                while (j + count < s.length() && s.charAt(j) == s.charAt(j + count)) {
                    count++;
                }

                next.append(count);
                next.append(s.charAt(j));

                j += count;
            }

            s = next.toString();
        }

        return s;
    }
}