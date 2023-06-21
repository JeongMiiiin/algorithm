
/*
카드에는 1 - 100까지 숫자가 젹혀있음.
2 ~ 100 자연수 중 하나를 정해 그 수보다 작거나 같은 숫자 카드들 준비.
준비한 카드 수만큼 작은 상자 준지

준비된 상자에 카드를 1장씩 넣고, 상자를 무작위로 섞어 일렬로 나열.
상자가 일렬로 나열되면 상자가 나열된 순서에 따라 1번부터 순차적으로 증가하는 번호 부여

*/
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length + 1];
        
        int len = cards.length;
        Queue<Integer> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i < len; i++){
            //방문했던 번호면 패스
            if(visited[i + 1]) continue;
            
            int cnt = 0;
            visited[i + 1] = true;
            
            q.clear();
            int cur = cards[i];
            q.add(cur);
            
            while(!q.isEmpty()){
                cur = q.poll();
                if(!visited[cur]) {
                    visited[cur] = true;
                    q.add(cards[cur - 1]);
                }
                
                cnt++;
            }
            pq.add(cnt);
            
        }
        
        int answer = 0;
        if(pq.size() == 1) return answer;
        answer = pq.poll() * pq.poll();
        
        return answer;
    }
}