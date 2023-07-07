class Solution {
    public long solution(long n) {
        double remain = Math.sqrt(n);
        int rm = (int) remain;
        if(remain != rm) return -1;
        return (long) Math.pow(remain + 1, 2);
    }
}