import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void solve(List<String> ans, StringBuilder s, int open, int close, int n) {
        if (s.length() == 2 * n) {
            ans.add(s.toString());
            return;
        }

        if (open < n) {
            s.append('(');
            solve(ans, s, open + 1, close, n);
            s.deleteCharAt(s.length() - 1);
        }

        if (close < open) {
            s.append(')');
            solve(ans, s, open, close + 1, n);
            s.deleteCharAt(s.length() - 1);
        }
    }
}