class Solution {
    static int N;
    static int[][] NList;
    static boolean[][] dp;
    public int solution(int[][] triangle) {
        
        N = triangle.length;
        NList = new int[N + 1][(N * 2) + 1];
        dp = new boolean[N + 1][(N * 2) + 1];
        
        for(int i=1, startIdx = 0, endIdx = 1, idx = 0; i <= N; i++){
            int[] target = triangle[idx++];
            for(int j=startIdx, z= 0; j < endIdx; j+=2, z++) NList[i][N + j] = target[z];
            startIdx--;
            endIdx++;
        }
        
        for(int i=1; i <= N; i++) {
			//row 값과 col값을 줘야 함
			find(N, (i * 2) - 1);
		}
        
        int answer = -1;
        for(int i=1; i <= N * 2; i++) answer = Math.max(answer, NList[N][i]);
        
        return answer;
    }
    
    private static int find(int row, int col){
        if(row == 0) return NList[1][N + 1];
		
		if(dp[row][col]) return NList[row][col];
		dp[row][col] = true;
		
		if(col < (N - row) + 1 || col > (N * 2) - (N - row + 1)) return 0;
		
		return NList[row][col] += Math.max(find(row - 1, col - 1), find(row - 1, col + 1));
    }
}