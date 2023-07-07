class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        if(a == b) return answer = a;
        else if(a < b) while(a <= b) answer += a++;
        else while(b <= a) answer += b++;
        return answer;
    }
}