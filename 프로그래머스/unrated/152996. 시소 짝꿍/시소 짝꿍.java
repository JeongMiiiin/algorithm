import java.util.*; 

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        
        for(int i=0; i < weights.length; i++){
            int cur = weights[i];
            for(int j= i + 1; j < weights.length; j++){
                if(cur == weights[j]) answer++; //둘이 같을 때
                else if(cur * 2 == weights[j]) answer++;
                else if(cur * 3 == weights[j] * 2) answer++;
                else if(cur * 4 == weights[j] * 3) answer++;
            }
        }
        
        return answer;
    }
}