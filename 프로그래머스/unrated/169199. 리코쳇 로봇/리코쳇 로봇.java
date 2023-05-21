import java.util.*;

class Solution {
    static class Robot {
        int r, c, dir;
        public Robot(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    
    public int solution(String[] board) {
        int N = board.length;
        int M = board[0].length();
        
        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        int idx = 0;
        
        int[] goal = new int[2];
        int[] robotTemp = new int[2];
        //맵 세팅
        for(String s : board){
            char[] cList = s.toCharArray();
            for(int i=0; i < cList.length; i++){
                map[idx][i] = cList[i];
                if(map[idx][i] == 'R'){ //로봇 위치일 때
                    robotTemp[0] = idx;
                    robotTemp[1] = i;
                } else if(map[idx][i] == 'G'){
                    goal[0] = idx;
                    goal[1] = i;
                }
            }
            idx++;
        }
        
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(robotTemp[0], robotTemp[1], -1));
        visited[robotTemp[0]][robotTemp[1]] = true;
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        int answer = 0;
        outer : while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i < size; i++){
                Robot cur = q.poll();
            
                //골인지점에 도달했을 경우
                if(goal[0] == cur.r && goal[1] == cur.c) break outer;

                for(int d=0; d < 4; d++){
                    if(cur.dir == d) continue; //왔던 방향이면 패스

                    int nr = cur.r + dr1[d];
                    int nc = cur.c + dc1[d];

                    while( !(nr < 0 || nr >= N || nc < 0 || nc >= M) && map[nr][nc] != 'D'){
                        nr += dr1[d];
                        nc += dc1[d];
                    }

                    nr -= dr1[d];
                    nc -= dc1[d];

                    if(visited[nr][nc]) continue; //이미 방향으로 방문했던 곳이면

                    q.add(new Robot(nr, nc, d));
                    visited[nr][nc] = true;
                }
            }
            
            answer++;
        }
        
        //골인 지점에 방문하지 못했을 경우
        if(!visited[goal[0]][goal[1]]) answer = -1;
        
        return answer;
    }
}