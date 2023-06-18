import java.util.*;
import java.awt.Point;

class Solution {
    public int[] solution(String[] maps) {
        int rowLen = maps.length;
        int colLen = maps[0].length();
        //맵세팅
        int[][] map = new int[rowLen][colLen];
        for(int i=0; i < rowLen; i++){
            char[] cList = maps[i].toCharArray();
            for(int j=0; j < colLen; j++){
                if(cList[j] == 'X') map[i][j] = 0;
                else map[i][j] = cList[j] - '0';
            }
        }
        
        boolean[][] visited = new boolean[rowLen][colLen];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        Queue<Point> q = new LinkedList<>();
        for(int i=0; i < rowLen; i++){
            for(int j=0; j < colLen; j++){
                //빈칸이거나 이미 방문한 경우 패스
                if(map[i][j] == 0 || visited[i][j]) continue;
                int cnt = map[i][j];
                visited[i][j] = true;
                q.add(new Point(i, j));
                Point cur;
                while(!q.isEmpty()){
                    cur = q.poll();
                    int nr, nc;
                    for(int d=0; d< 4; d++){
                        nr = cur.x + dr1[d];
                        nc = cur.y + dc1[d];
                        //맵에서 벗어나거나 빈칸이거나 방문했던 경우는 패스
                        if((nr < 0 || nr >= rowLen || nc < 0 || nc >= colLen) || map[nr][nc] == 0 || visited[nr][nc]) continue;
                        cnt += map[nr][nc];
                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc));
                    }
                }
                pq.add(cnt);
            }
        }
        
        int size = pq.size();
        int[] answer = new int[size];
        for(int i=0; i < size; i++) answer[i] = pq.poll();
        if(size == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }
}