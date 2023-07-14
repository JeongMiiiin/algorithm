class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(n >= a){
            int target = (n / a) * b;
            answer += target;
            n %= a;
            n += target;
        }
        
        return answer;
    }
}