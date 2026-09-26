class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[] a = score.clone();
        Arrays.sort(a);

        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int rank = n - i;

            if (rank == 1)
                map.put(a[i], "Gold Medal");
            else if (rank == 2)
                map.put(a[i], "Silver Medal");
            else if (rank == 3)
                map.put(a[i], "Bronze Medal");
            else
                map.put(a[i], String.valueOf(rank));
        }

        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            ans[i] = map.get(score[i]);
        }

        return ans;
    }
}