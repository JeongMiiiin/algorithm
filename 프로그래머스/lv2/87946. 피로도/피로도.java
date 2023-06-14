import java.util.*;

class Solution {
    static int N, ans;
    static boolean[] visited;
    static int[][] inputs;
    public int solution(int k, int[][] dungeons) {
        //초기화
        N = dungeons.length;
        ans = -1;
        inputs = dungeons;
        visited = new boolean[N];
        
        perm(k, 0);
        
        return ans;
    }
    
    private static void perm(int k, int cnt){
        ans = Math.max(ans, cnt);
        
        if(cnt == N) return;
        
        for(int i=0; i < N; i++){
            if(visited[i]) continue;
            
            if(k >= inputs[i][0]){
                visited[i] = true;
                perm(k - inputs[i][1], cnt + 1);
                visited[i] = false;
            }
        }
        
    }
}