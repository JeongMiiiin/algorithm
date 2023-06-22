import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(String[] grid) {
        int row = grid.length;
        int col = grid[0].length();
        List<Integer> temp = new ArrayList<>();
        //상 우 하 좌 방문했는지 표시
        boolean[][][] visited = new boolean[row][col][4];
        char[][] map = new char[row][col];
        for(int i=0; i < row; i++){
            char[] cList = grid[i].toCharArray();
            for(int j=0; j < col; j++) map[i][j] = cList[j];
        }
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i < row; i++){
            for(int j=0; j < col; j++){
               //상 우 하 좌 확인
               for(int d=0; d < 4; d++){
                   if(visited[i][j][d]) continue;
                   q.clear();
                   visited[i][j][d] = true;
                   q.add(new int[]{i, j, d});
                   
                   int cnt = 0;
                   int nr, nc;
                   while(!q.isEmpty()){
                       int[] cur = q.poll();
                       //사이클이 완성되었을 때
                       if(cur[0] == i && cur[1] == j && cur[2] == d && cnt > 0) break;
                       
                       nr = cur[0] + dr1[cur[2]];
                       if(nr < 0) nr = row - 1;
                       else if(nr >= row) nr = 0;
                       nc = cur[1] + dc1[cur[2]];
                       if(nc < 0) nc = col - 1;
                       else if(nc >= col) nc = 0;
                       
                       int nd = cur[2];
                       if(map[nr][nc] == 'L'){
                           if(--nd == -1) nd = 3;
                       } else if(map[nr][nc] == 'R'){
                            if(++nd == 4) nd = 0;   
                       }
                       
                       visited[nr][nc][nd] = true;
                       q.add(new int[]{nr, nc, nd});
                       
                       cnt++;
                   }
                   
                   temp.add(cnt);
               }
            }
        }
        
        Collections.sort(temp);
        int[] answer = new int[temp.size()];
        for(int i=0; i < temp.size(); i++) answer[i] = temp.get(i);
        return answer;
    }
}