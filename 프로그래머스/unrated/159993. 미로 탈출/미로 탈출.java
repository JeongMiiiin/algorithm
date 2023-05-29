import java.util.*;
import java.awt.Point;

/*
프로그래머스 - 미로 탈출
각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고, 통로로 된 칸으로만 이동 가능.
출구로 나가기 위해서는 레버를 당겨야 함
*/

class Solution {
    public int solution(String[] maps) {
        int N = maps.length;
        int M = maps[0].length();
        
        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        
        for(int i=0; i < maps.length; i++){
            char[] cList = maps[i].toCharArray();
            for(int j=0; j < cList.length; j++){
                map[i][j] = cList[j];
                //시작지점 추가
                if(map[i][j] == 'S'){
                    q.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }   
        }
        
        //상 우 하 좌
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1};
        
        int answer = 0;
        
        Point cur;
        int nr, nc, size;
        
        //레버 먼저 찾기
        boolean flag = false;
        
        outer1 : while(!q.isEmpty()){
            answer++;
            size = q.size();
            
            for(int i=0; i < size; i++){
                cur = q.poll();
                
                for(int d=0; d < 4; d++){
                    nr = cur.x + dr1[d];
                    nc = cur.y + dc1[d];
                    //맵에서 벗어나거나 벽을 만났거나 방문했던 곳이면 패스
                    if((nr < 0 || nr >= N || nc < 0 || nc >= M) || map[nr][nc] == 'X' || visited[nr][nc]) continue;
                    
                    //레버를 만난 경우
                    if(map[nr][nc] == 'L'){
                        flag = true;
                        q.clear();
                        q.add(new Point(nr, nc));
                        break outer1;
                    }
                    
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
            
        }
        
        //레버에 접근하지 못한 경우
        if(!flag) return -1;
        
        //초기화
        flag = false;
        visited = new boolean[N][M];
        
        cur = q.peek();
        visited[cur.x][cur.y] = true;
        
        //출구 찾기
        outer2 : while(!q.isEmpty()){
            answer++;
            size = q.size();
            
            for(int i=0; i < size; i++){
                cur = q.poll();
                
                for(int d=0; d < 4; d++){
                    nr = cur.x + dr1[d];
                    nc = cur.y + dc1[d];
                    //맵에서 벗어나거나 벽을 만났거나 방문했던 곳이면 패스
                    if((nr < 0 || nr >= N || nc < 0 || nc >= M) || map[nr][nc] == 'X' || visited[nr][nc]) continue;
                    
                    //레버를 만난 경우
                    if(map[nr][nc] == 'E'){
                        flag = true;
                        break outer2;
                    }
                    
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
            
        }
        
        //출구에 접근하지 못한 경우
        if(!flag) return -1;
        
        
        return answer;
    }
}