/*
1 -> 0
2 -> 3
3 -> 0
4 -> 11
5 -> 0

*/

class Solution {
    public int solution(int n) {
        int answer = 0;
        if(n % 2 == 1) return answer;
        long[] dp = new long[n + 1];
        dp[2] = 3;
        long sum = 0;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * 3 + (sum * 2 + 2)) % 1000000007L;
            sum += dp[i - 2] % 1000000007L;
        }

        return (int) dp[n];
    }
}