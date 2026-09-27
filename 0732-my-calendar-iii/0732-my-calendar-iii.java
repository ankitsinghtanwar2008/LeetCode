class MyCalendarThree {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public MyCalendarThree() {
    }

    public int book(int startTime, int endTime) {
        map.put(startTime, map.getOrDefault(startTime, 0) + 1);
        map.put(endTime, map.getOrDefault(endTime, 0) - 1);

        int count = 0;
        int ans = 0;

        for (int x : map.values()) {
            count += x;
            ans = Math.max(ans, count);
        }

        return ans;
    }
}