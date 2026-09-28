import java.util.*;

class Solution {
    HashMap<Integer, Boolean> dp = new HashMap<>();
    int max;
    int target;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        max = maxChoosableInteger;
        target = desiredTotal;

        if (target <= 0) return true;

        int sum = max * (max + 1) / 2;

        if (sum < target) return false;

        return solve(0, 0);
    }

    private boolean solve(int mask, int total) {
        if (dp.containsKey(mask)) {
            return dp.get(mask);
        }

        for (int i = 1; i <= max; i++) {
            int bit = 1 << (i - 1);

            if ((mask & bit) != 0) {
                continue;
            }

            if (total + i >= target || !solve(mask | bit, total + i)) {
                dp.put(mask, true);
                return true;
            }
        }

        dp.put(mask, false);
        return false;
    }
}