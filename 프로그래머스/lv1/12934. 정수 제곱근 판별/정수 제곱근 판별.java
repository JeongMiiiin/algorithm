class Solution {
    public long solution(long n) {
        double remain = Math.sqrt(n);
        if(remain != (int) remain) return -1; //정수가 아닌 경우
        return (long) Math.pow(remain + 1, 2); //1더해서 제곱
    }
}