import java.util.Arrays;

class Solution {
    public int[] solution(int n) {
        
        int max = 0;
        for(int i=1; i <= n; i++) max += i;
        
        int[][] map = new int[n][n];
        
        //하 우 좌상
        int[] dr1 = {1, 0, -1};
        int[] dc1 = {0, 1, -1};
        
        int curR = -1, curC = 0, d = 0, cnt = 0;
        while(cnt++ < max){
            curR += dr1[d];
            curC += dc1[d];
            if(curR >= n || curC >= n || map[curR][curC] > 0){
                curR -= dr1[d];
                curC -= dc1[d];
                if(++d > 2) d = 0;
                curR += dr1[d];
                curC += dc1[d];
            }
            map[curR][curC] = cnt;
        }
        
        int[] answer = new int[max];
        
        int answerIdx = 0; 
        for(int i=0; i < n; i++){
            for(int j=0; j < n; j++){
                if(map[i][j] == 0) break;
                answer[answerIdx++] = map[i][j];
            }
        }
        
        
        return answer;
    }
}