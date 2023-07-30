import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i < score.length; i++){
            int target = score[i];
            if(!pq.isEmpty()){
                if(pq.size() == k){
                    if(target > pq.peek()){
                        pq.poll();
                        pq.add(target);
                    }
                } else pq.add(target);
            } else pq.add(target);
            
            answer[i] = pq.peek();
        }
        
        return answer;
    }
}