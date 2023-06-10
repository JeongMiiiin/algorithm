import java.util.*;
import java.awt.Point;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length, m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        int answer = 1;
        boolean flag = false;
        outer : while(!q.isEmpty()){
            answer++;
            int size = q.size();
            Point cur;
            int nr, nc;
            for(int i=0; i < size; i++){
                cur = q.poll();
                for(int d=0; d < 4; d++){
                    nr = cur.x + dr1[d];
                    nc = cur.y + dc1[d];
                    
                    if(nr == n - 1 && nc == m - 1){
                        flag = true;
                        break outer;        
                    }
                    
                    //맵에서 벗어나거나 벽에 막힌 경우, 이미 방문한 곳인 경우 패스
                    if((nr < 0 || nr >= n || nc < 0 || nc >= m) || maps[nr][nc] == 0 || visited[nr][nc]) continue;
                    
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
                
            }
        }
        
        if(!flag) answer = -1;
        
        return answer;
    }
}