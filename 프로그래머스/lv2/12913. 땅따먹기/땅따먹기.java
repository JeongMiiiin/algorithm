class Solution {
    static int len;
    static int[][] dp, inputs;
    int solution(int[][] land) {
        len = land.length;
        
        
        dp = new int[len][4];
        //처음 값 채워놓기
        for(int i=0; i < 4; i++) dp[0][i] = land[0][i];
        
        for(int i=1; i < len; i++){
            for(int j=0; j < 4; j++){
                for(int k=0; k < 4; k++){
                    //다른 열에 해당할 때만
                    if(j != k) dp[i][j] = Math.max(dp[i - 1][k] + land[i][j], dp[i][j]);
                }
            }
        }
        
        //마지막 행 최대값 구하기
        int answer = 0;
        for(int i=0; i < 4; i++) answer = Math.max(answer, dp[len - 1][i]);
            
        return answer;
    }
}