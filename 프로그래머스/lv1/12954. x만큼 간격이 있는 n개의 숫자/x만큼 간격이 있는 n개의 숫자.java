class Solution {
    public long[] solution(int x, int n) {
        long X = new Long(x);
        long[] answer = new long[n];
        for(int i=0; i < n; i++) answer[i] = X + (X * i);
        return answer;
    }
}