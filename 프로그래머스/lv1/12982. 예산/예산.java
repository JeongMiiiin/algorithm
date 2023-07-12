import java.util.Arrays;
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        int idx = 0;
        while(budget > 0){
            if(idx >= d.length) break;
            budget -= d[idx++];
            if(budget >= 0) answer++;
        }
        
        return answer;
    }
}