class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * n], to = new int[2 * n];
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1]; nxt[ec] = head[e[0]]; head[e[0]] = ec++;
            to[ec] = e[0]; nxt[ec] = head[e[1]]; head[e[1]] = ec++;
        }

        int[] par = new int[n], depth = new int[n], tin = new int[n], tout = new int[n];
        int[] order = new int[n], stack = new int[n], ptr = head.clone();
        int[] cs = new int[n], base = new int[n];
        for (int i = 0; i < n; i++) cs[i] = s.charAt(i) - 'a';

        int sp = 0, timer = 0;
        par[0] = -1;
        base[0] = 1 << cs[0];
        order[timer++] = 0;
        stack[sp++] = 0;
        while (sp > 0) {
            int u = stack[sp - 1];
            int e = ptr[u];
            if (e == -1) {
                tout[u] = timer - 1;
                sp--;
                continue;
            }
            ptr[u] = nxt[e];
            int v = to[e];
            if (v == par[u]) continue;
            par[v] = u;
            depth[v] = depth[u] + 1;
            base[v] = base[u] ^ (1 << cs[v]);
            tin[v] = timer;
            order[timer++] = v;
            stack[sp++] = v;
        }

        int[] size = new int[n], heavy = new int[n], hd = new int[n];
        Arrays.fill(size, 1);
        Arrays.fill(heavy, -1);
        for (int i = n - 1; i > 0; i--) {
            int v = order[i], p = par[v];
            size[p] += size[v];
            if (heavy[p] == -1 || size[v] > size[heavy[p]]) heavy[p] = v;
        }
        for (int i = 1; i < n; i++) {
            int v = order[i], p = par[v];
            hd[v] = heavy[p] == v ? hd[p] : v;
        }

        int[] bit = new int[n + 2];
        List<Boolean> res = new ArrayList<>(queries.length);

        for (String q : queries) {
            if (q.charAt(0) == 'u') {
                int i = 7, u = 0;
                while (q.charAt(i) != ' ') u = u * 10 + q.charAt(i++) - '0';
                int c = q.charAt(i + 1) - 'a';
                int old = cs[u];
                if (old != c) {
                    int d = (1 << old) ^ (1 << c);
                    cs[u] = c;
                    for (int x = tin[u] + 1; x <= n; x += x & -x) bit[x] ^= d;
                    for (int x = tout[u] + 2; x <= n; x += x & -x) bit[x] ^= d;
                }
            } else {
                int i = 6, u = 0, v = 0;
                while (q.charAt(i) != ' ') u = u * 10 + q.charAt(i++) - '0';
                for (i++; i < q.length(); i++) v = v * 10 + q.charAt(i) - '0';

                int a = u, b = v;
                while (hd[a] != hd[b]) {
                    if (depth[hd[a]] > depth[hd[b]]) a = par[hd[a]];
                    else b = par[hd[b]];
                }
                int lca = depth[a] < depth[b] ? a : b;

                int mask = base[u] ^ base[v] ^ (1 << cs[lca]);
                for (int x = tin[u] + 1; x > 0; x -= x & -x) mask ^= bit[x];
                for (int x = tin[v] + 1; x > 0; x -= x & -x) mask ^= bit[x];

                res.add((mask & (mask - 1)) == 0);
            }
        }
        return res;
    }
}