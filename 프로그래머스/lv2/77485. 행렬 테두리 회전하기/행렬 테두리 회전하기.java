import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        //맵 세팅
        int[][] map = new int[rows + 1][columns + 1];
        int val = 1;
        for(int i=1; i <= rows; i++) for(int j=1; j <= columns; j++) map[i][j] = val++;
        
        int[] answer = new int[queries.length];
        
        for(int i=0; i < queries.length; i++) answer[i] = rotateMap(map, queries[i]);
        return answer;
    }
    
    private static int rotateMap(int[][] map, int[] target){
        //우 하 좌 상
        int[] dr1 = {0, 1, 0, -1};
        int[] dc1 = {1, 0, -1, 0};
        
        int startR = target[0];
        int startC = target[1];
        int endR = target[2];
        int endC = target[3];
        int d = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(map[startR][startC]);
        int ans = map[startR][startC];
        
        int curR = startR;
        int curC = startC;
        while(d < 4){
            curR += dr1[d];
            curC += dc1[d];
            //벗어난 경우
            if(curR < startR || curR > endR || curC < startC || curC > endC){
                curR -= dr1[d];
                curC -= dc1[d];
                if(++d == 4) break; //모두 순회한 경우
                curR += dr1[d];
                curC += dc1[d];
            }
            ans = Math.min(ans, map[curR][curC]);
            q.add(map[curR][curC]);
            map[curR][curC] = q.poll();
        }
        
        return ans;
        
    }
}