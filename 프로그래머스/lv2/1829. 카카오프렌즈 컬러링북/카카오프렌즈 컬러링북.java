import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        
        int[] answer = new int[2];
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        //방문처리할 배열
        boolean[][] visited = new boolean[m][n];
        
        Queue<Point> q = new LinkedList<>();
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                //빈칸이거나 이미 방문한 곳인 경우 패스
                if(picture[i][j] == 0 || visited[i][j]) continue;
                
                //큐 초기화
                q.clear();
                //해당 위치 방문처리 및 큐 담기
                visited[i][j] = true;
                q.add(new Point(i, j));
                
                int level = picture[i][j];
                int cnt = 0;
                Point cur;
                int nr, nc;
                while(!q.isEmpty()){
                    cur = q.poll();
                    cnt++;
                    for(int d=0; d < 4; d++){
                        nr = cur.x + dr1[d];
                        nc = cur.y + dc1[d];
                        //맵에서 벗어나거나 다른 난이도 영역이거나 이미 방문한 경우 패스
                        if(nr < 0 || nr >= m || nc < 0 || nc >= n
                           || picture[nr][nc] != level
                           || visited[nr][nc]) continue;
                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc));
                    }
                }
                //영역 개수 증가
                answer[0]++;
                answer[1] = Math.max(answer[1], cnt);
            }
        }
        
        return answer;
    }
}