/*
2 -> 2
3 -> 3
4 -> 5
5 -> 8
*/

class Solution {
    public int solution(int n) {
        long[] dp =  new long[n + 2];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i <= n; i++) dp[i] = dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007;
        return (int) dp[n] % 1000000007;
    }
}