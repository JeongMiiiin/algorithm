import java.util.Arrays;

class Solution {
    public int solution(int k, int m, int[] score) {
        Arrays.sort(score);
        int answer = 0;
        int idx = score.length;
        while(idx - m >= 0){
            answer += score[idx - m] * m;
            idx -= m;
        }
        
        return answer;
    }
}