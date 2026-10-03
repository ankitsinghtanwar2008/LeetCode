import java.util.*;

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        List<String> ans = new ArrayList<>();

        for (String word : words) {
            set.remove(word);

            if (canMake(word, set)) {
                ans.add(word);
            }

            set.add(word);
        }

        return ans;
    }

    private boolean canMake(String word, Set<String> set) {
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) {
                continue;
            }

            for (int j = i + 1; j <= n; j++) {
                if (set.contains(word.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }

        return dp[n];
    }
}