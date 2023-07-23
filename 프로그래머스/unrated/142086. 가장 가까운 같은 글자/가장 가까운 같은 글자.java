import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        int[] dp = new int[26];
        Arrays.fill(dp, -1);
        int[] answer = new int[s.length()];
        for(int i=0; i < s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(dp[idx] == -1) answer[i] = -1;
            else answer[i] = i - dp[idx];
            dp[idx] = i;
        }
        
        return answer;
    }
}