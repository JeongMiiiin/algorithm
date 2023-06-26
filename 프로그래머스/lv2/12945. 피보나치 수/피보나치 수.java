class Solution {
    static int[] dp;
    public int solution(int n) {
        dp = new int[n + 1];
        dp[1] = 1;
        return pibonacci(n);
    }
    
    private static int pibonacci(int n){
        if(n < 2) return dp[n];
        if(dp[n] > 0) return dp[n];
        return dp[n] = (pibonacci(n - 1) + pibonacci(n - 2)) % 1234567;
    }
}