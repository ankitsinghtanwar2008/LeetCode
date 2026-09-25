class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] a = new int[256];
        int[] b = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);

            if (a[x] != b[y]) {
                return false;
            }

            a[x] = i + 1;
            b[y] = i + 1;
        }

        return true;
    }
}