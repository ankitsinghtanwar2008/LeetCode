class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        char[] sc = s.toCharArray();

        int[] ptr = new int[n], nxt = new int[n];
        Arrays.fill(ptr, -1);
        for (int i = 1; i < n; i++) {
            nxt[i] = ptr[parent[i]];
            ptr[parent[i]] = i;
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] saved = new int[n], np = new int[n], order = new int[n], stack = new int[n];

        int sp = 0, cnt = 0;
        stack[sp++] = 0;
        order[cnt++] = 0;
        saved[0] = -1;
        last[sc[0] - 'a'] = 0;

        while (sp > 0) {
            int u = stack[sp - 1];
            int v = ptr[u];
            if (v == -1) {
                last[sc[u] - 'a'] = saved[u];
                sp--;
                continue;
            }
            ptr[u] = nxt[v];
            int c = sc[v] - 'a';
            np[v] = last[c] != -1 ? last[c] : u;
            saved[v] = last[c];
            last[c] = v;
            order[cnt++] = v;
            stack[sp++] = v;
        }

        int[] res = new int[n];
        Arrays.fill(res, 1);
        for (int i = n - 1; i > 0; i--) {
            int v = order[i];
            res[np[v]] += res[v];
        }
        return res;
    }
}