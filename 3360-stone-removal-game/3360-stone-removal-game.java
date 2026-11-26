class Solution {
    private static final long WIN =
        ((1L << 19) - (1L << 10)) |
        ((1L << 34) - (1L << 27)) |
        ((1L << 45) - (1L << 40)) |
        ((1L << 51) - (1L << 49));

    public boolean canAliceWin(int n) {
        return ((WIN >>> n) & 1L) != 0;
    }
}