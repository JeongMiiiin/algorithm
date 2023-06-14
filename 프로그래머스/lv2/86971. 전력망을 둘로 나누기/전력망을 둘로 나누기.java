import java.util.*;

class Solution {
    static int ans, N;
    static int[][] map;
    public int solution(int n, int[][] wires) {
        ans = Integer.MAX_VALUE;
        N = n;
        
        map = new int[N + 1][N + 1];
        
        //맵 그리기
        for(int[] wire : wires){
            map[wire[0]][wire[1]] = 1;
            map[wire[1]][wire[0]] = 1;
        }
        
        for(int i=1; i <= N; i++){
            for(int j=i; j <= N; j++){
                if(map[i][j] == 1){
                    map[i][j] = 0;
                    map[j][i] = 0;
                    find();
                    map[i][j] = 1;
                    map[j][i] = 1;
                }
            }
        }
        
        return ans;
    }
    
    private static void find(){
        int cnt = 0;
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(1);
        visited[1] = true;
        
        while(!q.isEmpty()){
            int target = q.poll();
            for(int i=1; i <= N; i++){
                if(map[target][i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
            cnt++;
        }
        
        
        int result = Math.abs((N - cnt) - cnt);
        
        ans = Math.min(ans, result);
    }
}