import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    class Road {
        int r, c, d, total;
        public Road(int r, int c, int d, int total){
            this.r = r;
            this.c = c;
            this.d = d;
            this.total = total;
        }
    }
    
    static int n;
    public int solution(int[][] board) {
        n = board.length;
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        int[][][] dp = new int[n][n][4];
        //초기화
        for(int i=0; i < n; i++) for(int j=0; j < n; j++) Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        Queue<Road> q = new LinkedList<>();
        //시작값은 0으로
        for(int i=0; i < 4; i++) dp[0][0][i] = 0;
        //우 하로 출발
        q.add(new Road(0, 0, 1, 0));
        q.add(new Road(0, 0, 2, 0));
        Road cur;
        int nr, nc;
        while(!q.isEmpty()){
            cur = q.poll();
            
            for(int nd=0; nd < 4; nd++){
                int plus = 600;
                
                if(cur.d == nd) plus = 100;
                nr = cur.r + dr1[nd];
                nc = cur.c + dc1[nd];    
                
                //벗어나거나 벽이 있거나 이미 해당방향으로 들어온 값보다 작을 때 패스
                if(outMap(nr, nc) || board[nr][nc] == 1 || dp[nr][nc][nd] < cur.total + plus) continue;
                dp[nr][nc][nd] = cur.total + plus;
                q.add(new Road(nr, nc, nd, cur.total + plus));
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i=0; i < 4; i++) answer = Math.min(answer, dp[n - 1][n - 1][i]);
        
        return answer;
    }
    
    private static boolean outMap(int r, int c){
        return (r < 0 || r >= n || c < 0 || c >= n);
    }
}