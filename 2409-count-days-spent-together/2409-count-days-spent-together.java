class Solution {
    private static final int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start = Math.max(toDay(arriveAlice), toDay(arriveBob));
        int end = Math.min(toDay(leaveAlice), toDay(leaveBob));
        return Math.max(0, end - start + 1);
    }

    private int toDay(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));
        int total = 0;
        for (int i = 0; i < month - 1; i++) {
            total += DAYS[i];
        }
        return total + day;
    }
}