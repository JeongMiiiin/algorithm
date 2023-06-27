class Solution {
    public int solution(int [][]board){
        int R = board.length;
        int C = board[0].length;
        int[][] map = new int[R + 1][C + 1];

        int answer = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                //빈칸이면 패스
                if(board[i-1][j-1] == 0) continue;
                
                map[i][j] = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]) + 1;

                answer = Math.max(answer, map[i][j]);
            }
        }

        return answer * answer;
    }
}