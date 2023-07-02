import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works) pq.add(w);
        
        while(!pq.isEmpty() && n > 0){
            int cur = pq.poll();
            n--;
            if(--cur > 0) pq.add(cur);
        }
        
        long answer = 0;
        while(!pq.isEmpty()) answer += Math.pow(pq.poll(), 2);
        
        return answer;
    }
}