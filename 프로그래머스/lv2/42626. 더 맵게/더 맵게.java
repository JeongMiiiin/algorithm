import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int n : scoville) pq.offer(n);
        
        int result = 0;
        while(pq.size() > 1) {
        	int first = pq.poll();
        	if(first >= K){
                result = first;
                break;
            }
        	int second = pq.poll();
        	
        	result = first + (second * 2);
        	
        	pq.offer(result);
        	answer++;
        }
        
        if(result < K) answer = -1;
        
        return answer;
    }
}