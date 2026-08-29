class Solution {
    public int compress(char[] chars) {

        int newChar = 0;

        for (int i = 0; i < chars.length; i++) {

            int count = 1;

            if (i < chars.length - 1 && chars[i] == chars[i + 1]) {

                while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                    count++;
                    i++;
                }
            }

            chars[newChar] = chars[i];
            newChar++;

            if (count > 1) {

                String num = String.valueOf(count);

                for (int j = 0; j < num.length(); j++) {
                    chars[newChar] = num.charAt(j);
                    newChar++;
                }
            }
        }

        return newChar;
    }
}