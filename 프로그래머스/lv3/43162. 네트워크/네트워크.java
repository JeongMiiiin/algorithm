import java.util.*;
/*
프로그래머스 - 네트워크
컴퓨터 A - B가 연결되어있고, B - C가 연결되어 있으면 정보 교환이 가능하므로 같은 네트워크상에 존재
컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때,
네트워크의 개수를 return
visited로 표시
*/
class Solution {
    public int solution(int n, int[][] computers) {
        
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        
        int answer = 0;
        for(int i=0; i < n; i++){
            //방문하지 않은 컴퓨터인 경우
            if( !visited[i] ){
                q.clear(); //초기화
                q.add(i);
                visited[i] = true; //방문처리
                
                //같은 네트워크에 속하는 애들 표시
                while(!q.isEmpty()){
                    int cur = q.poll();
                    for(int j=0; j < n; j++){
                        if(j != cur && !visited[j] && computers[cur][j] == 1){
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
                
                answer++;
            }
        }
        
        
        return answer;
    }
}