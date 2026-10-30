class Solution {
    private long[] heap = new long[1 << 12];
    private int size = 0;

    private void push(long x) {
        if (size == heap.length) heap = Arrays.copyOf(heap, size << 1);
        int i = size++;
        while (i > 0) {
            int p = (i - 1) >> 1;
            if (heap[p] <= x) break;
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = x;
    }

    private long pop() {
        long top = heap[0];
        long x = heap[--size];
        if (size > 0) {
            int i = 0;
            while (true) {
                int c = 2 * i + 1;
                if (c >= size) break;
                if (c + 1 < size && heap[c + 1] < heap[c]) c++;
                if (heap[c] >= x) break;
                heap[i] = heap[c];
                i = c;
            }
            heap[i] = x;
        }
        return top;
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        int m = edges.length;
        int[] start = new int[n + 1];
        for (int[] e : edges) start[e[0] + 1]++;
        for (int i = 0; i < n; i++) start[i + 1] += start[i];
        int[] adjTo = new int[m], adjW = new int[m];
        int[] pos = Arrays.copyOf(start, n);
        for (int[] e : edges) {
            int p = pos[e[0]]++;
            adjTo[p] = e[1];
            adjW[p] = e[2];
        }

        char[] lb = labels.toCharArray();
        int K = k + 1;
        int[] dist = new int[n * K];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] minRun = new int[n];
        Arrays.fill(minRun, Integer.MAX_VALUE);

        dist[K + 1 - K] = 0;
        push((0L << 22) | 1);

        final int MASK = (1 << 22) - 1;
        while (size > 0) {
            long top = pop();
            int d = (int) (top >>> 22);
            int low = (int) (top & MASK);
            int v = low >>> 6, r = low & 63;

            if (d > dist[v * K + r]) continue;
            if (v == n - 1) return d;
            if (minRun[v] <= r) continue;
            minRun[v] = r;

            for (int e = start[v]; e < start[v + 1]; e++) {
                int w = adjTo[e];
                int nr = lb[w] == lb[v] ? r + 1 : 1;
                if (nr > k || minRun[w] <= nr) continue;
                int nd = d + adjW[e];
                int idx = w * K + nr;
                if (nd < dist[idx]) {
                    dist[idx] = nd;
                    push(((long) nd << 22) | (w << 6) | nr);
                }
            }
        }
        return -1;
    }
}