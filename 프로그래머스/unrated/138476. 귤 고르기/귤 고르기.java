import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int n : tangerine) hm.put(n, hm.getOrDefault(n,0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(Map.Entry<Integer, Integer> entry : hm.entrySet()) pq.add(entry.getValue());
        
        int answer = 0, cnt = 0;
        while(cnt < k){
            answer++;
            cnt += pq.poll();
        }
        
        return answer;
    }
}