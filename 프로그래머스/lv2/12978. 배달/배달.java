import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int INFI = Integer.MAX_VALUE;
        //맵 정보 세팅
        int[][] map = new int[N + 1][N + 1];
        for(int i=1; i <= N; i++) Arrays.fill(map[i], INFI);
        for(int[] info : road){
            map[info[0]][info[1]] = Math.min(map[info[0]][info[1]], info[2]);
            map[info[1]][info[0]] = Math.min(map[info[1]][info[0]], info[2]);
        }
        
        //방문처리 세팅
        int[] visited = new int[N + 1];
        Arrays.fill(visited, INFI);
        //첫번째 마을 추가
        int answer = 1;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1, 0));
        visited[1] = 0;
        Point cur;
        while(!q.isEmpty()){
            cur = q.poll();
            for(int i=1; i <= N; i++){
                //갈 수 없는 곳인 경우 패스
                if(map[cur.x][i] == INFI) continue;
                
                int dis = cur.y + map[cur.x][i];
                
                if(dis < visited[i] && dis <= K){
                    //처음 방문한 곳인 경우
                    if(visited[i] == INFI) answer++;
                    visited[i] = cur.y + map[cur.x][i];
                    q.add(new Point(i, cur.y + map[cur.x][i]));
                }
            }
        }
        

        return answer;
    }
}