import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    long[][] comb = new long[1001][1001];

    public int numOfWays(int[] nums) {
        int n = nums.length;

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        return (int) ((solve(list) - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) return 1;

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }

        long ways = comb[n - 1][left.size()];
        ways = ways * solve(left) % MOD;
        ways = ways * solve(right) % MOD;

        return ways;
    }
}