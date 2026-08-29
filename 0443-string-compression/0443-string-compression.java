class Solution {
    public int compress(char[] chars) {

        int n = chars.length;
        int write = 0;
        int i = 0;

        while (i < n) {

            char ch = chars[i];
            int count = 0;

            while (i < n && chars[i] == ch) {
                count++;
                i++;
            }

            chars[write] = ch;
            write++;

            if (count > 1) {
                String num = String.valueOf(count);

                for (int j = 0; j < num.length(); j++) {
                    chars[write] = num.charAt(j);
                    write++;
                }
            }
        }

        return write;
    }
}