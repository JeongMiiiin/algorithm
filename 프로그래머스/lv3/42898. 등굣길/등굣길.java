class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        //물웅덩이 세팅
        for (int[] puddle : puddles) dp[puddle[1]-1][puddle[0]-1] = -1;
        //시작값 세팅
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] != -1) {
                    //위에 물웅덩이가 없는 경우
                    if (i >= 1 && dp[i - 1][j] != -1) dp[i][j] += dp[i - 1][j];
                    //왼쪽에 물웅덩이가 없는 경우
                    if (j >= 1 && dp[i][j - 1] != -1) dp[i][j] += dp[i][j - 1];
                    //나머지 처리
                    dp[i][j] %= 1000000007;
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}