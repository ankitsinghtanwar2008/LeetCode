class Solution {
    int[] map = new int[26];
    boolean[] used = new boolean[10];
    boolean[] leading = new boolean[26];

    String[] words;
    String result;
    int cols;

    public boolean isSolvable(String[] words, String result) {
        this.words = words;
        this.result = result;

        for (int i = 0; i < 26; i++) {
            map[i] = -1;
        }

        cols = 0;

        for (String word : words) {
            cols = Math.max(cols, word.length());

            if (word.length() > 1) {
                leading[word.charAt(0) - 'A'] = true;
            }
        }

        cols = Math.max(cols, result.length());

        if (result.length() > 1) {
            leading[result.charAt(0) - 'A'] = true;
        }

        boolean[] seen = new boolean[26];
        int count = 0;

        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!seen[c - 'A']) {
                    seen[c - 'A'] = true;
                    count++;
                }
            }
        }

        for (char c : result.toCharArray()) {
            if (!seen[c - 'A']) {
                seen[c - 'A'] = true;
                count++;
            }
        }

        if (count > 10) {
            return false;
        }

        return dfs(0, 0, 0);
    }

    private boolean dfs(int row, int col, int sum) {
        if (col == cols) {
            return sum == 0;
        }

        if (row == words.length) {

            if (col >= result.length()) {
                return sum == 0;
            }

            int digit = sum % 10;
            int carry = sum / 10;

            char c = result.charAt(result.length() - 1 - col);
            int index = c - 'A';

            if (map[index] != -1) {
                if (map[index] != digit) {
                    return false;
                }

                return dfs(0, col + 1, carry);
            }

            if (used[digit]) {
                return false;
            }

            if (digit == 0 && leading[index]) {
                return false;
            }

            map[index] = digit;
            used[digit] = true;

            if (dfs(0, col + 1, carry)) {
                return true;
            }

            map[index] = -1;
            used[digit] = false;

            return false;
        }

        if (col >= words[row].length()) {
            return dfs(row + 1, col, sum);
        }

        char c = words[row].charAt(words[row].length() - 1 - col);
        int index = c - 'A';

        if (map[index] != -1) {
            return dfs(row + 1, col, sum + map[index]);
        }

        for (int digit = 0; digit <= 9; digit++) {
            if (used[digit]) {
                continue;
            }

            if (digit == 0 && leading[index]) {
                continue;
            }

            map[index] = digit;
            used[digit] = true;

            if (dfs(row + 1, col, sum + digit)) {
                return true;
            }

            map[index] = -1;
            used[digit] = false;
        }

        return false;
    }
}